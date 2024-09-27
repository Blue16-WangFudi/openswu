package cn.blue16.provider.xdbbt.nativeInterface;

import cn.blue16.provider.xdbbt.nativeResponse.NativeInfoResponse;
import cn.blue16.provider.xdbbt.nativeResponse.NativeLoginResponse;
import java.io.IOException;
import java.util.Map;

import static cn.blue16.util.HttpUtil.sendPostRequest_Form;

public class NativeImp {
    public static NativeLoginResponse GeneralLogin(String stu_id, String password) {
        String url = "https://apiv2.xdbbtswu.com/bbtOf/api_bbtOf/NEW_JW/general_Login.php";
        Map<String, String> parameters = Map.of("stu_id", stu_id, "password", password);
        try {
            return sendPostRequest_Form(url, parameters, NativeLoginResponse.class);
        } catch (IOException e) {
            return new NativeLoginResponse(500,"服务端内部错误",new NativeLoginResponse.Res(""));
        }
    }

    public static NativeInfoResponse GetBaseInfo(String stu_id, String cookie) {
        String url = "https://apiv2.xdbbtswu.com/bbtOf/api_bbtOf/NEW_JW/GetBaseInfo.php";
        Map<String, String> parameters = Map.of("stu_id", stu_id, "cookie", cookie);
        try {
            return sendPostRequest_Form(url, parameters, NativeInfoResponse.class);
        } catch (IOException e) {
            return new NativeInfoResponse(500,"服务端内部错误",new NativeInfoResponse.Res());
        }
    }
}
