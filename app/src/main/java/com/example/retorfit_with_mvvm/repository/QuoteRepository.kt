package com.example.retorfit_with_mvvm.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retorfit_with_mvvm.api.QuotesService
import com.example.retorfit_with_mvvm.model.QuoteList
import com.example.retorfit_with_mvvm.roomdb.QuoteDB
import com.example.retorfit_with_mvvm.utils.NetworkUtils

class QuoteRepository internal constructor(
    private val quotesService: QuotesService,
    private val quoteDB: QuoteDB, private val applicationContext: Context)  {

    private val quotesLiveData = MutableLiveData<QuoteList>()
    val quotes: LiveData<QuoteList>
        get() = quotesLiveData

    suspend fun getQuotes(page: Int){

        if (NetworkUtils.isInternetAvailable(applicationContext)){
            val result = quotesService.getQuotes(page)
            if (result?.body() != null){
                quoteDB.quoteDao().addQuotes(result.body()!!.results)
                quotesLiveData.postValue(result.body())
            }
        }
        else{
            val quotes = quoteDB.quoteDao().getQuotes()
            val quoteList = QuoteList(1, 1, 1, quotes, 1, 1)
            quotesLiveData.postValue(quoteList)
        }

    }

}