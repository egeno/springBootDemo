package com.qjkj.qjcsp.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;

public class CheckWordsUtil {
	/**
	 * 检测是否包含非法词 return -1不包含非法词 0包含非法词 1读取非法词库异常
	 */
	public static int checkWord(String text) {
		String filePath = CheckWordsUtil.class.getClassLoader().getResource("key.txt").getPath();
		File file = new File(filePath);
		try {
			// Long startTime = System.currentTimeMillis();
			InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String jsonText = bufferedReader.readLine() + "";
			bufferedReader.close();
			String[] jsonTextArray = StringUtils.split(jsonText, "|");
			for (int i = 0; i < jsonTextArray.length; i++) {
				if (StringUtils.indexOf(text, jsonTextArray[i]) != -1) {
					// Long endTime = System.currentTimeMillis();
					return 0;
				}
			}
			// Long endTime = System.currentTimeMillis();
			return -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 1;
		}
	}

	/*
	 * public static void write() { File file = new File("d:/key.txt"); if
	 * (file.exists()) { try { InputStreamReader inputStreamReader = new
	 * InputStreamReader(new FileInputStream(file), "UTF-8"); BufferedReader
	 * bufferedReader = new BufferedReader(inputStreamReader); String jsonText =
	 * bufferedReader.readLine() + ""; bufferedReader.close(); String[]
	 * jsonTextArray = StringUtils.split(jsonText, "|");
	 * 
	 * File file1 = new File("D:/b.txt"); if (!file.exists()) {
	 * file.createNewFile(); } FileWriter fileWriter = new FileWriter(file1);
	 * for (int i = 0; i < jsonTextArray.length; i++) {
	 * fileWriter.write(jsonTextArray[i] + "\r\n"); } fileWriter.flush();
	 * fileWriter.close(); } catch (Exception e) { // TODO Auto-generated catch
	 * block e.printStackTrace();
	 * 
	 * } } }
	 */
}
