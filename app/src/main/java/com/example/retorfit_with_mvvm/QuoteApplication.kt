package com.example.retorfit_with_mvvm

import android.app.Application
import androidx.constraintlayout.widget.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.retorfit_with_mvvm.api.QuotesService
import com.example.retorfit_with_mvvm.api.RetrofitHelper
import com.example.retorfit_with_mvvm.repository.QuoteRepository
import com.example.retorfit_with_mvvm.roomdb.QuoteDB
import com.example.retorfit_with_mvvm.worker.QuoteWorker
import java.util.concurrent.TimeUnit

class QuoteApplication : Application() {

    lateinit var quoteRepository: QuoteRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
        setupWorker()
    }

    private fun setupWorker() {
        val constraints = androidx.work.Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val workerRequest = PeriodicWorkRequest.Builder(QuoteWorker::class.java, 15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueue(workerRequest)

    }

    private fun initialize() {
        val quoteService = RetrofitHelper.getInstance().create(QuotesService::class.java)
        val quoteDB = QuoteDB.getDatabase(applicationContext)
        quoteRepository = QuoteRepository(quoteService, quoteDB, applicationContext)
    }

}