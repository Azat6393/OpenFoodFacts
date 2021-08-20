package com.azatberdimyradov.openfoodfacts.ui.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class FoodFactsFragmentFactory @Inject constructor(
    private val historyFragment: HistoryFragment,
    private val scanFragment: ScanFragment,
    private val productDetailsFragment: ProductDetailsFragment,
    private val glide: RequestManager
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            ProductDetailsFragment::class.java.name -> ProductDetailsFragment(glide)
            else -> super.instantiate(classLoader, className)
        }
    }

}