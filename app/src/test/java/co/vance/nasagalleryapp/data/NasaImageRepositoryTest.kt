package co.vance.nasagalleryapp.data

import co.vance.nasagalleryapp.data.service.NasaService
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

@DisplayName("NasaRepositoryTest")
class NasaImageRepositoryTest {
  private lateinit var mockWebServer: MockWebServer
  private lateinit var baseUrl: HttpUrl
  private var service: NasaService = mockk(relaxed = true)
  private lateinit var repository: NasaRepository

  @BeforeEach
  fun setup() {
    mockWebServer = MockWebServer()
    mockWebServer.start()
    baseUrl = mockWebServer.url("/")

    service = Retrofit.Builder().baseUrl(baseUrl)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(NasaService::class.java)

    repository = NasaRepository(service)
  }

  @Test
  @DisplayName("Get Nasa - Success")
  fun getNasaSuccess() = runTest {
    //Given
    mockWebServer.enqueue(
      MockResponse().setResponseCode(HttpURLConnection.HTTP_OK)
        .setBody(ClassLoader.getSystemResource("nasa.json").readText())
    )

    //When
    val flowList = repository.getNasa().toList()

    //Then
    Assertions.assertNotNull(flowList[1].data)
    Assertions.assertEquals("M27: The Dumbbell Nebula", flowList[1].data?.get(0)?.title)
  }

  @Test
  @DisplayName("Get Nasa - Loading")
  fun getNasaLoading() = runTest {
    //Given
    mockWebServer.enqueue(
      MockResponse().setResponseCode(HttpURLConnection.HTTP_OK)
        .setBody(ClassLoader.getSystemResource("nasa.json").readText())
    )

    //When
    val flowList = repository.getNasa().toList()

    //Then
    Assertions.assertNull(flowList[0].data)
  }

  @Test
  @DisplayName("Get Nasa - Error")
  fun getNasaError() = runTest {
    //Given
    mockWebServer.enqueue(MockResponse().setResponseCode(HttpURLConnection.HTTP_INTERNAL_ERROR))

    //When
    val flowList = repository.getNasa().toList()

    //Then
    Assertions.assertNotNull(flowList[1].error)
  }

  @AfterEach
  fun tearDown() {
    mockWebServer.shutdown()
  }
}