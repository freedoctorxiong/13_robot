
package com.yscoco.robot.util;



import com.yscoco.robot.Exception.BizException;
import com.yscoco.robot.common.result.Code;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigDecimal;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 作者：karl.wei
 * 创建日期： 2017/1/24 0024 16:19
 * 邮箱：karl.wei@yscoco.com
 * 类介绍：AES加密
 */


public class AESUtils {

    public static final String VIPARA = "2019121010122019";/*初始化向量参数*/
    public static final String AESPassword = "yscoco0000000007";/*私钥*/

    /**
     * 加密
     *
     * @param content 需要加密的内容
     * @return
     */
    public static byte[] encrypt(String content) {
        try {
            IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
            SecretKeySpec key = new SecretKeySpec(AESPassword.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
            byte[] encryptedData = cipher.doFinal(content.getBytes());
            return encryptedData; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param content 待解密内容
     * @return
     */
    public static byte[] decrypt(byte[] content) {
        try {
            IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
            SecretKeySpec key = new SecretKeySpec(AESPassword.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
            byte[] encryptedData = cipher.doFinal(content);
            return encryptedData; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String toHex(byte[] buf) {
        if (buf == null) {

            return "";
        }
        StringBuffer result = new StringBuffer(2 * buf.length);
        for (int i = 0; i < buf.length; i++) {
            appendHex(result, buf[i]);
        }
        return result.toString();
    }

    private static void appendHex(StringBuffer sb, byte b) {
        final String HEX = "0123456789ABCDEF";
        sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
    }

   /* *//**
     * 生成加密字符串
     *
     * @param url    网络请求链接
     * @param params 请求参数
     *//*
    public static String encryptFirsth(String url, String... params) {
        String AESString = url + "?";
        if (params != null) {
            for (String param : params) {
                AESString += param + "&";
            }
        }
        //return AESString;
        return AESString + "yscoco=" + DateUtils.format(new Date(), DateUtils.DATE_FULL_STR);
    }*/




    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    public static boolean getBoolean(String json) {
        return (json != null && json.length() > 0);
    }

    @SuppressWarnings({ "static-access", "unused" })
    public static Map<String,String> getResult(String json){
        Map<String, String> m = new HashMap<String, String>();
        long longDate = 0 ;
        if(getBoolean(json)){
            byte[] decryptFrom =new Base64().decode(json);
            byte[] decryptResult = AESUtils.decrypt(decryptFrom);
            String postUrl = new String(decryptResult);
            String postUrlLittle = postUrl.substring(postUrl.lastIndexOf("?")+1);
            String[] strs = postUrlLittle.split("&");
            for(String s:strs){
                String[] ms = s.split("=");
                if(ms.length>1){
                    m.put(ms[0], ms[1]);//包装金map结构中
                }
            }
            // 判断数据是否出现问题
            if(m.get("yscoco") == null){throw new BizException(Code.ERROR);}
        }

//    	if(longDate == 0 || (System.currentTimeMillis() - longDate)>(2 * 1000 * 60)){
//    		m = null;
//    		throw new BizException(Code.ERROR);
//		}
        return m;
    }

    @SuppressWarnings({"unchecked", "unused"})
    public static <E> E getResultByKey(String key, Map<String, String> map, Class<E> clazz) {
        try {
            E e = null;
            String res = map.get(key);
            if (StringUtils.isNotEmpty(res)) {
                if ("Integer".equals(clazz.getSimpleName())) {
                    return (E) new Integer(res);
                } else if ("String".equals(clazz.getSimpleName())) {
                    return (E) res;
                } else if ("Double".equals(clazz.getSimpleName())) {
                    return (E) new Double(res);
                } else if ("Long".equals(clazz.getSimpleName())) {
                    return (E) new Long(res);
                } else if ("BigDecimal".equals(clazz.getSimpleName())) {
                    return (E) new BigDecimal(res);
                } else if (clazz.isEnum()) {
                    return EnumConverter.StingToEnum(res, clazz);
                } else {
                    throw new BizException(Code.UNKNOWCLASS);
                }
            } else {
                return null;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

}
