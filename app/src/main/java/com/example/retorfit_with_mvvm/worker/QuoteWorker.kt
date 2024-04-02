package com.example.retorfit_with_mvvm.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.retorfit_with_mvvm.QuoteApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteWorker(private val context: Context, private val params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {

        Log.d("RETRO", "Worker Called")

        val repository = (context as QuoteApplication).quoteRepository
        CoroutineScope(Dispatchers.IO).launch {
            repository.getBackgroundQuotes()
        }
        return Result.success()
    }

}