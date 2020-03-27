package com.mahmoud.nytimes.pkg.main.ui.details


import android.os.Bundle

import com.mahmoud.nytimes.R
import com.mahmoud.nytimes.pkg.main.data.model.MediaMetadata
import com.mahmoud.nytimes.pkg.main.data.model.Result
import com.mahmoud.nytimes.pkg.main.ui.bases.BaseFragment
import com.mahmoud.nytimes.pkg.main.utilities.Constants
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_details

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initViews()
    }

    private fun initViews() {
        arguments?.let {
            val article = it.getParcelable<Result>(Constants.bundle.ARTICLE)
            if (article != null) {
                initSlider(article.media[0].mediaMetadata)
                tv_title.text = article.title
                tv_abstract.text = article.abstract
                tv_by_line.text = article.byline
                tv_date.text = article.byline
                tv_section.text = article.section
                tv_source.text = article.source
                tv_url.text = article.url
            } else {
                navigationController.popBackStack()
            }
        } ?: run {
            navigationController.popBackStack()
        }
    }

    private fun initSlider(images : List<MediaMetadata>?) {

        indicator.removeAllTabs()
        images?.forEach{ _ ->
            indicator.addTab(indicator.newTab())
        }

        if(images == null || images.isEmpty())
            return

        imageSlider.sliderAdapter = ImageSliderAdapter(context!!,images){
            val item = indicator.getTabAt(it)
            item?.select()
        }
        imageSlider.setCurrentPageListener {
            if(imageSlider.isAutoCycle){
                val item = indicator.getTabAt(it)
                item?.select()
            }
        }
        imageSlider.startAutoCycle()
        imageSlider.setIndicatorAnimation(IndicatorAnimations.WORM)
        imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
    }

    override fun onDestroyView() {
        imageSlider.isAutoCycle = false
        super.onDestroyView()
    }
}
