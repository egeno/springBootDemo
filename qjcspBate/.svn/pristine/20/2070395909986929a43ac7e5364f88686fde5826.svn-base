package com.qjkj.qjcsp.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.swetake.util.Qrcode;

public class QRCodeGenerator {
	
	private final static Logger logger = LoggerFactory.getLogger(QRCodeGenerator.class); 
	
	public static long buildBarCodeTwo(File filePath,String fileName,String content,String imgType){
		long fileSizes = -1; 
		if(!filePath.exists()){
			logger.info("二维码目录：" + "[->]" + filePath.getName() + ",不存在") ;
			try {
				filePath.mkdirs() ;
				logger.info("二维码目录：" + "[->]" + filePath.getName() + ",创建成功!") ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("二维码目录：" + "[->]" + filePath.getName() + ",创建失败!",e) ;
			}
		}
		try {
			BufferedImage bufImg = qRCodeCommon(content, imgType, 8);  
			File imgFile = new File(filePath.getPath() + File.separator + fileName);  
			// 生成二维码QRCode图片  
			logger.info("二维码生成：" + "[->],bufImg=" + bufImg + ",imgType="+imgType+",imgFile="+imgFile) ;
			ImageIO.write(bufImg, imgType, imgFile);
			if(imgFile.exists()){
				fileSizes = imgFile.length() ;
				logger.info("二维码生成：" + "[->]" + fileName + ",创建成功!") ;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("二维码生成：" + "[->]" + fileName + ",创建失败1!",e) ;
		} catch (Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("二维码生成：" + "[->]" + fileName + ",创建失败2!",e) ;
		}
		return fileSizes ;
    }
	
	/** 
     * 生成二维码(QRCode)图片 
     * @param content 存储内容 
     * @param imgPath 图片路径 
     * @param imgType 图片类型 
     */  
    public static void encoderQRCode(String content, String imgPath, String imgType) {  
    	try {  
            BufferedImage bufImg = qRCodeCommon(content, imgType, 8);  
            File imgFile = new File(imgPath);  
            // 生成二维码QRCode图片  
            ImageIO.write(bufImg, imgType, imgFile);  
        } catch (Exception e) {  
        	logger.error("", e); 
        }   
    }
    /** 
     * 生成二维码(QRCode)图片的公共方法 
     * @param content 存储内容 
     * @param imgType 图片类型 
     * @param size 二维码尺寸 
     * @return 
     */  
    private static BufferedImage qRCodeCommon(String content, String imgType, int size) {  
        BufferedImage bufImg = null;  
        try {  
            Qrcode qrcodeHandler = new Qrcode();  
            // 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小  
            //qrcodeHandler.setQrcodeErrorCorrect('M');  
            //qrcodeHandler.setQrcodeEncodeMode('B');  
           qrcodeHandler.setQrcodeErrorCorrect('H');
            
            // 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大  
            qrcodeHandler.setQrcodeVersion(size);  
            // 获得内容的字节数组，设置编码格式  
            byte[] contentBytes = content.getBytes("utf-8");  
            // 图片尺寸  
            int imgSize = 67 + 12 * (size - 1);  
            bufImg = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);  
            Graphics2D gs = bufImg.createGraphics();  
            // 设置背景颜色  
            gs.setBackground(Color.WHITE);  
            gs.clearRect(0, 0, imgSize, imgSize);  
  
            // 设定图像颜色> BLACK  
            gs.setColor(Color.BLACK);  
            // 设置偏移量，不设置可能导致解析出错  
            int pixoff = 2;  
            // 输出内容> 二维码  
            /*if (contentBytes.length > 0 && contentBytes.length < 800) {  
                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);  
                for (int i = 0; i < codeOut.length; i++) {  
                    for (int j = 0; j < codeOut.length; j++) {  
                        if (codeOut[j][i]) {  
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);  
                        }  
                    }  
                }  
            } else {  
                throw new Exception("QRCode content bytes length = " + contentBytes.length + " not in [0, 800].");  
            }
            */
                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);  
                for (int i = 0; i < codeOut.length; i++) {  
                    for (int j = 0; j < codeOut.length; j++) {  
                        if (codeOut[j][i]) {  
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);  
                        }  
                    }  
                }
            gs.dispose();  
            bufImg.flush();  
        } catch (Exception e) {  
        	logger.error("", e);
        }  
        return bufImg;  
    }
    
    public static void main(String args[])
    {
    	buildBarCodeTwo(new File("D:/"),"weixin1.png","weixin://wxpay/bizpayurl?pr=oUB7km1","png");
    }
}
