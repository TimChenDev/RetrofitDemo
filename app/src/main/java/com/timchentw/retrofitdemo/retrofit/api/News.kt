package com.timchentw.retrofitdemo.retrofit.api

import com.timchentw.retrofitdemo.entity.NewsBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *  author: Tim Chen
 *  time  : 2020-05-29
 *  desc  : api 格式
 *
 *  實際 api: https://www.sinica.edu.tw/getJsonData.php?id=2
 *
 *  前面的域名(https://www.sinica.edu.tw) 在 RetrofitManager 裡面進行設定
 *
 *  這裡定義 api 路徑(/getJsonData.php)
 *  定義 api 參數(id=2)
 *
 *  api 資料來源: https://data.gov.tw/dataset/94024
 */
interface News {

    @GET("/getJsonData.php")
    fun getJsonData(@Query("id") id: Int): Call<List<NewsBean>>
}