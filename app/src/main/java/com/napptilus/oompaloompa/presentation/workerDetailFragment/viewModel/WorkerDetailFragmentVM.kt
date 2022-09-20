package com.napptilus.oompaloompa.presentation.workerDetailFragment.viewModel

import androidx.databinding.ObservableParcelable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.napptilus.oompaloompa.database.repository.OompaRepository
import com.napptilus.oompaloompa.presentation.workersListFragment.model.WorkersNetworkModelInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class WorkerDetailFragmentVM @Inject constructor(
    private val workersRepository: OompaRepository
) : ViewModel() {

    val userDetails = ObservableParcelable(WorkersNetworkModelInfo())
    val response: MutableLiveData<Response<WorkersNetworkModelInfo>?> by lazy {
        MutableLiveData<Response<WorkersNetworkModelInfo>?>()
    }

    fun getUserDetails(id: String) = workersRepository.getDetailWorker(id)

    fun refreshUserDetails(id: String) = viewModelScope.launch(Dispatchers.IO) {
        response.postValue(workersRepository.refreshUserDetails(id))
    }
}