package ru.netology.MapsMarker.repository

import kotlinx.coroutines.flow.Flow
import ru.netology.MapsMarker.dto.PointMap

interface PointRepository {
    val data: Flow<List<PointMap>>
    suspend fun edit(pointMap: PointMap)
    suspend fun removeById(id: Int)
    suspend fun save(pointMap: PointMap)
}