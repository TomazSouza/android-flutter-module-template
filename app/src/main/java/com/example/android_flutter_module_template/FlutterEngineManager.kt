package com.example.android_flutter_module_template

import android.content.Context
import android.content.Intent
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

class FlutterEngineManager constructor(context: Context) {

    private val flutterEngine: FlutterEngine = FlutterEngine(context)
    private val flutterEngineCache: FlutterEngineCache

    init {
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )
        flutterEngineCache = FlutterEngineCache.getInstance()
    }

    fun setEngineId(engineId: String) {
        flutterEngineCache.put(engineId, flutterEngine)
    }

    companion object {
        @JvmStatic
        fun getIntent(engineId: String, context: Context): Intent {
            return FlutterActivity
                .withCachedEngine(engineId).build(context)
        }
    }

}