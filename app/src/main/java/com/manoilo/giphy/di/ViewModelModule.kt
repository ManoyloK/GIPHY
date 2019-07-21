package com.manoilo.giphy.di

import androidx.lifecycle.ViewModelProvider
import com.manoilo.giphy.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Suppress("unused")
@Module
abstract class ViewModelModule {


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
