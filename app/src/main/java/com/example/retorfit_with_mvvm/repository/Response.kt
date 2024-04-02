package com.example.retorfit_with_mvvm.repository

import com.example.retorfit_with_mvvm.model.QuoteList

// 3. Generic Implementation : Contains data and status about loading the data
sealed class Response<T>(val data: T? = null, val errorMessage: String? = null) {
    class Loading<T> : Response<T>()
    class Success<T>(quoteList: T) : Response<T>(data = quoteList)
    class Error<T>(errorMsg: String) : Response<T>(errorMessage = errorMsg)
}

/*
// 2.
sealed class Response(val data: QuoteList? = null, val errorMessage: String? = null) {
    class Loading : Response()
    class Success(quoteList: QuoteList) : Response(data = quoteList)
    class Error(errorMessage: String) : Response(errorMessage = errorMessage)
}
*/

/*
// 1.
sealed class Response() {
    class Loading : Response()
    class Success(val quoteList: QuoteList) : Response()
    class Error(val errorMessage: String) : Response()
}
*/
