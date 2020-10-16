package com.siliconstack.framework.cleanarch.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.siliconstack.framework.cleanarch.R
import com.siliconstack.framework.cleanarch.data.source.remote.ApiHelper
import com.siliconstack.framework.cleanarch.data.source.remote.ApiServiceImpl
import com.siliconstack.framework.cleanarch.data.model.User
import com.siliconstack.framework.cleanarch.ui.base.ViewModelFactory
import com.siliconstack.framework.cleanarch.ui.main.adapter.MainAdapter
import com.siliconstack.framework.cleanarch.ui.main.viewmodel.MainViewModel
import com.siliconstack.framework.cleanarch.utils.Status
import com.siliconstack.rxkotlinassignment.data.room.appdatabase.AppDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        mainViewModel.getUsers().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let {
                            users -> renderList(users)
                                    savetoRoom(users)
                    }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    getDataFromDB()
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun getDataFromDB() {
        val db = AppDatabase.getAppDataBase(context = this)

        GlobalScope.launch {
          val op= db?.movieDao()?.getAll()
            op?.let { renderList(it) }
        }

    }


    private fun savetoRoom(users: List<User>){
        val db = AppDatabase.getAppDataBase(context = this)
        GlobalScope.launch {
            db?.movieDao()?.insertAll(users)
        }
    }

    private fun renderList(users: List<User>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(ApiServiceImpl()))
        ).get(MainViewModel::class.java)
    }
}
