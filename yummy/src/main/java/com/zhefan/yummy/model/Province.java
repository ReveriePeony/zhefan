package com.zhefan.yummy.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 * 
 * @author Reverien9@gmail.com
 * @date 2018年4月23日
 */
@Data
@XmlRootElement(name = "province")
@XmlAccessorType(XmlAccessType.FIELD)
public class Province {

	@XmlAttribute(name = "id")
	private String id;
	
	@XmlAttribute(name = "name")
	private String name;
	
	@XmlElement(name = "city")
	private List<City> cityList;
}
