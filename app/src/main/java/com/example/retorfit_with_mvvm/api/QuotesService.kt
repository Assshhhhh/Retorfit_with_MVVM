package com.example.retorfit_with_mvvm.api

import com.example.retorfit_with_mvvm.model.QuoteList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesService {

    @GET("/quotes")
    suspend fun getQuotes(@Query("page") page: Int) : Response<QuoteList>

}