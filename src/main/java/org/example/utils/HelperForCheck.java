package org.example.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class HelperForCheck  {


    public static String getUrlForRequest(String url) {
        if (url.contains("wp-admin")) {
            return String.format("https://%s/wp-login.php", getDomainFromUrl(url));
        }
        return url;
    }

    public static String getDomainFromUrl(String url) {

        String match = "";

        Pattern pattern = Pattern.compile("[A-z0-9\\.\\-]+\\.(ru|com)");
        Matcher matcher = pattern.matcher(url);
        while (matcher.find()) {
            match = matcher.group();
        }
        return match;
    }

    public static String getHostFromUrl(String url) {

        String match = "";
        Pattern pattern =
                Pattern
                        .compile("((25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[0-9]{1,2})[.]){3}" +
                                "(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[0-9]{1,2})");

        Matcher matcher = pattern.matcher(url);
        if(matcher.find()){
            match = matcher.group();
            return match;
        }
        return url;
    }
}
