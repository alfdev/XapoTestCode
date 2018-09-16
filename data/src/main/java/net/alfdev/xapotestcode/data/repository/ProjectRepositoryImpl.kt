package net.alfdev.xapotestcode.data.repository

import io.reactivex.Observable
import net.alfdev.xapotestcode.data.entities.DetailResult
import net.alfdev.xapotestcode.data.entities.SearchResult
import net.alfdev.xapotestcode.data.network.Query
import net.alfdev.xapotestcode.data.network.ServiceApi

class ProjectRepositoryImpl constructor(private val api: ServiceApi) : ProjectRepository {
    override fun getProjects(pageSize: Int, nextPageToken: String?): Observable<SearchResult> {
        val query = RepositoryUtility.formatQueryStringForProjects(
                pageSize = pageSize, nextPageToken = nextPageToken
        )

        return api.getProjects(query = Query(query))
    }

    override fun getProject(owner: String, name: String): Observable<DetailResult> {
        val query = RepositoryUtility.formatQueryStringForProjectDetail(
                owner = owner, name = name
                )

        return api.getProjectDetail(Query(query))
    }
}