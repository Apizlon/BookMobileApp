package com.apiclient.bookstoreapp.presentation.books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.apiclient.bookstoreapp.R
import com.apiclient.bookstoreapp.databinding.BottomSheetBookActionsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BookActionsBottomSheet : BottomSheetDialogFragment() {

    private var _binding: BottomSheetBookActionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetBookActionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCreateBook.setOnClickListener {
            findNavController().navigate(R.id.action_books_to_crudBook)
            dismiss()
        }

        binding.btnEditBook.setOnClickListener {
            // Для редактирования требуется выбрать книгу, пока заглушка
            dismiss()
        }

        binding.btnDeleteBook.setOnClickListener {
            // Для удаления требуется выбрать книгу, пока заглушка
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}