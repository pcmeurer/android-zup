package com.meurer.zuptest.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.meurer.zuptest.domain.model.RepoTestBuilder
import com.meurer.zuptest.repository.RepoRepository
import com.meurer.zuptest.ui.detail.RepoDetailViewModel
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class RepoDetailViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val repository = Mockito.mock(RepoRepository::class.java)
    private var repoDetailViewModel = RepoDetailViewModel(repository)

    @Test
    fun testNull() {
        MatcherAssert.assertThat(repoDetailViewModel.repo, CoreMatchers.notNullValue())
    }
}