package com.github.pedroluis02.springbootsamples.pdftemplategenerator

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

		val data = HashMap<String, Any>()
		data.put("users", users)
		service.generateAndSave("users-template", data,"template.pdf")
	}
}
