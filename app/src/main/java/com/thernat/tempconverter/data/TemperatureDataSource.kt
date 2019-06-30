package com.thernat.tempconverter.data

/**
 * Created by m.rafalski on 2019-06-29.
 */
interface TemperatureDataSource {

    suspend fun convertFromCelsiusToFahrenheit(celsius: Double): String
}