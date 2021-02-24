package th.co.cdgs.eservicelinebot.controller

import com.linecorp.bot.model.event.JoinEvent
import com.linecorp.bot.model.event.MessageEvent
import com.linecorp.bot.model.event.message.TextMessageContent
import com.linecorp.bot.spring.boot.annotation.EventMapping
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import th.co.cdgs.eservicelinebot.service.BusinessService
import th.co.cdgs.eservicelinebot.utils.Constants
import java.util.logging.Logger
import javax.websocket.server.PathParam

@LineMessageHandler
@RestController
@RequestMapping("/api")
class LineBotController {

    private val log: Logger = Logger.getLogger(LineBotController::class.java.simpleName)

    @Autowired
    lateinit var service: BusinessService

    @GetMapping("/test/{id}")
    fun test(@PathVariable("id") id: String): ResponseEntity<Any> {
        service.pushText(id, "test")
        return ResponseEntity.ok("test")
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