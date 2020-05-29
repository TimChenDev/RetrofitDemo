package com.timchentw.retrofitdemo.entity

/**
 *  author: Tim Chen
 *  time  : 2020-05-29
 *  desc  : 新聞的每筆資料的格式
 */
data class NewsBean(
    val catTitle: String,
    val catTitleEn: String,
    val icon_new: String,
    val linkOut: String,
    val linkUrl: String,
    val linkUrlEn: String,
    val nID: String,
    val pubDate: String,
    val subCatID: Any,
    val title: String,
    val titleEn: String
)