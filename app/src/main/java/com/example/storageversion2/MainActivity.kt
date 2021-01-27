package com.example.storageversion2

import android.Manifest
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.example.storageversion2.dataBase.Note
import com.example.storageversion2.dataBase.NotesDataBase
import com.example.storageversion2.databinding.ActivityMainBinding
import kotlin.concurrent.thread
import android.Manifest.permission.MANAGE_EXTERNAL_STORAGE as MANAGE_EXTERNAL_STORAGE

class MainActivity : AppCompatActivity() {

    companion object {
        private const val PREFS_KEY = "PREFS_KEY"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var prefs: SharedPreferences
    val externalStorage = ExternalStorage(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefs = getPreferences(MODE_PRIVATE)

        //save to preferences
        binding.saveToPrefs.setOnClickListener {
            val text = binding.editTextNote.editableText.toString()
            prefs.edit().apply {
                putString(PREFS_KEY, text)
                apply()
                binding.editTextNote.editableText.clear()
            }
        }

        //load from preferences
        binding.loadFromPrefs.setOnClickListener {
            val text = prefs.getString(PREFS_KEY, "")
            binding.textView.text = text
        }

        val internalStorage = InternalStorage(this)

        //save to internal storage
        binding.saveInternal.setOnClickListener {
            val text = binding.editTextNote.editableText.toString()
            internalStorage.write(text)
            binding.editTextNote.editableText.clear()
        }

        //load from internal storage
        binding.loadInternal.setOnClickListener {
            val text = internalStorage.read()
            binding.textView.text = text
        }

        //save to external storage
        binding.saveExternal.setOnClickListener {
            val text = binding.editTextNote.editableText.toString()
            externalStorage.write(text)
            binding.editTextNote.editableText.clear()
        }

        //load from external storage
        binding.loadExternal.setOnClickListener {
            val text = externalStorage.read()
            binding.textView.text = text
        }


        val db = Room.databaseBuilder(this, NotesDataBase::class.java, "dataBase")
                .fallbackToDestructiveMigration()
                .build()

        // save to dataBase
        binding.saveToBD.setOnClickListener {
            val text = binding.editTextNote.editableText.toString()
            thread {
                db.getNotesDao().insertNote(Note(name = text))
            }
            binding.editTextNote.editableText.clear()
        }

        //load from dataBase
        binding.loadFromBD.setOnClickListener {
            thread {
                val text = db.getNotesDao().getLastNote()
                this.runOnUiThread {
                    binding.textView.text = text
                }

            }

        }
    }

}








