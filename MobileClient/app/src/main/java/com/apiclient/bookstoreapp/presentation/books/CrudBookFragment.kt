package com.apiclient.bookstoreapp.presentation.books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.apiclient.bookstoreapp.databinding.FragmentCrudBookBinding
import com.apiclient.bookstoreapp.domain.model.BookResponse

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

        // Настройка кнопки "Назад"
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        // Заполнение полей для редактирования
        val book: BookResponse? = arguments?.getParcelable("book")
        book?.let {
            binding.etBookName.setText(it.name)
            binding.etBookAuthor.setText(it.author)
        }

        // Обработка кнопки "Сохранить"
        binding.btnSaveBook.setOnClickListener {
            val name = binding.etBookName.text.toString()
            val description = binding.etBookDescription.text.toString()
            val author = binding.etBookAuthor.text.toString()
            if (name.isNotBlank() && author.isNotBlank()) {
                viewModel.saveBook(name, description, author, book?.id ?: 0L)
                findNavController().popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}