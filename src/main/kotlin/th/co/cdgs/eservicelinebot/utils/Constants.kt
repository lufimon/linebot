package th.co.cdgs.eservicelinebot.utils

object Constants {
    const val BOT_ELSE = "bot:"
    const val BOT_COMMAND = "bot:command"
    const val BOT_ADD = "bot:add:"
    const val BOT_ADD_NO_SEMI = "bot:add"
    const val BOT_UPDATE = "bot:update:"
    const val BOT_UPDATE_NO_SEMI = "bot:update"
    const val COMMAND_MESSAGE = "คำสั่งต่างๆ\n- $BOT_COMMAND\n- $BOT_ADD[ชื่อแผนก] ex ชื่อแผนก. PC,NET,SE,ADD,BU1,SERVER\n- $BOT_UPDATE[ชื่อแผนก] ex ชื่อแผนก. PC,NET,SE,ADD,BU1,SERVER"
    const val GREETING_MESSAGE = "สวัสดีทุกท่าน\n$COMMAND_MESSAGE"
    const val ERROR_COMMAND_MESSAGE = "คำสั่งไม่ถูกต้อง\n$COMMAND_MESSAGE"
    const val ERROR_MESSAGE = "ไม่พบการทำงานที่คุณต้องการ"
    const val PLEASE_ENTER_DEPARTMENT_MESSAGE = "กรุณาระบุแผนกของท่าน"
    const val ADD_ERROR_MESSAGE = "ไม่สามารถเพิ่มข้อมูลได้หากท่านต้องการเปลี่ยนแปลงข้อมูลกรุณาเลือกอัพเดต"
    const val ADD_SUCCESS_MESSAGE = "บันทึกข้อมูลแผนกสำเร็จ"
    const val EXCEPTION_MESSAGE = "เกิดข้อผิดพลาด"
    const val ERROR_DEPARTMENT_MESSAGE = "กรุณาระบุแผนกให้ถูกต้อง!!! ex ชื่อแผนก. PC,NET,SE,ADD,BU1,SERVER"
}