package net.alfdev.xapotestcode.models

import net.alfdev.xapotestcode.data.entities.ProjectDetail
import java.util.Date

data class ProjectDetailModel (
        val id: String,
        val name: String,
        val nameWithOwner: String,
        val description: String,
        val url: String,
        val updatedAt: Date,
        val forkCount: Int,
        val owner: OwnerModel,
        val starsCount: Int,
        val topics: List<String>?,
        val languages: List<LanguageModel>?,
        val issue: Int,
        val pullRequests: Int,
        val watchers: Int,
        val releases: Int,
        val commitComments: Int
)

fun ProjectDetail.toModel(): ProjectDetailModel {
    return ProjectDetailModel (
            id = id,
            name = name,
            nameWithOwner = nameWithOwner,
            description = description,
            url = url,
            updatedAt = updatedAt,
            forkCount = forkCount,
            owner = owner.toModel(),
            languages = languages?.nodes?.map { it.toModel() },
            starsCount = stargazers?.totalCount ?: 0,
            topics = topics?.nodes?.map { it.name.replace("/topics/", "") },
            issue = this.issue?.totalCount ?: 0,
            pullRequests = this.pullRequests?.totalCount ?: 0,
            watchers = this.watchers?.totalCount ?: 0,
            releases = this.releases?.totalCount ?: 0,
            commitComments = this.commitComments?.totalCount ?: 0
    )
}