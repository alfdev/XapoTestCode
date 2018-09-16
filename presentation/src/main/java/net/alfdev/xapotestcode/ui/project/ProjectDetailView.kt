package net.alfdev.xapotestcode.ui.project

import net.alfdev.xapotestcode.models.ProjectDetailModel
import net.alfdev.xapotestcode.ui.BaseView

interface ProjectDetailView : BaseView {
    fun showDetail(item: ProjectDetailModel)

    fun showProjectNotFound()
}