package co.vance.nasagalleryapp.presentation.imageList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import co.vance.nasagalleryapp.R
import co.vance.nasagalleryapp.data.model.Nasa
import co.vance.nasagalleryapp.databinding.ImageListItemBinding
import co.vance.nasagalleryapp.presentation.imageList.ImageListAdapter.ImageViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class ImageListAdapter(private val listener: OnImageClickListener) :
  ListAdapter<Nasa, ImageViewHolder>(COMPARATOR) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
    val binding = ImageListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return ImageViewHolder(binding)
  }

  override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
    val current = getItem(position)
    current?.let { holder.bind(it) }
  }

  inner class ImageViewHolder(private val binding: ImageListItemBinding) :
    ViewHolder(binding.root) {

    init {
      binding.root.setOnClickListener {
        val position = adapterPosition
        if (position != RecyclerView.NO_POSITION) {
          listener.onImageClick(position)
        }
      }
    }

    fun bind(nasa: Nasa) {
      with(binding) {
        Glide.with(itemView)
          .load(nasa.url)
          .centerCrop()
          .transition(DrawableTransitionOptions.withCrossFade())
          .error(R.drawable.ic_error)
          .into(binding.ivNasa)
        tvDate.text = nasa.date
        tvDescription.text = nasa.explanation
        tvNasaTitle.text = nasa.title
      }
    }
  }

  companion object {
    private val COMPARATOR = object : DiffUtil.ItemCallback<Nasa>() {
      override fun areItemsTheSame(oldItem: Nasa, newItem: Nasa) =
        oldItem.title == newItem.title

      override fun areContentsTheSame(oldItem: Nasa, newItem: Nasa) =
        oldItem == newItem
    }
  }
}