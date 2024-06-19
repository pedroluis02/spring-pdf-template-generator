package com.github.pedroluis02.springbootsamples.pdftemplategenerator.sample

import com.github.pedroluis02.springbootsamples.pdftemplategenerator.image.BarcodeImageUtils
import com.github.pedroluis02.springbootsamples.pdftemplategenerator.image.Base64ImageUtils
import com.github.pedroluis02.springbootsamples.pdftemplategenerator.model.User

fun createUsersSampleV1(size: Int) = mapOf<String, Any>("users" to createUsers(size))

fun createUsersSampleV2(size: Int) = mapOf(
    "users" to createUsers(size),
    "logoData" to loadLogoData(),
    "logoQrData" to createQrCodeData()
)

private fun createUsers(size: Int) = List(size) {
    User("Name $it", "LastName $it", "example-$it@test.com")
}


private fun loadLogoData(): String {
    return Base64ImageUtils.encodeFromResource("jpeg", "templates/img/users-logo.jpg")
}

private fun createQrCodeData(): String {
    val qrCodeStream = BarcodeImageUtils.generateQrCode("123456789", 64, 64)
    return Base64ImageUtils.encodeFromByteArray("png", qrCodeStream.toByteArray())
}