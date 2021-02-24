package th.co.cdgs.eservicelinebot.model

data class LineBotRequest(
        var hotlineId: String? = null,
        var receiveDate: String? = null,
        var contractCode: String? = null,
        var callerName: String? = null,
        var telephoneNo: String? = null,
        var serviceLocation: String? = null,
        var model: String? = null,
        var serialNo: String? = null,
        var symptom: String? = null,
        var lineGroupName: String? = null,
        var lineGroupId: String? = null,
        var deptSupport: String? = null
)