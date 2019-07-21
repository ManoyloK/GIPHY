package com.manoilo.weatherapp.mappers


interface Mapper<OUT, IN> {
    fun map(input: IN): OUT
}