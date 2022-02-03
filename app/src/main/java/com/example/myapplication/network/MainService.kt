package com.example.myapplication.network

import com.example.myapplication.model.DeleteIssue
import com.example.myapplication.model.FarmIssue
import com.example.myapplication.network.response.DefaultResponse
import com.example.myapplication.network.response.IssuesResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MainService {

    @GET("farmIssue/list")
    fun getFarmIssue() : Single<IssuesResponse>

    @POST("farmIssue/delete")
    fun deleteFarmIssue(
        @Body deleteIssue: DeleteIssue
    ) : Single<DefaultResponse>
}