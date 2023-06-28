// package co.vance.nasagalleryapp.presentation
//
// import co.vance.nasagalleryapp.data.NasaRepository
// import co.vance.nasagalleryapp.data.model.Nasa
// import co.vance.nasagalleryapp.util.DataState
// import io.mockk.every
// import io.mockk.mockk
// import kotlinx.coroutines.Dispatchers
// import kotlinx.coroutines.ExperimentalCoroutinesApi
// import kotlinx.coroutines.channels.Channel
// import kotlinx.coroutines.flow.consumeAsFlow
// import kotlinx.coroutines.test.StandardTestDispatcher
// import kotlinx.coroutines.test.runTest
// import kotlinx.coroutines.test.setMain
// import org.junit.jupiter.api.Assertions
// import org.junit.jupiter.api.BeforeEach
// import org.junit.jupiter.api.DisplayName
// import org.junit.jupiter.api.Test
//
// @OptIn(ExperimentalCoroutinesApi::class)
// @DisplayName("SharedViewModel Test")
// class SharedViewModelTest {
//
//   private val repository: NasaRepository = mockk(relaxed = true)
//   private lateinit var viewModel: SharedViewModel
//
//   private val channel = Channel<DataState<List<Nasa>>>()
//   private val dispatcher = StandardTestDispatcher()
//
//   @BeforeEach
//   fun setUp() {
//     Dispatchers.setMain(dispatcher)
//     viewModel = SharedViewModel(repository)
//   }
//
//   @Test
//   @DisplayName("fetch images success")
//   fun fetchImagesSuccess() = runTest {
//     // given
//     every { repository.getNasa() } returns channel.consumeAsFlow()
//
//     // when
//     viewModel.getNasa()
//     channel.send(
//       DataState.Success(
//         listOf(
//           Nasa(
//             copyright = "a",
//             date = "b",
//             explanation = "c",
//             hdurl = "d",
//             media_type = "e",
//             service_version = "f",
//             title = "g",
//             url = "h"
//           )
//         )
//       )
//     )
//
//     println(viewModel.images.value?.data)
//
//     // then
//     Assertions.assertNotNull(viewModel.images.value?.data)
//   }
// }
// Todo -> Something wrong here. Need to fix it.