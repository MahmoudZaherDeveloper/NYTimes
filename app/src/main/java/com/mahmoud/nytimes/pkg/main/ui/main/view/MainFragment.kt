package com.mahmoud.nytimes.pkg.main.ui.main.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer

import org.koin.androidx.viewmodel.ext.android.viewModel
import com.mahmoud.nytimes.R
import com.mahmoud.nytimes.pkg.main.data.model.Result
import com.mahmoud.nytimes.pkg.main.ui.bases.BaseFragment
import com.mahmoud.nytimes.pkg.main.ui.bases.OnListItemClickListener
import com.mahmoud.nytimes.pkg.main.ui.bases.StateData
import com.mahmoud.nytimes.pkg.main.ui.main.viewModel.MainViewModel
import com.mahmoud.nytimes.pkg.main.utilities.Constants
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : BaseFragment(), OnListItemClickListener<Result> {
    override val layoutId: Int
        get() = R.layout.main_fragment

    private val viewModel: MainViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        observeAndShowArticles()
        sr_refresh.setOnRefreshListener {
            observeAndShowArticles()
        }
    }
    private fun observeAndShowArticles() {
        sr_refresh.isRefreshing = true

        viewModel.getAllArticles().observe(activity!!, Observer {
            when (it.getStatus()) {

                StateData.DataStatus.LOADING -> {
                    fl_container.visibility = View.GONE
                    showLayoutLoading()
                }

                StateData.DataStatus.SUCCESS -> {
                    sr_refresh.isRefreshing = false
                    fl_container.visibility = View.VISIBLE
                    hideLayoutErrorAndLoading()
                    it?.getData()?.let { res ->
                        rv_articles.adapter = ArticleAdapter(context!!, res.results, this)
                    } ?: run {
                        showLayoutError("No Data found in list")
                    }
                }

                StateData.DataStatus.ERROR -> {
                    fl_container.visibility = View.GONE
                    showLayoutError(it.getError()?.message.toString())
                }

                StateData.DataStatus.NOT_COMPLETED -> {
                    fl_container.visibility = View.GONE
                    showLayoutError("Something wrong happened")
                }
                StateData.DataStatus.NO_INTERNET -> {
                    fl_container.visibility = View.GONE
                    showLayoutError("No Internet Connection")
                }
            }
        })
    }

    override fun onItemClick(view: View, model: Result) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.bundle.ARTICLE, model)
        navigationController.navigate(R.id.action_homeFragment_to_detailsFragment, bundle)
    }
}
