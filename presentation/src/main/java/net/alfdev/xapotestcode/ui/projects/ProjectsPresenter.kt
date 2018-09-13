package net.alfdev.xapotestcode.ui.projects

import net.alfdev.xapotestcode.ui.BasePresenter

interface ProjectsPresenter : BasePresenter {
    fun loadProjects(forceUpdate: Boolean)

    fun openProjectDetail(owner: String, name: String)
}