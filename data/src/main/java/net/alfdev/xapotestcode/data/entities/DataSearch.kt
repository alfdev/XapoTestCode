package net.alfdev.xapotestcode.data.entities

import com.google.gson.annotations.SerializedName

data class DataSearch (
        @SerializedName("search")
        val search: Search
)