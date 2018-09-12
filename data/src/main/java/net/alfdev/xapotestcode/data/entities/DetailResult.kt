package net.alfdev.xapotestcode.data.entities

data class DetailResult (
        val data: Data<ProjectDetail>,
        val errors: List<Error>
)