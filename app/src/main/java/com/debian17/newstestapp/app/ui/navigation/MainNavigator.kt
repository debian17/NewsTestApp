package com.debian17.newstestapp.app.ui.navigation

import androidx.fragment.app.FragmentManager
import com.debian17.newstestapp.R
import com.debian17.newstestapp.app.base.mvp.BaseFragment

class MainNavigator(private val fragmentManager: FragmentManager) {

    fun addFragment(fragment: BaseFragment, addToBackStack: Boolean) {
        val transaction = fragmentManager.beginTransaction()
            .add(R.id.flMainContainer, fragment)
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

}