package com.github.pedroluis02.springbootsamples.pdftemplategenerator

import com.github.pedroluis02.springbootsamples.pdftemplategenerator.sample.createUsersSampleV1
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
        val file = service.generate("users-template", createUsersSampleV1(20), "template.pdf")

        assertThat(file).exists()
        assertThat(file.delete()).isTrue()
    }
}
