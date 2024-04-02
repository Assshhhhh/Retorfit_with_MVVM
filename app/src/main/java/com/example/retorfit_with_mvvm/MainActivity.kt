package com.example.retorfit_with_mvvm

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.retorfit_with_mvvm.api.QuotesService
import com.example.retorfit_with_mvvm.api.RetrofitHelper
import com.example.retorfit_with_mvvm.model.QuoteList
import com.example.retorfit_with_mvvm.repository.QuoteRepository
import com.example.retorfit_with_mvvm.viewmodels.MainViewModel
import com.example.retorfit_with_mvvm.viewmodels.ViewModelFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val repository = (application as QuoteApplication).quoteRepository

        mainViewModel = ViewModelProvider(this, ViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.quotes.observe(this, Observer {
            //Log.d("RETRO", it.results.toString())
            Toast.makeText(this@MainActivity, it.results.size.toString(), Toast.LENGTH_SHORT).show()
        })

    }
}