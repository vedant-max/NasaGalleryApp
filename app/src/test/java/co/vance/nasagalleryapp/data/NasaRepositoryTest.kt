// package co.vance.nasagalleryapp.data
//
// import co.vance.nasagalleryapp.data.service.NasaService
// import io.mockk.mockk
// import kotlinx.coroutines.flow.toList
// import kotlinx.coroutines.test.runTest
// import okhttp3.HttpUrl
// import okhttp3.mockwebserver.MockResponse
// import okhttp3.mockwebserver.MockWebServer
// import org.junit.Before
// import org.junit.jupiter.api.AfterEach
// import org.junit.jupiter.api.Assertions
// import org.junit.jupiter.api.BeforeEach
// import org.junit.jupiter.api.DisplayName
// import org.junit.jupiter.api.Test
// import retrofit2.Retrofit
// import retrofit2.converter.gson.GsonConverterFactory
// import java.net.HttpURLConnection
//
// @DisplayName("NasaRepositoryTest")
// class NasaRepositoryTest {
//   private lateinit var mockWebServer: MockWebServer
//   private lateinit var baseUrl: HttpUrl
//   private var service: NasaService = mockk(relaxed = true)
//   private lateinit var repository: NasaRepository
//
//   @BeforeEach
//   fun setup() {
//     mockWebServer = MockWebServer()
//     baseUrl = mockWebServer.url("/")
//     service = Retrofit.Builder().baseUrl(baseUrl)
//       .addConverterFactory(GsonConverterFactory.create())
//       .build()
//       .create(NasaService::class.java)
//
//     repository = NasaRepository(service)
//   }
//
//
//   @Test
//   @DisplayName("Get Nasa - Success")
//   fun getNasaSuccess() = runTest {
//     //Given
//     mockWebServer.enqueue(
//       MockResponse().setResponseCode(HttpURLConnection.HTTP_OK)
//         .setBody(ClassLoader.getSystemResource("nasa.json").readText())
//     )
//
//     //When
//     val result = repository.getNasa().toList()
//     println(result)
//     val response = result[0].data
//
//     //Then
//     Assertions.assertNotNull(response)
//   }
//
//   @Test
//   @DisplayName("Get Nasa - Error")
//   fun getNasaError() = runTest {
//     //Given
//     mockWebServer.enqueue(MockResponse().setResponseCode(HttpURLConnection.HTTP_INTERNAL_ERROR))
//
//     //When
//     val result = repository.getNasa().toList()
//     val response = result[1]
//     println(result)
//
//     //Then
//     Assertions.assertNotNull(response.error)
//   }
//
//   @AfterEach
//   fun tearDown() {
//     mockWebServer.shutdown()
//   }
// }

// Todo -> Something wrong here. Need to fix it.