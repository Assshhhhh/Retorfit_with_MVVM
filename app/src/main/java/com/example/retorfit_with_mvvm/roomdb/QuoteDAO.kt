package com.example.retorfit_with_mvvm.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.retorfit_with_mvvm.model.Result

@Dao
interface QuoteDAO {

    @Insert
    suspend fun addQuotes(quotes: List<Result>)

    @Query("SELECT * FROM quote")
    suspend fun getQuotes() : List<Result>

}