package th.co.cdgs.eservicelinebot.repository

import th.co.cdgs.eservicelinebot.model.CsstListMng
import th.co.cdgs.eservicelinebot.model.LineBotRequest

abstract class LineBotQueryRepository: CoreQueryRepository() {
    abstract fun getDepartment(depart: String): CsstListMng?
    abstract fun updateDepartment(isAdd: Boolean, lineName: String, lineId: String, depart: String): String
    abstract fun updateCsstHotline(lineBotRequest: LineBotRequest): Boolean
}