package main_page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class Shortcut_Search extends JPanel 
{
	private MainFrame mf;
	private HomeDialog hd;
	private InfoDialog id;

	private JTextField search;
	private JLabel kb_type;
	private String srh_text;
	private String arr[];
	private int kp = 0;
	
	private String key1[] = {"Esc", "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10", "F11", "F12", "Ins", "PrtSc", "Del"};
	private String key2[] = {"`", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", "=", "Bkspce", "Home"};
	private String key3[] = {"Tab", "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "[", "]", "\\", "PgUp"};
	private String key4[] = {"Caps Lock", "A", "S", "D", "F", "G", "H", "J", "K", "L", ";", "'", "Enter", "PgDn"};
	private String key5[] = {"Shift1", "Z", "X", "C", "V", "B", "N", "M", ",", ".", "/", "Shift2", "End"};
	private String key6[] = {"Ctrl", "Alt", "Space", "Left Arrow", "Up Arrow", "Down Arrow", "Right Arrow"};

	ImageIcon sc_bg, sc_kb, sc_kb_tp; // 배경, 키보드, 키입력 위치 표시
	ImageIcon sc_sh, sc_shroll; // 검색 아이콘
	ImageIcon sc_info, sc_inforoll; // 검색 안내 아이콘
	ImageIcon sc_erase, sc_eraseroll; // 텍스트 지우기 아이콘
	ImageIcon sc_back, sc_backroll; // 뒤로가기 아이콘
	
	ImageIcon sc_dig_bg, sc_dig_text; // 다이얼로그 배경, 텍스트
	ImageIcon sc_dig_home, sc_dig_homeroll; // 돌아가기 버튼
	ImageIcon sc_dig_cancle, sc_dig_cancleroll; // 취소 버튼
	
	ImageIcon sc_info_dig; // 다이얼로그 배경, 텍스트
	ImageIcon sc_dig_ok, sc_dig_okroll; // 확인 버튼

	public Shortcut_Search(MainFrame mf) 
	{
		this.mf = mf;
		setLayout(null);

		hd = null;
		id = null;
		
		// 텍스트필드
		search = new JTextField(50);
		search.setFont(new Font("나눔바른고딕", Font.PLAIN, 30));
		search.setBorder(BorderFactory.createEmptyBorder(3, 15, 3, 15)); // padding
		search.setSize(460, 46);
		search.setLocation(320, 120);
		
		search.setFocusTraversalKeysEnabled(false); // 텍스트필드에서 Tab키 이동 방지(Tab을 텍스트필드에 입력하기 위해 지정)
		
		search.addKeyListener(new KeyInput());
		add(search);
		
		// 검색 버튼
		sc_sh = new ImageIcon("image/search.png");
		sc_shroll = new ImageIcon("image/search2.png");
		
		JButton sh_icon = new JButton(sc_sh);
		sh_icon.setPressedIcon(sc_shroll);
		sh_icon.setRolloverIcon(sc_shroll);
		sh_icon.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		sh_icon.setBorderPainted(false);
		sh_icon.setContentAreaFilled(false);
		sh_icon.setFocusPainted(false);
		
		sh_icon.setSize(46, 46);
		sh_icon.setLocation(260, 120);
		
		sh_icon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				srh_text = search.getText();
				arr = srh_text.split("\\s"); // 공백으로 나누어 arr에 저장
				mf.change("Dictionary", arr);
			}
		});
		add(sh_icon);
		
		// 텍스트 지우기 버튼
		sc_erase = new ImageIcon("image/erase.png");
		sc_eraseroll = new ImageIcon("image/erase2.png");
		
		JButton sh_erase = new JButton(sc_erase);
		sh_erase.setPressedIcon(sc_eraseroll);
		sh_erase.setRolloverIcon(sc_eraseroll);
		sh_erase.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		sh_erase.setBorderPainted(false);
		sh_erase.setContentAreaFilled(false);
		sh_erase.setFocusPainted(false);
		
		sh_erase.setSize(46, 46);
		sh_erase.setLocation(795, 120);
		
		sh_erase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				search.setText("");
				kp = 0;
			}
		});
		add(sh_erase);
		
		// 검색 안내 버튼
		sc_info = new ImageIcon("image/shortcut_info.png");
		sc_inforoll = new ImageIcon("image/shortcut_info2.png");
		
		JButton sg_icon = new JButton(sc_info);
		sg_icon.setPressedIcon(sc_inforoll);
		sg_icon.setRolloverIcon(sc_inforoll);
		sg_icon.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		sg_icon.setBorderPainted(false);
		sg_icon.setContentAreaFilled(false);
		sg_icon.setFocusPainted(false);
		
		sg_icon.setSize(47, 50);
		sg_icon.setLocation(1022, 30);
		
		sg_icon.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e)
			{
				id = new InfoDialog(mf, "Info");
				id.setVisible(true);
				sg_icon.setFocusable(false);
			}
		});
		add(sg_icon);
		
		sc_back = new ImageIcon("image/back.png");
		sc_backroll = new ImageIcon("image/back2.png");
		
		JButton sb_icon = new JButton(sc_back);
		sb_icon.setPressedIcon(sc_backroll);
		sb_icon.setRolloverIcon(sc_backroll);
		sb_icon.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		sb_icon.setBorderPainted(false);
		sb_icon.setContentAreaFilled(false);
		sb_icon.setFocusPainted(false);
		
		sb_icon.setSize(48, 48);
		sb_icon.setLocation(30, 30);
		
		sb_icon.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e)
			{
				hd = new HomeDialog(mf, "Home");
				hd.setVisible(true);
			}
		});
		add(sb_icon);
		
		sc_kb_tp = new ImageIcon("image/keybord_typed.png");
		kb_type = new JLabel(sc_kb_tp);
		kb_type.setSize(27, 27);
		kb_type.setVisible(false);
		add(kb_type);
		
		// ESC가 눌렸을 경우 다이얼로그 객체 생성, 다이얼로그를 보이도록 함
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) 
			{
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) 
				{
					hd = new HomeDialog(mf, "Home");
					hd.setVisible(true);
				}
			}
		});
		
		this.setFocusable(true); // 패널 포커스 활성화
        this.requestFocus(); // 패널 강제 포커스 설정
	}

	// BackGround 설정을 위한 그리기 함수
	public void paintComponent(Graphics g)
	{
		sc_bg = new ImageIcon("image/shortcut_background.png");
		sc_kb = new ImageIcon("image/keybord.png");
		g.drawImage(sc_bg.getImage(), 0, 0, getWidth(), getHeight(), null);
		g.drawImage(sc_kb.getImage(), 30, 295, null);
		setOpaque(false);
		super.paintComponent(g);
	}
	
	// 제어키를 입력받기 위한 클래스
	private class KeyInput extends KeyAdapter 
	{
		public void keyTyped(KeyEvent e)
		{
			String s = null;
			// 텍스트필드에 입력한 알파벳이 소문자일 경우
			if(Character.isLowerCase(e.getKeyChar())) 
			{
				search.setText(search.getText() + Character.toUpperCase(e.getKeyChar())); // 소문자를 대문자로 바꿈
				s = Character.toString(Character.toUpperCase(e.getKeyChar()));
				e.consume(); // 소문자가 입력되는 것을 막음 (하지 않으면 Aa로 뜸)
			}
			
			// Space를 누른 경우
			if(e.getKeyChar() == ' ') 
			{
				search.setText(search.getText() + "Space");
				s = "Space";
				e.consume();
			}			

			if(s == null && e.getKeyChar() != '')
				s = Character.toString(e.getKeyChar());
			if(s != null)
				KeyTypedImage(s);
		}
		public void keyPressed(KeyEvent e) 
		{
			String s = null;
			kp++;
			int kc = e.getKeyCode();
			e.consume(); // 이벤트가 더 이상 이벤트 리스너로 전달되지 않도록 함
			
			// 스페이스바를 누르지 않고 한 칸을 띄워주도록 함
			if(kp != 1) // 키보드 입력을 1번만 하지 않은 경우
			{
				if(e.getKeyChar() != ',') // ','를 누르지 않은 경우
					search.setText(search.getText() + " "); // 한 칸을 띄워줌
			}
			
			// 제어키를 눌렀을 때 해당 제어키의 이름을 텍스트필드에 뜨도록 함
			switch (kc)
			{
			case KeyEvent.VK_BACK_SPACE:
				search.setText(search.getText() + "Bkspce");
				s = "Bkspce";
				break;
			case KeyEvent.VK_ENTER:
				search.setText(search.getText() + "Enter");
				s = "Enter";
				break;
			case KeyEvent.VK_CONTROL:
				search.setText(search.getText() + "Ctrl");
				s = "Ctrl";
				break;
			case KeyEvent.VK_ALT:
				search.setText(search.getText() + "Alt");
				s = "Alt";
				break;
			case KeyEvent.VK_TAB:
				search.setText(search.getText() + "Tab");
				s = "Tab";
				break;
			case KeyEvent.VK_SHIFT:
				search.setText(search.getText() + "Shift");
				if(e.getKeyLocation() == KeyEvent.KEY_LOCATION_LEFT) // shift를 누른 위치가 왼쪽일 경우
					s = "Shift1";
				else // shift를 누른 위치가 오른쪽일 경우
					s = "Shift2";
				break;
			case KeyEvent.VK_INSERT:
				search.setText(search.getText() + "Ins");
				s = "Ins";
				break;
			case KeyEvent.VK_DELETE:
				search.setText(search.getText() + "Del");
				s = "Del";
				break;
			case KeyEvent.VK_HOME:
				search.setText(search.getText() + "Home");
				s = "Home";
				break;
			case KeyEvent.VK_END:
				search.setText(search.getText() + "End");
				s = "End";
				break;
			case KeyEvent.VK_PAGE_DOWN:
				search.setText(search.getText() + "PgDn");
				s = "PgDn";
				break;
			case KeyEvent.VK_PAGE_UP:
				search.setText(search.getText() + "PgUp");
				s = "PgUp";
				break;
			case KeyEvent.VK_UP:
				search.setText(search.getText() + "Up Arrow");
				s = "Up Arrow";
				break;
			case KeyEvent.VK_DOWN:
				search.setText(search.getText() + "Down Arrow");
				s = "Down Arrow";
				break;
			case KeyEvent.VK_LEFT:
				search.setText(search.getText() + "Left Arrow");
				s = "Left Arrow";
				break;
			case KeyEvent.VK_RIGHT:
				search.setText(search.getText() + "Right Arrow");
				s = "Right Arrow";
				break;
			case KeyEvent.VK_F1:
				search.setText(search.getText() + "F1");
				s = "F1";
				break;
			case KeyEvent.VK_F2:
				search.setText(search.getText() + "F2");
				s = "F2";
				break;
			case KeyEvent.VK_F3:
				search.setText(search.getText() + "F3");
				s = "F3";
				break;
			case KeyEvent.VK_F4:
				search.setText(search.getText() + "F4");
				s = "F4";
				break;
			case KeyEvent.VK_F5:
				search.setText(search.getText() + "F5");
				s = "F5";
				break;
			case KeyEvent.VK_F6:
				search.setText(search.getText() + "F6");
				s = "F6";
				break;
			case KeyEvent.VK_F7:
				search.setText(search.getText() + "F7");
				s = "F7";
				break;
			case KeyEvent.VK_F8:
				search.setText(search.getText() + "F8");
				s = "F8";
				break;
			case KeyEvent.VK_F9:
				search.setText(search.getText() + "F9");
				s = "F9";
				break;
			case KeyEvent.VK_F10:
				search.setText(search.getText() + "F10");
				s = "F10";
				break;
			case KeyEvent.VK_F11:
				search.setText(search.getText() + "F11");
				s = "F11";
				break;
			case KeyEvent.VK_F12:
				search.setText(search.getText() + "F12");
				s = "F12";
				break;
			default:
				s = null;
			}
			
			if(s != null)
				KeyTypedImage(s);
		}
	}
	
	public void KeyTypedImage(String st)
	{
		kb_type.setVisible(true);
		
		for(int i = 0; i < key1.length; i++)
		{
			if(st.equals(key1[i]))
				kb_type.setLocation(i * 65 + 48, 300);
		}
		
		for(int i = 0; i < key2.length; i++)
		{
			if(st.equals(key2[i]))
			{
				if (i == 0)
					kb_type.setLocation(40, 355);
				else if (i == key2.length - 2)
					kb_type.setLocation(930, 355);
				else if (i == key2.length - 1)
					kb_type.setLocation(1020, 355);
				else
					kb_type.setLocation(i * 68 + 30, 355);
			}
		}
		
		for(int i = 0; i < key3.length; i++)
		{
			if(st.equals(key3[i]))
			{
				if (i == 0)
					kb_type.setLocation(56, 420);
				else
					kb_type.setLocation(i * 68 + 70, 420);
			}
		}
		
		for(int i = 0; i < key4.length; i++)
		{
			if(st.equals(key4[i]))
			{
				if (i == 0)
					kb_type.setLocation(70, 486);
				else if (i == key4.length - 2)
					kb_type.setLocation(930, 486);
				else if (i == key4.length - 1)
					kb_type.setLocation(1022, 486);
				else
					kb_type.setLocation(i * 68 + 90, 486);
			}
		}
		
		for(int i = 0; i < key5.length; i++)
		{
			if(st.equals(key5[i]))
			{
				if (i == 0)
					kb_type.setLocation(70, 552);
				else if (i == key5.length - 2)
					kb_type.setLocation(910, 552);
				else if (i == key5.length - 1)
					kb_type.setLocation(1022, 552);
				else
					kb_type.setLocation(i * 68 + 116, 552);
			}
		}
		
		for(int i = 0; i < key6.length; i++)
		{
			if(st.equals(key6[i]))
			{
				if (i == 0) // Ctrl
					kb_type.setLocation(50, 618);
				if (i == 1) // Alt
					kb_type.setLocation(265, 618);
				if (i == 2) // Space
					kb_type.setLocation(500, 618);
				if (i == 3) // Left Arrow
					kb_type.setLocation(886, 618);
				if (i == 4) // Up Arrow
					kb_type.setLocation(954, 600);
				if (i == 5) // Down Arrow
					kb_type.setLocation(954, 634);
				if (i == 6) // Right Arrow
					kb_type.setLocation(1022, 618);
			}
		}
	}
	
	// 단축키 검색 시 지켜야 할 사항, 검색을 어떻게 해야하는지 안내하는 다이얼로그
	private class InfoDialog extends JDialog 
	{
		public InfoDialog(JFrame fr, String title) 
		{
			super(fr, title, true);

			sc_info_dig = new ImageIcon("image/shortcut_infodig.png");
			JPanel background = new JPanel() 
			{
				public void paintComponent(Graphics g) 
				{
					g.drawImage(sc_info_dig.getImage(), 0, 0, getWidth(), getHeight(), null); // 배경 사진
					setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
					super.paintComponent(g);
				}
			};

			// 확인 버튼
			sc_dig_ok = new ImageIcon("image/shortcut_digok.png");
			sc_dig_okroll = new ImageIcon("image/shortcut_digok2.png");

			JButton ok_btn = new JButton(sc_dig_ok);
			ok_btn.setPressedIcon(sc_dig_okroll);
			ok_btn.setRolloverIcon(sc_dig_okroll);
			ok_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			
			ok_btn.setBorderPainted(false);
			ok_btn.setContentAreaFilled(false);
			ok_btn.setFocusPainted(false);

			ok_btn.setSize(100, 40);
			ok_btn.setLocation(280, 515);

			ok_btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					setVisible(false);
					id = null;
				}
			});
			background.add(ok_btn);

			setContentPane(background);
			setLayout(null);
			setUndecorated(true);

			setSize(662, 612);
			setLocation(getWidth() / 2 - 130 + mf.getLocation().x, getHeight() / 2 - 260 + mf.getLocation().y);
			
			background.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e)
				{
					if(e.getKeyCode() == KeyEvent.VK_ENTER)
					{
						// 엔터를 누르면 보이지 않게 함
						setVisible(false);
					}
				}
			});
			
			background.setFocusable(true);
			background.requestFocus();
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
					setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
					super.paintComponent(g);
				}
			};

			// 돌아가기 버튼
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
					if (keyCode == KeyEvent.VK_ENTER) 
					{
						// 엔터를 누르면 메인으로 돌아가고, 보이지 않게 함
						mf.change("BackToMain", null);
						setVisible(false);
					} 
					else if (keyCode == KeyEvent.VK_ESCAPE) 
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
}
