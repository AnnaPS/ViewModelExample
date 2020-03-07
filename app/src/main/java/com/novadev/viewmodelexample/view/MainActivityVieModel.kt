package com.novadev.viewmodelexample.view

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.text.InputType
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.novadev.viewmodelexample.data.Post
import com.novadev.viewmodelexample.repository.ConfigRetrofit
import com.novadev.viewmodelexample.utils.setEditText

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivityVieModel: ViewModel(){

    private val service = ConfigRetrofit.getService()

    private var _listPost = MutableLiveData<List<Post>>()
    var listPostMutableLiveData = _listPost

    private var _postById = MutableLiveData<Post>()
    var postByIdMutableLiveData = _postById


    fun getAllPosts(){
        //Recieve all posts
        service.getAllPosts().enqueue(object: Callback<List<Post>>{
            override fun onResponse(call: Call<List<Post>>?, response: Response<List<Post>>?) {
                val posts = response?.body()
                listPostMutableLiveData.value = posts
            }
            override fun onFailure(call: Call<List<Post>>?, t: Throwable?) {
                t?.printStackTrace()
            }
        })
    }

    fun getPostById(id: Int){
        //Recieve data from post with id 1
        var post: Post? = null
        service.getPostById(id).enqueue(object: Callback<Post>{
            override fun onResponse(call: Call<Post>?, response: Response<Post>?) {
                post = response?.body()
                postByIdMutableLiveData.value = post
            }
            override fun onFailure(call: Call<Post>?, t: Throwable?) {
                t?.printStackTrace()
            }
        })
    }


    fun showDialog(context: Context){
        val editText = EditText(context)
        editText.maxLines = 1
        editText.inputType = InputType.TYPE_CLASS_NUMBER
        AlertDialog.Builder(context)
            .setTitle("Enter your id number")
            .setEditText(editText)
            .setPositiveButton("OK") { _: DialogInterface, _: Int ->
                var value =  editText.text.toString()
                if( value.toInt() > 100){
                    Toast.makeText(context, "Id number can't be more than 100 or less than 0." +
                            " Please try again", Toast.LENGTH_SHORT).show()
                    editText.setText("")
                }else{
                   var post = editText.text.toString()
                    getPostById(post.toInt())
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

}