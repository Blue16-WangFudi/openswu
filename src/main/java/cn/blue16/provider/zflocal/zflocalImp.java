package cn.blue16.provider.zflocal;

import cn.blue16.swuLayer.SwuInterface;
import cn.blue16.swuLayer.exception.ConnectionException;
import cn.blue16.swuLayer.exception.LoginExpiredException;
import cn.blue16.swuLayer.exception.LoginFailedException;
import cn.blue16.swuLayer.response.LoginResult;
import cn.blue16.provider.zflocal.nativeInterface.NativeImp;
import cn.blue16.swuLayer.response.ScheduleResult;

public class zflocalImp implements SwuInterface {
    NativeImp nativeImp = new NativeImp(); // 创建一个实现类，因为这个代码内部存放着一个Client，后面会用到这个Client（后面会优化的）

    @Override
    public LoginResult login(String username, String password) throws LoginFailedException {
        if(nativeImp.login(username,password)){
            // 登录成功了，获取信息
            String[] arr = nativeImp.getInfo();
            return new LoginResult(username,arr[2],"null",arr[0],arr[1],"null",arr[1]);
        }
        throw new LoginFailedException("登录失败");
    }

    @Override
    public LoginResult getBaseInfo(String username) {
        return null;
    }

    @Override
    public ScheduleResult getSchedule(String username) {
        return null;
    }


}
