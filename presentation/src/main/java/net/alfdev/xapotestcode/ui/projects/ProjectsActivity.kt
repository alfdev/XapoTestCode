package net.alfdev.xapotestcode.ui.projects

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_projects.*
import net.alfdev.xapotestcode.R
import net.alfdev.xapotestcode.models.ProjectModel
import net.alfdev.xapotestcode.ui.BaseActivity
import net.alfdev.xapotestcode.ui.EXTRA_PROJECT_NAME
import net.alfdev.xapotestcode.ui.EXTRA_PROJECT_OWNER
import net.alfdev.xapotestcode.ui.project.ProjectDetailActivity
import javax.inject.Inject

class ProjectsActivity : BaseActivity(), ProjectsView, ProjectsAdapter.OnProjectClieckedListener{

    @Inject
    lateinit var presenter: ProjectsPresenter

    private val adapter = ProjectsAdapter(this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_projects)

        // setup toolbar
        setSupportActionBar(toolbar)

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
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showProjects(items: List<ProjectModel>, forceUpdate: Boolean) {
        adapter.items = if (forceUpdate) null else items
    }

    override fun showProjectDetailUi(owner: String, name: String) {
        val intent = Intent(this, ProjectDetailActivity::class.java)

        // put extras
        intent.putExtra(EXTRA_PROJECT_OWNER, owner)
        intent.putExtra(EXTRA_PROJECT_NAME, name)

        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
    }

    override fun onProjectClicked(project: ProjectModel) {
        presenter.openProjectDetail(project.owner.login, project.name)
    }
}
