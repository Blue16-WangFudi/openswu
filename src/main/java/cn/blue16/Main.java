package cn.blue16;
import cn.blue16.provider.zflocal.zflocalImp;
import cn.blue16.swuLayer.SwuInterface;
import cn.blue16.swuLayer.exception.ConnectionException;
import cn.blue16.swuLayer.exception.LoginFailedException;
import cn.blue16.swuLayer.response.LoginResult;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws LoginFailedException, ConnectionException {
        //SwuInterface obj1 = new xdbbtImp();
        SwuInterface obj1 = new zflocalImp();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String username = sc.nextLine();
            String password = sc.nextLine();
            LoginResult loginResult = obj1.login(username,password);
            System.out.println(loginResult.toString());
        }
    }
}