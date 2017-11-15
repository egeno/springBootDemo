package com.qjkj.qjcsp.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.qjkj.qjcsp.core.utilmapper.JsonMapper;

/**
 * 类名:UploadImage
 * 创建者:yjg
 * 版本号：V1.0
 * 日期：2015-12-29
 * 图片上传工具类
 */
public class UploadImage {

	/**
	 * 
	 * @param request
	 * @param imageFile 上传的文件
	 * @param limitWidth 限制宽度
	 * @param limitHeight 限制高度
	 * @param targetFile 指定上传到的目录
	 * @throws IOException 
	 */
	public static void upload(ServletRequest request, ServletResponse response, MultipartFile imageFile, 
			Integer limitWidth, Integer limitHeight, String targetFile) throws IOException {
		
		Map<String,Object> map = new HashMap<String,Object>() ;
		try {
			//限制宽度和高度均不为空
			File srcFile = multipartToFile(imageFile);
			if (limitWidth != null && limitHeight != null){
				//判断上传图片尺寸是否符合要求
//				CommonsMultipartFile cf= (CommonsMultipartFile)imageFile; 
//		        DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
//		        File f = fi.getStoreLocation();
				BufferedImage image = ImageIO.read(srcFile) ;
				//只上传符合图片尺寸的图片
				if (image.getWidth() == limitWidth && image.getHeight() == limitHeight) {
					map = uploadImage(request, response, srcFile, targetFile);
				}else {
					map.put("message","图片尺寸不符合!") ;
					map.put("status",Boolean.FALSE) ;
				}
			}else {
				map = uploadImage(request, response, srcFile, targetFile);
			}
		}catch(Exception e){
			map.put("message","图片上传失败,请联系管理人员!") ;
			map.put("status",Boolean.FALSE) ;
		}

		//返回图片上传的结果提示
		JsonMapper jsonMapper = new JsonMapper() ;
		String result = jsonMapper.toJson(map) ;
		response.getWriter().print(result);
	}
	
	/**
	 * 上传图片
	 * @param request
	 * @param response
	 * @param imageFile
	 * @param targetFile
	 * @return
	 * @throws IOException
	 */
	private static Map<String, Object> uploadImage(ServletRequest request, ServletResponse response, File srcFile, String targetFile) throws IOException{
		Map<String,Object> map = new HashMap<String,Object>() ;

		String originalFilename = srcFile.getName() ;
		String[] splitName = originalFilename.split("\\.");
		String extName = splitName[splitName.length -1];
		String newName = Calendar.getInstance().getTimeInMillis() + "." + extName;
		String path = request.getServletContext().getRealPath("/");
		String realPath = path + "uploadImage/" + targetFile;
		
		File dir = new File(realPath);
		/*判断目录是否存在，不存在则创建*/
		if (!dir.exists()) {
			dir.mkdir();
		}
		File uploadFile = new File(realPath + "/" + newName);
		/*判断文件是否存在，不存在则创建*/
		if (!uploadFile.exists()) {
			uploadFile.createNewFile();
		}
//		imageFile.transferTo(uploadFile);
		copyFile(srcFile, uploadFile);
		
		map.put("message","图片上传成功!") ;
		map.put("status",Boolean.TRUE) ;
		map.put("imageName",newName) ;
		map.put("imageUrl",Config.getInstance().getString(targetFile+".prefix") + newName) ;

		
		return map;
	}
	
	/**
	 * MultipartFile转成File
	 * @param multipart
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	private static File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException 
	{
	        File convFile = new File(multipart.getOriginalFilename());
	        multipart.transferTo(convFile);
	        return convFile;
	}
	
	/**
	 * 复制文件
	 * @param srcFile
	 * @param uploadFile
	 */
	private static void copyFile(File srcFile, File uploadFile){
	      // 复制文件  
        int byteread = 0; // 读取的字节数  
        InputStream in = null;  
        OutputStream out = null;  
        try {  
            in = new FileInputStream(srcFile);  
            out = new FileOutputStream(uploadFile);  
            byte[] buffer = new byte[1024];  
            while ((byteread = in.read(buffer)) != -1) {  
                out.write(buffer, 0, byteread);  
            }  
        } catch (Exception e) {  
        	e.printStackTrace();
        } finally {  
            try {  
                if (out != null)  
                    out.close();  
                if (in != null)  
                    in.close();  
                //删除原文件
                srcFile.delete();
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
	}
}
