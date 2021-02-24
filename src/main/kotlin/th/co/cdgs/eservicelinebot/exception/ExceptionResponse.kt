package th.co.cdgs.eservicelinebot.exception

import java.time.LocalDateTime

class ExceptionResponse {
    var timestamp: LocalDateTime? = null
    var message: String? = null
    var detail: String? = null

    constructor(){

    }

    constructor(timestamp: LocalDateTime, message: String, detail: String){
        this.timestamp = timestamp
        this.message = message
        this.detail = detail
    }
}
