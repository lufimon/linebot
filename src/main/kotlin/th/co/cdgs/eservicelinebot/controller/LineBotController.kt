package th.co.cdgs.eservicelinebot.controller

import com.linecorp.bot.model.event.JoinEvent
import com.linecorp.bot.model.event.MessageEvent
import com.linecorp.bot.model.event.message.TextMessageContent
import com.linecorp.bot.spring.boot.annotation.EventMapping
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import th.co.cdgs.eservicelinebot.exception.ExceptionResponse
import th.co.cdgs.eservicelinebot.model.LineBotRequest
import th.co.cdgs.eservicelinebot.service.BusinessService
import th.co.cdgs.eservicelinebot.utils.Constants
import java.time.LocalDateTime
import java.util.logging.Logger
import javax.websocket.server.PathParam

@LineMessageHandler
@RestController
@RequestMapping("/api")
class LineBotController {

    private val log = LoggerFactory.getLogger(LineBotController::class.java)

    @Autowired
    lateinit var service: BusinessService

    @GetMapping("/test/{id}")
    fun test(@PathVariable("id") id: String): ResponseEntity<Any> {
        service.pushText(id, "test")
        return ResponseEntity.ok("{\"result\": \"UP\"}")
    }

    @PostMapping(path = ["/linebot"], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun lineBot(@RequestBody request: LineBotRequest): ResponseEntity<Any> {
        val isSuccess = service.linebotBoardCast(request)
        return if(isSuccess){
            ResponseEntity.ok("{\"result\": \"SL\"}")
        } else {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionResponse(LocalDateTime.now(), Constants.EXCEPTION_MESSAGE, ""))
        }
    }

    @EventMapping
    fun handleTextMessageEvent(event: MessageEvent<TextMessageContent>) {
        service.handleTextContent(event.replyToken, event, event.message)
    }

    @EventMapping
    fun handleJoinEvent(event: JoinEvent) {
        service.replyText(event.replyToken, Constants.GREETING_MESSAGE)
    }
}