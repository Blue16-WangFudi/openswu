package cn.blue16.provider.xdbbt.nativeResponse;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NativeLoginResponse {
    private int code;
    private String errmsg;
    private String session_id;
}
