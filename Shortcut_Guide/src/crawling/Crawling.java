package crawling;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.ArrayList;

public class Crawling 
{
	public static void main(String[] args) 
	{
		String URL = "http://visualstudioshortcuts.com/2013/";
		Document doc = null;
		List<String> sc = new ArrayList<String>();
		
		try 
		{
			doc = Jsoup.connect(URL).get();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		Elements elem = doc.select("dl");
		for(Element el : elem.select("dd")) // dd를 넣으면 단축키, dt를 넣으면 단축키 기능
		{
			sc.add(el.text());
		}
		
		try 
		{
		    FileOutputStream fos = new FileOutputStream(new File("shortcut/shortcut.txt"));
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);
			
			for(String shortcuts : sc) {
				bw.write(shortcuts);
				bw.newLine();
			}
			
			bw.close();
			osw.close();
			fos.close();
		} 
		catch (Exception e) 
		{
			e.getStackTrace();
		}
	}
}
