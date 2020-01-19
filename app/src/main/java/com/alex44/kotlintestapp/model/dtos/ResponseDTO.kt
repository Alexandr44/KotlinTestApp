package com.alex44.kotlintestapp.model.dtos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseDTO (
    @Expose @SerializedName("message") val message : String? = null,
    @Expose @SerializedName("data") val data : List<DataDTO>? = null
)