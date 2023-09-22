package com.example.notesapp.ui.event

import com.example.notesapp.base.BaseFragment
import com.example.notesapp.databinding.FragmentEventBinding
import com.example.notesapp.ui.extensions.showSnackBar

class EventFragment : BaseFragment<FragmentEventBinding, EventViewModel >() {
    override fun getViewModelClass(): Class<EventViewModel> = EventViewModel::class.java

    override fun getViewBinding(): FragmentEventBinding  = FragmentEventBinding.inflate(layoutInflater)

    override fun setUpViews() {
        super.setUpViews()

    }
}