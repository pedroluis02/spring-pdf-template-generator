package com.github.pedroluis02.springbootsamples.pdftemplategenerator.service

import com.github.pedroluis02.springbootsamples.pdftemplategenerator.reporting.PdfFileGeneratorService
import com.github.pedroluis02.springbootsamples.pdftemplategenerator.reporting.PdfTemplateConverterService
import com.github.pedroluis02.springbootsamples.pdftemplategenerator.reporting.PdfTemplateGeneratorService
import com.github.pedroluis02.springbootsamples.pdftemplategenerator.sample.createUsersSampleV1
import com.github.pedroluis02.springbootsamples.pdftemplategenerator.sample.createUsersSampleV2
import org.springframework.stereotype.Service

@Service
class UserReportGeneratorServiceImpl(
    private val service1: PdfTemplateConverterService,
    private val service2: PdfTemplateGeneratorService
) : UserReportGeneratorService {

    override fun generateV1() = generate("users-template", createUsersSampleV1(50), service1)

    override fun generateV2() = generate("users-flying-template", createUsersSampleV2(50), service2)

    private fun generate(template: String, data: Map<String, Any>, service: PdfFileGeneratorService) =
        service.generate(template, data)
}