package com.kotlinspring.controller

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebClient
class GreetingControllerIntgTest {

  @Autowired
  lateinit var webTestClient: WebTestClient

  @Test
  fun retrieveGreetings() {
    val name = "test"

    val result = webTestClient.get().uri("/v1/greetings/{name}", name)
      .exchange()
      .expectStatus().is2xxSuccessful
      .expectBody(String::class.java)
      .returnResult()

    Assertions.assertEquals("Hello from default profile, $name!", result.responseBody)
  }
}
