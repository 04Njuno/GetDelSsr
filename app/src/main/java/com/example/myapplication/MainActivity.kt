package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.FarmIssue
import com.example.myapplication.ui.MainViewModel
import com.example.myapplication.ui.RecyclerViewAdapter
import com.example.myapplication.util.addTo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class MainActivity : AppCompatActivity() {
    private lateinit var usersEvent : List<FarmIssue>
    lateinit var recyclerViewAdapter: RecyclerViewAdapter

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.viewModel = viewModel
        requestPermission()
        observeEvent()
    }

    private fun requestPermission() {
        val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            Toast.makeText(this, "ok $it", Toast.LENGTH_SHORT).show()
        }
        permissionLauncher.launch(android.Manifest.permission.INTERNET)
    }

    private fun observeEvent() {
        viewModel.itemEventRelay
            .ofType(MainViewModel.UsersEvent::class.java)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                usersEvent = it.result
                Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show()
                getRecyclerView()
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
    private fun getRecyclerView(){
        recyclerViewAdapter = RecyclerViewAdapter(usersEvent, this)
        val layoutManager = LinearLayoutManager(this)
        binding.rvUsers.layoutManager = layoutManager
        binding.rvUsers.adapter = recyclerViewAdapter
    }



}