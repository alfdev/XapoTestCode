package net.alfdev.xapotestcode.di.modules

import dagger.Module
import dagger.Provides
import net.alfdev.xapotestcode.data.repository.ProjectRepository
import net.alfdev.xapotestcode.di.scopes.ProjectsScope
import net.alfdev.xapotestcode.ui.project.ProjectDetailActivity
import net.alfdev.xapotestcode.ui.project.ProjectDetailPresenter
import net.alfdev.xapotestcode.ui.project.ProjectDetailPresenterImpl
import net.alfdev.xapotestcode.ui.project.ProjectDetailView
import net.alfdev.xapotestcode.ui.projects.ProjectsActivity
import net.alfdev.xapotestcode.ui.projects.ProjectsPresenter
import net.alfdev.xapotestcode.ui.projects.ProjectsPresenterImpl
import net.alfdev.xapotestcode.ui.projects.ProjectsView

@Module
class ProjectsModule {

    @Provides
    @ProjectsScope
    fun providesProjectsView(activity: ProjectsActivity): ProjectsView = activity

    @Provides
    @ProjectsScope
    fun providesProjectsPresenter(view: ProjectsView, repository: ProjectRepository): ProjectsPresenter {
        return ProjectsPresenterImpl(view, repository)
    }

    @Provides
    @ProjectsScope
    fun providesProjectDetailView(activity: ProjectDetailActivity): ProjectDetailView = activity

    @Provides
    @ProjectsScope
    fun providesProjectDetailPresenter(view: ProjectDetailView, repository: ProjectRepository): ProjectDetailPresenter {
        return ProjectDetailPresenterImpl(view, repository)
    }
}