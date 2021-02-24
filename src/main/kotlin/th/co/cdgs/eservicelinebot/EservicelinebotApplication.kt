package th.co.cdgs.eservicelinebot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.web.WebApplicationInitializer

@SpringBootApplication
class EservicelinebotApplication

fun main(args: Array<String>) {
	runApplication<EservicelinebotApplication>(*args)
}
