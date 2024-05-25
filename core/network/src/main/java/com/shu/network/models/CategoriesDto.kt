package com.shu.network.models

import com.google.gson.annotations.SerializedName
import com.shu.modules.Category

data class CategoriesDto(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null
)


fun CategoriesDto.fromApi(): Category {
    return with(this) {
        Category(
            id = id,
            name = name
        )
    }
}