package com.example.retorfit_with_mvvm.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retorfit_with_mvvm.api.QuotesService
import com.example.retorfit_with_mvvm.model.QuoteList
import com.example.retorfit_with_mvvm.roomdb.QuoteDB
import com.example.retorfit_with_mvvm.utils.NetworkUtils
import kotlin.time.times

class QuoteRepository internal constructor(
    private val quotesService: QuotesService,
    private val quoteDB: QuoteDB, private val applicationContext: Context)  {

    private val quotesLiveData = MutableLiveData<Response<QuoteList>>()
    val quotes: LiveData<Response<QuoteList>>
        get() = quotesLiveData

    suspend fun getQuotes(page: Int){

        if (NetworkUtils.isInternetAvailable(applicationContext)){
            try {
                val result = quotesService.getQuotes(page)
                if (result?.body() != null){
                    quoteDB.quoteDao().addQuotes(result.body()!!.results)
                    quotesLiveData.postValue(Response.Success(result.body()!!))
                }
                else{
                    quotesLiveData.postValue(Response.Error("API Error!"))
                }
            }catch (e: Exception){
                quotesLiveData.postValue(Response.Error(e.message.toString()))
            }

        }
        else{
            val quotes = quoteDB.quoteDao().getQuotes()
            val quoteList = QuoteList(1, 1, 1, quotes, 1, 1)
            try {
                quotesLiveData.postValue(Response.Success(quoteList))
            }catch (e: Exception){
                quotesLiveData.postValue(Response.Error(e.message.toString()))
            }
        }

    }

    suspend fun getBackgroundQuotes() {

        val randomNumber = (Math.random() * 10).toInt() + 1
        val result = quotesService.getQuotes(randomNumber)
        if (result?.body() != null){
            quoteDB.quoteDao().addQuotes(result.body()!!.results)
        }
    }

}