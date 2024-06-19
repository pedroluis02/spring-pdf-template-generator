package com.github.pedroluis02.springbootsamples.pdftemplategenerator.service

import java.io.ByteArrayOutputStream

interface UserReportGeneratorService {

    fun generateV1(): ByteArrayOutputStream
    
    fun generateV2(): ByteArrayOutputStream
}