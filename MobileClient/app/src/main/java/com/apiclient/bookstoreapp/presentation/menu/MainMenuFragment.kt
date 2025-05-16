package com.apiclient.bookstoreapp.presentation.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.apiclient.bookstoreapp.R
import com.apiclient.bookstoreapp.databinding.FragmentMainMenuBinding

class MainMenuFragment : Fragment() {

    private var _binding: FragmentMainMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnViewBooks.setOnClickListener {
            findNavController().navigate(R.id.action_main_menu_to_books)
        }

        binding.btnAddBook.setOnClickListener {
            findNavController().navigate(R.id.action_main_menu_to_crud_book)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}