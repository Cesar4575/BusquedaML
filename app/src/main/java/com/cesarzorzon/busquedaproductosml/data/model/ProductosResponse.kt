package com.cesarzorzon.busquedaproductosml.data.model

import com.google.gson.annotations.SerializedName


data class ProductosResponse(
    @SerializedName("results")
    var resulted: List<Result>
    )
    data class Result (
        @SerializedName("thumbnail")
        var images: String,
        var title: String,
        var id:String,
        var price: String
        )




