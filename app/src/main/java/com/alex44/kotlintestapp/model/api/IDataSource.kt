package com.alex44.kotlintestapp.model.api

import com.alex44.kotlintestapp.model.dtos.ResponseDTO
import io.reactivex.Maybe
import retrofit2.http.GET
import retrofit2.http.Query

interface IDataSource {

    @GET("api.php")
    fun getData(@Query("query") query : String) : Maybe<ResponseDTO>

}