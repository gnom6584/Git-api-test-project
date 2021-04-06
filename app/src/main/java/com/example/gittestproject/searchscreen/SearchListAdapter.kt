package com.example.gittestproject.searchscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gittestproject.UserLoginReceiver
import com.example.gittestproject.databinding.RepositoryLayoutBinding
import javax.inject.Inject

class SearchListAdapter @Inject constructor(val userLoginReceiver: UserLoginReceiver) : PagedListAdapter<RepositoryViewModel, SearchListAdapter.ViewHolder>(defaultDiffCallback) {

    companion object {
        val defaultDiffCallback = object : DiffUtil.ItemCallback<RepositoryViewModel>() {

            override fun areItemsTheSame(oldItem: RepositoryViewModel, newItem: RepositoryViewModel) = oldItem.fullName.value == newItem.fullName.value

            override fun areContentsTheSame(oldItem: RepositoryViewModel, newItem: RepositoryViewModel) = true
        }
    }

    class ViewHolder(val binding: RepositoryLayoutBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            RepositoryLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repository = getItem(position)
        with(holder.binding) {
            if (repository != null) {
                viewModel = repository
                shimmerLayout.hideShimmer()
                invalidateAll()
                root.setOnClickListener { userLoginReceiver.receiveUserLogin(repository.owner.value!!.login.value!!) }
            }
            else {
                root.setOnClickListener(null)
                shimmerLayout.showShimmer(true)
                avatar.setImageBitmap(null)
                viewModel = null
                invalidateAll()
            }
        }
    }
}