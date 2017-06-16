package net.dean.jraw.databind

import com.squareup.moshi.FromJson
import net.dean.jraw.http.oauth.OAuthData

class OAuthDataAdapter {
    @FromJson fun fromJson(json: OAuthDataJson): OAuthData {
        return OAuthData(
            accessToken = json.access_token,
            shelfLife = json.expires_in * 1000,
            scopes = json.scope.split(",")
        )
    }

    data class OAuthDataJson(
        val access_token: String,
        val expires_in: Int,
        val scope: String,
        val refresh_token: String?
    )
}
