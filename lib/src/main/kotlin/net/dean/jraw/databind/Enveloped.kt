package net.dean.jraw.databind

import com.squareup.moshi.JsonQualifier

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
internal annotation class Enveloped
