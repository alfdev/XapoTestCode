package net.alfdev.xapotestcode.ui

import android.content.Context
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity: BaseView, AppCompatActivity() {

    override fun getContext(): Context = this
}