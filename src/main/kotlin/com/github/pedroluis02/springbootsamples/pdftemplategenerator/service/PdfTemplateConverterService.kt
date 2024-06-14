package com.github.pedroluis02.springbootsamples.pdftemplategenerator.service

import com.itextpdf.html2pdf.ConverterProperties
import com.itextpdf.html2pdf.HtmlConverter
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider
import com.itextpdf.io.source.ByteArrayOutputStream
import com.itextpdf.kernel.pdf.PdfWriter
import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import java.io.File
import java.io.FileOutputStream

@Service
class PdfTemplateConverterService(private val templateEngine: TemplateEngine) {

    fun generateAndSave(template: String, data: Map<String, Any>, output: String): File {
        val outputStream = generate(template, data)
        val fileOutputStream = FileOutputStream(output)
        outputStream.use {
            it.writeTo(fileOutputStream)
        }
        return File(output)
    }

    fun generate(template: String, data: Map<String, Any>): java.io.ByteArrayOutputStream {
        val context = Context()
        context.setVariables(data)

        val processedTemplate = templateEngine.process(template, context)
        return convert(processedTemplate)
    }

    private fun convert(processedTemplate: String): ByteArrayOutputStream {
        try {
            val defaultFontProvider = DefaultFontProvider(false, true, false)
            val converterProperties = ConverterProperties()
            converterProperties.setFontProvider(defaultFontProvider)

            val byteArrayOutputStream = ByteArrayOutputStream()
            val pdfWriter = PdfWriter(byteArrayOutputStream)

            HtmlConverter.convertToPdf(processedTemplate, pdfWriter, converterProperties)
            return byteArrayOutputStream
        } catch (e: Exception) {
            throw e
        }
    }
}