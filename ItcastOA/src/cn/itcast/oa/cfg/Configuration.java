package cn.itcast.oa.cfg;

public class Configuration {
	
	private static int pageSize;
	
	
	
	static{
		//从配置文件中读取
		pageSize=10;
	}



	public static int getPageSize() {
		return pageSize;
	}

	
	
	

}
