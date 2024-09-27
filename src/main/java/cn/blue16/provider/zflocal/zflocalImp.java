package cn.blue16.provider.zflocal;

import cn.blue16.swuLayer.SwuInterface;
import cn.blue16.swuLayer.exception.ConnectionException;
import cn.blue16.swuLayer.exception.LoginFailedException;
import cn.blue16.swuLayer.response.LoginResult;
import cn.blue16.provider.zflocal.nativeInterface.NativeImp;

public class zflocalImp implements SwuInterface {
    NativeImp nativeImp = new NativeImp(); // 创建一个实现类，因为这个代码内部存放着一个Client，后面会用到这个Client（后面会优化的）

    @Override
    public LoginResult login(String username, String password) throws LoginFailedException, ConnectionException {
        if(nativeImp.login(username,password)){
            // 登录成功了，获取信息
            String[] arr = nativeImp.getInfo();
            LoginResult loginResult = new LoginResult(username,arr[2],"null",arr[0],arr[1],arr[1]);
            return loginResult;
        }
        throw new LoginFailedException("登录失败");
    }
}
