package com.example.notesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavHost
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.notesapp.R
import com.example.notesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bindingActivity: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingActivity = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingActivity.root)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment) as NavHost
        val navController = navHostFragment.navController
        bindingActivity.bottomNav.setupWithNavController(navController)
    }
}
