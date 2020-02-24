package com.meurer.zuptest.repository

import androidx.lifecycle.asFlow
import com.meurer.zuptest.api.GithubService
import com.meurer.zuptest.model.Repo
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

open class RepoRepository(private val githubService: GithubService) {

    fun getRepos(): Flow<List<Repo>> = flow {

        coroutineScope {

            val repos = mutableListOf<Repo>()

            val microsoftRepos = launch {
                repos += githubService.getRepos("microsoft")
//                println("microsoft started")
//                delay(300)
//                println("microsoft half")
//                delay(500)
//                println("microsoft done")
            }
            val octokitRepos = launch {
                repos += githubService.getRepos("octokit")
//                println("octokit started")
//                delay(500)
//                println("octokit done")
            }

            microsoftRepos.join()
            octokitRepos.join()

//            println("emitiu repos")
            emit(
                repos
            )
        }
    }

    suspend fun getRepo(owner:String, name:String) = githubService.getRepo(owner, name)
}