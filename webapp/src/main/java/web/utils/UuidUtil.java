package web.utils;

import java.util.UUID;

/**
 * 产生UUID随机字符串工具类
 */
public final class UuidUtil {
	private UuidUtil(){}
	public static String getUuid(){
		return UUID.randomUUID().toString().replace("-","");
	}

	public static String getUuidFileName(String fileName) {
		int index = fileName.lastIndexOf(".");
		String extension = fileName.substring(index);
		return UUID.randomUUID().toString().replace("-","")+extension;
	}
	/**
	 * 测试
	 */
	public static void main(String[] args) {
		System.out.println(getUuidFileName("1.jpg"));
//		System.out.println(UuidUtil.getUuid());
//		System.out.println(UuidUtil.getUuid());
//		System.out.println(UuidUtil.getUuid());
//		System.out.println(UuidUtil.getUuid());
	}
}
