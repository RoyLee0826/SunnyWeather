package com.sunnyweather.android.logic.network


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


//定义数据源访问入口
object SunnyWeatherNetwork {
    private val placeService=ServiceCreator.create<PlaceService>() //创建PlaceService接口的动态代理对象
    suspend fun searchPlace(query:String)= placeService.searchPlaces(query).await() //发起搜索城市数据请求
    private suspend fun <T> Call<T>.await():T{
        return suspendCoroutine{continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call:Call<T>,response:Response<T>){
                    val body = response.body()
                    if (body!=null) continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("response body is null"))
                }
                override fun onFailure(call: Call<T>,t:Throwable){
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}