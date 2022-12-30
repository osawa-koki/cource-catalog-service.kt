package com.kotlinspring.controller

import com.kotlinspring.service.GreetingsService
import mu.KLogging
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class IndexController() {

  companion object: KLogging()

  @GetMapping("/")
  fun index(): String {
    return "Hello world!!!"
  }

  @GetMapping("/{name}")
  fun get(@PathVariable name: String): String {
    return "Hello, $name! this is get method."
  }

  @PostMapping("/{name}")
  fun post(@PathVariable name: String): String {
    return "Hello, $name! this is post method."
  }

  @PutMapping("/{name}")
  fun put(@PathVariable name: String): String {
    return "Hello, $name! this is put method."
  }

  @DeleteMapping("/{name}")
  fun delete(@PathVariable name: String): String {
    return "Hello, $name! this is delete method."
  }

}
