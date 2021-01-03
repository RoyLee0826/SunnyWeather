package com.sunnyweather.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context


class SunnyWeatherApplication : Application() {
    companion object {
        const val TOKEN ="SR8WtzXGlpj7TNQ2" //配置令牌值
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext //全局获取Context
    }
}