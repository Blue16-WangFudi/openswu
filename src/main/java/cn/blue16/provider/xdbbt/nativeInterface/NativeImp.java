package cn.blue16.provider.xdbbt.nativeInterface;

import cn.blue16.provider.xdbbt.nativeResponse.NativeInfoResponse;
import cn.blue16.provider.xdbbt.nativeResponse.NativeLoginResponse;
import cn.blue16.provider.xdbbt.nativeResponse.NativeScheduleResponse;

import java.io.IOException;
import java.util.Map;

import static cn.blue16.util.HttpUtil.sendPostRequest_Form;

public class NativeImp {
    public static NativeLoginResponse GeneralLogin(String stu_id, String password) {
        String url = "https://apiv2.xdbbtswu.com/bbtOf/api_bbtOf/NEW_JW_SESSION/general_Login.php";
        Map<String, String> parameters = Map.of("stu_id", stu_id, "password", password);
        try {
            return sendPostRequest_Form(url, parameters, NativeLoginResponse.class);
        } catch (Exception e) {
            return new NativeLoginResponse(500,"服务端内部错误",null);
        }
    }

    public static NativeInfoResponse GetBaseInfo(String stu_id, String session_id) {
        String url = "https://apiv2.xdbbtswu.com/bbtOf/api_bbtOf/NEW_JW_SESSION/GetBaseInfo.php";
        Map<String, String> parameters = Map.of("stu_id", stu_id, "session_id", session_id);
        try {
            return sendPostRequest_Form(url, parameters, NativeInfoResponse.class);
        } catch (Exception e) {
            return new NativeInfoResponse(500,"服务端内部错误",null);
        }
    }
    public static NativeScheduleResponse GetSchedule(String stu_id, String session_id) {
        String url = "https://apiv2.xdbbtswu.com/bbtOf/api_bbtOf/NEW_JW_SESSION/GetShedule.php";
        Map<String, String> parameters = Map.of("stu_id", stu_id, "session_id", session_id);
        try {
            return sendPostRequest_Form(url, parameters, NativeScheduleResponse.class);
        } catch (Exception e) {
            return new NativeScheduleResponse(500,"服务端内部错误",null);
        }
    }
}
