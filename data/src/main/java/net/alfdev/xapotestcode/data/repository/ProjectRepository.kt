package net.alfdev.xapotestcode.data.repository

import io.reactivex.Observable
import net.alfdev.xapotestcode.data.entities.DetailResult
import net.alfdev.xapotestcode.data.entities.SearchResult

interface ProjectRepository {
    fun getProjects(queryString: String, pageSize: Int, nextPageToken: String): Observable<SearchResult>

    fun getProject(queryString: String, owner: String, name: String): Observable<DetailResult>
}