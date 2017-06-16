package net.dean.jraw.databind

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.util.*

class UnixDateAdapter {
    @FromJson fun fromJson(time: Long): Date {
        return Date(time * 1000)
    }

    @ToJson fun toJson(d: Date) = d.time / 1000
}
