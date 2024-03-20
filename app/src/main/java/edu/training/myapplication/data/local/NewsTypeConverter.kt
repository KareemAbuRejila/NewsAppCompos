package edu.training.myapplication.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import edu.training.myapplication.domain.model.Source

@ProvidedTypeConverter
class NewsTypeConverter {

    @TypeConverter
    fun sourceToString(source: Source): String = "${source.id}, ${source.name}"

    @TypeConverter
    fun stringToSource(source: String): Source = source.split(",").let { sourceArray->
        Source(sourceArray[0],sourceArray[1])
    }
}