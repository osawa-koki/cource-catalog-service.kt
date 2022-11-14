package com.kotlinspring.entity

import javax.persistence.*

// 構成ファイル「generate-ddl: true」によって自動生成される。
@Entity
@Table(name = "cources")
data class Cource(
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  val id: Int?,
  val name: String,
  val category: String
)
