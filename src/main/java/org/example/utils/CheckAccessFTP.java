package org.example.utils;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.example.domain.Access;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CheckAccessFTP extends HelperForCheck implements CheckAccess {
    @Override
    public boolean checkAccess(Access access) {

        String host = getHostFromUrl(access.getUrl());
        String username = access.getLogin();
        String password = access.getPassword();

        try {
            FTPClient client = new FTPClient();
            client.connect(host);
            client.login(username, password);

            int reply = client.getReplyCode();
            client.disconnect();
            return FTPReply.isPositiveCompletion(reply);
        } catch (IOException ep) {
            System.out.println(ep.getMessage());
        }

        return false;
    }
}
