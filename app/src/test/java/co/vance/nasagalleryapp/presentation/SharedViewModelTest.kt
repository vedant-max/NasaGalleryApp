// package co.vance.nasagalleryapp.presentation
//
// import androidx.lifecycle.viewModelScope
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
// private val nasa = listOf(
//   Nasa(
//     "Steve Mazlin",
//     "2019-12-03",
//     "Is this what will become of our Sun? Quite possibly.  The first hint of our Sun's future was discovered inadvertently in 1764. At that time, Charles Messier was compiling a list of diffuse objects not to be confused with comets. The 27th object on Messier's list, now known as M27 or the Dumbbell Nebula, is a planetary nebula, the type of nebula our Sun will produce when nuclear fusion stops in its core. M27 is one of the brightest planetary nebulae on the sky, and can be seen toward the constellation of the Fox (Vulpecula) with binoculars. It takes light about 1000 years to reach us from M27, featured here in colors emitted by hydrogen and oxygen. Understanding the physics and significance of M27 was well beyond 18th century science. Even today, many things remain mysterious about bipolar planetary nebula like M27, including the physical mechanism that expels a low-mass star's gaseous outer-envelope, leaving an X-ray hot white dwarf.   APOD across world languages: Arabic, Catalan, Chinese (Beijing), Chinese (Taiwan), Croatian, Czech, Dutch, Farsi, French, French, German, Hebrew, Indonesian, Japanese, Korean, Montenegrin, Polish, Russian, Serbian, Slovenian,  Spanish and Ukrainian",
//     "https://apod.nasa.gov/apod/image/1912/M27_Mazlin_2000.jpg",
//     "image",
//     "v1",
//     "M27: The Dumbbell Nebula",
//     "https://apod.nasa.gov/apod/image/1912/M27_Mazlin_960.jpg"
//   )
// )
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
//     every { repository.getNasa() } returns channel.consumeAsFlow()
//   }
//
//   @Test
//   @DisplayName("Init - Success")
//   fun getNasaSuccess() = runTest {
//     //Given
//     viewModel = SharedViewModel(repository)
//
//     //When
//     channel.send(DataState.Success(nasa))
//
//     //Then
//     println(viewModel.images.value)
//     Assertions.assertNotNull(viewModel.images.value?.data?.get(0))
//   }
// }
// // Todo -> Something wrong here. Need to fix it.