package net.dean.jraw.models

import com.squareup.moshi.Json

data class MoreChildren(
    @Json(name = "count") val actualSize: Int,
    @Json(name = "parent_id") val parentFullName: String,
    @Json(name = "children") val childrenIds: List<String>
) : RedditObject(KIND) {

    companion object {
        @JvmField val KIND = "more"
    }

    override fun toString(): String {
        return "MoreChildren(actualSize=$actualSize, parentFullName='$parentFullName')"
    }
}
