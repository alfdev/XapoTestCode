package net.alfdev.xapotestcode.data.entities

data class Search (
        val repositoryCount: Int,
        val pageInfo: PageInfo,
        val nodes: List<Project>
)