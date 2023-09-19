package com.poklad.jobinterviewtestproject.di.components

import android.content.Context
import com.poklad.jobinterviewtestproject.di.annotations.ApplicationScope
import com.poklad.jobinterviewtestproject.di.modules.DispatcherModule
import com.poklad.jobinterviewtestproject.di.modules.NetworkModule
import com.poklad.jobinterviewtestproject.di.modules.RepositoryModule
import com.poklad.jobinterviewtestproject.di.viewModel.ViewModelFactoryModule
import com.poklad.jobinterviewtestproject.di.viewModel.ViewModelModule
import com.poklad.jobinterviewtestproject.presentation.MainActivity
import com.poklad.jobinterviewtestproject.presentation.ui.screens.giphy_list.GiphyListFragment
import com.poklad.jobinterviewtestproject.presentation.ui.screens.single_giphy.SingleGiphyFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DispatcherModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: GiphyListFragment)
    fun inject(fragment: SingleGiphyFragment)
}