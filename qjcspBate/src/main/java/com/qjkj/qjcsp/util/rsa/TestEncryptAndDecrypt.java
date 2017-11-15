package com.qjkj.qjcsp.util.rsa;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import javax.crypto.Cipher;
/*
 * 测试公钥加密，私钥解密
 */
public class TestEncryptAndDecrypt {    
    public static void main(String[] args) throws Exception {
        String input = "thisIsMyPassword$7788魏树林？》！）（VYKUYT@EO";
        Cipher cipher = Cipher.getInstance("RSA");        
        RSAPublicKey pubKey = (RSAPublicKey) PublicKeyReader.get("d:/publicKeyFile");
        RSAPrivateKey privKey = (RSAPrivateKey) PrivateKeyReader.get("d:/privateKeyFile");
//客户端传给服务器端        
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        //加密后的东西
        System.out.println("cipher:客户端传给服务器端     " + new String(cipherText));        
        //开始解密
        cipher.init(Cipher.DECRYPT_MODE, privKey); 
        byte[] plainText = cipher.doFinal(cipherText);
        System.out.println("plain : 服务器端 解密后" + new String(plainText));
       
//服务器端传给客户端             
        cipher.init(Cipher.ENCRYPT_MODE, privKey);
        cipherText = cipher.doFinal(input.getBytes());
        //加密后的东西
        System.out.println("cipher:服务器端传给客户端 ");
        System.out.println(new String(cipherText));        
        //开始解密
        cipher.init(Cipher.DECRYPT_MODE, pubKey); 
        plainText = cipher.doFinal(cipherText);
        System.out.println("plain :客户端解密后 " + new String(plainText));
        
    }

}