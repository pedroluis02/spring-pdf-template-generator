package com.github.pedroluis02.springbootsamples.pdftemplategenerator.reporting

import com.github.pedroluis02.springbootsamples.pdftemplategenerator.reporting.image.ImageReplacedElementFactory
import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import org.xhtmlrenderer.pdf.ITextRenderer
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

@Service
class PdfTemplateGeneratorService(private val templateEngine: TemplateEngine) : PdfFileGeneratorService {

    override fun generate(template: String, data: Map<String, Any>, output: String): File {
        val outputStream = FileOutputStream(output)
        generate(template, data, outputStream)
        return File(output)
    }

    override fun generate(template: String, data: Map<String, Any>): ByteArrayOutputStream {
        val outputStream = ByteArrayOutputStream()
        generate(template, data, outputStream)
        return outputStream
    }

    private fun generate(template: String, data: Map<String, Any>, outputStream: OutputStream) {
        val context = Context()
        context.setVariables(data)

        val processedTemplate = templateEngine.process(template, context)
        return convert(processedTemplate, outputStream)
    }

    private fun convert(processedTemplate: String, outputStream: OutputStream) {
        try {
            val renderer = ITextRenderer().apply {
                sharedContext.replacedElementFactory = ImageReplacedElementFactory(sharedContext.replacedElementFactory)
                setDocumentFromString(processedTemplate)
                layout()
            }
            renderer.createPDF(outputStream, true)
        } catch (e: Exception) {
            throw e
        }
    }
}