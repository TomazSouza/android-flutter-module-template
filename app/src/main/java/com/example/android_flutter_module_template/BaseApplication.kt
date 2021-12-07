package com.example.android_flutter_module_template

import android.app.Application

class BaseApplication : Application() {

    lateinit var mFlutterEngineManager: FlutterEngineManager

    override fun onCreate() {
        super.onCreate()

        mFlutterEngineManager = FlutterEngineManager(this)
        mFlutterEngineManager.setEngineId("my_engine_id")
    }

}