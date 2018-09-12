package net.alfdev.xapotestcode.data.entities

import com.google.gson.annotations.SerializedName
import java.util.*

data class Project (
        val id: String,
        val nameWithOwner: String,
        val name: String,
        val url: String,
        val updatedAt: Date,
        val forkCount: Int,

        val stargazers: SubItemsCount,

        @SerializedName("repositoryTopics")
        val topics: NestedData<Topic>,

        @SerializedName("languages")
        val languages: NestedData<Language>
)