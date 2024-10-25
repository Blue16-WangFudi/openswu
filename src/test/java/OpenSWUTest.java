import cn.blue16.provider.xdbbt.xdbbtImp;
import cn.blue16.swuLayer.SwuInterface;
import cn.blue16.swuLayer.exception.ConnectionException;
import cn.blue16.swuLayer.exception.LoginExpiredException;
import cn.blue16.swuLayer.exception.LoginFailedException;
import cn.blue16.swuLayer.response.LoginResult;
import cn.blue16.swuLayer.response.ScheduleResult;
import org.junit.Assert;
import org.junit.Test;

public class OpenSWUTest {
    static String testUserName = "222023335012081";
    static String testPassword = "";
    @Test
    public void test() throws LoginFailedException, ConnectionException, LoginExpiredException {
        SwuInterface swuClient = new xdbbtImp();
        LoginResult login = swuClient.login(testUserName, testPassword);
        ScheduleResult schedule = swuClient.getSchedule(testUserName);
        Assert.assertFalse("课程获取失败，课程数量为空", schedule.getRes().isEmpty());
    }
}
