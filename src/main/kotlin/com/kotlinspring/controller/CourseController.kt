package com.kotlinspring.controller

import com.kotlinspring.dto.CourseDTO
import com.kotlinspring.service.CourseServise
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/courses")
class CourseController(val courseService: CourseServise) {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun addCourse(@RequestBody courseDTO: CourseDTO): CourseDTO {
    return courseService.addCourse(courseDTO)
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  fun retrieveCourses(): List<CourseDTO> {
    return courseService.retrieveAllCourses()
  }

}
