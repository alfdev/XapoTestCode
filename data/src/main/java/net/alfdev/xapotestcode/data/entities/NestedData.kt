package net.alfdev.xapotestcode.data.entities

data class NestedData<T> (
        val totalCount: Int,
        val nodes: List<T>
)