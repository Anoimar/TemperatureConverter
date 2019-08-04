package com.thernat.tempconverter.api.xml.response

import com.thernat.tempconverter.api.xml.ResultNodeFinder
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root
import org.simpleframework.xml.convert.Convert
import org.simpleframework.xml.core.Persister
import org.simpleframework.xml.stream.InputNode
import org.simpleframework.xml.stream.OutputNode

/**
 * Created by m.rafalski on 2019-06-30.
 */
@Root(name = "Envelope")
@Namespace(prefix = "soap")
@Convert(value = FahrenheitToCelsiusResponseEnvelope.SOAPEnvelopeConverter::class)
class FahrenheitToCelsiusResponseEnvelope {

    var result: String? = null

    class SOAPEnvelopeConverter :
        org.simpleframework.xml.convert.Converter<FahrenheitToCelsiusResponseEnvelope> {
        @Throws(Exception::class)
        override fun read(node: InputNode): FahrenheitToCelsiusResponseEnvelope {
            val envelope = FahrenheitToCelsiusResponseEnvelope()
            val resultNode = ResultNodeFinder(
                "FahrenheitToCelsiusResult"
            ).findResultNode(node)
            val serializer = Persister()
            envelope.result = serializer.read(String::class.java, resultNode)
            return envelope
        }


        @Throws(Exception::class)
        override fun write(node: OutputNode, value: FahrenheitToCelsiusResponseEnvelope) {
            throw UnsupportedOperationException("Not supported yet.")
        }
    }
}