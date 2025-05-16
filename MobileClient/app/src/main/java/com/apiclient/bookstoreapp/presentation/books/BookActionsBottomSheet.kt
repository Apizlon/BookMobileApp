package com.apiclient.bookstoreapp.presentation.books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.apiclient.bookstoreapp.R
import com.apiclient.bookstoreapp.databinding.BottomSheetBookActionsBinding
import com.apiclient.bookstoreapp.domain.model.BookResponse
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

        val book: BookResponse? = arguments?.getParcelable("book")

        binding.btnCreateBook.setOnClickListener {
            findNavController().navigate(R.id.action_books_to_crudBook)
            dismiss()
        }

        binding.btnEditBook.setOnClickListener {
            book?.let {
                val bundle = Bundle().apply {
                    putParcelable("book", it)
                }
                findNavController().navigate(R.id.action_books_to_crudBook, bundle)
            }
            dismiss()
        }

        binding.btnDeleteBook.setOnClickListener {
            book?.let {
                val parentFragment = parentFragment as? BooksFragment
                parentFragment?.viewModel?.deleteBook(it.id)
            }
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(book: BookResponse? = null): BookActionsBottomSheet {
            return BookActionsBottomSheet().apply {
                arguments = Bundle().apply {
                    putParcelable("book", book)
                }
            }
        }
    }
}