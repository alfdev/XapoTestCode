package net.alfdev.xapotestcode.data.entities

import com.google.gson.annotations.SerializedName

data class Topic(
        @SerializedName("resourcePath")
        val name: String
)