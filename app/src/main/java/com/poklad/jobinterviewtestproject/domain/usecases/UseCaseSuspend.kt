package com.poklad.jobinterviewtestproject.domain.usecases

interface UseCaseSuspend<Parameter, Result> {
    suspend fun execute(parameter: Parameter): Result
}