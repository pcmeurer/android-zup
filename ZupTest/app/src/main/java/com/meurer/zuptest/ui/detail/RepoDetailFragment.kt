package com.meurer.zuptest.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.meurer.zuptest.databinding.FragmentRepoDetailBinding
import com.meurer.zuptest.util.autoCleared
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepoDetailFragment : Fragment() {
    private val viewmodel: RepoDetailViewModel by viewModel()
    private var binding by autoCleared<FragmentRepoDetailBinding>()

    private fun getRepoName(): String =
        arguments?.getString("repoName") ?: ""

    private fun getRepoOwner(): String =
        arguments?.getString("repoOwner") ?: ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRepoDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewmodel.setRepoID(getRepoOwner(), getRepoName())
        observeViewModel()
    }

    private fun observeViewModel() {
        viewmodel.repo.observe(
            viewLifecycleOwner,
            Observer { repo ->
                binding.repo = repo
            }
        )
    }
}
