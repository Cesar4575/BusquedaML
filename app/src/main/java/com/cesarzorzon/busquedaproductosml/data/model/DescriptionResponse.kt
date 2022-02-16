package com.cesarzorzon.busquedaproductosml.data.model

import com.google.gson.annotations.SerializedName

data class DescriptionResponse (
    @SerializedName("plain_text")
    var description: String
    )