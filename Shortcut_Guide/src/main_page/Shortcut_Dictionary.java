package main_page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Shortcut_Dictionary extends JFrame
{
	static LinkedList<String[]> sc = new LinkedList<>();
	JLabel shortcut_lb = new JLabel();
	JLabel explain_lb = new JLabel();
	JLabel page_lb = new JLabel();
	boolean isNext = false;
	boolean isPrev = false;
	private int cnt = 0;
	private int pageCnt = 1;
	
	public Shortcut_Dictionary()
	{
		try
		{
			File read = new File("shortcut/shortcut.txt");
			FileReader fr = new FileReader(read);
			BufferedReader br = new BufferedReader(fr);
			
			StringBuilder input = new StringBuilder();
			// input에 두 줄을 ※로 나눠놓고, split으로 ※를 기준으로 쪼개어 추가한다
			while(true) {
				input.append(br.readLine()+"※");
				if(input.toString().equals("null※")) break;
				input.append(br.readLine());
				sc.add(input.toString().split("※"));
				input.replace(0, input.length(), "");
			}
			if(fr != null) fr.close();
			if(br != null) br.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		setTitle("Shortcut Dictionary");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		setLayout(null);
		
		page_lb = new JLabel(Integer.toString(cnt + 1));
		page_lb.setSize(50, 30);
		page_lb.setFont(new Font("Calibri", Font.PLAIN, 30));
		page_lb.setLocation(10, 30);
		c.add(page_lb);
		
		shortcut_lb = new JLabel(sc.get(0)[0]);
		shortcut_lb.setSize(1000, 50);
		shortcut_lb.setFont(new Font("Calibri", Font.PLAIN, 50));
		shortcut_lb.setLocation(10, 70);
		c.add(shortcut_lb);
		
		explain_lb = new JLabel(sc.get(0)[1]);
		explain_lb.setSize(1000, 100);
		explain_lb.setFont(new Font("나눔바른고딕", Font.PLAIN, 30));
		explain_lb.setLocation(10, 100);
		c.add(explain_lb);
		
		JButton prev_btn = new JButton("< Prev");
		prev_btn.setSize(200, 40);
		prev_btn.setFont(new Font("Calibri", Font.PLAIN, 30));
		prev_btn.setLocation(150, 450);
		prev_btn.addActionListener(new PrevBtnAction());
		c.add(prev_btn);
		
		JButton next_btn = new JButton("Next >");
		next_btn.setSize(200, 40);
		next_btn.setFont(new Font("Calibri", Font.PLAIN, 30));
		next_btn.setLocation(750, 450);
		next_btn.addActionListener(new NextBtnAction());
		c.add(next_btn);
		
		setSize(1100, 700);
		setResizable(false);
		setVisible(true);
	}
	
	private class PrevBtnAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			cnt--;
			pageCnt--;
			if(cnt == -1) 
			{
				cnt = sc.size() - 1; // cnt가 -1인 경우 cnt를 sc의 요소 개수 - 1로 함
			}
			if(pageCnt == 0) 
			{
				pageCnt = sc.size(); // pageCnt가 0인 경우 pageCnt를 sc의 요소 개수로 함
			}
			page_lb.setText(Integer.toString(pageCnt));
			shortcut_lb.setText(sc.get(cnt)[0]); // 단축키
			explain_lb.setText(sc.get(cnt)[1]); // 기능 
		}
	}
	
	private class NextBtnAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			cnt++;
			pageCnt++;
			if(cnt == sc.size()) 
			{
				cnt = 0; // cnt가 sc의 요소 개수와 같으면 cnt를 0으로 함
			}
			if(pageCnt == sc.size() + 1) 
			{
				pageCnt = 1; // pageCnt가 sc의 요소 개수 + 1과 같으면 pageCnt를 1로 함
			}
			page_lb.setText(Integer.toString(pageCnt));
			shortcut_lb.setText(sc.get(cnt)[0]); // 단축키
			explain_lb.setText(sc.get(cnt)[1]); // 기능 
		}
	}
	
	public static void main(String[] args) 
	{
		new Shortcut_Dictionary();
	}
}