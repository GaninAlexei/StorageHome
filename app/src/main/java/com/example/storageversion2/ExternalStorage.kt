package com.example.storageversion2

import android.content.Context
import android.os.Environment
import android.widget.Toast
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.PrintWriter

class ExternalStorage(private val context: Context) {

    companion object{
        private const val FILE_NAME = "FILE_NAME"
    }

    fun write(text: String){
        if (isExternalStorageWritable()) {

            val root = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
            val file = File(root, FILE_NAME)

            try {
                var fileOutputStream = FileOutputStream(file)
                val printWriter = PrintWriter(fileOutputStream)
                printWriter.println(text)
                printWriter.flush()
                printWriter.close()
                fileOutputStream.close()
            } catch (ex: Exception) {
                Toast.makeText(context, "Error, file not found", Toast.LENGTH_LONG).show()
            }
        }
        else{
            Toast.makeText(context, "Sorry, we can't write", Toast.LENGTH_LONG).show()
        }
    }

    fun read() : String?{
        if (isExternalStorageReadable()) {

            val root = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)

            return try {
                val file = File(root, FILE_NAME)
                val fileInputStream = FileInputStream(file)
                val read = fileInputStream.readBytes()
                fileInputStream.close()
                read.decodeToString()
            } catch (ex: Exception) {
                Toast.makeText(context, "Error, file not found", Toast.LENGTH_LONG).show()
                null
            }
        }
        else{
            Toast.makeText(context, "Sorry, we can't read ", Toast.LENGTH_LONG).show()
            return null
        }
    }

    // Checks if a volume containing external storage is available
    // for read and write.
    private fun isExternalStorageWritable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    // Checks if a volume containing external storage is available to at least read.
    private fun isExternalStorageReadable(): Boolean {
        return Environment.getExternalStorageState() in
                setOf(Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY)
    }
}