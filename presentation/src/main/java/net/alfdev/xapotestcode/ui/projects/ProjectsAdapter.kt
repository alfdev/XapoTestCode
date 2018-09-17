package net.alfdev.xapotestcode.ui.projects

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.support.v7.widget.DrawableUtils
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.project_list_item.view.*
import net.alfdev.xapotestcode.R
import net.alfdev.xapotestcode.models.ProjectModel
import net.alfdev.xapotestcode.ui.DrawablesUtility
import java.text.DateFormat
import java.util.*


class ProjectsAdapter(private val context: Context, private val listener: OnProjectClieckedListener) : RecyclerView.Adapter<ProjectsAdapter.ProjectViewHolder>() {
    private val formatter: DateFormat by lazy {
        DateFormat.getDateInstance(DateFormat.MEDIUM)
    }

    private var cached: MutableList<ProjectModel>? = mutableListOf()

    var items: List<ProjectModel>? = null
        set(value) {
            if (value == null) {
                cached?.clear()
                notifyDataSetChanged()
            } else {
                val positionStart = cached?.size ?: 0
                val itemCount = value?.size ?: 0

                if (cached?.size ?: 0 == 0) {
                    cached = value as MutableList<ProjectModel>
                } else {
                    cached?.addAll(value as MutableList)
                }

                notifyItemRangeInserted(positionStart, itemCount)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        return ProjectViewHolder(LayoutInflater.from(context).inflate(R.layout.project_list_item, parent, false))
    }

    override fun getItemCount(): Int = cached?.size ?: 0

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        val item = cached?.get(position)

        holder.ownerWithName.text = item?.nameWithOwner
        holder.description.text = item?.description
        holder.topics.text = item?.topics?.joinToString(separator = ", ") ?: ""
        holder.starsCount.text = item?.starsCount?.toString() ?: "0"
        holder.updatedAt.text = String.format(Locale.getDefault(), context.getString(R.string.updated_on), formatter.format(item?.updatedAt))

        holder.language.text = ""
        holder.language.visibility = View.INVISIBLE

        val language = item?.languages?.firstOrNull()

        if (language != null) {
            // get and colorize circle drawable
            val drawable = DrawablesUtility.colorizeDrawable(
                    context.getDrawable(R.drawable.language_badge)!!,
                    language.color
            )

            holder.language.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)

            holder.language.text = item?.languages?.first()?.name
            holder.language.visibility = View.VISIBLE
        }

        holder.itemView?.setOnClickListener { listener.onProjectClicked(item!!) }
    }

    // inner classes and interfaces
    class ProjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ownerWithName = view.textViewOwnerWithName
        val description = view.textViewDescription
        val topics = view.textViewTopics
        val language = view.textViewLanguage
        val updatedAt = view.textViewUpdatedAt
        val starsCount = view.textViewStarsCount
    }

    interface OnProjectClieckedListener {
        fun onProjectClicked(project: ProjectModel)
    }
}