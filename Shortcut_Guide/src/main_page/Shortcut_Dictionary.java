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
		
		shortcut_lb = new JLabel();
		shortcut_lb.setSize(300, 30);
		shortcut_lb.setFont(new Font("Calibri", Font.PLAIN, 30));
		shortcut_lb.setLocation(10, 40);
		c.add(shortcut_lb);
		
		JButton prev_btn = new JButton("< Prev");
		prev_btn.setSize(200, 40);
		prev_btn.setFont(new Font("Calibri", Font.PLAIN, 30));
		prev_btn.setLocation(150, 450);
		prev_btn.addActionListener(new PrevBtnAction());
		c.add(prev_btn);
		
		JButton next_btn = new JButton("Next >");
		next_btn.setSize(200, 40);
		next_btn.setFont(new Font("Calibri", Font.PLAIN, 30));
		next_btn.setLocation(700, 450);
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
			
			for(int i = cnt; i > cnt - 1; i--)
			{
				shortcut_lb.setText(sc.get(i));
			}
			if(cnt == 0) cnt = sc.size()-1;
			else cnt--;
		}
	}
	
	private class NextBtnAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			JButton b = (JButton)e.getSource();
			
			for(int i = cnt; i < cnt + 1; i++)
			{
				shortcut_lb.setText(sc.get(i));
			}
			if(cnt==sc.size()-1) cnt = 0;
			else cnt++;
		}
	}
	
	public static void main(String[] args) 
	{
		
	}
}
