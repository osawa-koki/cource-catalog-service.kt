package com.kotlinspring.controller

import com.kotlinspring.dto.CourseDTO
import com.kotlinspring.service.CourseServise
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.reactive.server.WebTestClient

@WebMvcTest(controllers = [CourseController::class])
@AutoConfigureWebClient
class CourseControllerUnitTest {

  @Autowired
  lateinit var webTestClient: WebTestClient

  @MockkBean
  lateinit var courseServiceMockk: CourseServise

  @Test
  fun addCourse() {

    val courseDTO = CourseDTO(null, "kotlin", "webapi")

    // Mock
    every { courseServiceMockk.addCourse(any()) } returns courseDTO(id = 1)

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

    every { courseServiceMockk.retrieveAllCourses() }.returnsMany(
      listOf(
        courseDTO(id = 1),
        courseDTO(id = 2),
      )
    )

    val courseDTOs = webTestClient
      .get()
      .uri("/v1/courses")
      .exchange()
      .expectStatus().isOk
      .expectBodyList(CourseDTO::class.java)
      .returnResult()
      .responseBody

    println("courseDTOs: $courseDTOs")
    Assertions.assertEquals(2, courseDTOs!!.size)
  }

  @Test
  fun updateCourse() {

    every { courseServiceMockk.updateCourse(any(), any()) } returns courseDTO(id = 100, name = "kotlin updated", description = "webapi updated")

    val updatedCourseDTO = CourseDTO(null, "kotlin", "webapi")

    val updatedCourse = webTestClient
      .put()
      .uri("/v1/courses/{course_id}", 100)
      .bodyValue(updatedCourseDTO)
      .exchange()
      .expectStatus().isOk
      .expectBody(CourseDTO::class.java)
      .returnResult()
      .responseBody

    Assertions.assertEquals("kotlin updated", updatedCourse?.name)
    Assertions.assertEquals("webapi updated", updatedCourse?.category)
  }


  fun courseDTO(
    id: Int? = null,
    name: String = "kotlin",
    description: String = "webapi",
  ) = CourseDTO(id, name, description)

}
