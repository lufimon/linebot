package th.co.cdgs.eservicelinebot.service

import com.linecorp.bot.client.LineMessagingClient
import com.linecorp.bot.model.PushMessage
import com.linecorp.bot.model.ReplyMessage
import com.linecorp.bot.model.event.Event
import com.linecorp.bot.model.event.message.TextMessageContent
import com.linecorp.bot.model.event.source.GroupSource
import com.linecorp.bot.model.message.Message
import com.linecorp.bot.model.message.TextMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.lang.NonNull
import org.springframework.stereotype.Service
import th.co.cdgs.eservicelinebot.controller.LineBotController
import th.co.cdgs.eservicelinebot.repository.LineBotQueryRepository
import th.co.cdgs.eservicelinebot.utils.Constants
import java.util.concurrent.ExecutionException
import java.util.logging.Logger

@Service
class BusinessServiceImpl @Autowired constructor(queryRepo: LineBotQueryRepository) : BusinessService {

    private val queryRepository = queryRepo

    private val log: Logger = Logger.getLogger(BusinessServiceImpl::class.java.simpleName)

    @Autowired
    private val lineMessagingClient: LineMessagingClient? = null

    override fun reply(@NonNull replyToken: String, @NonNull message: Message) {
        reply(replyToken, listOf(message))
    }

    override fun reply(@NonNull replyToken: String, @NonNull messages: List<Message>) {
        reply(replyToken, messages, false)
    }

    override fun reply(@NonNull replyToken: String,
                       @NonNull messages: List<Message>,
                       notificationDisabled: Boolean) {
        try {
            val apiResponse = lineMessagingClient?.replyMessage(ReplyMessage(replyToken, messages, notificationDisabled))
                    ?.get()
            log.info("Sent messages: {${apiResponse}}")
        } catch (e: InterruptedException) {
            throw RuntimeException(e)
        } catch (e: ExecutionException) {
            throw RuntimeException(e)
        }
    }

    override fun replyText(@NonNull replyToken: String, @NonNull message: String) {
        var message = message
        require(replyToken.isNotEmpty()) { "replyToken must not be empty" }
        if (message.length > 1000) {
            message = message.substring(0, 1000 - 2) + "……"
        }
        this.reply(replyToken, TextMessage(message))
    }

    override fun push(@NonNull id: String, @NonNull message: Message) {
        push(id, listOf(message))
    }

    override fun push(@NonNull id: String,
                      @NonNull messages: List<Message>) {
        try {
            val apiResponse = lineMessagingClient?.pushMessage(PushMessage(id, messages))
                    ?.get()
            log.info("Sent messages: {${apiResponse?.message}}")
        } catch (e: InterruptedException) {
            throw RuntimeException(e)
        } catch (e: ExecutionException) {
            throw RuntimeException(e)
        }
    }

    override fun pushText(@NonNull id: String, @NonNull message: String) {
        var message = message
        require(id.isNotEmpty()) { "id must not be empty" }
        if (message.length > 1000) {
            message = message.substring(0, 1000 - 2) + "……"
        }
        this.push(id, TextMessage(message))
    }

    override fun handleTextContent(replyToken: String, event: Event, content: TextMessageContent) {
        val text = content.text
        when {
            text.contains(Constants.BOT_ADD) -> {
                val userId = event.source.userId
                if(userId != null) {
                    if (event.source is GroupSource) {
                        val groupId = (event.source as GroupSource).groupId
                        val depart = text.split(":")[2]
                        if(depart.isNotBlank() && depart.isNotEmpty()) {
                            lineMessagingClient?.getGroupMemberProfile(groupId, userId)
                                    ?.whenComplete { profile, throwable ->
                                        if (throwable != null) {
                                            replyText(replyToken, Constants.ERROR_MESSAGE)
                                            return@whenComplete
                                        }
                                        val messageResult = queryRepository.updateDepartment(true, profile.displayName, groupId, depart)
                                        replyText(replyToken, messageResult)
                                    }
                        } else {
                            replyText(replyToken, Constants.PLEASE_ENTER_DEPARTMENT_MESSAGE)
                        }
                    } else {
                        replyText(replyToken, Constants.ERROR_MESSAGE)
                    }
                } else {
                    replyText(replyToken, Constants.ERROR_MESSAGE)
                }
            }
            text.contains(Constants.BOT_UPDATE) -> {
                val userId = event.source.userId
                if(userId != null) {
                    if (event.source is GroupSource) {
                        val groupId = (event.source as GroupSource).groupId
                        val depart = text.split(":")[2]
                        if(depart.isNotBlank() && depart.isNotEmpty()) {
                            val groupSummary = lineMessagingClient?.getGroupSummary(groupId)?.get()
                            val messageResult = queryRepository.updateDepartment(false, groupSummary?.groupName ?: depart.toUpperCase(), groupId, depart)
                            replyText(replyToken, messageResult)
                        } else {
                            replyText(replyToken, Constants.PLEASE_ENTER_DEPARTMENT_MESSAGE)
                        }
                    } else {
                        replyText(replyToken, Constants.ERROR_MESSAGE)
                    }
                } else {
                    replyText(replyToken, Constants.ERROR_MESSAGE)
                }
            }
            text == Constants.BOT_COMMAND -> {
                replyText(replyToken, Constants.COMMAND_MESSAGE)
            }
            text == Constants.BOT_ELSE -> {
                replyText(replyToken, Constants.COMMAND_MESSAGE)
            }
        }
    }
}