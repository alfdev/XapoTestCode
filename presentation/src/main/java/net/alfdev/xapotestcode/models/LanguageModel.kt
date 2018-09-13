package net.alfdev.xapotestcode.models

import net.alfdev.xapotestcode.data.entities.Language

data class LanguageModel (
        val name: String,
        val color: String
)

fun Language.toModel(): LanguageModel {
    return LanguageModel(
            name = this.name,
            color = this.color
    )
}