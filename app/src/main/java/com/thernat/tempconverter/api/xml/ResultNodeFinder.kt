package com.thernat.tempconverter.api.xml

import org.simpleframework.xml.stream.InputNode

class ResultNodeFinder(private val resultNodeName: String) {

    @Throws(Exception::class)
    fun findResultNode(rootNode: InputNode): InputNode {
       rootNode.getNext(BODY_NODE_NAME)?.let {body->
           var node = body
           while (true) {
               if (node.name == resultNodeName) {
                   return node
               }
               node = node.next ?: break
           }
           
       }
        throw NodeParsingFailedException("$resultNodeName node not found!")
    }

    companion object {

        private const val BODY_NODE_NAME = "Body"
    }

}