package com.example.retorfit_with_mvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retorfit_with_mvvm.model.QuoteList
import com.example.retorfit_with_mvvm.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: QuoteRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getQuotes(1)

        }
    }

    val quotes : LiveData<QuoteList>
        get() = repository.quotes
}