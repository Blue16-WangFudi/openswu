package provider.xdbbt;

import cn.blue16.provider.xdbbt.nativeInterface.NativeImp;
import cn.blue16.provider.xdbbt.nativeResponse.NativeLoginResponse;
import cn.blue16.provider.xdbbt.nativeResponse.NativeScheduleResponse;
import org.junit.Assert;
import org.junit.Test;

public class NativeImpTest {
    static String testUserName = "222023335012081";
    static String testPassword = "";

    @Test
    public void GetScheduleTest(){
        // 首先登录
        NativeLoginResponse nativeLoginResponse = NativeImp.GeneralLogin(testUserName, testPassword);
        // 获取课表信息
        NativeScheduleResponse nativeScheduleResponse = NativeImp.GetSchedule(testUserName, nativeLoginResponse.getSession_id());
        Assert.assertTrue("课程获取失败，课程数量为空",!nativeScheduleResponse.getRes().isEmpty());
        //System.out.println(nativeScheduleResponse.getRes().size());
    }

    @Test
    public void GetScheduleTest2(){
                // 获取课表信息
        NativeScheduleResponse nativeScheduleResponse = NativeImp.GetSchedule(testUserName,"cf0a06e1-91ac-480e-92e1-78e94590b07c");
        System.out.println(nativeScheduleResponse.getRes().size());

    }
}
