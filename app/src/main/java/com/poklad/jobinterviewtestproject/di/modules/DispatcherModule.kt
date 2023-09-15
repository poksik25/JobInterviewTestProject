package com.poklad.jobinterviewtestproject.di.modules

import com.poklad.jobinterviewtestproject.di.annotations.ApplicationScope
import com.poklad.jobinterviewtestproject.utils.CoroutineDispatchersProvider
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
object DispatcherModule {

    @ApplicationScope
    @Provides
    fun provideDispatchersModule(): CoroutineDispatchersProvider =
        object : CoroutineDispatchersProvider {
            override fun getIO(): CoroutineDispatcher = Dispatchers.IO
            override fun getMain(): CoroutineDispatcher = Dispatchers.Main
            override fun getUnconfined(): CoroutineDispatcher = Dispatchers.Unconfined
            override fun getDefault(): CoroutineDispatcher = Dispatchers.Default
        }
}