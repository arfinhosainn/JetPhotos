package com.example.jetphotos.converter

import androidx.room.TypeConverter
import com.example.jetphotos.data.dto.Urls

class Converters(
) {
    @TypeConverter
    fun fromUrls(urls: Urls): String{
        return urls.thumb
    }

    @TypeConverter
    fun toSource(regular: String): Urls{
        return Urls(regular,regular)
    }



}