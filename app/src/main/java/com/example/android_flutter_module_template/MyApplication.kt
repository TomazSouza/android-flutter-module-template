package com.example.android_flutter_module_template

import android.app.Application
import android.content.Context
import android.util.Log
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugins.GeneratedPluginRegistrant

class MyApplication : Application() {

    lateinit var flutterEngine : FlutterEngine

    companion object {
        const val ENGINE_ID = "my_engine_id"
    }

    override fun onCreate() {
        super.onCreate()

        //init(this)

    }

    fun init(context: Context) {
        flutterEngine = FlutterEngine(context)
        flutterEngine.dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())
        
        FlutterEngineCache
            .getInstance()
            .put(ENGINE_ID, flutterEngine)

       // GeneratedPluginRegistrant.registerWith(flutterEngine)

    }

}