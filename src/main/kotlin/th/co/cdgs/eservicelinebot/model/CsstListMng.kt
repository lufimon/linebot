package th.co.cdgs.eservicelinebot.model

import java.io.Serializable

data class CsstListMng(
        var listseq: Int? = null,
        var listcode: String? = null,
        var listval: String? = null,
        var listname: String? = null,
        var linegroupName: String? = null,
        var linegroupId: String? = null
) : Serializable