package com.thernat.tempconverter.data

import com.thernat.tempconverter.api.ApiException
import com.thernat.tempconverter.api.ApiService
import com.thernat.tempconverter.api.xml.request.CelsiusToFahrenheit
import com.thernat.tempconverter.api.xml.request.ConvertToFahrenheitRequestBody
import com.thernat.tempconverter.api.xml.request.ConvertToFahrenheitRequestEnvelope

/**
 * Created by m.rafalski on 2019-06-29.
 */
class TemperatureRepository(private val apiService: ApiService): TemperatureDataSource {

    override suspend fun convertFromCelsiusToFahrenheit(celsius: Double): String {
        return apiService.convertCelsiusToFahrenheit(createRequestEnvelope(celsius)).result ?: throw ApiException()
    }

    private fun createRequestEnvelope(celsius: Double): ConvertToFahrenheitRequestEnvelope {
        return ConvertToFahrenheitRequestEnvelope(ConvertToFahrenheitRequestBody(CelsiusToFahrenheit(celsius.toString())))
    }
}