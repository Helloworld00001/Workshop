package changcheng.lin.java.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author greatwall
 */
public class DateDemo {

	public static void main(String[] args) throws Exception {
		String pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		System.out.println(dateFormat.format(new Date()));


		String strDate = "2019-08-13T19:00:00Z";
		System.out.println(dateFormat.parse(strDate));
	}
}
