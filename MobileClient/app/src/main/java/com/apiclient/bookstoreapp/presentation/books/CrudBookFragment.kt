package com.apiclient.bookstoreapp.presentation.books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.apiclient.bookstoreapp.databinding.FragmentCrudBookBinding
import com.apiclient.bookstoreapp.domain.model.Book

class CrudBookFragment : Fragment() {

    private var _binding: FragmentCrudBookBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BooksViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCrudBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Pre-fill fields if editing
        val book: Book? = arguments?.getParcelable("book")
        book?.let {
            binding.etBookTitle.setText(it.title)
            binding.etBookAuthor.setText(it.authorName)
        }

        binding.btnSaveBook.setOnClickListener {
            val title = binding.etBookTitle.text.toString()
            val authorName = binding.etBookAuthor.text.toString()
            if (title.isNotBlank() && authorName.isNotBlank()) {
                viewModel.saveBook(book?.copy(title = title, authorName = authorName) ?: Book(0, title, authorName))
                findNavController().popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}