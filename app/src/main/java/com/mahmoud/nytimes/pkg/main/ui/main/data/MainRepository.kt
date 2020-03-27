package com.mahmoud.nytimes.pkg.main.ui.main.data

import com.mahmoud.nytimes.pkg.main.data.model.PopularArticlesResponse
import com.mahmoud.nytimes.pkg.main.data.retrofit.ApiInterface
import com.mahmoud.nytimes.pkg.main.ui.bases.BaseRepository
import com.mahmoud.nytimes.pkg.main.utilities.Constants
import retrofit2.Call

class MainRepository(apiInterface: ApiInterface) : BaseRepository(apiInterface) {
    fun getAllArticles(): Call<PopularArticlesResponse> =
        apiInterface.getAllArticles(Constants.api.API_KEY)
}