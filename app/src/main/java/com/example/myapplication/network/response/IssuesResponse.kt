package com.example.myapplication.network.response

import com.example.myapplication.model.FarmIssue
import com.google.gson.annotations.SerializedName

class IssuesResponse {
    @SerializedName("success")
    val success: Boolean = false

    @SerializedName("IssueList")
    val farmIssues: List<FarmIssue> = emptyList()
}