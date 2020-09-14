package У԰���Զ���¼����;

import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
public class DesUtil {
 /**
  * @param args
  */
 public static void main(String[] args) {

 // System.out.println(DesUtil.getInstance("Default").getEnCodeString("hello"));

 // System.out.println(DesUtil.getInstance("Default").getDecodeString("0I9GRY6tw9Au2ztvdLb4Zg=="));
 }
 private SecretKey key=null;//��Կ
 //���� �����㷨,���� DES,DESede,Blowfish,AES
 //��ͬ�ļ��ܷ�ʽ����᲻ͬ
 private static String algorithm="AES";
 private static DesUtil desUtil=null;
 public DesUtil(){}
 public static DesUtil getInstance(String strKey){
  desUtil=new DesUtil();
  desUtil.createKey(strKey);
  return desUtil;
 }
 /**
  * algorithm �㷨
  * @param strKey
  */
 public void createKey(String strKey){
  try{
   KeyGenerator kg=KeyGenerator.getInstance(DesUtil.algorithm);
   byte[] bt=strKey.getBytes("UTF-8");
   SecureRandom sr=new SecureRandom(bt);
   kg.init(sr);
   this.setKey(kg.generateKey());
  }catch(Exception e){
  }
 }
 /**
  * ���ܷ�������������
  * cipher ����
  * @param dataStr
  */
 public String getEnCodeString(String dataStr){
  byte[] miwen=null;//����
  byte[] mingwen=null;//����
  Cipher cipher;
  String result="";//�����ַ���
  try{
   mingwen=dataStr.getBytes("UTF-8");
   cipher=Cipher.getInstance(DesUtil.algorithm);
   cipher.init(Cipher.ENCRYPT_MODE, this.getKey());
   miwen=cipher.doFinal(mingwen);
   BASE64Encoder base64en = new BASE64Encoder();
   result=base64en.encodeBuffer(miwen);
//  result=byte2hex(miwen);//�ڶ��ּ��ܷ��������Ľ������2C:37:B0:18:06:64:99:61:DE:60:58:C1:CF:9E:B2:97
  }catch(Exception e){
   e.printStackTrace();
  }
  return result;
 }
 /**
  * ���ܷ�������������
  * @param codeStr
  * @return
  */
 public String getDecodeString(String codeStr){
  BASE64Decoder base64De = new BASE64Decoder();
  byte[] miwen=null;
  byte[] mingwen=null;
  String resultData="";//���ص�����
  Cipher cipher;
  try{
   miwen=base64De.decodeBuffer(codeStr);
   cipher=Cipher.getInstance(DesUtil.algorithm);
   cipher.init(Cipher.DECRYPT_MODE, this.getKey());
   mingwen=cipher.doFinal(miwen);
   resultData = new String(mingwen,"UTF-8");
  }catch(Exception e){
   return "��Կ����ȷ������ԭ�����쳣���޷����ܣ�";
  }
  return resultData;
 }
  //������ת�ַ���
 public String byte2hex(byte[] b) {
  String hs = "";
  String stmp = "";
  for (int n = 0; n < b.length; n++) {
   stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
   if (stmp.length() == 1)
    hs = hs + "0" + stmp;
   else
    hs = hs + stmp;
   if (n < b.length - 1)
    hs = hs + ":";
  }
  return hs.toUpperCase();
 }
 public SecretKey getKey() {
  return key;
 }
 public void setKey(SecretKey key) {
  this.key = key;
 }
 public static String getAlgorithm() {
  return algorithm;
 }
 public static void setAlgorithm(String algorithm) {
  algorithm = algorithm;
 	}
}