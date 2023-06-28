package co.vance.nasagalleryapp.presentation.imageDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import co.vance.nasagalleryapp.R
import co.vance.nasagalleryapp.data.model.NasaImage
import co.vance.nasagalleryapp.databinding.DetailItemBinding
import co.vance.nasagalleryapp.presentation.imageDetail.ImageDetailAdapter.ImageDetailViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class ImageDetailAdapter : ListAdapter<NasaImage, ImageDetailViewHolder>(COMPARATOR) {

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
    fun bind(nasaImage: NasaImage) {
      with(binding) {
        Glide.with(itemView)
          .load(nasaImage.hdurl)
          .centerCrop()
          .transition(DrawableTransitionOptions.withCrossFade())
          .error(R.drawable.ic_error)
          .into(ivNasaImage)
        tvDate.text = nasaImage.date
        tvTitle.text = nasaImage.title
        tvDescription.text = nasaImage.explanation
      }
    }
  }

  companion object {
    private val COMPARATOR = object : DiffUtil.ItemCallback<NasaImage>() {
      override fun areItemsTheSame(oldItem: NasaImage, newItem: NasaImage) =
        oldItem.title == newItem.title

      override fun areContentsTheSame(oldItem: NasaImage, newItem: NasaImage) =
        oldItem == newItem
    }
  }

}