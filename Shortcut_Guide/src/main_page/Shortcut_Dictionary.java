package main_page;

import javax.swing.*;
import javax.xml.crypto.dsig.keyinfo.PGPData;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Shortcut_Dictionary extends JPanel
{
	private MainFrame mf;
	
	static LinkedList<String[]> sc = new LinkedList<>();
	JLabel shortcut_lb = new JLabel();
	JLabel explain_lb = new JLabel();
	JLabel page_lb = new JLabel();

	private int cnt = 0;
	private int pageCnt = 1;
	
	private HomeDialog hd;
	private NotFoundDialog nfd;
	
	ImageIcon sc_bg, sc_home, sc_homeroll; // 배경, 홈버튼
	ImageIcon sc_prev, sc_prevroll; // 이전 버튼
	ImageIcon sc_next, sc_nextroll; // 다음 버튼

	ImageIcon sc_dig_bg, sc_dig_text; // 다이얼로그 배경, 텍스트
	ImageIcon sc_dig_home, sc_dig_homeroll; // 돌아가기 버튼
	ImageIcon sc_dig_cancle, sc_dig_cancleroll; // 취소 버튼
	
	public Shortcut_Dictionary(MainFrame mf, String srh_sc[])
	{
		this.mf = mf; // MainFrame 정보를 저장
		setLayout(null);
		
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
		
		// 검색
		String arr[];
		int sc_cnt = 0;
		nfd = null;
		
		if(srh_sc != null)
		{
			for(int i = 0; i < sc.size(); i++)
			{
				arr = sc.get(i)[0].split("\\+|\\s"); // +로 나누어 arr에 저장
	
				if(arr.length == srh_sc.length)
				{
					for(int j = 0; j < srh_sc.length; j++)
					{
						if(arr[j].equals(srh_sc[j]))
						{
							sc_cnt++;
						}
					}
				}
				
				if(sc_cnt == srh_sc.length)
				{
					cnt = i;
					pageCnt = i + 1;
					break;
				}
				
				if(sc_cnt == 0 && i == sc.size() - 1)
				{
					nfd = new NotFoundDialog(mf, "NotFound");
					nfd.setVisible(true);
				}
				sc_cnt = 0;
			}
		}
		
		sc_bg = new ImageIcon("image/shortcut_background.png");
        
        // 페이지
		page_lb = new JLabel(Integer.toString(cnt + 1));
		page_lb.setSize(50, 30);
		page_lb.setFont(new Font("Calibri", Font.PLAIN, 30));
		page_lb.setForeground(Color.WHITE);
		page_lb.setLocation(50, 40);
		add(page_lb);
		
		// 단축키
		shortcut_lb = new JLabel(sc.get(cnt)[0]);
		shortcut_lb.setSize(1000, 70);
		shortcut_lb.setFont(new Font("Calibri", Font.PLAIN, 70));
		shortcut_lb.setForeground(Color.WHITE);
		shortcut_lb.setLocation(50, 100);
		add(shortcut_lb);
		
		// 설명
		explain_lb = new JLabel(sc.get(cnt)[1]);
		explain_lb.setSize(1000, 110);
		explain_lb.setFont(new Font("나눔바른고딕", Font.PLAIN, 35));
		explain_lb.setForeground(Color.WHITE);
		explain_lb.setLocation(50, 160);
		add(explain_lb);
		
		// 이전 버튼
		sc_prev = new ImageIcon("image/shortcut_prevbtn.png");
		sc_prevroll = new ImageIcon("image/shortcut_prevbtn2.png");
		
		JButton prev_btn = new JButton(sc_prev);
		prev_btn.setPressedIcon(sc_prevroll);
		prev_btn.setRolloverIcon(sc_prevroll);
		prev_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		prev_btn.setBorderPainted(false);
		prev_btn.setContentAreaFilled(false);
		prev_btn.setFocusPainted(false);
		
		prev_btn.setSize(92, 62);
		prev_btn.setLocation(200, 450);
		prev_btn.addActionListener(new PrevBtnAction());
		prev_btn.setFocusable(false);
		add(prev_btn);
		
		// 다음 버튼
		sc_next = new ImageIcon("image/shortcut_nextbtn.png");
		sc_nextroll = new ImageIcon("image/shortcut_nextbtn2.png");
		
		JButton next_btn = new JButton(sc_next);
		next_btn.setPressedIcon(sc_nextroll);
		next_btn.setRolloverIcon(sc_nextroll);
		next_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		next_btn.setBorderPainted(false);
		next_btn.setContentAreaFilled(false);
		next_btn.setFocusPainted(false);
		
		next_btn.setSize(92, 62);
		next_btn.setLocation(800, 450);
		next_btn.addActionListener(new NextBtnAction());
		next_btn.setFocusable(false);
		add(next_btn);
		
		// 홈 버튼
		hd = null;
		sc_home = new ImageIcon("image/shortcut_logo.png");
		sc_homeroll = new ImageIcon("image/shortcut_logo3.png");
				
		JButton home = new JButton(sc_home);
		home.setPressedIcon(sc_homeroll);
		home.setRolloverIcon(sc_homeroll);
		home.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		home.setBorderPainted(false);
		home.setContentAreaFilled(false);
		home.setFocusPainted(false);
				
		home.setSize(123, 150);
		home.setLocation(500, 410);
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				hd = new HomeDialog(mf, "Exit");
				hd.setVisible(true);
				home.setFocusable(false);
			}
		});
		add(home);
		
		// ESC가 눌렸을 경우 다이얼로그 객체 생성, 다이얼로그를 보이도록 함
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					hd = new HomeDialog(mf, "Home");
					hd.setVisible(true);
				}
			}
		}); 
	}
	
	// 배경 설정 
	public void paintComponent(Graphics g) 
    {
        g.drawImage(sc_bg.getImage(), 0, 0, getWidth(), getHeight(), null); // 배경 사진
        setOpaque(false); // 이미지를 표시할 수 있도록 함
        super.paintComponent(g);
    }
	
	// 이전 버튼을 눌렀을 때의 이벤트
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
	
	// 다음 버튼을 눌렀을 때의 이벤트
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
	
	// 메인페이지로 돌아갈지에 대한 여부를 묻는 다이얼로그
	private class HomeDialog extends JDialog
	{
		public HomeDialog(JFrame fr, String title)
		{
			super(fr, title, true);
			
			sc_dig_bg = new ImageIcon("image/shortcut_digback.png");
			sc_dig_text = new ImageIcon("image/shortcut_dighometext.png");
			JPanel background = new JPanel() 
	        {
	            public void paintComponent(Graphics g) 
	            {
	                g.drawImage(sc_dig_bg.getImage(), 0, 0, getWidth(), getHeight(), null); // 배경 사진
	                g.drawImage(sc_dig_text.getImage(), 45, 45, null);
	                setOpaque(false); //그림을 표시하게 설정,투명하게 조절
	                super.paintComponent(g);
	            }
	        };
			
			// 돌아가기(확인) 버튼
			sc_dig_home = new ImageIcon("image/shortcut_digok.png");
			sc_dig_homeroll = new ImageIcon("image/shortcut_digok2.png");
			
			JButton home_btn = new JButton(sc_dig_home);
			home_btn.setPressedIcon(sc_dig_homeroll);
			home_btn.setRolloverIcon(sc_dig_homeroll);
			home_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			home_btn.setBorderPainted(false);
			home_btn.setContentAreaFilled(false);
			home_btn.setFocusPainted(false);
			
			home_btn.setSize(100, 40);
			home_btn.setLocation(50, 135);
			
			home_btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					// 돌아가기 버튼을 누르면 메인으로 이동하고, 보이지 않게 함
				    mf.change("BackToMain", null); // MainFrame에 있는 change 함수를 사용하여 Mainpg Panel로 이동
					setVisible(false);
				}
			});
			background.add(home_btn);
			
			// 취소 버튼
			sc_dig_cancle = new ImageIcon("image/shortcut_digcancle.png");
			sc_dig_cancleroll = new ImageIcon("image/shortcut_digcancle2.png");
			
			JButton cancle_btn = new JButton(sc_dig_cancle);
			cancle_btn.setPressedIcon(sc_dig_cancleroll);
			cancle_btn.setRolloverIcon(sc_dig_cancleroll);
			cancle_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			cancle_btn.setBorderPainted(false);
			cancle_btn.setContentAreaFilled(false);
			cancle_btn.setFocusPainted(false);
			
			cancle_btn.setSize(100, 40);
			cancle_btn.setLocation(200, 135);
			
			cancle_btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					// 취소 버튼을 누르면 보이지 않게 하고, 객체 삭제
					setVisible(false);
					hd = null;
				}
			});
			background.add(cancle_btn);
			
			background.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e)
				{
					int keyCode = e.getKeyCode();
					if(keyCode == KeyEvent.VK_ENTER)
					{
						// 엔터를 누르면 메인으로 돌아가고, 보이지 않게 함
						mf.change("BackToMain", null);
						setVisible(false);
					}
					else if(keyCode == KeyEvent.VK_ESCAPE)
					{
						// ESC를 누르면 보이지 않도록 하고, 객체 삭제
						setVisible(false);
						hd = null;
					}
				}
			});
			
			setContentPane(background);
			setLayout(null);
			setUndecorated(true);
			
			setSize(350, 200);
			setLocation(getWidth() / 2 + 210 + mf.getLocation().x, getHeight() / 2 + 120 + mf.getLocation().y);
			
			background.setFocusable(true);
			background.requestFocus();
		}
	}
	
	// 검색결과를 찾지 못했을 때의 다이얼로그
	private class NotFoundDialog extends JDialog
	{
		public NotFoundDialog(JFrame fr, String title)
		{
			super(fr, title, true);
			
			sc_dig_bg = new ImageIcon("image/shortcut_digback.png");
			sc_dig_text = new ImageIcon("image/shortcut_notfound.png");
			JPanel background = new JPanel() 
	        {
	            public void paintComponent(Graphics g) 
	            {
	                g.drawImage(sc_dig_bg.getImage(), 0, 0, getWidth(), getHeight(), null); // 배경 사진
	                g.drawImage(sc_dig_text.getImage(), 45, 60, null);
	                setOpaque(false); //그림을 표시하게 설정,투명하게 조절
	                super.paintComponent(g);
	            }
	        };
			
			// 확인 버튼
			sc_dig_home = new ImageIcon("image/shortcut_digok.png");
			sc_dig_homeroll = new ImageIcon("image/shortcut_digok2.png");
			
			JButton ok_btn = new JButton(sc_dig_home);
			ok_btn.setPressedIcon(sc_dig_homeroll);
			ok_btn.setRolloverIcon(sc_dig_homeroll);
			ok_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			ok_btn.setBorderPainted(false);
			ok_btn.setContentAreaFilled(false);
			ok_btn.setFocusPainted(false);
			
			ok_btn.setSize(100, 40);
			ok_btn.setLocation(120, 135);
			
			ok_btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					setVisible(false);
					nfd = null;
					mf.change("Search", null);
				}
			});
			background.add(ok_btn);
			
			setContentPane(background);
			setLayout(null);
			setUndecorated(true);
			
			setSize(350, 200);
			setLocation(getWidth() / 2 + 210 + mf.getLocation().x, getHeight() / 2 + 120 + mf.getLocation().y);
		}
	}
}