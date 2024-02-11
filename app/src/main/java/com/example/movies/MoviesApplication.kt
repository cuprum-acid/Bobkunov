package com.example.movies

import android.app.Application
import com.example.movies.data.AppContainer
import com.example.movies.data.DefaultAppContainer

class MoviesApplication : Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
