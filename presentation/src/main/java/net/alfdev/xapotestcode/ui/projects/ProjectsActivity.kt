package net.alfdev.xapotestcode.ui.projects

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_projects.*
import net.alfdev.xapotestcode.R
import net.alfdev.xapotestcode.models.ProjectModel
import net.alfdev.xapotestcode.ui.BaseActivity
import javax.inject.Inject

class ProjectsActivity : BaseActivity(), ProjectsView, ProjectsAdapter.OnProjectClieckedListener{

    @Inject
    lateinit var presenter: ProjectsPresenter

    private val adapter = ProjectsAdapter(this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_projects)

        // init ui elements
        swipeRefreshLayout.setOnRefreshListener { presenter.loadProjects(true) }

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        presenter.onViewCreated()
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.onViewDestroyed()
    }

    override fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showError(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProjects(items: List<ProjectModel>, forceUpdate: Boolean) {
        adapter.items = if (forceUpdate) null else items
    }

    override fun showProjectDetailUi(owner: String, name: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProjectClicked(project: ProjectModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
