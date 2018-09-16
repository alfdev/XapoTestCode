package net.alfdev.xapotestcode.ui.project

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import net.alfdev.xapotestcode.R
import net.alfdev.xapotestcode.data.repository.ProjectRepository
import net.alfdev.xapotestcode.models.toModel
import javax.inject.Inject

class ProjectDetailPresenterImpl @Inject constructor(
        private val view: ProjectDetailView,
        private val repository: ProjectRepository
    ) : ProjectDetailPresenter {

    private var disposable: Disposable? = null

    override fun onViewCreated() {
        super.onViewCreated()
    }

    override fun onViewDestroyed() {
        disposable?.dispose()
    }

    override fun loadDetail(owner: String, name: String) {
        if (owner.isNullOrEmpty() || name.isNullOrEmpty()) {
            view.showProjectNotFound()

            return
        }

        view.showLoading()

        disposable = repository.getProject(
                    owner = owner,
                    name = name)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate { view.hideLoading() }
                .subscribe(
                        {
                            if (it.errors != null) {
                                view.showError(it.errors?.first()?.message!!)
                            } else {
                                view.showDetail(it.data.repository.toModel())
                            }
                        },
                        { view.showError(R.string.generic_error_message)}
                )
    }
}