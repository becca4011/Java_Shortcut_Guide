package main_page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Shortcut_Dictionary extends JFrame
{
	JLabel [] shortcut_lb = new JLabel[10];
	
	public Shortcut_Dictionary()
	{
		setTitle("Shortcut Dictionary");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		setLayout(null);
		
		for(int i = 0; i < shortcut_lb.length; i++)
		{
			shortcut_lb[i] = new JLabel();
			c.add(shortcut_lb[i]);
		}
		
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
		public void actionPerformed(ActionEvent e)
		{
			JButton b = (JButton)e.getSource();
			if(cnt <= 82)
			{
				int arr = -1;
				for(int i = cnt * 10; i < (cnt + 1) * 10; i++)
				{
					arr++;
					shortcut_lb[arr].setText((String) cl.shortcut().get(i));
					shortcut_lb[arr].setSize(300, 30);
					shortcut_lb[arr].setFont(new Font("Calibri", Font.PLAIN, 30));
					shortcut_lb[arr].setLocation(10, (arr + 1) * 40);
				}
				cnt++;
				b.setText("Next >");
			}
			else
			{
				cnt = 0;
				b.setText("First");
			}
		}
	}
	
	public static void main(String[] args) 
	{
		new Shortcut_Dictionary();
	}
}
