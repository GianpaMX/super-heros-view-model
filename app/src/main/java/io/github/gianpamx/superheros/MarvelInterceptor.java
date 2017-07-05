package io.github.gianpamx.superheros;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;

public class MarvelInterceptor implements Interceptor {

    private final String key;
    private final String secret;

    public MarvelInterceptor(String apiKey, String secret) {
        this.key = apiKey;
        this.secret = secret;
    }

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        String timeStamp = String.valueOf(System.currentTimeMillis() / 1000L);

        String hash = getHash(timeStamp);

        Request originalRequest = chain.request();
        HttpUrl originalHttpUrl = originalRequest.url();

        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("ts", timeStamp)
                .addQueryParameter("apikey", key)
                .addQueryParameter("hash", hash)
                .build();

        Request.Builder requestBuilder = originalRequest.newBuilder().url(url);
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }

    private String getHash(String timeStamp) {
        return md5(timeStamp + secret + key);
    }

    public String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
