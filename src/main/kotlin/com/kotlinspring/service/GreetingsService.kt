package com.kotlinspring.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class GreetingsService() {

  // application.ymlの値を使用する。
  @Value("\${message}")
  lateinit var message: String

  fun retrieveGreetings(name: String): String {
    return "$message, $name!"
  }

}
