package net.dean.jraw.models

abstract class RedditObject(val kind: String) {
    override fun toString(): String {
        return "RedditObject(kind='$kind')"
    }
}
