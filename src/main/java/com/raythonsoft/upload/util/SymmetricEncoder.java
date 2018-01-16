package com.raythonsoft.upload.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

/**
 * Created by Anur IjuoKaruKas on 2017/11/24.
 * Description :
 */
public class SymmetricEncoder {

    private static SecretKeySpec getKey(String strKey) throws Exception {
        byte[] arrBTmp = strKey.getBytes();
        byte[] arrB = new byte[16]; // 创建一个空的16位字节数组（默认值为0）

        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(arrB, "AES");
        return secretKeySpec;
    }

    public static String AESEncode(String strKey, String content) {
        String result = null;
        try {
            SecretKeySpec secretKeySpec = getKey(strKey);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);
            byte[] encrypted = cipher.doFinal(content.getBytes());
            result = Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String AESDecode(String strKey, String content) {
        String result = null;
        try {
            SecretKeySpec secretKeySpec = getKey(strKey);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
            byte[] decode = Base64.getDecoder().decode(content);
            byte[] original = cipher.doFinal(decode);
            result = new String(original);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        SymmetricEncoder se = new SymmetricEncoder();
        Scanner scanner = new Scanner(System.in);
        /*
         * 加密
         */
        System.out.println("使用AES对称加密，请输入加密的规则");
        String encodeRules = scanner.next();
        System.out.println("请输入要加密的内容:");
        String content = scanner.next();
        System.out.println("根据输入的规则" + encodeRules + "加密后的密文是:" + se.AESEncode(encodeRules, content));

        /*
         * 解密
         */
        System.out.println("使用AES对称解密，请输入加密的规则：(须与加密相同)");
        encodeRules = scanner.next();
        System.out.println("请输入要解密的内容（密文）:");
        content = scanner.next();
        System.out.println("根据输入的规则" + encodeRules + "解密后的明文是:" + se.AESDecode(encodeRules, content));
    }

}
