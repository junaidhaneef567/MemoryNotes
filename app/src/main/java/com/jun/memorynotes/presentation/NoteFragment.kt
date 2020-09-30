package com.jun.memorynotes.presentation


import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.jun.core.data.Note

import com.jun.memorynotes.R
import com.jun.memorynotes.framework.NoteViewModel
import kotlinx.android.synthetic.main.fragment_note.*

class NoteFragment : Fragment() {

    private lateinit var viewModel: NoteViewModel
    private var currentNote = Note("","",0L,0L)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        checkButton.setOnClickListener {

            if(titleView.text.toString() != "" || contentView.text.toString() != ""){
                val time = System.currentTimeMillis()
                currentNote.title= titleView.text.toString()
                currentNote.content=contentView.text.toString()
                currentNote.updateTime=time
                if(currentNote.id == 0L)
                {
                    currentNote.creationTime= time
                }

                viewModel.savedNote(currentNote)
            }else {
                Navigation.findNavController(it).popBackStack()
            }
        }

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.saved.observe(this, Observer {
            if(it){
                Toast.makeText(context,"Done!",Toast.LENGTH_SHORT).show()
                hidekeyboard()
                Navigation.findNavController(titleView).popBackStack()
            }else
            {
                Toast.makeText(context,"Something went wrong, please try again!",Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun hidekeyboard()
    {
        val imm=context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(titleView.windowToken,0)
    }

}
