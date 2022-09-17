package com.napptilus.oompaloompa.presentation.WorkersActivity.retrofit

import com.napptilus.oompaloompa.presentation.WorkersActivity.model.WorkersActivityModel

class GetWorkersUseCase {
    private val repository = OompaRepository()

    suspend operator fun invoke(page: Int):List<WorkersActivityModel>? = repository.getAllWorkers(page)
    
}