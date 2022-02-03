package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.ui.MainViewModel
import com.example.myapplication.util.addTo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestCameraPermission()
        observeEvent()

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    private fun requestCameraPermission() {
        val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            Toast.makeText(this, "ok $it", Toast.LENGTH_SHORT).show()
        }
        permissionLauncher.launch(android.Manifest.permission.ACCESS_NETWORK_STATE)
    }

    private fun observeEvent() {
        viewModel.itemEventRelay
            .ofType(MainViewModel.UsersEvent::class.java)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                viewModel.usersEvent.set(it.result.toString())
            }
            .addTo(disposable)

        viewModel.itemEventRelay
            .ofType(MainViewModel.AddSuccessEvent::class.java)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Toast.makeText(this, it.isSuccess.toString(), Toast.LENGTH_SHORT).show()
            }
            .addTo(disposable)
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }

}