package com.thernat.tempconverter.api.xml.request

import org.simpleframework.xml.Namespace

/**
 * Created by m.rafalski on 2019-06-30.
 */

@Namespace(reference = "https://www.w3schools.com/xml/")
data class CelsiusToFahrenheit(val Celsius: String)
