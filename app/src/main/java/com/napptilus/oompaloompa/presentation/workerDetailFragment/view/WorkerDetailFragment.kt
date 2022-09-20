package com.napptilus.oompaloompa.presentation.workerDetailFragment.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.napptilus.oompaloompa.R
import com.napptilus.oompaloompa.databinding.WorkerDetailFragmentBinding
import com.napptilus.oompaloompa.presentation.workerDetailFragment.viewModel.WorkerDetailFragmentVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkerDetailFragment : Fragment() {
    private val viewModel: WorkerDetailFragmentVM by viewModels()
    private val args: WorkerDetailFragmentArgs by navArgs()

    private var _binding: WorkerDetailFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(
            inflater, R.layout.worker_detail_fragment, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.refreshUserDetails(args.worker)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserDetails(args.worker).observe(viewLifecycleOwner) {
            viewModel.userDetails.set(it)
        }
        viewModel.response.observe(viewLifecycleOwner){
            if(it?.isSuccessful == false && !it.message().isNullOrEmpty()){
                errorCheck(resources.getString(R.string.general_error))
            }else if(it == null){
                errorCheck(resources.getString(R.string.no_network))
            }
        }
    }
    fun errorCheck(message: String) {
        val builder1 = AlertDialog.Builder(requireActivity())
        builder1.setMessage(message)
        builder1.setCancelable(true)

        builder1.setPositiveButton(
            "Ok"
        ) { dialog, id -> dialog.cancel() }

        val alert11 = builder1.create()
        alert11.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}