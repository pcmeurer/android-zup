package com.meurer.zuptest.di

import com.meurer.zuptest.AppExecutors
import com.meurer.zuptest.api.retrofit.RetrofitInitializer
import com.meurer.zuptest.repository.RepoRepository
import com.meurer.zuptest.ui.RepoListViewModel
import com.meurer.zuptest.ui.detail.RepoDetailViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { AppExecutors() }
    viewModel { RepoListViewModel(get()) }
    viewModel { RepoDetailViewModel(get()) }
}

val dataSourceModule = module {
    single { RepoRepository(get()) }

    single {RetrofitInitializer(androidApplication()).githubService()}
}

val appModules = arrayListOf(appModule, dataSourceModule)