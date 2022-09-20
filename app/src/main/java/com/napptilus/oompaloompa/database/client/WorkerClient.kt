package com.napptilus.oompaloompa.database.client

import com.napptilus.oompaloompa.presentation.workersListFragment.model.WorkersNetworkModel
import com.napptilus.oompaloompa.presentation.workersListFragment.model.WorkersNetworkModelInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WorkerClient {
    @GET("oompa-loompas")
    suspend fun listWorkers(@Query("page") page: Int): Response<WorkersNetworkModel>

    @GET("oompa-loompas/{id}")
    suspend fun detailWorker(@Path("id") id: String): Response<WorkersNetworkModelInfo>
}