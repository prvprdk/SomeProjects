package org.example.utils;

import org.example.domain.Access;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


@Component
public class CheckAccessAdmin   extends HelperForCheck implements CheckAccess {


    public boolean checkAccess(Access access) {


        try {
            String urlForRequest = getUrlForRequest(access.getUrl());

            URL u = new URL(urlForRequest);
            HttpURLConnection con = getHttpURLConnection(access.getLogin(), access.getPassword(), u);
            String location = con.getHeaderField("Location");

            return location.contains("wp-admin");
        } catch (Exception ep) {
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

}