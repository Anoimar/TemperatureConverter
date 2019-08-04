package com.thernat.tempconverter.data

import com.thernat.tempconverter.api.ApiException
import com.thernat.tempconverter.api.ApiService
import com.thernat.tempconverter.api.xml.request.RequestBody
import com.thernat.tempconverter.api.xml.request.c2f.CelsiusToFahrenheit
import com.thernat.tempconverter.api.xml.request.RequestEnvelope
import com.thernat.tempconverter.api.xml.request.c2f.CelsiusToFahrenheitRequestBody
import com.thernat.tempconverter.api.xml.request.f2c.FahrenheitToCelsius
import com.thernat.tempconverter.api.xml.request.f2c.FahrenheitToCelsiusRequestBody

/**
 * Created by m.rafalski on 2019-06-29.
 */
class TemperatureRepository(private val apiService: ApiService): TemperatureDataSource {

    override suspend fun convertFromCelsiusToFahrenheit(celsius: Double): String {
        val requestBody = CelsiusToFahrenheitRequestBody(
            CelsiusToFahrenheit(celsius.toString()))
        return apiService.convertCelsiusToFahrenheit(createRequestEnvelope(requestBody)).result ?: throw ApiException()
    }

    private fun createRequestEnvelope(requestBody: RequestBody ): RequestEnvelope {
        return RequestEnvelope(requestBody)
    }

    override suspend fun convertFromFahrenheitToCelsius(fahrenheit: Double): String {
        val requestBody =  FahrenheitToCelsiusRequestBody(FahrenheitToCelsius(fahrenheit.toString()))
        return apiService.convertFahrenheitToCelsius(createRequestEnvelope(requestBody)).result ?: throw ApiException()

    }

}