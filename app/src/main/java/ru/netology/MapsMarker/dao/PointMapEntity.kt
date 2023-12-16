package ru.netology.MapsMarker.dao

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.MapsMarker.dto.PointMap

@Entity
data class PointMapEntity (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val latitude:Double,
    val longitude:Double,
    val title:String,
    val description:String,
) {
    fun toDto() = PointMap(
        id, latitude, longitude, title, description
    )

    companion object {
        fun fromDto(dto: PointMap) =
            PointMapEntity(
                dto.id, dto.latitude, dto.longitude, dto.title, dto.description
            )
    }
}

fun List<PointMapEntity>.toDto(): List<PointMap> = map(PointMapEntity::toDto)