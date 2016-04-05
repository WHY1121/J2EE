package cn.itcast.oa.test;

import java.io.File;

/**
 * 递归读取一个目录下的所有文件名
 * 
 * @author fsdfsdsss
 * 
 */
public class fileInput {

	public static void main(String[] args) {

		File file1 = new File("F:\\J2EE\\ItcastOA\\WebRoot\\WEB-INF\\lib");
		readFile(file1);

	}

	public static void readFile(File file) {
		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				System.out.println(files[i].getName());
			}

			if (files[i].isDirectory()) {
				readFile(files[i]);

			}
		}
	}

}
