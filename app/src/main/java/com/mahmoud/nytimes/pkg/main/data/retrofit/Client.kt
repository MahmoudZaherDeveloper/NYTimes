package com.mahmoud.nytimes.pkg.main.data.retrofit

import com.mahmoud.nytimes.pkg.main.utilities.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Client {
    companion object {

        private var instance : Retrofit? = null

        fun getApiService(): ApiInterface?{

            if(instance == null){
                instance = Retrofit.Builder()
                    .baseUrl(Constants.api.URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return instance?.create(ApiInterface::class.java)
        }
    }
}