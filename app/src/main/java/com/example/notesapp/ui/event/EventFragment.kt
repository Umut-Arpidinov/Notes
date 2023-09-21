package com.example.notesapp.ui.event

import com.example.notesapp.base.BaseFragment
import com.example.notesapp.databinding.FragmentEventBinding

class EventFragment : BaseFragment<FragmentEventBinding, EventViewModel >() {
    override fun getViewModelClass(): Class<EventViewModel> = EventViewModel::class.java

    override fun getViewBinding(): FragmentEventBinding  = FragmentEventBinding.inflate(layoutInflater)
}