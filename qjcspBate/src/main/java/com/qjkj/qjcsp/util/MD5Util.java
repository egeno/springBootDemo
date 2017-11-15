package com.qjkj.qjcsp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 类名:MD5Util
 * 创建者:yjg
 * 版本号：V1.0
 * 日期：2015-12-29
 * MD5加密工具类
 */
public class MD5Util {

	/**
	 * 默认的密码字符串组合，用来将字节转换成 16 进制表示的字符,apache校验下载的文件的正确性用的就是默认的这个组合
	 */
	protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6',

			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	protected static MessageDigest messagedigest = null;
	
	//MD5加密初始化
	static 
	{
		try {
			messagedigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException nsaex) {
			System.err.println(MD5Util.class.getName() + "初始化失败，MessageDigest不支持MD5Util。");
			nsaex.printStackTrace();
		}
	}

	/**
	 * 生成字符串的md5校验值
	 * 
	 * @param s
	 * @return
	 */
	public static String getMD5String(String s) {
		return getMD5String(s.getBytes());
	}

	/**
	 * 带Hex的MD5加密
	 * 
	 * @param s
	 * @return
	 */
	public static String getMD5String2(String s) {
		return DigestUtils.md5Hex(s);
	}

	/**
	 * 
	 * 判断字符串的md5校验码是否与一个已知的md5码相匹配
	 * 
	 * @param password  要校验的字符串
	 * @param md5PwdStr 已知的md5校验码
	 * @return
	 */
	public static boolean checkMd5Code(String md5, String md5PwdStr) {
		return md5.equals(md5PwdStr);
	}

	/**
	 * 生成文件的md5校验值
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String getFileMD5String(File file) throws IOException 
	{
		InputStream fis;
		byte[] buffer = new byte[1024];
		int numRead = 0;

		fis = new FileInputStream(file);
		while ((numRead = fis.read(buffer)) > 0) {
			messagedigest.update(buffer, 0, numRead);
		}
		fis.close();
		return bufferToHex(messagedigest.digest());

	}

	public static String getMD5String(byte[] bytes) {
		messagedigest.update(bytes);
		return bufferToHex(messagedigest.digest());
	}

	private static String bufferToHex(byte bytes[]) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte bytes[], int m, int n) {
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;

		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], stringbuffer);
		}

		return stringbuffer.toString();

	}

	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
		char c0 = hexDigits[(bt & 0xf0) >> 4];// 取字节中高 4 位的数字转换, >>>
												// 为逻辑右移，将符号位一起右移,此处未发现两种符号有何不同
		char c1 = hexDigits[bt & 0xf];// 取字节中低 4 位的数字转换
		stringbuffer.append(c0);
		stringbuffer.append(c1);
	}
	public static void main(String args[]) {
		String s = new String("123456");
		System.out.println("原始：" + s);
		System.out.println("MD5后：" + getMD5String(s));
		System.out.println("MD5后：" + getMD5String2(s));
	}

}