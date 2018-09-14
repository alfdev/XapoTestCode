package net.alfdev.xapotestcode.data.entities

import com.google.gson.annotations.SerializedName

data class Search (
        @SerializedName("repositoryCount")
        val repositoryCount: Int,
        val pageInfo: PageInfo,
        val nodes: List<Project>
)