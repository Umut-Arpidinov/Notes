package com.example.notesapp.presentation.search

import com.example.notesapp.base.BaseFragment
import com.example.notesapp.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {
    override fun getViewModelClass(): Class<SearchViewModel> = SearchViewModel::class.java
    override fun getViewBinding(): FragmentSearchBinding = FragmentSearchBinding.inflate(layoutInflater)

}