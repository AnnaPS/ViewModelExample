package com.novadev.viewmodelexample.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.novadev.viewmodelexample.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityVieModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initObservers()
        initListeners()

    }

    private fun initObservers(){

        viewModel.listPostMutableLiveData.observe(this, Observer {
            textViewPost.text = "${it[(0..100).random()].body}"
        })

        viewModel.postByIdMutableLiveData.observe(this, Observer {
            textViewByID.text = "${it.body}"
            var text = textView1.text
            textView1.text = "$text ${it.id}"
        })

    }

    private fun initListeners(){
        btnAll.setOnClickListener {
            viewModel.getAllPosts()
        }

        btnByid.setOnClickListener {
            showDialog()
        }
    }

    private fun initView(){
        viewModel = ViewModelProviders
            .of(this)
            .get(MainActivityVieModel::class.java)
    }

    private fun showDialog(){
        viewModel.showDialog(this)
    }
}
