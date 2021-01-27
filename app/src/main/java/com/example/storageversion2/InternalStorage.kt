package com.example.storageversion2

import android.content.Context
import android.widget.Toast
import java.io.*

class InternalStorage(private val context: Context){

    companion object{
        private const val FILE_NAME = "FILE_NAME"
    }

    fun write(text: String){

        try {
            val fileOutputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)
            fileOutputStream.write(text.toByteArray())
            fileOutputStream.close()
        }
        catch (ex: Exception) {
            Toast.makeText(context, "Error, file not found", Toast.LENGTH_LONG).show()
        }
    }

    fun read() : String?{

        return try {
            val fileInputStream = context.openFileInput(FILE_NAME)
            val read = fileInputStream.readBytes()
            fileInputStream.close()
            read.decodeToString()
        }
        catch (ex: Exception){
            Toast.makeText(context, "Error, file not found", Toast.LENGTH_LONG).show()
            null
        }
    }
}