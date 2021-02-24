package th.co.cdgs.eservicelinebot.model

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.MappedSuperclass

@MappedSuperclass
class FwEntity {
    @get:Column(name = "CREATE_BY", length = 13)
    var createBy: String? = null
    @get:Column(name = "UPDATE_BY", length = 13)
    var updateBy: String? = null
    @get:Column(name = "CREATE_DATE")
    var createDate: LocalDateTime? = null
    @get:Column(name = "UPDATE_DATE")
    var updateDate: LocalDateTime? = null
}
