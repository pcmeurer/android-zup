package com.meurer.zuptest.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.meurer.zuptest.AppExecutors
import com.meurer.zuptest.R
import com.meurer.zuptest.databinding.ListItemRepoBinding
import com.meurer.zuptest.model.Repo
import com.meurer.zuptest.ui.common.DataBoundListAdapter

class RepoListAdapter(
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors,
    private val itemClickCallback: ((Repo) -> Unit)
) : DataBoundListAdapter<Repo, ListItemRepoBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<Repo>() {
        override fun areItemsTheSame(oldItem: Repo, newItem: Repo) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Repo, newItem: Repo) =
            oldItem.id == newItem.id
                    && oldItem.name == newItem.name
    }
) {
    override fun createBinding(parent: ViewGroup): ListItemRepoBinding {
        val binding = DataBindingUtil.inflate<ListItemRepoBinding>(
            LayoutInflater.from(parent.context),
            R.layout.list_item_repo,
            parent,
            false,
            dataBindingComponent
        )

        binding.root.setOnClickListener {
            binding.repo?.let {
                itemClickCallback.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: ListItemRepoBinding, item: Repo, position: Int) {
        binding.repo = item
    }

}