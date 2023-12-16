package ru.netology.MapsMarker.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.MapsMarkers.databinding.PointCardBinding
import ru.netology.MapsMarker.dto.PointMap

interface OnInteractionListener {
    fun onEdit(pointMap: PointMap) {}
    fun onRemove(pointMap: PointMap) {}
    fun onMove(pointMap: PointMap) {}
}

class PointsAdapter (
    private val onInteractionListener: OnInteractionListener,
) : ListAdapter<PointMap, PointViewHolder>(PointsDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointViewHolder {
        val binding =
            PointCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PointViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: PointViewHolder, position: Int) {
        val pointMap = getItem(position)
        holder.renderingPostStructure(pointMap)
    }
}

class PointsDiffCallback : DiffUtil.ItemCallback<PointMap>() {
    override fun areItemsTheSame(oldItem: PointMap, newItem: PointMap): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PointMap, newItem: PointMap): Boolean {
        return oldItem == newItem
    }
}

class PointViewHolder(
    private val binding: PointCardBinding,
    private val onInteractionListener: OnInteractionListener
) : RecyclerView.ViewHolder(binding.root) {

    fun renderingPostStructure(pointMap: PointMap) {
        with(binding) {
            descriptionPoint.text = pointMap.description
            titlePoint.text = pointMap.title
            val coordinatePoint = "${pointMap.latitude}; ${pointMap.longitude}"
            coordinate.text = coordinatePoint
            postListeners(pointMap)
        }
    }

    private fun postListeners(pointMap: PointMap) {
        with(binding) {
            point.setOnClickListener {
                onInteractionListener.onMove(pointMap)
            }
            remove.setOnLongClickListener {
                onInteractionListener.onRemove(pointMap)
                true
            }
            edit.setOnLongClickListener {
                onInteractionListener.onEdit(pointMap)
                true
            }
        }
    }
}