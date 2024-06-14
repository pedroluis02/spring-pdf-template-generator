package com.github.pedroluis02.springbootsamples.pdftemplategenerator

import com.github.pedroluis02.springbootsamples.pdftemplategenerator.image.BarcodeImageUtils
import com.github.pedroluis02.springbootsamples.pdftemplategenerator.image.Base64ImageUtils
import com.github.pedroluis02.springbootsamples.pdftemplategenerator.model.User
import com.github.pedroluis02.springbootsamples.pdftemplategenerator.service.PdfTemplateGeneratorService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
class PdfTemplateGeneratorApplicationTest {

    @Autowired
    lateinit var service: PdfTemplateGeneratorService

    @Test
    fun shouldGeneratePdf() {
        val users = List(50) {
            User("Name $it", "LastName $it", "example-$it@test.com")
        }

        val data = mapOf(
            "users" to users,
            "logoData" to loadLogoData(),
            "logoQrData" to createQrCodeData()
        )

        val file = service.generateFile("users-flying-template", data, "template-v2.pdf")

        assertThat(file).exists()
        assertThat(file.delete()).isTrue()
    }

    private fun loadLogoData(): String {
        return Base64ImageUtils.encodeFromResource("jpeg", "templates/img/users-logo.jpg")
    }

    private fun createQrCodeData(): String {
        val qrCodeStream = BarcodeImageUtils.generateQrCode("123456789", 64, 64)
        return Base64ImageUtils.encodeFromByteArray("png", qrCodeStream.toByteArray())
    }
}
