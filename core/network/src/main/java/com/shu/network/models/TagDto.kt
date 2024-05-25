package com.shu.network.models

import com.google.gson.annotations.SerializedName
import com.shu.modules.Category
import com.shu.modules.Tag

data class TagDto(
    @SerializedName("id"   ) var id   : Int?    = null,
    @SerializedName("name" ) var name : String? = null
)

fun TagDto.fromApi(): Tag {
    return with(this) {
        Tag(
            id = id,
            name = name
        )
    }
}