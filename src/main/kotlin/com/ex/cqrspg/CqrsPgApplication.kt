package com.ex.cqrspg

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling


@EnableScheduling
@SpringBootApplication
class CqrsPgApplication

fun main(args: Array<String>) {
	runApplication<CqrsPgApplication>(*args)
}
