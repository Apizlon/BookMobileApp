package com.apiclient.bookstoreapp.presentation.books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.apiclient.bookstoreapp.R
import com.apiclient.bookstoreapp.databinding.FragmentBooksBinding

class BooksFragment : Fragment() {

    private var _binding: FragmentBooksBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BooksViewModel by viewModels()
    private lateinit var adapter: BooksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBooksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup RecyclerView
        adapter = BooksAdapter(emptyList()) { book ->
            val bundle = Bundle().apply {
                putParcelable("book", book)
            }
            findNavController().navigate(R.id.action_books_to_crudBook, bundle)
        }
        binding.recyclerBooks.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerBooks.adapter = adapter

        // Observe books from ViewModel
        viewModel.books.observe(viewLifecycleOwner) { books ->
            adapter.updateBooks(books)
        }

        // Handle add button click
        binding.btnAddBook.setOnClickListener {
            findNavController().navigate(R.id.action_books_to_crudBook)
        }

        // Fetch books
        viewModel.fetchBooks()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}