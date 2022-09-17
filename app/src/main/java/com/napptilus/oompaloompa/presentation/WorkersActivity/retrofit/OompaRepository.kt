package com.napptilus.oompaloompa.presentation.WorkersActivity.retrofit

import com.napptilus.oompaloompa.presentation.WorkersActivity.model.WorkersActivityModel

class OompaRepository {
    private val api = OompaService()
    suspend fun getAllWorkers(page:Int):List<WorkersActivityModel>{
        val response = api.getWorkers(page)
        OompaProvider.workers = response
        return response.orEmpty()
    }
}