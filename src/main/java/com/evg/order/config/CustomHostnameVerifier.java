package com.evg.order.config;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.net.HttpURLConnection;
import java.util.Set;

public class CustomHostnameVerifier implements HostnameVerifier {

    private final Set<String> naivelyTrustedHostnames;

    private final HostnameVerifier hostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();

    public CustomHostnameVerifier(String ... naivelyTrustedHostnames) {
        this.naivelyTrustedHostnames = Set.of(naivelyTrustedHostnames);
    }

    @Override
    public boolean verify(String hostname, SSLSession session) {
        return naivelyTrustedHostnames.contains(hostname) || hostnameVerifier.verify(hostname, session);
    }
}
