package com.example.notesapp.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.commit
import androidx.navigation.NavHost
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.notesapp.R
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.presentation.create_note.NewNoteFragment
import com.example.notesapp.presentation.notes.NotesFragment
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

            if (destination.id == R.id.newNoteFragment || destination.id == R.id.updateNoteFragment) {
                bindingActivity.bottomNav.visibility = View.GONE
            } else {
                bindingActivity.bottomNav.visibility = View.VISIBLE
            }
        }

    }




    private fun initDaggerComponent() {
        component.inject(this)
    }


}
