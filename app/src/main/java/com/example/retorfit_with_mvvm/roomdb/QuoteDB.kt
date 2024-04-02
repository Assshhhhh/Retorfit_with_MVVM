package com.example.retorfit_with_mvvm.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.retorfit_with_mvvm.model.Result

@Database(entities = [Result::class], version = 1)
abstract class QuoteDB : RoomDatabase() {

    abstract fun quoteDao() : QuoteDAO

    companion object{

        @Volatile
        private var INSTANCE: QuoteDB? = null

        fun getDatabase(context: Context) : QuoteDB {
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        QuoteDB::class.java,
                        "QuoteDB"
                        )
                        .build()
                }
            }
            return INSTANCE!!
        }

    }

}