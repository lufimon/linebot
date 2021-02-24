package th.co.cdgs.eservicelinebot.service

import com.linecorp.bot.model.PushMessage
import com.linecorp.bot.model.ReplyMessage
import com.linecorp.bot.model.event.Event
import com.linecorp.bot.model.event.message.TextMessageContent
import com.linecorp.bot.model.message.Message
import com.linecorp.bot.model.message.TextMessage
import org.springframework.lang.NonNull
import java.util.concurrent.ExecutionException

interface BusinessService {

    fun reply(@NonNull replyToken: String, @NonNull message: Message)

    fun reply(@NonNull replyToken: String, @NonNull messages: List<Message>)

    fun reply(@NonNull replyToken: String,
              @NonNull messages: List<Message>,
              notificationDisabled: Boolean)

    fun replyText(@NonNull replyToken: String, @NonNull message: String)

    fun push(@NonNull id: String, @NonNull message: Message)

    fun push(@NonNull id: String, @NonNull messages: List<Message>)

    fun pushText(@NonNull id: String, @NonNull message: String)

    fun handleTextContent(replyToken: String, event: Event, content: TextMessageContent)
}