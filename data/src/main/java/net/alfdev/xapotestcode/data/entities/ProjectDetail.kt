package net.alfdev.xapotestcode.data.entities

import com.google.gson.annotations.SerializedName
import java.util.*

data class ProjectDetail (
        val id: String,
        val nameWithOwner: String,
        val name: String,
        val description: String,
        val url: String,
        val updatedAt: Date,
        val forkCount: Int,
        val owner: Owner,
        val issue: SubItemsCount?,
        val pullRequests: SubItemsCount?,
        val stargazers: SubItemsCount?,
        val watchers: SubItemsCount?,
        val releases: SubItemsCount?,
        val commitComments: SubItemsCount?,

        @SerializedName("repositoryTopics")
        val topics: NestedData<Topic>,

        @SerializedName("languages")
        val languages: NestedData<Language>
)