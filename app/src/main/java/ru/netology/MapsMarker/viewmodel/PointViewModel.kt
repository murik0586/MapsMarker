package ru.netology.MapsMarker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.netology.MapsMarker.dto.PointMap
import ru.netology.MapsMarker.repository.PointRepository
import javax.inject.Inject

@HiltViewModel
class PointViewModel @Inject constructor(
    application: Application,
    private val repository: PointRepository,
) : AndroidViewModel(application) {

    val data: LiveData<List<PointMap>> =
            repository.data.asLiveData(Dispatchers.Default)

    fun removeById(id:Int) = viewModelScope.launch {
        repository.removeById(id)
    }

    fun savePoint(mapPoint: PointMap) = viewModelScope.launch {
        repository.save(mapPoint)
    }
}