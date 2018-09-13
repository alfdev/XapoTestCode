package net.alfdev.xapotestcode.data.entities

data class SearchResult (
        val data: Data<Search>,
        val errors: List<Error>?
)