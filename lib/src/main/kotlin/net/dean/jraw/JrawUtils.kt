package net.dean.jraw

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import net.dean.jraw.databind.OAuthDataAdapter
import net.dean.jraw.databind.RedditModelJsonAdapter
import net.dean.jraw.databind.UnixDateAdapter
import okio.Okio
import java.io.InputStream

object JrawUtils {
    @JvmStatic val moshi: Moshi = Moshi.Builder()
        .add(OAuthDataAdapter())
        .add(UnixDateAdapter())
        .add(KotlinJsonAdapterFactory())
        // This one has to be last for whatever reason
        .add(RedditModelJsonAdapter.Factory())
        .build()

//    @JvmStatic fun parseJson(str: String): Map<String, Any> = moshi.adapter<JsonNode>(jsonNodeType).fromJson(str)!!
    @JvmStatic inline fun <reified T> parseJson(str: String): T = moshi.adapter<T>(T::class.java).fromJson(str)!!
    @JvmStatic inline fun <reified T> parseJson(stream: InputStream): T = moshi.adapter<T>(T::class.java).fromJson(Okio.buffer(Okio.source(stream)))!!

    @JvmStatic fun parseUrlEncoded(str: String): Map<String, String> =
        // Neat little one-liner. This function splits the query string by '&', then maps each value to a Pair of
        // Strings, converts it to a typed array, and uses the spread operator ('*') to call mapOf()
        mapOf(*str.split("&").map { val parts = it.split("="); parts[0] to parts[1] }.toTypedArray())
}
