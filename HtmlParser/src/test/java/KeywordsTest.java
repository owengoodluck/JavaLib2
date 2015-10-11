import java.io.File;

import org.junit.Test;

import com.owen.htmlparser.StringUtil;

public class KeywordsTest {

	@Test
	public void testGetKeyWord() throws Exception{
		File file=new File("C:/Users/owen/git/HtmlParser/src/test/resources/keywords.txt");
		StringUtil.uniqueKeyword(file);
	}
}
