/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.saputroekosulistiyo.gempa15.network

import com.saputroekosulistiyo.gempa15.model.MarsPhoto
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

    private const val BASE_URL =
        "https://data.bmkg.go.id/"

// Gunakan builder Retrofit untuk membangun objek retrofit dengan menggunakan konverter kotlinx.serialization
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

// Objek layanan Retrofit untuk membuat panggilan API
    interface GempaApiService {
        @GET("DataMKG/TEWS/gempaterkini.json")
        suspend fun getgempaterkini.json(): List<MarsPhoto>
    }

// Objek Api publik yang menyediakan layanan Retrofit yang diinisialisasi secara lazy
    object MarsApi {
        val retrofitService: GempaApiService by lazy {
            retrofit.create(GempaApiService::class.java)
        }
}
