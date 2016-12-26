package teststudy.com.joypupil.test;

public class TestMethod {
	public TestMethod() {
		try {
			DownFileInfoBean bean = new DownFileInfoBean(
					"http://localhost:8080/api/down/Gamersky.0713.PC500.rar", "D:\\temp",
					"test.rar", 5,true,null);
			/*File file = new File("D:\\dan07.apk");
			DownFileInfoBean bean = new DownFileInfoBean(null, "D:\\temp",
					"dan07.apk", 3,false,file);*/
			DownFileFetch fileFetch = new DownFileFetch(bean);
			fileFetch.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public static void main(String[] args) {
//		new TestMethod();
//		System.out.println(Long.valueOf("aaa"));
//	}
	
}
