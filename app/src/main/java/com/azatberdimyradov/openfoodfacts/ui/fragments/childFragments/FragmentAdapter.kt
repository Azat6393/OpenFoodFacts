package com.azatberdimyradov.openfoodfacts.ui.fragments.childFragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 3
    }


    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> SummaryFragment()
            1 -> IngredientsFragment()
            2 -> NutritionFragment()
            else -> createFragment(position)
        }
    }
}