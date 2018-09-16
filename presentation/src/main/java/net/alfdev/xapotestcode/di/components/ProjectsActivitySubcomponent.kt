package net.alfdev.xapotestcode.di.components

import dagger.Subcomponent
import net.alfdev.xapotestcode.di.modules.ProjectsModule
import net.alfdev.xapotestcode.di.modules.RepositoriesModule
import net.alfdev.xapotestcode.di.scopes.ProjectsScope
import net.alfdev.xapotestcode.ui.project.ProjectDetailActivity
import net.alfdev.xapotestcode.ui.projects.ProjectsActivity

@ProjectsScope
@Subcomponent(modules = [RepositoriesModule::class, ProjectsModule::class])
interface ProjectsActivitySubcomponent {
    fun inject(activity: ProjectsActivity)
    fun inject(activity: ProjectDetailActivity)

    @Subcomponent.Builder
    interface Builder {
        fun build(): ProjectsActivitySubcomponent

        fun repositoriesModule(module: RepositoriesModule): Builder
        fun projectsModule(module: ProjectsModule): Builder
    }
}