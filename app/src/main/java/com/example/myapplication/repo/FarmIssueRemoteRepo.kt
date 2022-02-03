package com.example.myapplication.repo

import android.util.Log
import com.example.myapplication.model.DeleteIssue
import com.example.myapplication.model.FarmIssue
import com.example.myapplication.network.HttpClient
import com.example.myapplication.network.MainService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class FarmIssueRemoteRepo:FarmIssueRepo {

    override fun getFarmIssue(): Single<List<FarmIssue>> {
        return HttpClient.client
            .create(MainService::class.java)
            .getFarmIssue()
            .subscribeOn(Schedulers.io())
            .map {
                it.farmIssues
            }
            .onErrorReturn { error ->
                Log.d("error", error.toString())
                emptyList()
            }
    }

    override fun deleteFarmIssue(deleteIssue: DeleteIssue): Single<Boolean> {
        return HttpClient.client
            .create(MainService::class.java)
            .deleteFarmIssue(deleteIssue)
            .subscribeOn(Schedulers.io())
            .map {
                it.success
            }
            .onErrorReturn { error ->
                Log.d("error", error.toString())
                false
            }
    }

    companion object {
        fun getInstance(): FarmIssueRemoteRepo {
            return LazyHolder.INSTANCE
        }
    }
    private object LazyHolder {
        val INSTANCE = FarmIssueRemoteRepo()
    }
}