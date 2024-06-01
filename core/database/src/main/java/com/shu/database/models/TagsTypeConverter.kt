package com.shu.database.models

import androidx.room.TypeConverter
import java.util.Arrays

import java.util.stream.Collectors


class TagsTypeConverter {
    @TypeConverter
    fun fromTags(tags: ArrayList<String>): String {
        return tags.stream().collect(Collectors.joining(","))
    }

    @TypeConverter
    fun toTags(data: String): ArrayList<String> {
        return arrayListOf(*data.split(",".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray())
    }
}