package com.meurer.zuptest.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.meurer.zuptest.domain.model.RepoTestBuilder
import com.meurer.zuptest.repository.RepoRepository
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock

@RunWith(JUnit4::class)
class RepoListViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val repository = mock(RepoRepository::class.java)
    private var repoViewModel = RepoListViewModel(repository)

    @Test
    fun testNull() {
        assertThat(repoViewModel.repos, notNullValue())
    }
}