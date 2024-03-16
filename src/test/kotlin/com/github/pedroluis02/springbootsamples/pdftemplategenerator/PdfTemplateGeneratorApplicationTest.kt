package com.github.pedroluis02.springbootsamples.pdftemplategenerator

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

        val outputFile = "template-v2.pdf"
        val data = mapOf<String, Any>("users" to users)
        service.generateFile("users-flying-template", data, outputFile)

        assertThat(File(outputFile)).exists()
    }
}
