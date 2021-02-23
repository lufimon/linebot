package th.co.cdgs.eservicelinebot.controller

import com.linecorp.bot.model.event.Event
import com.linecorp.bot.model.event.MessageEvent
import com.linecorp.bot.model.event.message.TextMessageContent
import com.linecorp.bot.model.message.TextMessage
import com.linecorp.bot.spring.boot.annotation.EventMapping
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@LineMessageHandler
@RequestMapping("/api")
class LineBotController {

    @GetMapping("/test")
    fun test(): ResponseEntity<Any> {
        return ResponseEntity.ok("test")
    }

    @EventMapping
    fun handleTextMessageEvent(event: MessageEvent<TextMessageContent>): TextMessage {
        println("event: $event")
        return TextMessage(event.message.text)
    }

    fun handleDefaultMessageEvent(event: Event) {
        println("event: $event")
    }
}