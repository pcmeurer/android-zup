package com.meurer.zuptest.ui.detail

import androidx.lifecycle.*
import com.meurer.zuptest.model.Repo
import com.meurer.zuptest.repository.RepoRepository

class RepoDetailViewModel(repoRepository: RepoRepository) : ViewModel() {

    private var repoID = MutableLiveData<RepoId>()
    val repo: LiveData<Repo> =
        repoID.switchMap { repoId ->
            liveData {
                emit(repoRepository.getRepo(repoId.owner, repoId.name))
            }
        }

    fun setRepoID(owner: String, name: String) {
        repoID.value = RepoId(owner, name)
    }

    data class RepoId(val owner: String, val name: String)
}