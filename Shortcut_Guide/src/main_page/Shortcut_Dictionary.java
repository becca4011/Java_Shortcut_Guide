package main_page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Shortcut_Dictionary extends JFrame
{
	static List<String> sc = new ArrayList<String>();
	JLabel shortcut_lb = new JLabel();
	JLabel explain_lb = new JLabel();
	JLabel page_lb = new JLabel();
	private int cnt = 0;
	
	public Shortcut_Dictionary()
	{
		try
		{
			File read = new File("shortcut/shortcut.txt");
			FileReader fr = new FileReader(read);
			BufferedReader br = new BufferedReader(fr);
			
			String s;
			// 파일에서 한 줄씩 읽어와 list에 저장
			while((s = br.readLine()) != null)
			{
				sc.add(s);
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
		
		shortcut_lb = new JLabel(sc.get(0));
		shortcut_lb.setSize(1000, 50);
		shortcut_lb.setFont(new Font("Calibri", Font.PLAIN, 50));
		shortcut_lb.setLocation(10, 70);
		c.add(shortcut_lb);
		
		explain_lb = new JLabel(sc.get(1));
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
			JButton b = (JButton)e.getSource();
			
			if (cnt == 0)
				cnt = sc.size(); // cnt가 0일 때, 리스트의 크기를 cnt로 함
			
			explain_lb.setText(sc.get(--cnt)); // 기능 
			shortcut_lb.setText(sc.get(--cnt)); // 단축키
			page_lb.setText(Integer.toString(cnt + 1));
		}
	}
	
	private class NextBtnAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			JButton b = (JButton)e.getSource();
			
			if (cnt == sc.size() - 1)
				cnt = 0; // cnt에 1을 증가시킨 것이 리스트의 크기와 같을 경우 cnt를 0으로 함
			
			explain_lb.setText(sc.get(++cnt)); // 기능(수정해야 함)
			shortcut_lb.setText(sc.get(++cnt)); // 단축키
			page_lb.setText(Integer.toString(cnt + 1));
		}
	}
	
	public static void main(String[] args) 
	{
		new Shortcut_Dictionary();
	}
}
