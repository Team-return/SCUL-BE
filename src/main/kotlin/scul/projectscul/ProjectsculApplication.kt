package scul.projectscul

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ConfigurationPropertiesScan
class ProjectsculApplication

fun main(args: Array<String>) {
	runApplication<ProjectsculApplication>(*args)
}
