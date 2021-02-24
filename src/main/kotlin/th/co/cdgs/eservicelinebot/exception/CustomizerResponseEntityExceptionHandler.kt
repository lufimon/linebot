package th.co.cdgs.eservicelinebot.exception

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
@RestController
class CustomizerResponseEntityExceptionHandler: ResponseEntityExceptionHandler() {
    val log: Logger = LoggerFactory.getLogger(CustomizerResponseEntityExceptionHandler::class.java)

    @ExceptionHandler(Exception::class)
    fun handleAllException(ex: Exception, request: WebRequest): ResponseEntity<Any> {
        log.error("service has throws Exception", ex)
        val exceptionResponse = ExceptionResponse(LocalDateTime.now(), ex.message!!, request.getDescription(false))
        return ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

}
