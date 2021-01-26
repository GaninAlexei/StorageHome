package com.example.storageversion2

import android.content.Context
import java.io.*

class InternalStorage(private val activity: MainActivity){

    companion object{
        private const val FILE_NAME = "FILE_NAME"
    }

    fun write(text: String){

        try {
            val fileOutputStream = activity.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)
            fileOutputStream.write(text.toByteArray())
            fileOutputStream.close()
        }
        catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    fun read() : String?{

        return try {
            val fileInputStream = activity.openFileInput(FILE_NAME)
            val read = fileInputStream.readBytes()
            fileInputStream.close()
            read.decodeToString()
        }
        catch (ex: Exception){
            ex.printStackTrace()
            null
        }
    }
}