package com.github.pedroluis02.springbootsamples.pdftemplategenerator

import com.github.pedroluis02.springbootsamples.pdftemplategenerator.model.User
import com.github.pedroluis02.springbootsamples.pdftemplategenerator.service.PdfTemplateConverterService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PdfTemplateConverterApplicationTest {

    @Autowired
    lateinit var service: PdfTemplateConverterService

    @Test
    fun shouldGeneratePdf() {
        val users = List(20) {
            User("Name $it", "LastName $it", "example-$it@test.com")
        }

        val data = mapOf<String, Any>("users" to users)
        val file = service.generateAndSave("users-template", data, "template.pdf")

        assertThat(file).exists()
        assertThat(file.delete()).isTrue()
    }
}
