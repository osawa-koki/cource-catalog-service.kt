package com.kotlinspring.controller

import com.kotlinspring.service.GreetingsService
import com.ninjasquad.springmockk.MockkBean
import com.ninjasquad.springmockk.MockkBeans
import io.mockk.every
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient

@WebMvcTest(controllers = [GreetingController::class])
@AutoConfigureWebClient
class GreetingControllerUnitTest {

  @Autowired
  lateinit var webTestClient: WebTestClient

  @MockkBean
  lateinit var greetingsServiceMock: GreetingsService

  @Test
  fun retrieveGreetings() {
    val name = "test"

    every { greetingsServiceMock.retrieveGreetings(any()) } returns "Hello from default profile, $name!"

    val result = webTestClient.get().uri("/v1/greetings/{name}", name)
      .exchange()
      .expectStatus().is2xxSuccessful
      .expectBody(String::class.java)
      .returnResult()

    Assertions.assertEquals("Hello from default profile, $name!", result.responseBody)
  }

}
