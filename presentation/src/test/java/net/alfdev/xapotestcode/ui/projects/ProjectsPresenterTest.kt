package net.alfdev.xapotestcode.ui.projects

import io.reactivex.Observable
import net.alfdev.xapotestcode.R
import net.alfdev.xapotestcode.data.entities.*
import net.alfdev.xapotestcode.data.repository.ProjectRepository
import net.alfdev.xapotestcode.models.OwnerModel
import net.alfdev.xapotestcode.models.ProjectModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class ProjectsPresenterTest {

    @Mock
    lateinit var repository: ProjectRepository

    @Mock
    lateinit var projectsView: ProjectsView

    lateinit var presenter: ProjectsPresenter
    lateinit var models: MutableList<Project>
    lateinit var fakeSearchResult: SearchResult

    @Before
    fun setup() {
        presenter = ProjectsPresenterImpl(projectsView, repository)

        // initialize models list
        models = IntRange(1, 10).map {
            Project(
                    id = "ID_$it",
                    name = "Project $it",
                    nameWithOwner = "Project $it / Owner $it",
                    description = "Project description $it",
                    url = "",
                    updatedAt = Date(),
                    forkCount = it,
                    owner = Owner("Owner $it", ""),
                    languages = null,
                    stargazers = SubItemsCount(it),
                    topics = null

            )
        }.toMutableList()

        fakeSearchResult = SearchResult(Data<Search>(
                Search(10, PageInfo("", ""), models)
            ),
                errors = null
        )

        `when`(projectsView.getContext().getString(R.string.project_list_query))
                .thenReturn("10, after:null")
    }

    @Test
    fun loadProjectsList() {
        `when`(repository.getProjects("", 10, null))
                .thenReturn(Observable.just(fakeSearchResult))

        presenter.loadProjects(true)

        verify(projectsView).showLoading()
    }
}