package org.example.service;

import org.example.domain.Access;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CheckAccessHelper {


    public boolean checkAccess(Access access) throws IOException {

        try {
            String urlForRequest = setUrl(access.getUrl());

            URL u = new URL(urlForRequest);
            HttpURLConnection con = getHttpURLConnection(access.getLogin(), access.getPassword(), u);
            String location = con.getHeaderField("Location");


            return location.contains("wp-admin");
        }catch (Exception ep){
            System.out.println(ep.getMessage());
            return false;
        }
    }

    private static HttpURLConnection getHttpURLConnection(String login, String password, URL u) throws IOException {
        HttpURLConnection con = (HttpURLConnection) u.openConnection();

        con.setInstanceFollowRedirects(false);
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-type", "application/x-www-form-urlencoded");

        try (OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream())) {

            String data = String.format("log=%s&pwd=%s", login, password);
            out.write(data);
            out.flush();
        }
        return con;
    }


    private String setUrl(String  url) {


        if (url.contains("wp-admin")) {
            return String.format("https://%s/wp-login.php", matchUrl(url));
        }
        return url;
    }

    private String matchUrl(String url) {

        String match = "";

        Pattern pattern = Pattern.compile("[A-z0-9\\.\\-]+\\.(ru|com)");
        Matcher matcher = pattern.matcher(url);
        while (matcher.find()) {
            match = matcher.group();
        }
        return match;
    }

}