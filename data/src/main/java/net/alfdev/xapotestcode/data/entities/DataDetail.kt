package net.alfdev.xapotestcode.data.entities

import com.google.gson.annotations.SerializedName

data class DataDetail (
        @SerializedName("repository")
        val repository: ProjectDetail
)