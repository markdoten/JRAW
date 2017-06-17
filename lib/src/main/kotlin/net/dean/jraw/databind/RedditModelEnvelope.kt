package net.dean.jraw.databind

data class RedditModelEnvelope<out T>(val kind: String, val data: T)
