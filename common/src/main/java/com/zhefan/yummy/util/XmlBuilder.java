package com.zhefan.yummy.util;

import java.io.ByteArrayOutputStream;
import java.io.Reader;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * 
 * @author Reverien9@gmail.com
 * @date 2018年4月23日
 */

public class XmlBuilder {

	public static Object xmlStr2Object(Class<?> clazz, String xmlStr) throws Exception {
		Object xmlObject = null;
		JAXBContext context = JAXBContext.newInstance(clazz);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Reader reader = new StringReader(xmlStr);
		xmlObject = unmarshaller.unmarshal(reader);
		if(reader != null) {
			reader.close();
		}
		return xmlObject;
	}
	
	public static String object2XmlStr(Object obj) throws Exception {
		JAXBContext context = JAXBContext.newInstance(obj.getClass());
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		marshaller.marshal(obj, baos);  
		return new String(baos.toByteArray());
	}
	
}