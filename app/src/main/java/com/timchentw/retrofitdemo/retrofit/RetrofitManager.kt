package com.timchentw.retrofitdemo.retrofit

import com.google.gson.GsonBuilder
import com.timchentw.retrofitdemo.retrofit.api.News
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *  author: Tim Chen
 *  time  : 2020-05-29
 *  desc  : 管理 Retrofit 的相關參數與管理所有 api
 */
object RetrofitManager {

    private lateinit var retrofit: Retrofit

    private const val timeout = 10L

    val apiNews: News = create(News::class.java)

    /**
     * 實際上是取代以下這個動作, 並且進行更多的初始化設定
     * retrofit = Retrofit.Builder()
     * retrofit.create(News::class.java)
     */
    private fun <S> create(serviceClass: Class<S>): S {

        val client: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
            .build()

        val gson = GsonBuilder()
//            .serializeNulls() // 是否將 null 變數也進行轉換, 暫時不使用
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create()

        // create retrofit
        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://www.sinica.edu.tw")
            .client(client)
            .build()
        return retrofit.create(serviceClass)
    }

    /**
     * Retrofit 執行完必須使用 Callback 類別去接收參數
     * Callback 有 onResponse 跟 onFailure 兩個回傳的 function 接收 api 的執行結果成功與否
     *
     * 這裡使用 customCallback 繼承自 Callback, 將回傳的兩個 function 近一步做篩選處理
     * 將執行結果細分為 success, failure, error 三種並回傳
     */
    fun <T> customCallback(returnFunction: (success: Response<T>?, failure: Response<T>?, error: Throwable?) -> Unit): Callback<T> {
        return object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.code() == 200) {
                    returnFunction(response, null, null)
                } else {
                    returnFunction(null, response, null)
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) = returnFunction(null, null, t)
        }
    }
}