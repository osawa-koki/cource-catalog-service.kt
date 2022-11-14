package com.kotlinspring.controller;

import com.kotlinspring.dto.CourseDTO;
import com.kotlinspring.repository.CourseRepository
import com.kotlinspring.util.courseEntityList
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
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

  @Autowired
  lateinit var courseRepository: CourseRepository

  @BeforeEach
  fun setUp() {
    courseRepository.deleteAll()
    val courses = courseEntityList()
    courseRepository.saveAll(courses)
  }

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

  @Test
  fun retrieveAllCourses() {

    val courseDTOs = webTestClient
      .get()
      .uri("/v1/courses")
      .exchange()
      .expectStatus().isOk
      .expectBodyList(CourseDTO::class.java)
      .returnResult()
      .responseBody

    println("courseDTOs: $courseDTOs")
    assertEquals(3, courseDTOs!!.size)
  }

  @Test
  fun updateCourse() {

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

    val updatedCourseDTO = CourseDTO(savedCourseDTO?.id, "kotlin updated", "webapi updated")

    val updatedCourse = webTestClient
      .put()
      .uri("/v1/courses/${updatedCourseDTO.id}")
      .bodyValue(updatedCourseDTO)
      .exchange()
      .expectStatus().isOk
      .expectBody(CourseDTO::class.java)
      .returnResult()
      .responseBody

    assertEquals(updatedCourseDTO.id, updatedCourse?.id)
    assertEquals(updatedCourseDTO.name, updatedCourse?.name)
    assertEquals(updatedCourseDTO.category, updatedCourse?.category)
  }
}
