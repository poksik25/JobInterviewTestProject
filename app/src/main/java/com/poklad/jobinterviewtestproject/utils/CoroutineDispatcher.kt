package com.poklad.jobinterviewtestproject.utils

import kotlinx.coroutines.CoroutineDispatcher
interface CoroutineDispatchersProvider {
    fun getIO(): CoroutineDispatcher
    fun getMain(): CoroutineDispatcher
    fun getUnconfined(): CoroutineDispatcher
    fun getDefault(): CoroutineDispatcher
}