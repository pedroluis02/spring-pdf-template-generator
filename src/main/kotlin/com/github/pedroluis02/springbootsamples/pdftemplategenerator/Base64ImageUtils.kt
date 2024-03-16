package com.github.pedroluis02.springbootsamples.pdftemplategenerator

import org.springframework.core.io.ClassPathResource
import java.util.*


object Base64ImageUtils {

    fun encodeFromResource(resource: String): String {
        val pathResource = ClassPathResource(resource)
        return encode(pathResource.contentAsByteArray)
    }

    fun createData(type: String, data: String): String {
        return "data:image/$type;base64,$data";
    }

    private fun encode(byteArray: ByteArray): String {
        return Base64.getEncoder().encodeToString(byteArray);
    }
}