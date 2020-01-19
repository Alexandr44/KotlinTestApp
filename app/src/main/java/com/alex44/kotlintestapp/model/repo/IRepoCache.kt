package com.alex44.kotlintestapp.model.repo

import com.alex44.kotlintestapp.model.dtos.DataDTO

interface IRepoCache {

    fun put(query : String, data : List<DataDTO>)

    fun get(query: String) : List<DataDTO>

}