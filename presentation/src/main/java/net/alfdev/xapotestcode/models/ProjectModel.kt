package net.alfdev.xapotestcode.models

import net.alfdev.xapotestcode.data.entities.Project
import java.util.Date

data class ProjectModel (
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
        val languages: List<LanguageModel>?
)

fun Project.toModel(): ProjectModel {
    return ProjectModel (
            id = id,
            name = name,
            nameWithOwner = nameWithOwner,
            description = description,
            url = url,
            updatedAt = updatedAt,
            forkCount = forkCount,
            owner = owner.toModel(),
            languages = languages?.nodes?.map { it.toModel() },
            starsCount = stargazers?.totalCount!!,
            topics = topics?.nodes?.map { it.name.replace("/topics/", "") }
    )
}