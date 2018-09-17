package net.alfdev.xapotestcode.ui.projects

import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.observers.TestObserver
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import net.alfdev.xapotestcode.R
import net.alfdev.xapotestcode.data.entities.*
import net.alfdev.xapotestcode.data.repository.ProjectRepository
import net.alfdev.xapotestcode.models.OwnerModel
import net.alfdev.xapotestcode.models.ProjectModel
import net.alfdev.xapotestcode.models.toModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
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
    lateinit var fakeSearchResultWithEmptyList: SearchResult
    lateinit var fakeSearchResultWithErrors: SearchResult

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

        fakeSearchResult = SearchResult(
                DataSearch(Search(10, PageInfo("", ""), models)),
                errors = null
        )

        fakeSearchResultWithEmptyList = SearchResult(
                DataSearch(Search(0, PageInfo("", ""), emptyList())),
                errors = null
        )

        // setup fakeSearchResult with errors
        fakeSearchResultWithErrors = SearchResult(
                DataSearch(Search(0, PageInfo("", ""), emptyList())),
                errors = listOf(Error(message = "test"))
        )

        // setup RxJava to execute all the client calls on the same Thread
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun loadProjectsListAndShowIntoView() {
        `when`(repository.getProjects(10, null))
                .thenReturn(Observable.just(fakeSearchResult))

        val observer = TestObserver<SearchResult>()
        repository.getProjects(10, null)
                .subscribe(observer)

        presenter.loadProjects(true)

        val inOrder = Mockito.inOrder(projectsView)
        inOrder.verify(projectsView).showLoading()
        inOrder.verify(projectsView).showProjects(models.map { it.toModel() }, true)
        inOrder.verify(projectsView).hideLoading()
    }

    @Test
    fun loadProjectWithEmptyList() {
        `when`(repository.getProjects(10, null))
                .thenReturn(Observable.just(fakeSearchResultWithEmptyList))

        val observer = TestObserver<SearchResult>()
        repository.getProjects(10, null)
                .subscribe(observer)

        presenter.loadProjects(true)

        val inOrder = Mockito.inOrder(projectsView)
        inOrder.verify(projectsView).showLoading()
        inOrder.verify(projectsView).hideLoading()
    }

    @Test
    fun callLoadProjectsWithError() {
        `when`(repository.getProjects(10, null))
                .thenReturn(Observable.just(fakeSearchResultWithErrors))

        val observer = TestObserver<SearchResult>()
        repository.getProjects(10, null)
                .subscribe(observer)

        presenter.loadProjects(true)

        val inOrder = Mockito.inOrder(projectsView)
        inOrder.verify(projectsView).showLoading()
        inOrder.verify(projectsView).showError(fakeSearchResultWithErrors.errors!!.first().message)
        inOrder.verify(projectsView).hideLoading()
    }
}