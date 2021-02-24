package th.co.cdgs.eservicelinebot.repository

import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import th.co.cdgs.eservicelinebot.model.CsstListMng
import th.co.cdgs.eservicelinebot.utils.Constants
import javax.persistence.EntityManager

@Repository
class LineBotQueryRepositoryImpl(override var entityManager: EntityManager) : LineBotQueryRepository() {

    @Transactional(propagation = Propagation.REQUIRED)
    override fun getDepartment(depart: String): CsstListMng? {
        return try {
            val sql = "SELECT T1.LISTSEQ, " +
                    "T1.LISTCODE, " +
                    "T1.LISTVAL, " +
                    "T1.LISTNAME, " +
                    "T1.LINEGROUP_NAME, " +
                    "T1.LINEGROUP_ID " +
                    "FROM CSSTLISTMNG T1 " +
                    "WHERE T1.LISTCODE = '01' " +
                    "AND T1.LISTVAL = :depart "
            val query = entityManager.createNativeQuery(sql)
            query.setParameter("depart", depart.toUpperCase())
            val objResult = arrayOf(query.singleResult)
            if (objResult[0] != null) {
                val item = objResult[0] as Array<Any>
                CsstListMng(
                        listseq = item[0] as? Int,
                        listcode = item[1] as? String,
                        listval = item[2] as? String,
                        listname = item[3] as? String,
                        linegroupName = item[4] as? String,
                        linegroupId = item[5] as? String
                )
            } else {
                null
            }
        } catch (e: Exception) {
            throw e
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun updateDepartment(isAdd: Boolean, lineName: String, lineId: String, depart: String): String {
        return try {
            val csstListMng = getDepartment(depart)
            if (csstListMng != null) {
                val sql = "UPDATE CSSTLISTMNG " +
                        "SET LINEGROUP_NAME = :lineGroupName, " +
                        "LINEGROUP_ID = :lineGroupId " +
                        "WHERE LISTSEQ = :listSeq "
                val query = entityManager.createNativeQuery(sql)
                if (isAdd && (csstListMng.linegroupId?.isNotBlank() == true && csstListMng.linegroupId?.isNotEmpty() == true)) {
                    Constants.ADD_ERROR_MESSAGE
                } else {
                    query.setParameter("lineGroupName", lineName)
                    query.setParameter("lineGroupId", lineId)
                    query.setParameter("listSeq", csstListMng.listseq)
                    query.executeUpdate()
                    Constants.ADD_SUCCESS_MESSAGE
                }
            } else {
                Constants.ERROR_DEPARTMENT_MESSAGE
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Constants.EXCEPTION_MESSAGE
        }
    }

}