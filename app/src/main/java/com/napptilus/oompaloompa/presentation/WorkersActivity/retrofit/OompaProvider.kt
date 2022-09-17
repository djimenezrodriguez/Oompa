package com.napptilus.oompaloompa.presentation.WorkersActivity.retrofit

import com.napptilus.oompaloompa.presentation.WorkersActivity.model.WorkersActivityModel

class OompaProvider {
    companion object {
        var workers:MutableList<WorkersActivityModel>? = mutableListOf()
    }
}