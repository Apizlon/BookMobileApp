package com.apiclient.bookstoreapp.presentation.books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.apiclient.bookstoreapp.R
import com.apiclient.bookstoreapp.databinding.BottomSheetBookActionsBinding
import com.apiclient.bookstoreapp.domain.model.BookResponse
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.util.Log

class BookActionsBottomSheet : BottomSheetDialogFragment() {

    private var _binding: BottomSheetBookActionsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BooksViewModel by viewModels({ requireParentFragment() })

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
        Log.d("BookActionsBottomSheet", "Received book: ${book?.id}")

        binding.btnEditBook.setOnClickListener {
            book?.let {
                Log.d("BookActionsBottomSheet", "Navigating to edit book: ${it.id}")
                val bundle = Bundle().apply {
                    putParcelable("book", it)
                }
                findNavController().navigate(R.id.action_books_to_crudBook, bundle)
            }
            dismiss()
        }

        binding.btnDeleteBook.setOnClickListener {
            book?.let {
                Log.d("BookActionsBottomSheet", "Deleting book: ${it.id}")
                viewModel.deleteBook(it.id)
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