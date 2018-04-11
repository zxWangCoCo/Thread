package com.atguigu.exe;

public enum Seasons {
	SPRI(1,"春天","小雨"),SUM(2,"夏天","大雨"),AUTU(3,"秋天","干旱"),WIN(4,"冬天","大雪");
	private Integer num;
	private String sea;
	private String wea;

	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getSea() {
		return sea;
	}
	public void setSea(String sea) {
		this.sea = sea;
	}
	public String getWea() {
		return wea;
	}
	public void setWea(String wea) {
		this.wea = wea;
	}
	//遍历枚举
	public static Seasons forEachEnum(Integer num) {
		for (Seasons season : values()) {
			if (season.getNum()==num) {
				return season;
			}
		}
		return null;
	}
	//设置枚举的构造方法
	private Seasons(Integer num, String sea, String wea) {
		this.num = num;
		this.sea = sea;
		this.wea = wea;
	}
	
}