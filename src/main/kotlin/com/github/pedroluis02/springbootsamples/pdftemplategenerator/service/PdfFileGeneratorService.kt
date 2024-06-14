package com.github.pedroluis02.springbootsamples.pdftemplategenerator.service

import java.io.ByteArrayOutputStream
import java.io.File

interface PdfFileGeneratorService {
    fun generate(template: String, data: Map<String, Any>, output: String): File
    fun generate(template: String, data: Map<String, Any>): ByteArrayOutputStream
}