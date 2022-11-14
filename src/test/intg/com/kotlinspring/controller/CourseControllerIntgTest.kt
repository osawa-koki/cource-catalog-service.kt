package com.kotlinspring.controller;

import com.kotlinspring.dto.CourseDTO;
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebClient
public class CourseControllerIntgTest {

  @Autowired
  lateinit var webTestClient: WebTestClient

  @Test
  fun addCourse() {

    val courseDTO = CourseDTO(null, "kotlin", "webapi")

    val savedCourseDTO = webTestClient
      .post()
      .uri("/v1/courses")
      .bodyValue(courseDTO)
      .exchange()
      .expectStatus().isCreated
      .expectBody(CourseDTO::class.java)
      .returnResult()
      .responseBody

    Assertions.assertTrue {
      savedCourseDTO?.id != null
    }

  }

}
