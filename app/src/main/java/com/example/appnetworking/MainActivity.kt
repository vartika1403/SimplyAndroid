package com.example.appnetworking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {
    private lateinit var counter:String
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
    }

    private fun initView(){
        counter_plus.setOnClickListener(::incrementCounter)
        counter_minus.setOnClickListener(::decrementCounter)

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