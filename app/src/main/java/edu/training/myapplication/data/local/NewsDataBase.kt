package edu.training.myapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import edu.training.myapplication.domain.model.Article

@Database(entities = [Article::class], version = 1)
@TypeConverters(NewsTypeConverter::class)
abstract class NewsDataBase: RoomDatabase() {

    abstract val newsDao: NewsDao
}