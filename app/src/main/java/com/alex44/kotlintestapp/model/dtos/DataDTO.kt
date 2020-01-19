package com.alex44.kotlintestapp.model.dtos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DataDTO (
    @Expose @SerializedName("url") val imgUrl : String? = null,
    @Expose @SerializedName("title") val title : String? = null
) : Serializable