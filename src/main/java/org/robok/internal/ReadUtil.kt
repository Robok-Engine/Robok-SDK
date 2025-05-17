@file:JvmName("__INTERNAL_READ_UTIL__")

package org.robok.internal

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

fun Context.readTextFromAndroidAssets(dir: String): String {
    val builder = StringBuilder()
    val reader = BufferedReader(InputStreamReader(assets.open(dir)))
    var line: String?
    while (reader.readLine().also { line = it } != null) {
        builder.append(line).append("\n")
    }
    reader.close()
    return builder.toString()
}
