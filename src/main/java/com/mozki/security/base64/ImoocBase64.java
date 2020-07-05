package com.mozki.security.base64;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

public class ImoocBase64 {
    private static String src="imooc security base64";
    public static void main(String[] args) {

    }
    @Test
    public void jdkBase64(){
        try {
            BASE64Encoder encoder = new BASE64Encoder();
            String encode = encoder.encode(src.getBytes());
            System.out.println(encode);

            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes = decoder.decodeBuffer(encode);
            System.out.println(new String(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void commonsCodecBAse64(){
        byte[] encodedbytes = Base64.encodeBase64(src.getBytes());
        String encodedStr = new String(encodedbytes);
        System.out.println("encoded: "+ encodedStr);

        System.out.println("decode bytes: " + new String(Base64.decodeBase64(encodedbytes)));
        System.out.println("decode str: " + new String(Base64.decodeBase64(encodedStr)));
    }

    @Test
    public void bouncyCastleBase64(){
        byte[] encode = org.bouncycastle.util.encoders.Base64.encode(src.getBytes());
        System.out.println("encode: " + new String(encode   ));

        byte[] decode = org.bouncycastle.util.encoders.Base64.decode(encode);
        System.out.println("decode: " + new String(decode));
    }
}
