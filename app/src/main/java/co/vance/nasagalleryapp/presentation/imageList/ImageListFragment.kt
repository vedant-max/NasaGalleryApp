package co.vance.nasagalleryapp.presentation.imageList

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import co.vance.nasagalleryapp.R
import co.vance.nasagalleryapp.databinding.ImageListFragmentBinding
import co.vance.nasagalleryapp.presentation.SharedViewModel
import co.vance.nasagalleryapp.util.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageListFragment : Fragment(R.layout.image_list_fragment), OnImageClickListener {

  private var _binding: ImageListFragmentBinding? = null
  private val binding get() = _binding!!
  private val viewModel: SharedViewModel by activityViewModels()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    _binding = ImageListFragmentBinding.bind(view)

    val imageListAdapter = ImageListAdapter(this)
    with(binding) {
      rvImageList.apply {
        adapter = imageListAdapter
        setHasFixedSize(true)
      }
    }

    viewModel.images.observe(viewLifecycleOwner) { result ->
      imageListAdapter.submitList(result.data)
      with(binding) {
        loadingAnim.isVisible = result is DataState.Loading && result.data.isNullOrEmpty()
        errorLayout.isVisible = result is DataState.Error && result.data.isNullOrEmpty()
        tvError.text = getString(R.string.error, result.error?.localizedMessage)
      }
    }
  }

  override fun onImageClick(position: Int) {
    val action = ImageListFragmentDirections.actionGlobalImageDetailFragment(position.toString())
    findNavController().navigate(action)
  }

  override fun onDestroy() {
    super.onDestroy()
    _binding = null
  }
}