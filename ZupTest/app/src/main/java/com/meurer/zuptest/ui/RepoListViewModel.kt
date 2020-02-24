package com.meurer.zuptest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.meurer.zuptest.model.Repo
import com.meurer.zuptest.repository.RepoRepository

open class RepoListViewModel (repository: RepoRepository) : ViewModel() {

    val repos: LiveData<List<Repo>> = repository.getRepos().asLiveData()
}