package net.alfdev.xapotestcode.data.entities

import com.google.gson.annotations.SerializedName

data class SearchResult (
        @SerializedName("data")
        val dataSearch: DataSearch,
        val errors: List<Error>?
)