package com.meurer.zuptest.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.meurer.zuptest.api.GithubService
import com.meurer.zuptest.domain.model.RepoTestBuilder
import com.meurer.zuptest.model.Repo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@RunWith(JUnit4::class)
class RepoRepositoryTest {

    private lateinit var repository: RepoRepository
    private val service = mock(GithubService::class.java)

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        repository = RepoRepository(service)
    }

    @Test
    fun getRepoTest() {
        val repo = RepoTestBuilder()
            .withTestValues()
            .build()

        `when`(runBlocking {
            service.getRepo(
                "microsoft",
                "HealthVault-Mobile-iOS-Library"
            )
        }).thenReturn(repo)

        val data = runBlocking { repository.getRepo("microsoft", "HealthVault-Mobile-iOS-Library") }

        Truth.assertThat(repo.id).isEqualTo(data.id)
        Truth.assertThat(repo.name).isEqualTo(data.name)
        Truth.assertThat(repo.fullname).isEqualTo(data.fullname)
        Truth.assertThat(repo.description).isEqualTo(data.description)
        Truth.assertThat(repo.owner.login).isEqualTo(data.owner.login)
        Truth.assertThat(repo.owner.url).isEqualTo(data.owner.url)
    }

    @Test
    fun getAllReposTest() {
        val reposMs = arrayListOf(

            RepoTestBuilder()
                .withId(3085152)
                .withName("BeanSpy")
                .withFullName("\"microsoft/BeanSpy")
                .withDescription("BeanSpy is an open source Java servlet technology...")
                .withOwner("microsoft", "https://api.github.com/users/microsoft")
                .build()
        )

        val reposOctokit = arrayListOf(
            RepoTestBuilder()
                .withTestValues()
                .build()
        )

        val repos = arrayListOf<Repo>()
        repos.addAll(reposMs)
        repos.addAll(reposOctokit)

        `when`(runBlocking {
            service.getRepos("microsoft")
        }).thenReturn(reposMs)

        `when`(runBlocking {
            service.getRepos("octokit")
        }).thenReturn(reposOctokit)

        runBlocking {
            println(repository.getRepos().first())
            Truth.assertThat(repos).containsExactlyElementsIn(repository.getRepos().first())
        }


    }

}