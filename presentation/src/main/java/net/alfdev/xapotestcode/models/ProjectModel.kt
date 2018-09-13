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
    val model = ProjectModel (
            id = this.id,
            name = this.name,
            nameWithOwner = this.nameWithOwner,
            description = this.description,
            url = this.url,
            updatedAt = this.updatedAt,
            forkCount = this.forkCount,
            owner = this.owner.toModel(),
            languages = this.languages?.nodes?.map { it.toModel() },
            starsCount = this.stargazers?.totalCount!!,
            topics = this.topics?.nodes?.map { it.name }
    )

    return model
}