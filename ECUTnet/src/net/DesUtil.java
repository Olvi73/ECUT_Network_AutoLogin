package net;

import java.nio.charset.StandardCharsets;
//import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DesUtil {
//    /**
//     * @param args test code and encode
//     */
//    public static void main(String[] args) {
//
////  System.out.println(DesUtil.getInstance("Default").getEnCodeString("hello6546jhfksaj,;l s'"));
////
////  System.out.println(DesUtil.getInstance("Default").getDecodeString("ZSLPPQFGUb9O/d6mn6x9Z6eFrYmXFjsO"));
//   }

    private SecretKey key = null;//��Կ
    //���� �����㷨,���� DES,DESede,Blowfish,AES
    //��ͬ�ļ��ܷ�ʽ����᲻ͬ
    private static final String algorithm = "DES";

 public DesUtil() {
    }

    public static DesUtil getInstance(String strKey) {
     DesUtil desUtil = new DesUtil();
        desUtil.createKey(strKey);
        return desUtil;
    }

    /**
     * algorithm �㷨
     *
     * @param strKey key
     */
    public void createKey(String strKey) {
        try {
            KeyGenerator kg = KeyGenerator.getInstance(DesUtil.algorithm);
            byte[] bt = strKey.getBytes(StandardCharsets.UTF_8);
            SecureRandom sr = new SecureRandom(bt);
            kg.init(sr);
            this.setKey(kg.generateKey());
        } catch (Exception ignored) {
        }
    }

    /**
     * ���ܷ�������������
     * cipher ����
     *
     * @param dataStr ����ܵ���������
     */
    public String getEnCodeString(String dataStr) {
        byte[] Ciphertext;//����
        byte[] Plaintext;//����
        Cipher cipher;
        String result = "";//�����ַ���
        try {
            Plaintext = dataStr.getBytes(StandardCharsets.UTF_8);
            cipher = Cipher.getInstance(DesUtil.algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, this.getKey());
            Ciphertext = cipher.doFinal(Plaintext);
            BASE64Encoder base64en = new BASE64Encoder();
            result = base64en.encodeBuffer(Ciphertext);
//  result=byte2hex(Ciphertext);//�ڶ��ּ��ܷ��������Ľ������2C:37:B0:18:06:64:99:61:DE:60:58:C1:CF:9E:B2:97
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * ���ܷ�������������
     *
     * @param codeStr ����ܵ���������
     */
    public String getDecodeString(String codeStr) {
        BASE64Decoder base64De = new BASE64Decoder();
        byte[] Ciphertext;
        byte[] Plaintext;
        String resultData;//���ص�����
        Cipher cipher;
        try {
            Ciphertext = base64De.decodeBuffer(codeStr);
            cipher = Cipher.getInstance(DesUtil.algorithm);
            cipher.init(Cipher.DECRYPT_MODE, this.getKey());
            Plaintext = cipher.doFinal(Ciphertext);
            resultData = new String(Plaintext, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return "��Կ����ȷ������ԭ�����쳣���޷����ܣ�";
        }
        return resultData;
    }

    public SecretKey getKey() {
        return key;
    }

    public void setKey(SecretKey key) {
        this.key = key;
    }

//    public static String getAlgorithm() {
//        return algorithm;
//    }
//
//    public static void setAlgorithm(String algorithm) {
//        algorithm = algorithm;
//    }
}