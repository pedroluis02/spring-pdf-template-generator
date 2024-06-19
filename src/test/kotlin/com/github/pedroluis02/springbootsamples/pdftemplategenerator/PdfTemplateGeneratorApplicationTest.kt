package com.github.pedroluis02.springbootsamples.pdftemplategenerator

import com.github.pedroluis02.springbootsamples.pdftemplategenerator.sample.createUsersSampleV2
import com.github.pedroluis02.springbootsamples.pdftemplategenerator.service.PdfTemplateGeneratorService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PdfTemplateGeneratorApplicationTest {

    @Autowired
    lateinit var service: PdfTemplateGeneratorService

    @Test
    fun shouldGeneratePdf() {
        val file = service.generate("users-flying-template", createUsersSampleV2(50), "template-v2.pdf")

        assertThat(file).exists()
        assertThat(file.delete()).isTrue()
    }
}
