package com.alex44.kotlintestapp.model.repo

import com.alex44.kotlintestapp.common.interfaces.INetworkStatus
import com.alex44.kotlintestapp.model.api.IDataSource
import com.alex44.kotlintestapp.model.dtos.DataDTO
import io.reactivex.Maybe
import io.reactivex.MaybeEmitter
import io.reactivex.schedulers.Schedulers

class DataRepo(private var source: IDataSource, private val repoCache: IRepoCache, private val networkStatus: INetworkStatus) : IDataRepo {

    override fun getData(query: String): List<DataDTO> {
        return repoCache.get(query)
    }

    override fun loadData(query: String): Maybe<List<DataDTO>> {
        if (networkStatus.isOnline()) {
            return source.getData(query)
                .subscribeOn(Schedulers.io())
                .map { response ->
                    if (response.data != null) {
                        repoCache.put(query, response.data)
                        return@map response.data
                    }
                    else return@map ArrayList<DataDTO>()
                }
        }
        else {
            return Maybe.create { emitter: MaybeEmitter<List<DataDTO>> ->
                emitter.onError(RuntimeException("No internet"))
            }
                .subscribeOn(Schedulers.io())
        }
    }

}