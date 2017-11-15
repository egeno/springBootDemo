package com.qjkj.qjcsp.util.rsa;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.security.KeyFactory;
/*
 * 读取公钥方法
 */
public class PublicKeyReader {    
    public static PublicKey get(String filename) throws Exception {
        File f = new File(filename);
        FileInputStream fis = new FileInputStream(f); 
        DataInputStream dis = new DataInputStream(fis);
        byte[] keyBytes = new byte[(int)f.length()]; 
        dis.readFully(keyBytes); 
        dis.close();
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA"); 
        System.out.println(kf.generatePublic(spec));
        return kf.generatePublic(spec);
    }
    
    public static void main(String[] args) throws Exception, InvalidKeySpecException, IOException {
    	PublicKeyReader.get("d:/publicKeyFile");
    }

}
