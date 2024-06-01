package com.shu.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "category")
data class CategoryDbo(
    @PrimaryKey
    @ColumnInfo("id")
    val id   : Int?   ,
    @ColumnInfo("name")
    val name : String?
)
