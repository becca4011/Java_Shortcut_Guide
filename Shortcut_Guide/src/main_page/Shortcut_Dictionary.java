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
	
	ImageIcon sc_bg, sc_prev, sc_prevroll, sc_next, sc_nextroll, sc_home;
	
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
		
		sc_bg = new ImageIcon("image/shortcut_background.png");
		
		JPanel background = new JPanel() 
        {
            public void paintComponent(Graphics g) 
            {
                g.drawImage(sc_bg.getImage(), 0, 0, getWidth(), getHeight(), null); // 배경 사진
                setOpaque(false); //그림을 표시하게 설정,투명하게 조절
                super.paintComponent(g);
            }
        };
        
        // 페이지
		page_lb = new JLabel(Integer.toString(cnt + 1));
		page_lb.setSize(50, 30);
		page_lb.setFont(new Font("Calibri", Font.PLAIN, 30));
		page_lb.setForeground(Color.WHITE);
		page_lb.setLocation(50, 40);
		background.add(page_lb);
		
		// 단축키
		shortcut_lb = new JLabel(sc.get(0)[0]);
		shortcut_lb.setSize(1000, 70);
		shortcut_lb.setFont(new Font("Calibri", Font.PLAIN, 70));
		shortcut_lb.setForeground(Color.WHITE);
		shortcut_lb.setLocation(50, 100);
		background.add(shortcut_lb);
		
		// 설명
		explain_lb = new JLabel(sc.get(0)[1]);
		explain_lb.setSize(1000, 110);
		explain_lb.setFont(new Font("나눔바른고딕", Font.PLAIN, 35));
		explain_lb.setForeground(Color.WHITE);
		explain_lb.setLocation(50, 160);
		background.add(explain_lb);
		
		// 이전 버튼
		sc_prev = new ImageIcon("image/shortcut_prevbtn.png");
		sc_prevroll = new ImageIcon("image/shortcut_prevbtn2.png");
		
		JButton prev_btn = new JButton(sc_prev);
		prev_btn.setPressedIcon(sc_prevroll);
		prev_btn.setRolloverIcon(sc_prevroll);
		
		prev_btn.setBorderPainted(false);
		prev_btn.setContentAreaFilled(false);
		prev_btn.setFocusPainted(false);
		
		prev_btn.setSize(250, 70);
		prev_btn.setLocation(170, 450);
		prev_btn.addActionListener(new PrevBtnAction());
		background.add(prev_btn);
		
		// 다음 버튼
		sc_next = new ImageIcon("image/shortcut_nextbtn.png");
		sc_nextroll = new ImageIcon("image/shortcut_nextbtn2.png");
		
		JButton next_btn = new JButton(sc_next);
		next_btn.setPressedIcon(sc_nextroll);
		next_btn.setRolloverIcon(sc_nextroll);
		
		next_btn.setBorderPainted(false);
		next_btn.setContentAreaFilled(false);
		next_btn.setFocusPainted(false);
		
		next_btn.setSize(250, 70);
		next_btn.setLocation(680, 450);
		next_btn.addActionListener(new NextBtnAction());
		background.add(next_btn);
		
		// Home 버튼
		sc_home = new ImageIcon("image/shortcut_logo.png");
				
		JButton home = new JButton(sc_home);
				
		home.setBorderPainted(false);
		home.setContentAreaFilled(false);
		home.setFocusPainted(false);
				
		home.setSize(250, 250);
		home.setLocation(430, 370);
		background.add(home);
		
		setContentPane(background);
		setLayout(null);
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