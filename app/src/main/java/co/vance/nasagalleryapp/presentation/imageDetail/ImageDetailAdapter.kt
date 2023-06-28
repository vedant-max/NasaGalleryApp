package co.vance.nasagalleryapp.presentation.imageDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import co.vance.nasagalleryapp.R
import co.vance.nasagalleryapp.data.model.Nasa
import co.vance.nasagalleryapp.databinding.DetailItemBinding
import co.vance.nasagalleryapp.presentation.imageDetail.ImageDetailAdapter.ImageDetailViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class ImageDetailAdapter : ListAdapter<Nasa, ImageDetailViewHolder>(COMPARATOR) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageDetailViewHolder {
    val binding =
      DetailItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return ImageDetailViewHolder(binding)
  }

  override fun onBindViewHolder(holder: ImageDetailViewHolder, position: Int) {
    val current = getItem(position)
    current?.let { holder.bind(it) }
  }

  inner class ImageDetailViewHolder(private val binding: DetailItemBinding) :
    ViewHolder(binding.root) {
    fun bind(nasa: Nasa) {
      with(binding) {
        Glide.with(itemView)
          .load(nasa.hdurl)
          .centerCrop()
          .transition(DrawableTransitionOptions.withCrossFade())
          .error(R.drawable.ic_error)
          .into(ivNasaImage)
        tvDate.text = nasa.date
        tvTitle.text = nasa.title
        tvDescription.text = nasa.explanation
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