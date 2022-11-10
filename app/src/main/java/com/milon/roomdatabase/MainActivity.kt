package com.milon.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.room.Room
import com.milon.roomdatabase.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var database: ContactDatabase

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        database=ContactDatabase.getDatabase(this)

        GlobalScope.launch {
            database.contactDao().insertContact(Contact(0,"milon","abcd"))
        }

        binding.getDataBtn.setOnClickListener {

            database.contactDao().getContact().observe(this, Observer {
                Log.d("milon",it.toString())
            })
        }
    }






}