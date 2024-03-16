package com.github.pedroluis02.springbootsamples.pdftemplategenerator


import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import org.xhtmlrenderer.pdf.ITextRenderer
import java.io.ByteArrayOutputStream
import java.io.FileOutputStream
import java.io.OutputStream

@Service
class PdfTemplateGeneratorService(private val templateEngine: TemplateEngine) {

    fun generateFile(template: String, data: Map<String, Any>, output: String) {
        val outputStream = FileOutputStream(output)
        generate(template, data, outputStream)
    }

    fun generateStream(template: String, data: Map<String, Any>): ByteArrayOutputStream {
        val outputStream = ByteArrayOutputStream()
        generate(template, data, outputStream)
        return outputStream
    }

    private fun generate(template: String, data: Map<String, Any>, outputStream: OutputStream) {
        val context = Context()
        val newData = data.toMutableMap()
        newData["logoData"] = Base64ImageUtils.encodeFromResource("jpeg", "templates/img/users-logo.jpg")

        val qrCodeStream = BarcodeImageUtils.generateQrCode("123456789", 64, 64)
        newData["logoQrData"] = Base64ImageUtils.encodeFromByteArray("png", qrCodeStream.toByteArray())

        context.setVariables(newData)

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