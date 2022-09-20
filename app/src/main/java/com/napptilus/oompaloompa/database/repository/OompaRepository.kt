package com.napptilus.oompaloompa.database.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.napptilus.oompaloompa.database.WorkersDatabase
import com.napptilus.oompaloompa.database.client.WorkerClient
import com.napptilus.oompaloompa.database.model.WorkersRoomModel
import com.napptilus.oompaloompa.database.model.asDomainSingleModel
import com.napptilus.oompaloompa.presentation.workersListFragment.model.WorkersNetworkModel
import com.napptilus.oompaloompa.presentation.workersListFragment.model.WorkersNetworkModelInfo
import com.napptilus.oompaloompa.presentation.workersListFragment.model.asDatabaseModel
import com.napptilus.oompaloompa.presentation.workersListFragment.model.asSingleDatabaseModel
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Response
import javax.inject.Inject


class OompaRepository @Inject constructor(
    private val workerListService: WorkerClient,
    private val database: WorkersDatabase,
    @ApplicationContext private val appContext: Context
) {

    val workers: LiveData<List<WorkersRoomModel>> =
        Transformations.map(database.workerDao.getDatabaseWorkers()) {
            it
        }

    suspend fun getAllWorkers(page: Int): Response<WorkersNetworkModel>? {
        var allWorkersResponse: Response<WorkersNetworkModel>? = null
        if (checkForInternet(appContext)) {
            allWorkersResponse = workerListService.listWorkers(page)
            if (allWorkersResponse.isSuccessful) {
                val workersList = workerListService.listWorkers(page).body()!!.results
                database.workerDao.insertAllWorkers(workersList.asDatabaseModel())
            }
        }
        return allWorkersResponse
    }

    fun getDetailWorker(id: String): LiveData<WorkersNetworkModelInfo> {
        return Transformations.map(database.workerDao.getWorkerDetails(id)) {
            it.id = id
            it?.asDomainSingleModel()
        }
    }

    suspend fun refreshUserDetails(id: String): Response<WorkersNetworkModelInfo>? {
        var workerDetailsResponse: Response<WorkersNetworkModelInfo>? = null
        if (checkForInternet(appContext)) {
            workerDetailsResponse = workerListService.detailWorker(id)
            if (workerDetailsResponse.isSuccessful && workerDetailsResponse.body() != null) {
                val workerDetails = workerDetailsResponse.body()!!
                workerDetails.id = id
                database.workerDao.insertWorkerDetails(workerDetails.asSingleDatabaseModel())
            }
        }
        return workerDetailsResponse
    }

    fun checkForInternet(context: Context): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // if the android version is equal to M
        // or greater we need to use the
        // NetworkCapabilities to check what type of
        // network has the internet connection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // Returns a Network object corresponding to
            // the currently active default data network.
            val network = connectivityManager.activeNetwork ?: return false

            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                // Indicates this network uses a Wi-Fi transport,
                // or WiFi has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Indicates this network uses a Cellular transport. or
                // Cellular has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // else return false
                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

}