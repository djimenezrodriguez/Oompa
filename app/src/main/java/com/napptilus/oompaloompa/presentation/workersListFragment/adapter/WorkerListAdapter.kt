package com.napptilus.oompaloompa.presentation.workersListFragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.napptilus.oompaloompa.database.model.WorkersRoomModel
import com.napptilus.oompaloompa.databinding.WorkersListItemBinding
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class WorkerListAdapter @Inject constructor(val clickListener: ClickListener) :
    ListAdapter<WorkersRoomModel, WorkerListAdapter.ViewHolder>(WorkersListDiffCallback()) {

    class ViewHolder private constructor(private val binding: WorkersListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: WorkersRoomModel, clickListener: ClickListener) {
            binding.data = item
            binding.executePendingBindings()
            binding.clickListener = clickListener
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = WorkersListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

}

class WorkersListDiffCallback : DiffUtil.ItemCallback<WorkersRoomModel>() {

    override fun areItemsTheSame(oldItem: WorkersRoomModel, newItem: WorkersRoomModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: WorkersRoomModel, newItem: WorkersRoomModel): Boolean {
        return oldItem == newItem
    }

}

class ClickListener @Inject constructor() {

    var onItemClick: ((WorkersRoomModel) -> Unit)? = null

    fun onClick(data: WorkersRoomModel) {
        onItemClick?.invoke(data)
    }
}