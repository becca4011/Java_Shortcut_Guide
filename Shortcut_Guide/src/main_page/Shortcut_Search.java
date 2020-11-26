package main_page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Shortcut_Search extends JPanel {
	private MainFrame mf;
	private HomeDialog hd;

	private JTextField search;

	ImageIcon sc_bg, sc_kb; // 배경, 키보드
	ImageIcon sc_sh, sc_shroll; // 검색 아이콘
	ImageIcon sc_dig_bg, sc_dig_text; // 다이얼로그 배경, 텍스트
	ImageIcon sc_dig_home, sc_dig_homeroll; // 돌아가기 버튼
	ImageIcon sc_dig_cancle, sc_dig_cancleroll; // 취소 버튼

	public Shortcut_Search(MainFrame mf) 
	{
		this.mf = mf;
		setLayout(null);

		hd = null;
		
		// 텍스트필드
		search = new JTextField(50);
		search.setFont(new Font("나눔바른고딕", Font.PLAIN, 30));
		search.setBorder(BorderFactory.createEmptyBorder(3, 15, 3, 15)); // padding
		search.setSize(460, 46);
		search.setLocation(340, 120);
		
		search.setFocusTraversalKeysEnabled(false); // 텍스트필드에서 Tab키 이동 방지(Tab을 텍스트필드에 입력하기 위해 지정)
		
		search.addKeyListener(new KeyInput());
		search.addActionListener(new EnterPressed());
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
		sh_icon.setLocation(280, 120);
		
		sh_icon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				mf.change("Dictionary");
			}
		});
		add(sh_icon);

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
	
	// 텍스트필드에서 엔터키가 눌리면 이동하는 클래스
	private class EnterPressed implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			mf.change("Dictionary"); // 검색 결과를 사전에서 띄워줌 (안되면 여기서 띄우기)
		}
	}
	
	// 제어키를 입력받기 위한 클래스
	private class KeyInput extends KeyAdapter 
	{
		public void keyPressed(KeyEvent e) 
		{
			int kc = e.getKeyCode();

			switch (kc) 
			{
			case KeyEvent.VK_CONTROL:
				search.setText(search.getText() + "Ctrl");
				break;
			case KeyEvent.VK_ALT:
				search.setText(search.getText() + "Alt");
				e.consume(); // Alt + Space로 메뉴가 뜨는 것을 막음
				break;
			case KeyEvent.VK_TAB:
				search.setText(search.getText() + "Tab");
				break;
			case KeyEvent.VK_SHIFT:
				search.setText(search.getText() + "Shift");
				break;
			case KeyEvent.VK_INSERT:
				search.setText(search.getText() + "Ins");
				break;
			case KeyEvent.VK_DELETE:
				search.setText(search.getText() + "Del");
				break;
			case KeyEvent.VK_HOME:
				search.setText(search.getText() + "Home");
				break;
			case KeyEvent.VK_PAGE_DOWN:
				search.setText(search.getText() + "PgDn");
				break;
			case KeyEvent.VK_PAGE_UP:
				search.setText(search.getText() + "PgUp");
				break;
			case KeyEvent.VK_UP:
				search.setText(search.getText() + "Up Arrow");
				break;
			case KeyEvent.VK_DOWN:
				search.setText(search.getText() + "Down Arrow");
				break;
			case KeyEvent.VK_LEFT:
				search.setText(search.getText() + "Left Arrow");
				e.consume(); // 왼쪽 화살표를 눌렀을 때 왼쪽으로 옮겨가는 것을 막음
				break;
			case KeyEvent.VK_RIGHT:
				search.setText(search.getText() + "Right Arrow");
				break;
			case KeyEvent.VK_F1:
				search.setText(search.getText() + "F1");
				break;
			case KeyEvent.VK_F2:
				search.setText(search.getText() + "F2");
				break;
			case KeyEvent.VK_F3:
				search.setText(search.getText() + "F3");
				break;
			case KeyEvent.VK_F4:
				search.setText(search.getText() + "F4");
				break;
			case KeyEvent.VK_F5:
				search.setText(search.getText() + "F5");
				break;
			case KeyEvent.VK_F6:
				search.setText(search.getText() + "F6");
				break;
			case KeyEvent.VK_F7:
				search.setText(search.getText() + "F7");
				break;
			case KeyEvent.VK_F8:
				search.setText(search.getText() + "F8");
				break;
			case KeyEvent.VK_F9:
				search.setText(search.getText() + "F9");
				break;
			case KeyEvent.VK_F10:
				search.setText(search.getText() + "F10");
				break;
			case KeyEvent.VK_F11:
				search.setText(search.getText() + "F11");
				break;
			case KeyEvent.VK_F12:
				search.setText(search.getText() + "F12");
				break;
			}
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
					mf.change("BackToMain"); // MainFrame에 있는 change 함수를 사용하여 Mainpg Panel로 이동
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
						mf.change("BackToMain");
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
