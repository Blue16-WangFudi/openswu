package cn.blue16.swuLayer;

import cn.blue16.swuLayer.exception.ConnectionException;
import cn.blue16.swuLayer.exception.LoginFailedException;
import cn.blue16.swuLayer.response.LoginResult;

public interface SwuInterface {
    LoginResult login(String username, String password) throws LoginFailedException, ConnectionException;
}
