package com.example.avito2.data.local

import android.content.Context
import com.example.avito2.data.local.dto.AdDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString

class JsonAdsDataSource(
    private val context: Context,
){
    private val mutex = Mutex()
    private var cachedAds: List<AdDto>? = null

    suspend fun getAds() : List<AdDto>{
        mutex.lock()
        try{
            cachedAds?.let { ads ->
                return ads
            }

            val ads = withContext(Dispatchers.IO){
                val jsonString = readJson()
                Json.decodeFromString<List<AdDto>>(jsonString)
            }
            cachedAds = ads
            return ads
        }
        finally {
            mutex.unlock()
        }
    }
    private fun readJson() : String{
        val file = context.assets.open("ads.json")
        return file.bufferedReader().use{
            reader -> reader.readText()
        }
    }
}