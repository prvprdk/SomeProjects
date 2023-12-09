package org.example.utils;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.example.domain.Access;
import org.springframework.stereotype.Component;


@Component
public class CheckAccessSSH  extends HelperForCheck implements CheckAccess{
    @Override
    public boolean checkAccess(Access access) {
        String username = access.getLogin();
        String host =  getHostFromUrl(access.getUrl());
        String password = access.getPassword();
        Session session = null;

        try {
            session = new JSch().getSession(username, host);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            return session.isConnected();

        } catch (JSchException e) {
            return false;
        } finally {
            if (session != null) {
                session.disconnect();
            }
        }
    }
}
