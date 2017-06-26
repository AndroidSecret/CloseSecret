package com.secret.util;

import java.util.Random;
import java.util.UUID;

public class Token {	//token生成工具类

	public static String generate(){//使用UUID算法生成
		return generateValue(UUID.randomUUID().toString());
	}

	public static String generateValue(String param) {
		return UUID.fromString(UUID.nameUUIDFromBytes(param.getBytes()).toString()).toString();
	}
}
