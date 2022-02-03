package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class FarmIssue(
    @SerializedName("edit_num")
    val editNum: String,
    @SerializedName("edit_address")
    val editAddress : String,
    @SerializedName("img_url")
    val imgUrl: String = ""
)
