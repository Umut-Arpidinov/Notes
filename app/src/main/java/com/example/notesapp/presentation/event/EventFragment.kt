package com.example.notesapp.presentation.event

import android.os.Build
import android.view.View
import android.widget.PopupMenu
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notesapp.R
import com.example.notesapp.base.BaseFragment
import com.example.notesapp.base.ViewModelFactory
import com.example.notesapp.data.local.database.enitites.NoteType
import com.example.notesapp.databinding.FragmentEventBinding
import com.example.notesapp.presentation.event.adapter.EventAdapter
import com.example.notesapp.presentation.extensions.showSnackBar
import javax.inject.Inject

class EventFragment @Inject constructor(
    viewModelFactory: ViewModelFactory
) : BaseFragment<FragmentEventBinding>() {

    private val eventViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[EventViewModel::class.java]
    }

    private lateinit var eventAdapter: EventAdapter


    override fun getViewBinding(): FragmentEventBinding =
        FragmentEventBinding.inflate(layoutInflater)

    override fun setUpViews() {
        super.setUpViews()
        eventAdapter = EventAdapter(requireContext())
        eventViewModel.getEvents()
        binding.rvEvents.adapter = eventAdapter
    }

    override fun setUpListener()=with(binding){
        super.setUpListener()
        val action = EventFragmentDirections.actionEventFragmentToCreateEventFragment()

        btnAddEvent.setOnClickListener{
            findNavController().navigate(action)
        }

        eventAdapter.onEventClickListener{view, uid, position ->
            popUpMenu(view,uid,position)
        }

    }




    private fun popUpMenu(v: View, uid: Int, position: Int) {
        val popUp = PopupMenu(requireContext(), v)
        popUp.inflate(R.menu.event_option)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            popUp.setForceShowIcon(true)
        }
        popUp.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.delete_event -> {
                    println("this block")
                    eventViewModel.removeEvent(uid)
                    eventAdapter.notifyItemRemoved(position)
                    true
                }

                else -> true
            }
        }

        popUp.show()
    }


    private fun showSnackBar(message: String) {
        context?.showSnackBar(message, this.view)
    }

    override fun observeData() {
        super.observeData()
        eventViewModel.events.observe(viewLifecycleOwner){
            println(it.size)
            eventAdapter.submitList(it)
        }
    }
}