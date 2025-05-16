package com.apiclient.bookstoreapp.presentation.books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.apiclient.bookstoreapp.R
import com.apiclient.bookstoreapp.databinding.FragmentBooksBinding
import com.apiclient.bookstoreapp.domain.model.BookResponse

class BooksFragment : Fragment() {

    private var _binding: FragmentBooksBinding? = null
    private val binding get() = _binding!!
    val viewModel: BooksViewModel by viewModels()
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

        // Настройка кнопки "Назад"
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        // Настройка RecyclerView
        adapter = BooksAdapter(emptyList()) { book ->
            BookActionsBottomSheet.newInstance(book)
                .show(parentFragmentManager, "BookActionsBottomSheet")
        }
        binding.recyclerBooks.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerBooks.adapter = adapter

        // Наблюдение за книгами
        viewModel.books.observe(viewLifecycleOwner) { books ->
            adapter.updateBooks(books)
        }

        // Наблюдение за ошибками
        viewModel.error.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
        }

        // Обработка кнопки "+"
        binding.btnAddBook.setOnClickListener {
            findNavController().navigate(R.id.action_books_to_crudBook)
        }

        // Загрузка книг
        viewModel.fetchBooks()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}