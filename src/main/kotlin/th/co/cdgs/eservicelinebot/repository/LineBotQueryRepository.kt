package th.co.cdgs.eservicelinebot.repository

import th.co.cdgs.eservicelinebot.model.CsstListMng

abstract class LineBotQueryRepository: CoreQueryRepository() {
    abstract fun getDepartment(depart: String): CsstListMng?
    abstract fun updateDepartment(isAdd: Boolean, lineName: String, lineId: String, depart: String): String
}