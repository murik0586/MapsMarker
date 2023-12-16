package ru.netology.MapsMarker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.netology.MapsMarker.dao.PointDao
import ru.netology.MapsMarker.dao.PointMapEntity

@Database(entities = [PointMapEntity::class], version = 1)
abstract class AppDb : RoomDatabase() {
    abstract fun pointDao(): PointDao

    companion object {
        @Volatile
        private var instance: AppDb? = null

        fun getInstance(context: Context): AppDb {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDb::class.java, "app.db")
                .allowMainThreadQueries()
                .build()
    }
}