package org.example.service;

import org.example.domain.Access;
import org.example.utils.CheckAccessAdmin;
import org.example.utils.CheckAccessFTP;
import org.example.utils.CheckAccessSSH;
import org.springframework.stereotype.Service;

@Service
public class CheckService {

    private final CheckAccessSSH checkAccessSSH;
    private final CheckAccessAdmin checkAccessAdmin;

    private final CheckAccessFTP checkAccessFTP;

    public CheckService(CheckAccessSSH checkAccessSSH, CheckAccessAdmin checkAccessAdmin, CheckAccessFTP checkAccessFTP) {
        this.checkAccessSSH = checkAccessSSH;
        this.checkAccessAdmin = checkAccessAdmin;
        this.checkAccessFTP = checkAccessFTP;
    }

    public boolean check(Access access) {

        return switch (access.getTypeAccess()) {
            case "ADMIN" -> checkAccessAdmin.checkAccess(access);
            case "SSH" -> checkAccessSSH.checkAccess(access);
            case "FTP" -> checkAccessFTP.checkAccess(access);
            default -> false;
        };
    }
}
