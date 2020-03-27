package com.mahmoud.nytimes.pkg.main.ui.main.viewModel

import android.net.ConnectivityManager
import com.mahmoud.nytimes.pkg.main.data.model.PopularArticlesResponse
import com.mahmoud.nytimes.pkg.main.ui.bases.BaseViewModel
import com.mahmoud.nytimes.pkg.main.ui.bases.StateLiveData
import com.mahmoud.nytimes.pkg.main.ui.main.data.MainRepository

class MainViewModel(
    private val mainRepository: MainRepository,
    connectivityManager: ConnectivityManager
) : BaseViewModel(connectivityManager) {

    val allArticlesLiveData: StateLiveData<PopularArticlesResponse> = StateLiveData()

    fun getAllArticles() : StateLiveData<PopularArticlesResponse>{

        if(!isNetworkAvailable){
            publishNoInternet(allArticlesLiveData)
            return allArticlesLiveData
        }
        performApiCall(allArticlesLiveData,mainRepository.getAllArticles())

        publishLoading(allArticlesLiveData)

        return allArticlesLiveData
    }
}
