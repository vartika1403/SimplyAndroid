package com.example.appnetworking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {
    private lateinit var counter:String
    private lateinit var adapter: MainAdapter
    companion object X {
        val TAG = "MainActivity"
    }

    private val viewModel:QuoteViewModel by lazy {
        ViewModelProvider(this,ViewModelFactory(ApiHelper(RetrofitBuilder.apiService)))[QuoteViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        initCountObsever()
        initView()
        setUpObservers();
    }

    private fun setUpObservers(){
        viewModel.getUsers().observe(this, Observer {
           it?.let {
               resource ->
               when (resource.status) {
                   Status.SUCCESS -> {
                       recyclerView.visibility = View.VISIBLE
                       progressBar.visibility = View.GONE
                       resource.data?.let { users -> retrieveList(users) }
                   }
                   Status.ERROR -> {
                       recyclerView.visibility = View.VISIBLE
                       progressBar.visibility = View.GONE
                       Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                   }
                   Status.LOADING -> {
                       progressBar.visibility = View.VISIBLE
                       recyclerView.visibility = View.GONE
                   }
               }
           }
        })
    }

    private fun retrieveList(users: List<User>) {
        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }
    }

    private fun initView(){
        counter_plus.setOnClickListener(::incrementCounter)
        counter_minus.setOnClickListener(::decrementCounter)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter((arrayListOf()))
        recyclerView.addItemDecoration(DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
        ))
        recyclerView.adapter = adapter
    }

    private fun incrementCounter(view: View) {
        viewModel.increamentState()
    }

    private fun decrementCounter(view: View) {
        viewModel.decreamentState()
    }

    private fun initCountObsever() {
        lifecycleScope.launchWhenStarted {
           viewModel.countState.collect { value ->
                text_counter.text = "$value"
            }
        }
    }
}