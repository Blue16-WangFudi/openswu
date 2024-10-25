package cn.blue16.swuLayer;

import cn.blue16.swuLayer.exception.ConnectionException;
import cn.blue16.swuLayer.exception.LoginExpiredException;
import cn.blue16.swuLayer.exception.LoginFailedException;
import cn.blue16.swuLayer.response.LoginResult;
import cn.blue16.swuLayer.response.ScheduleResult;

public interface SwuInterface {
    LoginResult login(String username, String password) throws LoginFailedException, ConnectionException;
    LoginResult getBaseInfo(String username) throws LoginExpiredException;
    ScheduleResult getSchedule(String username) throws LoginExpiredException;
}
