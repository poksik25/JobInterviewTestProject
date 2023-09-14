package com.poklad.jobinterviewtestproject.utils

interface Mapper<Source, Destination> {
    fun map(data: Source): Destination
}
