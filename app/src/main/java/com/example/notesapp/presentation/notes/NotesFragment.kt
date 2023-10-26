package com.example.notesapp.presentation.notes

import android.os.Build
import android.view.View
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notesapp.R
import com.example.notesapp.base.BaseFragment
import com.example.notesapp.base.ViewModelFactory
import com.example.notesapp.data.local.database.enitites.Note
import com.example.notesapp.data.local.database.enitites.NoteType
import com.example.notesapp.data.local.database.enitites.NotesCategory
import com.example.notesapp.databinding.FragmentNotesBinding
import com.example.notesapp.presentation.extensions.showSnackBar
import com.example.notesapp.presentation.notes.adapter.NotesAdapter
import com.example.notesapp.presentation.notes.adapter.NotesCategoryAdapter
import javax.inject.Inject

class NotesFragment @Inject constructor(
    viewModelFactory: ViewModelFactory,
) : BaseFragment<FragmentNotesBinding>() {

    private lateinit var notesAdapter: NotesAdapter
    private lateinit var notesCategoryAdapter: NotesCategoryAdapter

    override fun getViewBinding(): FragmentNotesBinding =
        FragmentNotesBinding.inflate(layoutInflater)

    private val notesViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[NotesViewModel::class.java]
    }

    override fun setUpViews() {
        super.setUpViews()
        notesAdapter = NotesAdapter(requireContext())
        binding.notesRv.apply {
            adapter = notesAdapter
        }
        notesCategoryAdapter = NotesCategoryAdapter(getNotesCategories())
        binding.rvCategories.adapter = notesCategoryAdapter
        setUpCurrentTime()
        notesViewModel.getDefaultNotes()


    }


    override fun setUpListener() = with(binding) {
        super.setUpListener()
        notesAdapter.setOnNoteClickListener { note, position ->
            notesAdapter.notifyItemChanged(position)
            val action = NotesFragmentDirections.actionNotesFragmentToUpdateNoteFragment(note.uid)
            findNavController().navigate(action)

        }

        notesAdapter.setOnNoteActionClickListener { uid, position, type, view ->
            popUpMenu(
                v = view,
                position = position,
                uid = uid,
                type = type
            )
        }
        notesCategoryAdapter.setOnCategoryClickListener { _, type ->
            binding.tvTitleRecentNote.text = type.name.lowercase()
            notesViewModel.getNotes(type)
        }


    }

    override fun observeData() {
        super.observeData()
        notesViewModel.notes.observe(viewLifecycleOwner) {
            onNotesListReceived(it)
        }
    }

//
//    private fun setOnSwipeListener(rvNotes: RecyclerView) {
//        val callback = object : ItemTouchHelper.SimpleCallback(
//            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
//        ) {
//            override fun onMove(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                target: RecyclerView.ViewHolder,
//            ): Boolean {
//                return false
//            }
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                val pos = viewHolder.adapterPosition
//                val note = notesAdapter.getNoteAt(pos)
//
//            }
//
//        }
//        val itemTouchHelper = ItemTouchHelper(callback)
//        itemTouchHelper.attachToRecyclerView(rvNotes)
//    }


    private fun popUpMenu(v: View, uid: Int, position: Int, type: NoteType?) {
        val popUp = PopupMenu(requireContext(), v)
        popUp.inflate(R.menu.action_menu)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            popUp.setForceShowIcon(true)
        }
        popUp.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.make_favourite -> {
                    notesViewModel.updateNoteType(uid, NoteType.FAVOURITES, type!!)
                    notesAdapter.notifyItemChanged(position)
                    true
                }

                R.id.delete_note -> {
                    notesViewModel.removeNote(uid, type!!)
                    notesAdapter.notifyItemChanged(position)
                    true
                }

                R.id.hide_note -> {
                    notesViewModel.updateNoteType(uid, NoteType.HIDDEN, type!!)
                    notesAdapter.notifyItemChanged(position)
                    true
                }

                else -> true
            }
        }

        popUp.show()
    }


    private fun onNotesListReceived(list: List<Note>) {
        notesAdapter.submitList(list.toMutableList())
    }

    private fun getNotesCategories(): List<NotesCategory> {
        return listOf(
            NotesCategory(
                resources.getString(R.string.msg_all_notes),
                ContextCompat.getDrawable(requireActivity(), R.drawable.ic_notes),
                ContextCompat.getColor(requireActivity(), R.color.gray_2),
                NoteType.DEFAULT
            ),
            NotesCategory(
                resources.getString(R.string.msg_favourites),
                ContextCompat.getDrawable(requireActivity(), R.drawable.ic_favourite),
                ContextCompat.getColor(requireActivity(), R.color.yellow),
                NoteType.FAVOURITES
            ),
            NotesCategory(
                resources.getString(R.string.msg_hidden),
                ContextCompat.getDrawable(requireActivity(), R.drawable.ic_hidden),
                ContextCompat.getColor(requireActivity(), R.color.blue),
                NoteType.HIDDEN
            ),
            NotesCategory(
                resources.getString(R.string.msg_trash),
                ContextCompat.getDrawable(requireActivity(), R.drawable.ic_trash),
                ContextCompat.getColor(requireActivity(), R.color.red),
                NoteType.TRASH
            ),

            )
    }

    private fun setUpCurrentTime() {
        val currentTime = notesViewModel.getTime(DATE)
        binding.tvTitleDate.text = currentTime
    }

    private fun showSnackBar(message: String) {
        context?.showSnackBar(message, this.view)
    }

    companion object {
        private const val DATE  = "dd MMMM HH:mm"
    }
}
