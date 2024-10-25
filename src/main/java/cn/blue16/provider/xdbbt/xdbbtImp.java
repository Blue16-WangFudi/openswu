package cn.blue16.provider.xdbbt;

import cn.blue16.provider.xdbbt.nativeResponse.NativeScheduleResponse;
import cn.blue16.swuLayer.SwuInterface;
import cn.blue16.swuLayer.exception.ConnectionException;
import cn.blue16.swuLayer.exception.LoginExpiredException;
import cn.blue16.swuLayer.exception.LoginFailedException;
import cn.blue16.swuLayer.response.LoginResult;
import cn.blue16.provider.xdbbt.nativeResponse.NativeInfoResponse;
import cn.blue16.provider.xdbbt.nativeResponse.NativeLoginResponse;

import java.util.concurrent.ConcurrentHashMap;
import cn.blue16.provider.xdbbt.nativeInterface.NativeImp;
import cn.blue16.swuLayer.response.ScheduleResult;

public class xdbbtImp implements SwuInterface {
    ConcurrentHashMap<String, String> stu_cookies = new ConcurrentHashMap<>();

    @Override
    public LoginResult login(String username, String password) throws LoginFailedException {
        // 先查找是否有记录，如果有记录，则先尝试一下
        String session_id = stu_cookies.get(username);
        if (session_id != null) {
            NativeInfoResponse nativeInfoResponse = NativeImp.GetBaseInfo(username, session_id);
            NativeInfoResponse.Res res = nativeInfoResponse.getRes();
            if (nativeInfoResponse.getCode() == 200) {
                //这个Cookie是有用的，且成功获取到数据了，直接返回
                return new LoginResult(username, res.getXm(), res.getXb(), res.getXymc(), res.getZymc(),res.getZjhm(), res.getBjmc());
            }
            //否则就相当于没有用，那就先试试登录，直接删掉这个记录
            stu_cookies.remove(username);
        }
        // 登录流程
        NativeLoginResponse nativeLoginResponse = NativeImp.GeneralLogin(username, password);
        if (nativeLoginResponse.getCode() == 200) {
            // 登录成功了，尝试获取信息
            // 先设置cookie为登录成功的cookie
            session_id = nativeLoginResponse.getSession_id();
            // 调用原生类
            NativeInfoResponse nativeInfoResponse = NativeImp.GetBaseInfo(username, session_id);
            // 判断是否获取成功
            if (nativeInfoResponse.getCode() == 200) {
                // 成功获取用户信息，解码并返回
                NativeInfoResponse.Res res = nativeInfoResponse.getRes();
                LoginResult loginResult = new LoginResult(username, res.getXm(), res.getXb(), res.getXymc(), res.getZymc(),res.getZjhm(), res.getBjmc());
                // 保存cookie以便下次使用
                stu_cookies.put(username, session_id);
                return loginResult;
            }
        }
        // 如果出现错误，抛出异常
        throw new LoginFailedException("登录失败");
    }

    @Override
    public LoginResult getBaseInfo(String username) throws LoginExpiredException {
        // 先获取一下当前用户是否存放了cookie
        String sessionId = stu_cookies.get(username);

        // 没有登录，记录为null
        if (sessionId == null) {
            throw new LoginExpiredException();
        }

        // 存在记录，尝试获取
        try{
            NativeInfoResponse nativeInfoResponse = NativeImp.GetBaseInfo(username, sessionId);
            // 成功获取用户信息，解码并返回
            NativeInfoResponse.Res res = nativeInfoResponse.getRes();
            return new LoginResult(username, res.getXm(), res.getXb(), res.getXymc(), res.getZymc(),res.getZjhm(), res.getBjmc());
        }catch(Exception e){
            throw new LoginExpiredException();
        }
    }

    @Override
    public ScheduleResult getSchedule(String username) throws LoginExpiredException {

        // 先获取一下当前用户是否存放了cookie
        String sessionId = stu_cookies.get(username);

        // 没有登录，记录为null
        if (sessionId == null) {
            throw new LoginExpiredException();
        }

        // 存在记录，尝试获取
        try{
            NativeScheduleResponse nativeScheduleResponse = NativeImp.GetSchedule(username, sessionId);
            return new ScheduleResult(username,nativeScheduleResponse.getRes());
        }catch(Exception e){
            throw new LoginExpiredException();
        }


    }
}
