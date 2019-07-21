package com.manoilo.giphy.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manoilo.giphy.ui.SearchViewModel
import com.manoilo.giphy.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
