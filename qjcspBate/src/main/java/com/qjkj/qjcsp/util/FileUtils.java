package com.qjkj.qjcsp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;

/**
 * 类名:FileUtils
 * 创建者:yjg
 * 版本号：V1.0
 * 日期：2015-12-29
 * 文件操作共用类
 */
public class FileUtils {

	/**
	 * @function 根据文件名创建文件
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static boolean createFile(String filename) throws IOException {
		File file = new File(filename);
		boolean b = false;
		if (!file.exists())//如果文件存在，则创建
		{
			File parent = file.getParentFile();
			if (!parent.exists()) {
				parent.mkdirs();
			}
			b = file.createNewFile();
		}
		return b;
	}

	/**
	 * @function 根据文件名删除文件
	 * @param filename
	 * @return
	 */
	public static boolean deleteFile(String filename) {
		return new File(filename).delete();
	}

	/**
	 * @function 根据文件路径删除目录
	 * @param filepath
	 * @throws IOException
	 */
	public static void deleteDirectory(String filepath) throws IOException {
		File f = new File(filepath);
		if (f.exists() && f.isDirectory()) {
			if (f.listFiles().length == 0)//如果目录为空，则直接删除 
			{
				f.delete();
			} 
			else//如果目录不为空，则先删除目录下的所有文件，再删除目录
			{
				File[] delFile = f.listFiles();
				for (int index = 0; index < f.listFiles().length; index++) {
					if (delFile[index].isDirectory()) {
						deleteDirectory(delFile[index].getAbsolutePath());
					}
					delFile[index].delete();
				}
			}
			deleteDirectory(filepath);
		}
	}

	/**
	 * @function 在指定文件中写入或更新文本
	 * @param file
	 * @param txt
	 * @param appent 覆盖或后增标志
	 * @return
	 * @throws IOException
	 */
	public static boolean writeFile(String file, String txt, boolean appent) throws IOException {
		File f = new File(file);
		if (!f.exists()) {
			createFile(file);
		}
		FileWriter writer = new FileWriter(f, appent);
		writer.write(txt);
		writer.close();
		return true;
	}

	/**
	 * @function 在指定文件中写入或更新指定编码格式的文本
	 * @param file
	 * @param txt
	 * @param encoding
	 * @return
	 * @throws IOException
	 */
	public static boolean writeFile(String file, String txt, String encoding) throws IOException {
		File f = new File(file);
		if (!f.exists()) {
			createFile(file);
		}
		FileOutputStream fos = new FileOutputStream(file, true);
		OutputStreamWriter os = new OutputStreamWriter(fos, encoding == null ? "UTF-8" : encoding);
		os.write(txt);
		os.close();
		fos.close();
		return true;
	}

	/**
	 * @function 在指定文件中更新对象
	 * @param obj
	 * @param filename
	 * @throws IOException
	 */
	@Deprecated
	public static void writeObjectToFile(Object obj, String filename) throws IOException {
		FileUtils.deleteFile(filename);
		final FileOutputStream fos = new FileOutputStream(filename);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(obj);
		fos.close();
		oos.close();
	}

	/**
	 * @function 将指定文件中读取为对象
	 * @param filename
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@Deprecated
	public static Object readObjectFromFile(String filename) throws IOException, ClassNotFoundException {
		final FileInputStream fis = new FileInputStream(filename);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object obj = ois.readObject();
		fis.close();
		ois.close();
		return obj;
	}

	/**
	 * 获取文件名
	 * 
	 * @param path
	 * @return
	 */
	public static String getFileName(String path) {
		int len = path.lastIndexOf(File.separator);
		return path.substring(len + 1);
	}

	/**
	 * 获取文件名 前缀
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFilePrefix(String fileName) {
		int len = fileName.lastIndexOf(".");
		return fileName.substring(0, len);
	}

	/**
	 * 获取文件名 后缀(文件类型）
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileSuffix(String fileName) {
		int len = fileName.lastIndexOf(".");
		return fileName.substring(len + 1);
	}

}
