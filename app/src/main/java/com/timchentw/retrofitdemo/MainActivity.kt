package com.timchentw.retrofitdemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.timchentw.retrofitdemo.entity.NewsBean
import com.timchentw.retrofitdemo.retrofit.RetrofitManager
import com.timchentw.retrofitdemo.retrofit.RetrofitManager.customCallback

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RetrofitManager.apiNews.getJsonData(2)
            .enqueue(customCallback<List<NewsBean>> { success,
                                                      failure,
                                                      error ->

                success?.run {
                    body()?.forEach {
                        Log.d("response", "it: $it")
                    }
                }

                failure?.run {
                    Log.w("response", "request fail, response.code: ${code()}")
                }

                error?.run {
                    Log.e("response", "error, message: $message")
                }
            })

    }
}
