package com.github.pedroluis02.springbootsamples.pdftemplategenerator.api

import org.springframework.core.io.InputStreamResource
import org.springframework.core.io.Resource
import org.springframework.http.CacheControl
import org.springframework.http.ContentDisposition
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

fun createResourceResponse(outputName: String, outputStream: ByteArrayOutputStream): ResponseEntity<Resource> {
    val resource = InputStreamResource(ByteArrayInputStream(outputStream.toByteArray()))

    val contentDisposition = ContentDisposition.inline()
        .filename(outputName)
        .build()

    val headers = HttpHeaders()
    headers.contentDisposition = contentDisposition

    return ResponseEntity.ok()
        .cacheControl(CacheControl.noCache())
        .headers(headers)
        .body(resource)
}