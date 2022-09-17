package com.napptilus.oompaloompa.presentation.WorkersActivity.Interface

import com.napptilus.oompaloompa.presentation.WorkersActivity.model.WorkersActivityModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WorkerClient {
    @GET("oompa-loompas?page={page}")
    suspend fun listWorkers(@Query("page") page: Int): Response<MutableList<WorkersActivityModel>>?
}