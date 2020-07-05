package com.mozki.security.md;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.digests.MD4Digest;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

public class ImoocMD {
    private static String src = "imooc security md";

    public static void main(String[] args) {
        jdkMD5();
        bcMD5();
        bcMD4();
        jdkMD4();
        ccMD5();
    }
    public static void jdkMD5(){
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(src.getBytes());
            System.out.println("jdk md5: " + org.apache.commons.codec.binary.Hex.encodeHexString(md5Bytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    public static void bcMD4(  ){
        MD4Digest digest = new MD4Digest();
        digest.update(src.getBytes(), 0, src.getBytes().length);
        byte[] md4Bytes = new byte[digest.getDigestSize()];
        digest.doFinal(md4Bytes, 0);
        System.out.println("BC MD4: " + org.bouncycastle.util.encoders.Hex.toHexString(md4Bytes));
    }

    public static void bcMD5(  ){
        MD5Digest digest = new MD5Digest();
        digest.update(src.getBytes(), 0, src.getBytes().length);
        byte[] md5Bytes = new byte[digest.getDigestSize()];
        digest.doFinal(md5Bytes, 0);
        System.out.println("BC MD5: " + org.bouncycastle.util.encoders.Hex.toHexString(md5Bytes));
    }

    /**
     * jdk没有提供md4,动态添加一个bc的provider，就有了
     */
    public static void jdkMD4(){

        try {
            Security.addProvider(new BouncyCastleProvider());
            // 如果没有添加provider，这里会抛出NoSuchAlgorithmException
            MessageDigest md4 = MessageDigest.getInstance("MD4");
            byte[] digest = md4.digest(src.getBytes());
            System.out.println("jdk bc provide md4: " + Hex.encodeHexString(digest));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    public static void ccMD5(){
        System.out.println("cc MD5: " + DigestUtils.md5Hex(src));
    }
}
