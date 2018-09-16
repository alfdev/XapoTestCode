package net.alfdev.xapotestcode.ui.project

import net.alfdev.xapotestcode.ui.BasePresenter

interface ProjectDetailPresenter : BasePresenter {
    fun loadDetail(owner: String, name: String)
}