package com.example.notesapp.presentation.notes

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.base.BaseFragment
import com.example.notesapp.base.ViewModelFactory
import com.example.notesapp.data.local.database.enitites.Crud
import com.example.notesapp.data.local.database.enitites.Note
import com.example.notesapp.databinding.FragmentNotesBinding
import com.example.notesapp.presentation.notes.adapter.NotesAdapter
import javax.inject.Inject

class NotesFragment @Inject constructor(
    viewModelFactory: ViewModelFactory,
) : BaseFragment<FragmentNotesBinding>() {

    private val notesAdapter = NotesAdapter()

    override fun getViewBinding(): FragmentNotesBinding =
        FragmentNotesBinding.inflate(layoutInflater)

    private val notesViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[NotesViewModel::class.java]
    }

    override fun setUpViews() {
        super.setUpViews()
        binding.notesRv.apply {
            adapter = notesAdapter
        }
        setUpCurrentTime()
        notesViewModel.getAllNotes()

    }


    override fun setUpListener() = with(binding) {
        super.setUpListener()
        notesAdapter.setOnNoteClickListener { note, position ->
            val action = NotesFragmentDirections.actionNotesFragmentToUpdateNoteFragment(note.uid)
            notesAdapter.notifyItemChanged(position)
            findNavController().navigate(action)

        }
        setOnSwipeListener(notesRv)
    }


    private fun setOnSwipeListener(rvNotes: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition
                val note = notesAdapter.getNoteAt(pos)
                notesViewModel.removeNote(note.uid)
            }

        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvNotes)
    }


    override fun observeData() {
        super.observeData()
        notesViewModel.notes.observe(viewLifecycleOwner) {
            onNotesListReceived(it)
        }
    }

    private fun onNotesListReceived(list: List<Note>) {
        notesAdapter.submitList(list)
    }

    private fun setUpCurrentTime() {
        val currentTime = notesViewModel.getTime()
        binding.tvTitleDate.text = currentTime
    }


}