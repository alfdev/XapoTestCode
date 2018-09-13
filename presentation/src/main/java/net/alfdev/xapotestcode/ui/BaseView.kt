package net.alfdev.xapotestcode.ui

import android.content.Context
import android.support.annotation.StringRes

interface BaseView {
    fun getContext(): Context

    fun showLoading()

    fun hideLoading()

    fun showError(message: String)

    fun showError(@StringRes errorResId: Int){
        this.showError(getContext().getString(errorResId))
    }
}