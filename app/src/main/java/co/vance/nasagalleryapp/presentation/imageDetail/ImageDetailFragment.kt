package co.vance.nasagalleryapp.presentation.imageDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.PagerSnapHelper
import co.vance.nasagalleryapp.R
import co.vance.nasagalleryapp.databinding.FragmentImageDetailBinding
import co.vance.nasagalleryapp.presentation.imageList.SharedViewModel

class ImageDetailFragment : Fragment(R.layout.fragment_image_detail) {

  private var _binding: FragmentImageDetailBinding? = null
  private val binding get() = _binding!!
  private val viewModel: SharedViewModel by activityViewModels()
  private val args by navArgs<ImageDetailFragmentArgs>()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    _binding = FragmentImageDetailBinding.bind(view)

    val imageDetailAdapter = ImageDetailAdapter()
    with(binding) {
      rvImageDetail.apply {
        adapter = imageDetailAdapter
        scrollToPosition(args.position.toInt())
        setHasFixedSize(true)
      }
      PagerSnapHelper().attachToRecyclerView(rvImageDetail)
    }
    viewModel.images.observe(viewLifecycleOwner) { result ->
      imageDetailAdapter.submitList(result.data)
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    _binding = null
  }
}