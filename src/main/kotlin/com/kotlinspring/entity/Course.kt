package com.kotlinspring.entity

import javax.persistence.*

// 構成ファイル「generate-ddl: true」によって自動生成される。
@Entity
@Table(name = "courses")
data class Course(
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  var id: Int?,
  var name: String,
  var category: String
)
