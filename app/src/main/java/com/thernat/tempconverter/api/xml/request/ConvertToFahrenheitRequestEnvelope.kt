package com.thernat.tempconverter.api.xml.request

import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.NamespaceList
import org.simpleframework.xml.Root

/**
 * Created by m.rafalski on 2019-06-30.
 */
@Root(name = "soap:Envelope")
@NamespaceList(
    Namespace(prefix = "xsi", reference = "http://www.w3.org/2001/XMLSchema-instance"),
    Namespace(prefix = "xsd", reference = "http://www.w3.org/2001/XMLSchema"),
    Namespace(prefix = "soap", reference = "http://schemas.xmlsoap.org/soap/envelope/")
)
data class ConvertToFahrenheitRequestEnvelope(
    @field:Element(name = "soap:Body") @param:Element(name = "soap:Body")val convertToFahrenheitRequestBody: ConvertToFahrenheitRequestBody
)