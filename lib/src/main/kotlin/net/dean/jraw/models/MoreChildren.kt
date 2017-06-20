package net.dean.jraw.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class MoreChildren(
    @JsonProperty("count") val actualSize: Int,
    @JsonProperty("parent_id") val parentFullName: String,
    @JsonProperty("children") val childrenIds: List<String>
) : RedditObject(KIND) {

    companion object {
        @JvmField val KIND = "more"
    }

    override fun toString(): String {
        return "MoreChildren(actualSize=$actualSize, parentFullName='$parentFullName')"
    }
}