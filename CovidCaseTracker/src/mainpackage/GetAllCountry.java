package mainpackage;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class GetAllCountry {

	public static void main(String[] args) throws Exception
	{
		
		// TODO Auto-generated method stub
		
		String url="https://www.worldometers.info/coronavirus/country/india/";
		Document doc=Jsoup.connect(url).get();
		
		Elements elements=doc.select(".mt_a");
		 Elements img = doc.getElementsByTag("img");
		 
         for (org.jsoup.nodes.Element el : img) {

             //for each element get the srs url
             String src = el.absUrl("src");

             System.out.println("Image Found!");
             System.out.println("src attribute is : "+src);
				if(src.contains("flags"))
				{
//				             saveImage(src);
				}
	   }
         func();
		
//         downloadimage("https://www.worldometers.info/img/worldometers-logo-footer.png");
	}
	 private static void getImages(String src) throws IOException {
		 
	        String folder = null;
	 
	        //Exctract the name of the image from the src attribute
	        int indexname = src.lastIndexOf("/");
	 
	        if (indexname == src.length()) {
	            src = src.substring(1, indexname);
	        }
	 
	        indexname = src.lastIndexOf("/");
	        String name = src.substring(indexname, src.length());
	 
	        System.out.println(name);
	 
	        //Open a URL Stream
	        URL url = new URL(src);
	        InputStream in = url.openStream();
	 
	        OutputStream out = new BufferedOutputStream(new FileOutputStream( "./image/"+"imageofcountry.png"));
	 
	        for (int b; (b = in.read()) != -1;) {
	            out.write(b);
	        }
	        out.close();
	        in.close();
	 
	    }
	        
	 public static void saveImage(String imageUrl) throws IOException {
			URL url = new URL(imageUrl);
			String fileName = url.getFile();
			String destName = "C:/Users/Sohansinh Rathod/eclipse-workspace/CovidCaseTracker/image" + fileName.substring(fileName.lastIndexOf("/"));
			System.out.println(destName);
		 
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(destName);
		 
			byte[] b = new byte[2048];
			int length;
		 
			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}
		 
			is.close();
			os.close();
		}
	 public static void downloadimage(String linkurl) throws IOException
	 {
		 	URL url = new URL(linkurl);
	        BufferedImage img = ImageIO.read(url);
	        String fileName = url.getFile();
			String destName = "C:/Users/Sohansinh Rathod/eclipse-workspace/CovidCaseTracker/image" + fileName.substring(fileName.lastIndexOf("/"));
	        File file = new File(destName);
	        ImageIO.write(img, "jpg", file);
	 } 
	static void func() throws IOException
	{
		URL url = new URL("http://cdn.countryflags.com/thumbs/india/flag-800.png");
        BufferedImage img = ImageIO.read(url);
        File file = new File("C:/Users/Sohansinh Rathod/eclipse-workspace/CovidCaseTracker/image/download2.png");
        ImageIO.write(img, "png", file);
	}
	

}
