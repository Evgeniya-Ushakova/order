package com.evg.order.config;

import lombok.SneakyThrows;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Set;

public class CustomSSlSocketFactory extends SSLSocketFactory {

    private final SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();

    private final SSLContext sslContext;
    private final Set<String> trustedHostnames;

    @SneakyThrows
    public CustomSSlSocketFactory(String... trustedHostnames) {
        this.trustedHostnames = Set.of(trustedHostnames);

        sslContext = SSLContext.getInstance("TLS");

        TrustManager tm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };

        sslContext.init(null, new TrustManager[]{tm}, null);

    }


    @Override
    public String[] getDefaultCipherSuites() {
        return sslSocketFactory.getDefaultCipherSuites();
    }

    @Override
    public String[] getSupportedCipherSuites() {
        return sslSocketFactory.getSupportedCipherSuites();
    }

    @Override
    public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException {
        return trustedHostnames.contains(host)
                ? sslContext.getSocketFactory().createSocket(socket, host, port, autoClose)
                : sslSocketFactory.createSocket(socket, host, port, autoClose);
    }

    @Override
    public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
        return trustedHostnames.contains(host)
                ? sslContext.getSocketFactory().createSocket(host, port)
                : sslSocketFactory.createSocket(host, port);
    }

    @Override
    public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException, UnknownHostException {
        return trustedHostnames.contains(host)
                ? sslContext.getSocketFactory().createSocket(host, port, localHost, localPort)
                : sslSocketFactory.createSocket(host, port, localHost, localPort);
    }

    @Override
    public Socket createSocket(InetAddress host, int port) throws IOException {
        return trustedHostnames.contains(host.getHostName())
                ? sslContext.getSocketFactory().createSocket(host, port)
                : sslSocketFactory.createSocket(host, port);
    }

    @Override
    public Socket createSocket(InetAddress host, int port, InetAddress localAddress, int localPort) throws IOException {
        return trustedHostnames.contains(host.getHostName())
                ? sslContext.getSocketFactory().createSocket(host, port, localAddress, localPort)
                : sslSocketFactory.createSocket(host, port, localAddress, localPort);
    }
}
