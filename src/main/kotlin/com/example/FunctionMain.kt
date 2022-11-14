package com.example

import com.google.cloud.functions.HttpFunction
import com.google.cloud.functions.HttpRequest
import com.google.cloud.functions.HttpResponse
import org.slf4j.LoggerFactory

class FunctionMain:HttpFunction {
    private val logger = LoggerFactory.getLogger("my-logger")
    override fun service(request: HttpRequest?, response: HttpResponse?) {
        logger.info("Function called!")
        response?.writer?.write("Done")
    }
}
