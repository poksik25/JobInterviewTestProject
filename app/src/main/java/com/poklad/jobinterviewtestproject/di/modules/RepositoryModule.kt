package com.poklad.jobinterviewtestproject.di.modules

import com.poklad.jobinterviewtestproject.data.repository.GiphyRepositoryImp
import com.poklad.jobinterviewtestproject.domain.repository.GiphyRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindGiphyRepository(repositoryImpl: GiphyRepositoryImp): GiphyRepository
}