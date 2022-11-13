package com.kotlinspring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CourceCatalogServiceApplication

// VM Options: -Dspring.profiles.active=prod
// を設定することで、application-prod.ymlの値を制御する。
fun main(args: Array<String>) {
	runApplication<CourceCatalogServiceApplication>(*args)
}
