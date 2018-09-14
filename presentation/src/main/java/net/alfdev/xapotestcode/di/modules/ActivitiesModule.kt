package net.alfdev.xapotestcode.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.alfdev.xapotestcode.di.scopes.ProjectsScope
import net.alfdev.xapotestcode.ui.project.ProjectDetailActivity
import net.alfdev.xapotestcode.ui.projects.ProjectsActivity

@Module
abstract class ActivitiesModule {

    @ProjectsScope
    @ContributesAndroidInjector(modules = [RepositoriesModule::class, ProjectsModule::class])
    abstract fun bindProjectsActivity(): ProjectsActivity

    @ProjectsScope
    @ContributesAndroidInjector(modules = [RepositoriesModule::class, ProjectsModule::class])
    abstract fun bindProjectDetailActivity(): ProjectDetailActivity
}