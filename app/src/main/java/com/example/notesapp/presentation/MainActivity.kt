package com.example.notesapp.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentFactory
import androidx.navigation.NavHost
import androidx.navigation.ui.setupWithNavController
import com.example.notesapp.R
import com.example.notesapp.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var bindingActivity: ActivityMainBinding

    @Inject
    lateinit var fragmentFactory: FragmentFactory

    private val component by lazy {
        (application as NotesApplication).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDaggerComponent()

        supportFragmentManager.fragmentFactory = fragmentFactory

        supportFragmentManager.addOnBackStackChangedListener {

        }
        bindingActivity = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingActivity.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment) as NavHost
        val navController = navHostFragment.navController
        bindingActivity.bottomNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            bindingActivity.bottomNav.visibility = when (destination.id) {
                R.id.newNoteFragment,
                R.id.updateNoteFragment,
                R.id.loginFragment,
                R.id.resetFragment,
                R.id.createEventFragment,
                R.id.registerFragment -> View.GONE
                else -> View.VISIBLE
            }
        }

    }


    private fun initDaggerComponent() {
        component.inject(this)
    }


}
