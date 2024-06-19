package com.github.pedroluis02.springbootsamples.pdftemplategenerator.api

import com.github.pedroluis02.springbootsamples.pdftemplategenerator.service.UserReportGeneratorService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserReportGeneratorController(private val service: UserReportGeneratorService) {

    @PostMapping("/api/v1/user-reports/pdf", produces = [MediaType.APPLICATION_PDF_VALUE])
    fun generatePdfV1() = createResourceResponse(filename, service.generateV1())

    @PostMapping("/api/v2/user-reports/pdf", produces = [MediaType.APPLICATION_PDF_VALUE])
    fun generatePdfV2() = createResourceResponse(filename, service.generateV2())

    private val filename: String
        get() = "user-${System.currentTimeMillis()}.pdf"
}