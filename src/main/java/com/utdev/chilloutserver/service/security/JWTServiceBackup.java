package com.utdev.chilloutserver.service.security;

import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.security.*;
import java.security.cert.Certificate;
import java.util.Base64;
import java.util.Calendar;

public class JWTServiceBackup {

    private final static Charset UTF8_CHARSET = Charset.forName("UTF-8");
    private static KeyStore myStore = null;
    private static FileInputStream in_cert = null;

    private static Key key = null;
    private static Certificate certificate = null;
    private static PublicKey publicKey = null;


    public JWTServiceBackup(){
        PrivateKey privateKey = null;
        try{
            //in_cert = new FileInputStream(getClass().getResourceAsStream("/keystore.p12").toString());
            File p12 = new File(getClass().getClassLoader().getResource("keystore.p12").getFile());
            in_cert = new FileInputStream(p12);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try{
            myStore = KeyStore.getInstance("PKCS12");
            myStore.load(in_cert, "M4st3r.K3y".toCharArray());
            String alias = "";

            key = myStore.getKey("server", "M4st3r.K3y".toCharArray());
            privateKey = (PrivateKey) key;
            certificate = myStore.getCertificate("server");
            publicKey = certificate.getPublicKey();

            System.out.println(Base64.getEncoder().encodeToString(
                    publicKey.getEncoded()));
            in_cert.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        JsonObject header = new JsonObject();
        header.addProperty("alg","RS256");
        header.addProperty("typ", "JWT");

        Calendar cal = Calendar.getInstance();
        cal.set(1970, 01, 01);
        String iat = Long.toString((System.currentTimeMillis() - cal.getTimeInMillis())/1000);
        String exp = Long.toString((System.currentTimeMillis() - cal.getTimeInMillis())/1000 + 60000L);

        JsonObject claim = new JsonObject();
        claim.addProperty("access_type", "offline");
        claim.addProperty("exp", exp);
        claim.addProperty("iat", iat);

        System.out.println("Header : " + header);
        String headerStr = header.toString();
        System.out.println("claim : " + claim);
        String claimStr = claim.toString();

        try {
            Base64.Encoder encoder = Base64.getEncoder();

            byte[] headerArr = headerStr.getBytes(UTF8_CHARSET);
            System.out.println(encoder.encodeToString(headerArr));

            byte[] claimArr = claimStr.getBytes(UTF8_CHARSET);
            System.out.println(encoder.encodeToString(claimArr));

            String inputStr = encoder.encodeToString(headerArr) + "." + encoder.encodeToString(claimArr);

            Signature signature = Signature.getInstance("NONEwithRSA");
            signature.initSign(privateKey);
            signature.update(inputStr.getBytes(UTF8_CHARSET));

            System.out.println("Final JWT : " + encoder.encodeToString(headerArr) + "." + encoder.encodeToString(claimArr) + "." + encoder.encodeToString(signature.sign()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
