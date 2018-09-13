package net.alfdev.xapotestcode.data.repository

import java.util.Locale

object RepositoryUtility {
    fun formatQueryStringForProjects(value: String, pageSize: Int, nextPageToken: String?)
            = String.format(Locale.getDefault(), value, pageSize, nextPageToken)

    fun formatQueryStringForProjectDetail(value: String, owner: String, name: String)
            = String.format(Locale.getDefault(), value, owner, name)
}