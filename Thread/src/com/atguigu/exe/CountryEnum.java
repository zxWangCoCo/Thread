package com.atguigu.exe;
/**
 * 枚举类可以一值多键，可以出巡对象的属性，相当于一个小数据库
 * 重点：
 * 	枚举的属性，构造方法及枚举的遍历
 * 
 *
 */
public enum CountryEnum {
	ONE(1,"韩国"),TWO(2,"燕国"),THREE(3,"楚国"),FOUR(4,"齐国"),FIVE(5,"魏国"),SIX(6,"赵国");
	//定义键值的属性
	private Integer retCode;
	private String retMsg;
	//枚举的构造方法
	private CountryEnum(Integer retCode, String retMsg) {
		this.retCode = retCode;
		this.retMsg = retMsg;
	}
	//set get方法
	public Integer getRetCode() {
		return retCode;
	}
	public void setRetCode(Integer retCode) {
		this.retCode = retCode;
	}
	public String getRetMsg() {
		return retMsg;
	}
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	//遍历枚举集合-oo
	public static CountryEnum forCountry(Integer i) {
		for (CountryEnum element : values()) {
			if(element.getRetCode() == i) {
				return element;
			}
		}
		return null;
	}
}
