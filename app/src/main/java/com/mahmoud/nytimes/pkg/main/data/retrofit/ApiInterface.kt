package com.mahmoud.nytimes.pkg.main.data.retrofit

import com.mahmoud.nytimes.pkg.main.data.model.PopularArticlesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("viewed/7.json")
    fun getAllArticles(@Query("api-key") apiKey : String) : Call<PopularArticlesResponse>
}