package com.github.pedroluis02.springbootsamples.pdftemplategenerator

import com.google.zxing.BarcodeFormat
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import java.io.ByteArrayOutputStream

object BarcodeImageUtils {

    fun generateQrCode(text: String, width: Int, height: Int): ByteArrayOutputStream {
        val writer = QRCodeWriter()
        val matrix = writer.encode(text, BarcodeFormat.QR_CODE, width, height)

        val outputStream = ByteArrayOutputStream()
        MatrixToImageWriter.writeToStream(matrix, "PNG", outputStream)

        return outputStream
    }
}