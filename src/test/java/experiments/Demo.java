package experiments;

import java.util.Date;

public class Demo {
	
	public String generateTimeStamp() {
		Date date = new Date();
		String timestamp = date.toString().replace(" ", "_").replace(":", "_");
		return "Tarun" + timestamp+"Singh@gmail.com";
	}
	
	 public static void main(String[] args) {
		
		 Demo d = new Demo();
		String store = d.generateTimeStamp();
		System.out.println(store);
		
	}

}
