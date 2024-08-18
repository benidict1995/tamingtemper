package com.benidict.domain.utilities

import android.content.Context
import java.io.File
import java.io.IOException

@Throws(IOException::class)
fun getFileFromAssets(source: String, context: Context): File =
    File(context.cacheDir, source)
        .also {
            if (!it.exists()) {
                it.outputStream().use { cache ->
                    context.assets.open(source).use { inputStream ->
                        inputStream.copyTo(cache)
                    }
                }
            }
        }