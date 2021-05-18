package ImageCrawl; 

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * <pre>
 * ImageCrawl 
 * ImageJsoup.java
 *7472
 * Ό³Έν :
 * </pre>
 * 
 * @since : 2020. 11. 29.
 * @author : ymg74
 * @version : v1.0
 */
public class ImageJsoup {
	public byte[] recoverImageFromUrl(String urlText) throws Exception {
		URL url = new URL(urlText);
		ByteArrayOutputStream output = new ByteArrayOutputStream();      
		try (InputStream inputStream = url.openStream()) {
			int n = 0;
			byte [] buffer = new byte[ 1024 ];
			while (-1 != (n = inputStream.read(buffer))) {
				output.write(buffer, 0, n);
			}
		}
		return output.toByteArray();
	}
	public String byteArrayToHex(byte[] a) {
		String encoded = Base64.getEncoder().encodeToString(a);
		return encoded;
	}

	public static void main(String[] args) throws Exception {
		ImageJsoup ij = new ImageJsoup();
		ArrayList<String> linkList = new ArrayList<String>();
		ArrayList<String> imgHexList = new ArrayList<String>();
		String url = "https://search.naver.com/search.naver?where=image&sm=tab_jum&query=%EA%B0%9C";
		Document doc = Jsoup.connect(url).get();
		Elements titles = doc.select("div.img_area._item img[src]");
		for(Element e : titles) {
			linkList.add(e.attr("data-source"));
			System.out.println(e.attr("data-source"));
		}
		
		for(String link : linkList) {
			byte[] asd = ij.recoverImageFromUrl(link);
			String hex = ij.byteArrayToHex(asd).replaceAll(" ", "");
			System.out.println(hex);
			imgHexList.add(hex);
		}


	}

}
