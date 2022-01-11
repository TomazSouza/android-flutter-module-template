package com.example.android_flutter_module_template

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterActivityLaunchConfigs
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.embedding.engine.loader.FlutterApplicationInfo
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity : AppCompatActivity() {

    var flutterEngine: FlutterEngine? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStartDart = findViewById<Button>(R.id.button)

        btnStartDart.setOnClickListener {
            startActivity(
                MainActivity2
                    .withCachedEngine(MyApplication.ENGINE_ID)
                    .destroyEngineWithActivity(true)
                    .build(this)
            )
        }

    }

    override fun onResume() {
        super.onResume()
        Thread {
            runOnUiThread(Runnable {
                Thread.sleep(1000)
                if (flutterEngine != null) {
                    FlutterEngineCache.getInstance().get(MyApplication.ENGINE_ID)?.destroy()
                    FlutterEngineCache.getInstance().remove(MyApplication.ENGINE_ID)
                }
                // Instantiate a FlutterEngine.
                flutterEngine = FlutterEngine(this)

                // Start executing Dart code to pre-warm the FlutterEngine.
                flutterEngine?.dartExecutor?.executeDartEntrypoint(
                    DartExecutor.DartEntrypoint.createDefault()
                )

                // Cache the FlutterEngine to be used by FlutterActivity.
                FlutterEngineCache
                    .getInstance()
                    .put(MyApplication.ENGINE_ID, flutterEngine)
            })
        }.start()
    }
    override fun onRestart() {
        super.onRestart()
    }

}