package net.alfdev.xapotestcode.models

import net.alfdev.xapotestcode.data.entities.Owner

data class OwnerModel (
        val login: String,
        val avatarUrl: String
)

fun Owner.toModel(): OwnerModel {
    return OwnerModel(this.login, this.avatarUrl)
}