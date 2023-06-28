package co.vance.nasagalleryapp.presentation.imageList

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import co.vance.nasagalleryapp.R
import co.vance.nasagalleryapp.databinding.ImageListFragmentBinding
import co.vance.nasagalleryapp.util.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageListFragment : Fragment(R.layout.image_list_fragment), OnImageClickListener {

  private var _binding: ImageListFragmentBinding? = null
  private val binding get() = _binding!!
  private val viewModel: ImagesViewModel by viewModels()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    _binding = ImageListFragmentBinding.bind(view)

    val imageListAdapter = ImageListAdapter(this)
    with(binding) {
      rvImageList.apply {
        adapter = imageListAdapter
        setHasFixedSize(true)
      }
      btnTryAgain.setOnClickListener {
        Log.d(TAG, "onViewCreated: btnTryAgain clicked")
        viewModel.getImages()
      }
    }

    viewModel.images.observe(viewLifecycleOwner) { result ->
      imageListAdapter.submitList(result.data)
      with(binding) {
        loadingAnim.isVisible = result is DataState.Loading && result.data.isNullOrEmpty()
        // errorAnim.isVisible = result is DataState.Error && result.data.isNullOrEmpty()
        // Log.d(TAG, "error: ${errorAnim.isVisible}")
        // tvError.isVisible = result is DataState.Error && result.data.isNullOrEmpty()
        errorLayout.isVisible = result is DataState.Error && result.data.isNullOrEmpty()
        tvError.text = result.error?.localizedMessage + ". Please try Again."
      }
    }
  }

  override fun onImageClick(position: Int) {
    val action = ImageListFragmentDirections.actionGlobalImageDetailFragment(position.toString())
    findNavController().navigate(action)
    Log.d(TAG, "onImageClick: $position")
  }

  override fun onDestroy() {
    super.onDestroy()
    _binding = null
  }
}