package com.thernat.tempconverter.api

import com.thernat.tempconverter.api.xml.request.RequestEnvelope
import com.thernat.tempconverter.api.xml.response.CelsiusToFahrenheitResponseEnvelope
import com.thernat.tempconverter.api.xml.response.FahrenheitToCelsiusResponseEnvelope
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * Created by m.rafalski on 2019-06-29.
 */
interface ApiService {

    @Headers("Content-Type: text/xml", "Accept-Charset: utf-8")
    @POST("tempconvert.asmx")
    suspend fun convertCelsiusToFahrenheit(@Body envelope: RequestEnvelope): CelsiusToFahrenheitResponseEnvelope

    @Headers("Content-Type: text/xml", "Accept-Charset: utf-8")
    @POST("tempconvert.asmx")
    suspend fun convertFahrenheitToCelsius(@Body envelope: RequestEnvelope): FahrenheitToCelsiusResponseEnvelope
}