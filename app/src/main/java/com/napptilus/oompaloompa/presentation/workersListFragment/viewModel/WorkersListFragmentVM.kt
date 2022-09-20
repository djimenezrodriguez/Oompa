package com.napptilus.oompaloompa.presentation.workersListFragment.viewModel

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.napptilus.oompaloompa.database.repository.OompaRepository
import com.napptilus.oompaloompa.presentation.workersListFragment.model.WorkersNetworkModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class WorkersListFragmentVM @Inject constructor(
    private val workersListRepository: OompaRepository
) : ViewModel() {



    val data = workersListRepository.workers
    val response: MutableLiveData<Response<WorkersNetworkModel>?> by lazy {
        MutableLiveData<Response<WorkersNetworkModel>?>()
    }

    // Create a LiveData with a String
    val page: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>(0)
    }


    fun doCallWorkers(numPage: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            response.postValue(workersListRepository.getAllWorkers(numPage))
        }
    }


}