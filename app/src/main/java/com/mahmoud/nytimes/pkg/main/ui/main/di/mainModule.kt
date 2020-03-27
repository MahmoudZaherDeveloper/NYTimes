package com.mahmoud.nytimes.pkg.main.ui.main.di

import com.mahmoud.nytimes.pkg.main.ui.main.data.MainRepository
import com.mahmoud.nytimes.pkg.main.ui.main.viewModel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel {
        MainViewModel(get(),get())
    }

    single {
        MainRepository(get())
    }

}