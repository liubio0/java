import java.io.*;
import java.lang.reflect.Method;

public class MyClassLoader extends ClassLoader{
	
	public static void main(String[] args) throws Exception {
		Class<?> clazz = new MyClassLoader().findClass("Hello");
		Object instance = clazz.getDeclaredConstructor().newInstance();
		Method method = clazz.getMethod("hello");
		method.invoke(instance);
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		File file=new File("Hello.xlass");
		try {
			FileInputStream fis = new FileInputStream(file);
			try{
				int length = fis.available();
				byte[] bytes = new byte[length];
				int i = 0;
				int k = 0;
				while((i = fis.read()) != -1){
					bytes[k] = (byte)((255 - i) & 0xff);
					k++;
				}
				return defineClass(name, bytes, 0, bytes.length);	
			} catch(IOException e ) {
				e.printStackTrace();
			} finally {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} 
		return null;
	}
}
