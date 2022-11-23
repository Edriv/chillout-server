package com.utdev.chilloutserver.service.security;

import com.google.gson.JsonObject;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Signature;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Enumeration;

@Service
public class JWTService {

    private final static Charset UTF8_CHARSET = Charset.forName("UTF-8");
    private static KeyStore myStore = null;
    private static FileInputStream in_cert = null;

    public JWTService(){
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

            Enumeration objEnumeration = myStore.aliases();
            while(objEnumeration.hasMoreElements() == true){
                alias = (String) objEnumeration.nextElement();
                privateKey = (PrivateKey) myStore.getKey(alias, "M4st3r.K3y".toCharArray());
                System.out.println(privateKey.getFormat());
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        JsonObject header = new JsonObject();
        header.addProperty("alg","RS256");
        header.addProperty("typ", "JWT");

        Calendar cal = Calendar.getInstance();
        cal.set(1970, 01, 01);
        String iat = Long.toString((System.currentTimeMillis() - cal.getTimeInMillis())/1000);
        //String iat = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
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
            byte[] headerArr = headerStr.getBytes(UTF8_CHARSET);
            System.out.println(Base64.encodeBase64URLSafeString(headerArr));

            byte[] claimArr = claimStr.getBytes(UTF8_CHARSET);
            System.out.println(Base64.encodeBase64URLSafeString(claimArr));

            String inputStr = Base64.encodeBase64URLSafeString(headerArr) + "." + Base64.encodeBase64URLSafeString(claimArr);

            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(privateKey);
            signature.update(inputStr.getBytes(UTF8_CHARSET));

            System.out.println("Final JWT : " + Base64.encodeBase64URLSafeString(headerArr) + "." + Base64.encodeBase64URLSafeString(claimArr) + "." + Base64.encodeBase64URLSafeString(signature.sign()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
