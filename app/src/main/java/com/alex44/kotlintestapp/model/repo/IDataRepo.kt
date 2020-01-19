package com.alex44.kotlintestapp.model.repo

import com.alex44.kotlintestapp.model.dtos.DataDTO
import io.reactivex.Maybe

interface IDataRepo {

    fun getData(query : String) : List<DataDTO>

    fun loadData(query : String) : Maybe<List<DataDTO>>

}