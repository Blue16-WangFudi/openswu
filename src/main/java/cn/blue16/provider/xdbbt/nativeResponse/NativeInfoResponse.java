package cn.blue16.provider.xdbbt.nativeResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NativeInfoResponse {
    private int code;
    private String msg;
    private Res res;

    @Data
    @AllArgsConstructor
    public static class Res {
        private String bjmc;
        private String xb;
        private String xh;
        private String xm;
        private String xymc;
        private String zjhm;
        private String zymc;
        public Res(){
            bjmc="";
            xb="";
            xh="";
            xm="";
            xymc="";
            zjhm="";
            zymc="";
        }
    }
}