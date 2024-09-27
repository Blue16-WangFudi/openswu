package cn.blue16.provider.xdbbt.nativeResponse;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NativeLoginResponse {
    private int code;
    private String errmsg;
    private Res res;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Res {
        @SerializedName("jw_cookie")
        private String jwCookie;
    }
}
