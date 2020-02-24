package com.meurer.zuptest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.meurer.zuptest.AppExecutors
import com.meurer.zuptest.R
import com.meurer.zuptest.binding.FragmentDataBindingComponent
import com.meurer.zuptest.model.Repo
import com.meurer.zuptest.util.autoCleared
import kotlinx.android.synthetic.main.fragment_repo_list.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepoListFragment : Fragment() {

    private val appExecutors: AppExecutors by inject()
    private val dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    private var adapter by autoCleared<RepoListAdapter>()
    private val viewModel: RepoListViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        initRepoList()
    }

    private fun setupRecycler() {
        val adapter = RepoListAdapter(dataBindingComponent,appExecutors){ repo ->
            openDetailView(repo)
        }
        this.adapter = adapter

        recyclerView.adapter = adapter

        val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(itemDecoration)
    }

    private fun initRepoList() {
        viewModel.repos.observe(
            viewLifecycleOwner,
            Observer { messageList ->
                adapter.submitList(messageList.map { it.copy() })
            }
        )
    }

    private fun openDetailView(repo: Repo) {
        findNavController().navigate(
            RepoListFragmentDirections.actionRepoListToRepoDetail(repo.name, repo.owner.login))
    }
}