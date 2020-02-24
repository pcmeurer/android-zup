package com.meurer.zuptest.api

import androidx.lifecycle.LiveData
import com.meurer.zuptest.model.Repo
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("orgs/{org}/repos")
    suspend fun getRepos(@Path("org") org: String, @Query("page") page: Int = 1): List<Repo>

    @GET("repos/{owner}/{name}")
    suspend fun getRepo(
        @Path("owner") owner: String,
        @Path("name") name: String
    ): Repo
}