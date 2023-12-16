package ru.netology.MapsMarker.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PointDao {
    @Query("SELECT * FROM PointMapEntity ORDER BY id DESC")
    fun getAll(): Flow<List<PointMapEntity>>

    @Query("SELECT * FROM PointMapEntity WHERE id = :id")
    suspend fun getPointById(id: Int): PointMapEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(point: PointMapEntity)

    @Query("DELETE FROM PointMapEntity WHERE id = :id")
    suspend fun removeById(id: Int)

    @Query("UPDATE PointMapEntity SET description = :description WHERE id = :id")
    suspend fun updateContentById(id: Long, description: String)
}