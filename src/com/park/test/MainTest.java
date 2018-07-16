package com.park.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSONObject;

public class MainTest {

	public static void main(String[] args) throws IOException {
		File file = new File("F:\\dcs\\java_space\\CarPark\\src\\main\\resources\\protocal.json");
		InputStream input = new FileInputStream(file);
		
		String fcontent = IOUtils.toString(input,StandardCharsets.UTF_8);
		System.out.println(fcontent);
		
		JSONObject jsonObj = JSONObject.parseObject(fcontent);
		String loginStr = jsonObj.getJSONArray("login").toString();
		
		System.out.println(loginStr);
	}

}
