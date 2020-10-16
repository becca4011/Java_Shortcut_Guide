package crawling;

import java.io.IOException;

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
		
	}
	public List<Object> shortcut()
	{
		String URL = "http://visualstudioshortcuts.com/2013/";
		Document doc = null;
		List<Object> sc = new ArrayList<Object>();
		int cnt = 0;
		
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
		
		return sc; // 총 813개의 단축키가 있음
	}
}
