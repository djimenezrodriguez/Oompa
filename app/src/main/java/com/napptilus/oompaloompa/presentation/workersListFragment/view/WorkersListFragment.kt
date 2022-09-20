package com.napptilus.oompaloompa.presentation.workersListFragment.view

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.napptilus.oompaloompa.R
import com.napptilus.oompaloompa.WorkersActivity
import com.napptilus.oompaloompa.databinding.WorkersListFragmentBinding
import com.napptilus.oompaloompa.presentation.workersListFragment.adapter.WorkerListAdapter
import com.napptilus.oompaloompa.presentation.workersListFragment.viewModel.WorkersListFragmentVM
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WorkersListFragment : Fragment() {

    private val viewModel: WorkersListFragmentVM by viewModels()

    @Inject
    lateinit var adapter: WorkerListAdapter

    private var _binding: WorkersListFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(
            inflater, R.layout.workers_list_fragment, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.adapter = adapter
        binding.workerSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrEmpty()) {
                    adapter.submitList(viewModel.data.value)
                } else {
                    val arrFiltered = viewModel.data.value?.filter {
                        if(binding.rbName.isChecked && binding.rbProfession.isChecked ){
                            it.profession.lowercase().contains(query.lowercase()) ||
                                    it.firstName.lowercase().contains(query.lowercase())
                        }else if(binding.rbName.isChecked){
                            it.firstName.lowercase().contains(query.lowercase())
                        }else if(binding.rbProfession.isChecked){
                            it.profession.lowercase().contains(query.lowercase())
                        }else{
                            it.firstName.lowercase().contains(query.lowercase())
                        }
                    }
                    adapter.submitList(arrFiltered)
                }
                adapter.notifyDataSetChanged()
                binding?.recyclerView?.adapter = adapter
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    adapter.submitList(viewModel.data.value)
                    adapter.notifyDataSetChanged()
                    return true
                } else {
                    return false
                }
            }

        })
        viewModel.response.observe(viewLifecycleOwner){
            if(it?.isSuccessful == false && !it.message().isNullOrEmpty()){
                binding.idRefreshButton.visibility = View.VISIBLE
                errorCheck(resources.getString(R.string.general_error))
            }else if(it == null){
                binding.idRefreshButton.visibility = View.VISIBLE
                errorCheck(resources.getString(R.string.no_network))
            }else{
                binding.idRefreshButton.visibility = View.GONE
            }
        }
        binding.idRefreshButton.setOnClickListener {
            viewModel.doCallWorkers(viewModel.page.value?:0)
        }
        binding.idPlusButon.setOnClickListener {
            viewModel.page.value = viewModel.page.value?.plus(1)
        }
        binding.idRestButton.setOnClickListener {
            if ((viewModel.page.value ?: 0) > 0) {
                viewModel.page.value = viewModel.page.value?.minus(1)
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.data.observe(viewLifecycleOwner) {
            adapter.submitList(it.toMutableList())
            adapter.notifyDataSetChanged()
        }

        val pageObserver = Observer<Int> { newPage ->
            // Update the UI, in this case, a TextView.
            binding.idCountPage.text = newPage.toString()
            viewModel.doCallWorkers(newPage)
        }
        viewModel.page.observe(viewLifecycleOwner, pageObserver)

        adapter.clickListener.onItemClick = {
            findNavController().navigate(WorkersListFragmentDirections.actionGoDetail(it.id))
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
        binding?.recyclerView?.adapter = null
        _binding = null
    }

}