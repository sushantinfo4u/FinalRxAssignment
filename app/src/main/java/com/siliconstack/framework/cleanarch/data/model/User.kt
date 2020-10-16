package com.siliconstack.framework.cleanarch.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user_table")
data class User(

    @PrimaryKey
    @SerializedName("id")
    val id: Int = -1,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String = "",

    @ColumnInfo(name = "email")
    @SerializedName("email")
    val email: String = "",

    @ColumnInfo(name = "avatar")
    @SerializedName("avatar")
    val avatar: String = ""
)