package net.alfdev.xapotestcode.ui.projects

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import net.alfdev.xapotestcode.R
import net.alfdev.xapotestcode.data.repository.ProjectRepository
import net.alfdev.xapotestcode.models.toModel
import javax.inject.Inject

class ProjectsPresenterImpl @Inject constructor(
        private val view: ProjectsView,
        private val repository: ProjectRepository
    ): ProjectsPresenter {

    private var disposable: Disposable? = null
    private var nextPageToken: String? = null

    override fun onViewCreated() {
        loadProjects(false)
    }

    override fun onViewDestroyed() {
        disposable?.dispose()
    }

    override fun loadProjects(forceUpdate: Boolean) {
        view.showLoading()

        if (forceUpdate) {
            nextPageToken = null
        }

        disposable = repository.getProjects(
                    view.getContext().getString(R.string.project_list_query),
                    pageSize = 10,
                    nextPageToken = nextPageToken)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate { view.hideLoading() }
                .subscribe(
                        { result ->
                            run {
                                if (result.errors != null) {
                                    view.showError(result.errors?.first()?.message!!)
                                } else {
                                    view.showProjects(result.dataSearch.search.nodes.map { it.toModel() }, forceUpdate)
                                }
                            }
                        },
                        { view.showError(R.string.generic_error_message)}
                )
    }

    override fun openProjectDetail(owner: String, name: String) {
        view.showProjectDetailUi(owner, name)
    }
}