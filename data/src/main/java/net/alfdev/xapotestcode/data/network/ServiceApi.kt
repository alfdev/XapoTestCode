package net.alfdev.xapotestcode.data.network

import io.reactivex.Observable
import net.alfdev.xapotestcode.data.entities.DetailResult
import net.alfdev.xapotestcode.data.entities.SearchResult
import retrofit2.http.Body
import retrofit2.http.POST

interface ServiceApi {
    @POST("graphql")
    fun getProjects(@Body query: Query): Observable<SearchResult>

    @POST("graphql")
    fun getProjectDetail(@Body query: Query): Observable<DetailResult>
}