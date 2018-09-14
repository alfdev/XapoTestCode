package net.alfdev.xapotestcode.ui.project

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.widget.Toast
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_project_detail.*
import net.alfdev.xapotestcode.R
import net.alfdev.xapotestcode.models.ProjectDetailModel
import net.alfdev.xapotestcode.ui.BaseActivity
import net.alfdev.xapotestcode.ui.EXTRA_PROJECT_NAME
import net.alfdev.xapotestcode.ui.EXTRA_PROJECT_OWNER
import java.text.DateFormat
import java.util.*
import javax.inject.Inject

class ProjectDetailActivity : BaseActivity(), ProjectDetailView {
    private val formatter: DateFormat by lazy {
        DateFormat.getDateInstance(DateFormat.LONG)
    }

    @Inject
    lateinit var presenter: ProjectDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_detail)

        // setup toolbar
        setSupportActionBar(toolbar)

        hideLoading()

        // retrieves extras value needed to load project detail
        val owner = intent.extras.getString(EXTRA_PROJECT_OWNER, null)
        val name = intent.extras.getString(EXTRA_PROJECT_NAME, null)
        presenter.loadDetail(owner, name)
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showDetail(item: ProjectDetailModel) {
        supportActionBar?.title = item.nameWithOwner

        textViewDescription.text = item.description
        textViewTopics.text = item?.topics?.joinToString(separator = ", ") ?: ""
        textViewUpdateAt.text = String.format(Locale.getDefault(), getString(R.string.updated_on), formatter.format(item?.updatedAt))

        textViewLanguage.text = ""
        textViewLanguage.visibility = View.INVISIBLE

        val language = item?.languages?.firstOrNull()

        if (language != null) {
            // get and colorize circle drawable
            val drawable = getDrawable(R.drawable.language_badge)
            drawable.setColorFilter(Color.parseColor(language.color), PorterDuff.Mode.ADD)

            textViewLanguage.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)

            textViewLanguage.text = item?.languages?.first()?.name
            textViewLanguage.visibility = View.VISIBLE
        }
    }

    override fun showProjectNotFound() {
        Toast.makeText(this, getString(R.string.project_not_found_error_message), Toast.LENGTH_LONG).show()
    }
}
