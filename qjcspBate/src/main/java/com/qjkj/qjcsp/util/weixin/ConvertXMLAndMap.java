package com.qjkj.qjcsp.util.weixin;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


public class ConvertXMLAndMap {

	public Map<String, Object> getMapfromXML (String xml) {
        try {  
            Map<String, Object> map = new HashMap<String, Object>();  
            Document document = DocumentHelper.parseText(xml);  
            Element nodeElement = document.getRootElement();  
            List node = nodeElement.elements();  
            for (Iterator it = node.iterator(); it.hasNext();) {  //解析xml为map类型
                Element elm = (Element) it.next();  
                map.put(elm.getName(), elm.getText());  
                elm = null;  
            }  
            node = null;  
            nodeElement = null;  
            document = null;  
            return map;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null; 
	}
	
	public String getXMLfromMap (Map<String, Object> map) {

		Document document = DocumentHelper.createDocument();  
        Element nodeElement = document.addElement("xml");  
        for (Object obj : map.keySet()) {  
            Element keyElement = nodeElement.addElement(String.valueOf(obj));  
            //keyElement.addAttribute("label", String.valueOf(obj));  
            keyElement.setText(String.valueOf(map.get(obj)));  
//            keyElement.setText("<![CDATA["+String.valueOf(map.get(obj))+"]]>");  
        }  
        String s = document.getRootElement().asXML();
//        return doc2String(document);  
        return s;  
		
	}
	
	public String getXMLfromMapInISO (Map<String, Object> map) {
		
		try{
			Document document = DocumentHelper.createDocument();  
	        Element nodeElement = document.addElement("xml");  
	        for (Object obj : map.keySet()) {  
	            Element keyElement = nodeElement.addElement(String.valueOf(obj));  
	            //keyElement.addAttribute("label", String.valueOf(obj));  
	            keyElement.setText(String.valueOf(map.get(obj)));  
//	            keyElement.setText("<![CDATA["+String.valueOf(map.get(obj))+"]]>");  
	        }  
	        String xmlstring = document.getRootElement().asXML();
	        return new String(xmlstring.toString().getBytes(), "GBK");  
		}catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
    public static String doc2String(Document document) {  
        String s = "";  
        try {  
            // 使用输出流来进行转化  
            ByteArrayOutputStream out = new ByteArrayOutputStream();  
            // 使用UTF-8编码  
            OutputFormat format = new OutputFormat("   ", true, "UTF-8");  
            XMLWriter writer = new XMLWriter(out, format);  
            writer.write(document);  
            //s = out.toString("UTF-8");  
            s= formatXML_cdata(out.toString("UTF-8"));
        } catch (Exception ex) {  
            ex.printStackTrace();  
        }  
        return s;  
    }  
    
    public static String formatXML_cdata(String inputXML) throws Exception {  
        SAXReader reader = new SAXReader();  
        Document document = reader.read(new StringReader(inputXML));  
        String requestXML = null;  
        XMLWriter xw = null;  
        if (document != null) {  
          try {  
              OutputFormat format = OutputFormat.createPrettyPrint();   
              format.setEncoding("UTF-8");   
              StringWriter sw = new StringWriter();   
              xw = new XMLWriter(sw, format);   
              xw.setEscapeText(false);  
              xw.write(document);  
              requestXML = sw.toString();  
              xw.flush();   
          } finally {  
            if (xw != null) {  
              try {  
                xw.close();  
              } catch (IOException e) {  
              }  
            }  
          }  
        }  
        return requestXML;  
    }  
    
    /**
     * 删除XML声明
     */
}
