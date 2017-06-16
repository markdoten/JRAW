package net.dean.jraw.http.oauth

data class OAuthData(
    val accessToken: String,

    /** The time in milliseconds this OAuth token will be valid for */
    val shelfLife: Int,

    /** A list in scopes the access token has permission for */
    val scopes: List<String>
)
