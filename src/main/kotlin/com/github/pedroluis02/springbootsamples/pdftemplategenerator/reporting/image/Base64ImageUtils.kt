package com.github.pedroluis02.springbootsamples.pdftemplategenerator.reporting.image

import org.springframework.core.io.ClassPathResource
import java.util.*


object Base64ImageUtils {

    fun encodeFromResource(type: String, resource: String): String {
        val pathResource = ClassPathResource(resource)
        return encodeFromByteArray(type, pathResource.contentAsByteArray)
    }

    fun encodeFromByteArray(type: String, byteArray: ByteArray): String {
        return createData(type, Base64.getEncoder().encodeToString(byteArray))
    }

    private fun createData(type: String, data: String): String {
        return "data:image/$type;base64,$data"
    }
}