package com.manoilo.giphy.utils

import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class AppExecutors(
    private val diskIO: Executor
) {
    @Inject
    constructor() : this(
        Executors.newSingleThreadExecutor()
    )

    fun diskIO(): Executor {
        return diskIO
    }
}
