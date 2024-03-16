package com.github.pedroluis02.springbootsamples.pdftemplategenerator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PdfTemplateGeneratorApplication

fun main(args: Array<String>) {
    runApplication<PdfTemplateGeneratorApplication>(*args)
}
