package com.example.myapplication.repo

import com.example.myapplication.model.DeleteIssue
import com.example.myapplication.model.FarmIssue
import io.reactivex.Single

interface FarmIssueRepo {
    fun getFarmIssue() : Single<List<FarmIssue>>
    fun deleteFarmIssue(deleteIssue: DeleteIssue) : Single<Boolean>
}