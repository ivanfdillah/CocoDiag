package com.dicoding.capstone.cocodiag.common

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.util.Base64
import android.util.Log
import android.widget.Toast
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())

// Toast message
fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun uriToFile(imageUri: Uri, context: Context): File {
    Log.d("uriToFile", "Starting uriToFile with URI: $imageUri")

    val myFile = createCustomTempFile(context)
    Log.d("uriToFile", "Created temp file: ${myFile.absolutePath}")

    val inputStream = context.contentResolver.openInputStream(imageUri)
        ?: throw IllegalArgumentException("Invalid URI: $imageUri")

    Log.d("uriToFile", "Opened InputStream for URI: $imageUri")

    val outputStream = FileOutputStream(myFile)
    val buffer = ByteArray(1024)
    var length: Int
    while (inputStream.read(buffer).also { length = it } > 0) {
        outputStream.write(buffer, 0, length)
    }
    outputStream.close()
    inputStream.close()

    Log.d("uriToFile", "File successfully written: ${myFile.absolutePath}")

    return myFile
}

fun createCustomTempFile(context: Context): File {
    val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
    val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    if (storageDir?.exists() == false) {
        storageDir.mkdirs()
    }
    Log.d("createCustomTempFile", "Storage directory: ${storageDir?.absolutePath}")
    return File.createTempFile(
        "JPEG_${timestamp}_", /* prefix */
        ".jpg", /* suffix */
        storageDir /* directory */
    )
}

fun convertBitmapToBase64(bitmap: Bitmap): String {
    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}

fun convertBase64ToBitmap(base64String: String): Bitmap? {
    return try {
        val decodedString = Base64.decode(base64String, Base64.DEFAULT)
        BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    } catch (e: IllegalArgumentException) {
        e.printStackTrace()
        null
    }
}