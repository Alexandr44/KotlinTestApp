package com.alex44.kotlintestapp.model.repo

import com.alex44.kotlintestapp.model.dtos.DataDTO
import io.paperdb.Paper

class PaperCache : IRepoCache {

    companion object {
        private const val DATA_BOOK = "Data"
    }

    override fun put(query: String, data: List<DataDTO>) {
        Paper.book(DATA_BOOK).write(query, data)
    }

    override fun get(query: String): List<DataDTO> {
        return Paper.book(DATA_BOOK).read(query)
    }

}