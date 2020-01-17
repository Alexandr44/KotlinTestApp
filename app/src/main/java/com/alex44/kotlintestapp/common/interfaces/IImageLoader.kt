package com.alex44.kotlintestapp.common.interfaces

interface IImageLoader<T> {

    fun loadInto(url : String, container : T, corners : Int)

}