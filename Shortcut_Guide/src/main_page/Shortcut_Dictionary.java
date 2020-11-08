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
		
		JButton next_btn = new JButton("Next >");
		next_btn.setSize(200, 40);
		next_btn.setFont(new Font("Calibri", Font.PLAIN, 30));
		next_btn.setLocation(10, 450);
		next_btn.addActionListener(new NextBtnAction());
		c.add(next_btn);
		
		setSize(1100, 700);
		setVisible(true);
	}
	
	private class NextBtnAction implements ActionListener
	{
		int cnt = 0;
		@Override
		public void actionPerformed(ActionEvent e)
		{
			JButton b = (JButton)e.getSource();
			
			for(int i = cnt; i < cnt + 1; i++)
			{
				shortcut_lb.setText(sc.get(i));
			}
			cnt++;
			
			if(cnt < sc.size())
				b.setText("Next >");
			else
			{
				cnt = 0;
				b.setText("First");
			}
		}
	}
	
	public static void main(String[] args) 
	{
		
	}
}
