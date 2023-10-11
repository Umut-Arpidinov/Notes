package com.example.notesapp.presentation.event

import com.example.notesapp.base.BaseFragment
import com.example.notesapp.databinding.FragmentEventBinding

class EventFragment : BaseFragment<FragmentEventBinding, EventViewModel >() {
    override fun getViewModelClass(): Class<EventViewModel> = EventViewModel::class.java

    override fun getViewBinding(): FragmentEventBinding  = FragmentEventBinding.inflate(layoutInflater)

    override fun setUpViews() {
        super.setUpViews()

    }
}