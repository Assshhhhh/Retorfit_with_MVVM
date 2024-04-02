package com.example.retorfit_with_mvvm

import android.app.Application
import com.example.retorfit_with_mvvm.api.QuotesService
import com.example.retorfit_with_mvvm.api.RetrofitHelper
import com.example.retorfit_with_mvvm.repository.QuoteRepository
import com.example.retorfit_with_mvvm.roomdb.QuoteDB

class QuoteApplication : Application() {

    lateinit var quoteRepository: QuoteRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val quoteService = RetrofitHelper.getInstance().create(QuotesService::class.java)
        val quoteDB = QuoteDB.getDatabase(applicationContext)
        quoteRepository = QuoteRepository(quoteService, quoteDB, applicationContext)
    }

}