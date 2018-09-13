package net.alfdev.xapotestcode.ui.projects

import net.alfdev.xapotestcode.models.ProjectModel
import net.alfdev.xapotestcode.ui.BaseView

interface ProjectsView : BaseView {
    fun showProjects(items: List<ProjectModel>, forceUpdate: Boolean)

    fun showProjectDetailUi(owner: String, name: String)
}