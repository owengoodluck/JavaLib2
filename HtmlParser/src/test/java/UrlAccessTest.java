import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.owen.htmlparser.HttpClientUtil;

public class UrlAccessTest {

	@Test
	public void testAccessUrl() throws Exception{
		String baseUrl="http://www.amazon.com/Elegant-Wallet-Leather-Cover-Iphone/dp/B015LZ6XW2/ref=sr_1_1?ie=UTF8&qid=1442842044&sr=8-1&keywords";
		Set<String> urlSet = new HashSet<String>();
		urlSet.add(baseUrl+"iphone+5c+cases");
		urlSet.add(baseUrl+"iphone+5c+case");
		urlSet.add(baseUrl+"iphone+case");
		urlSet.add(baseUrl+"iphone+cases");
		int count=1;
		for(int i=0;i<200;i++){
			for(String url:urlSet){
				HttpClientUtil.accessUrl(url);
				System.out.println(count++);
			}
		}
	}
}
