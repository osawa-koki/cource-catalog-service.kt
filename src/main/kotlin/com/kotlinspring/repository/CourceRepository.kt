package com.kotlinspring.repository

import com.kotlinspring.entity.Cource
import org.springframework.data.repository.CrudRepository

interface CourceRepository: CrudRepository<Cource, Int> {
}