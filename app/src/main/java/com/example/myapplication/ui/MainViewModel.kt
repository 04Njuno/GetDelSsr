package com.example.myapplication.ui

import androidx.lifecycle.ViewModel
import com.example.myapplication.model.DeleteIssue
import com.example.myapplication.model.FarmIssue
import com.example.myapplication.repo.FarmIssueRemoteRepo
import com.example.myapplication.util.RxAction
import com.example.myapplication.util.addTo
import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import io.reactivex.disposables.CompositeDisposable

class MainViewModel : ViewModel() {
    private val farmIssueRepository = FarmIssueRemoteRepo.getInstance()
    private val disposables = CompositeDisposable()

    val itemEventRelay: Relay<RxAction> = PublishRelay.create()

    fun loadIssues() {
        farmIssueRepository.getFarmIssue()
            .subscribe { result ->
                itemEventRelay.accept(UsersEvent(result))
            }
            .addTo(disposables)
    }

    fun deleteIssue() {
        farmIssueRepository.deleteFarmIssue(DeleteIssue(editNum = "23" , editAddress = "23", imgUrl= "12315"))
            .subscribe { result ->
                itemEventRelay.accept(AddSuccessEvent(result))
            }
            .addTo(disposables)
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

    class UsersEvent(val result: List<FarmIssue>) : RxAction
    class AddSuccessEvent(val isSuccess: Boolean) : RxAction

}
