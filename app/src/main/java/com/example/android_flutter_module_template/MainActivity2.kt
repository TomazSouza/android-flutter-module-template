package com.example.android_flutter_module_template

import android.util.Log
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity2 : FlutterActivity() {

    companion object {
        fun withCachedEngine(cachedEngineId: String): CachedEngineIntentBuilder {
            return CachedEngineIntentBuilder(MainActivity2::class.java, cachedEngineId)
        }

        fun withNewEngine(): NewEngineIntentBuilder {
            return NewEngineIntentBuilder(MainActivity2::class.java)
        }
    }

    override fun onResume() {
        super.onResume()
        Log.w("wow", "onResume")
    }

    override fun onDestroy() {
        Log.w("wow", "onDestroy")
        super.onDestroy()
    }

    override fun onStop() {
        super.onStop()
        Log.w("wow", "onStop")
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        GeneratedPluginRegistrant.registerWith(flutterEngine)

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, "APP_CHANNEL")
            .setMethodCallHandler { call, result ->
                if (call.method.equals("method")) {
                    result.success(true)
                } else {
                    result.notImplemented()
                }
            }
    }
}