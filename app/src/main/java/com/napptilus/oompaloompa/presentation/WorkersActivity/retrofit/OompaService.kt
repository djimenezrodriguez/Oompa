package com.napptilus.oompaloompa.presentation.WorkersActivity.retrofit

import com.napptilus.oompaloompa.Retrofit.RetrofitClient
import com.napptilus.oompaloompa.presentation.WorkersActivity.Interface.WorkerClient
import com.napptilus.oompaloompa.presentation.WorkersActivity.model.WorkersActivityModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OompaService {

    private val retrofit = RetrofitClient.getRetrofit()

    suspend fun getWorkers(page:Int): MutableList<WorkersActivityModel>? {
        return withContext(Dispatchers.IO) {
            retrofit.create(WorkerClient::class.java).listWorkers(page)
        }?.body()
    }
}