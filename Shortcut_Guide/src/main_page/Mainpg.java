package main_page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Mainpg extends JPanel
{
	private MainFrame mf;
	
	ImageIcon sc_bg, sc_logo; // 배경, 로고
	ImageIcon sc_exit, sc_exitroll; // 나가기 버튼
	ImageIcon sc_dict_btn, sc_dictroll_btn; // Dictionary 버튼
	ImageIcon sc_srh_btn, sc_srhroll_btn; // Search 버튼
	
	ImageIcon sc_dig_bg, sc_dig_text; // 다이얼로그 배경, 텍스트
	ImageIcon sc_dig_exit, sc_dig_exitroll; // 종료 버튼
	ImageIcon sc_dig_cancle, sc_dig_cancleroll; // 취소 버튼
	
	private ExitDialog ed;
	
	public Mainpg(MainFrame mf)
	{
		this.mf = mf; // MainFrame 정보를 저장
		setLayout(null); // 배치관리자 제거
		
        // Dictionary 버튼
        sc_dict_btn = new ImageIcon("image/shortcut_dictbtn.png");
        sc_dictroll_btn = new ImageIcon("image/shortcut_dictbtn2.png");
        
		JButton dict = new JButton(sc_dict_btn); // 이미지 버튼
		dict.setPressedIcon(sc_dictroll_btn); // 버튼이 클릭된 경우
		dict.setRolloverIcon(sc_dictroll_btn); // 버튼에 마우스가 올라간 경우
		dict.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서를 바꿔줌
		
		dict.setBorderPainted(false); // 외곽선을 없앰
		dict.setContentAreaFilled(false); // 내용영역 채우기를 하지 않음
		dict.setFocusPainted(false); // 포커스가 보이지 않도록 함
		
		dict.setSize(250, 70);
		dict.setLocation(170, 450);
		
		dict.addActionListener(new DictionaryBtn());
		add(dict);
		
		// Search 버튼
		sc_srh_btn = new ImageIcon("image/shortcut_srhbtn.png");
        sc_srhroll_btn = new ImageIcon("image/shortcut_srhbtn2.png");
        
		JButton search = new JButton(sc_srh_btn);
		search.setPressedIcon(sc_srhroll_btn);
		search.setRolloverIcon(sc_srhroll_btn);
		search.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		search.setBorderPainted(false);
		search.setContentAreaFilled(false);
		search.setFocusPainted(false);
		
		search.setSize(250, 70);
		search.setLocation(680, 450);
		
		search.addActionListener(new SearchBtn());
		add(search);
		
		// Exit 버튼
		ed = null;
		sc_exit = new ImageIcon("image/shortcut_exitbtn.png");
		sc_exitroll = new ImageIcon("image/shortcut_exitbtn2.png");
		
		JButton exit = new JButton(sc_exit);
		exit.setPressedIcon(sc_exitroll);
		exit.setRolloverIcon(sc_exitroll);
		exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		exit.setBorderPainted(false);
		exit.setContentAreaFilled(false);
		exit.setFocusPainted(false);
		
		exit.setSize(45, 45);
		exit.setLocation(1025, 30);
		
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				ed = new ExitDialog(mf, "Exit");
				ed.setVisible(true);
				exit.setFocusable(false);
			}
		});
		add(exit);
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					ed = new ExitDialog(mf, "Exit");
					ed.setVisible(true);
				}
			}
		});
		
		this.setFocusable(true); // 패널 포커스 활성화
        this.requestFocus(); // 패널 강제 포커스 설정
	}
	
	// 배경 설정
	public void paintComponent(Graphics g) 
	{ 
        sc_bg = new ImageIcon("image/shortcut_background.png");
        sc_logo = new ImageIcon("image/shortcut_logo2.png");
        g.drawImage(sc_bg.getImage(), 0, 0, getWidth(), getHeight(), null);
        g.drawImage(sc_logo.getImage(), 165, 155, null);
        setOpaque(false);
        super.paintComponent(g);
    }
	
	// Dictionary 버튼 이벤트
	class DictionaryBtn implements ActionListener 
	{
        public void actionPerformed(ActionEvent e) 
        {
            mf.change("Dictionary", null); // MainFrame에 있는 change 함수를 사용하여 Shortcut_Dictionary Panel로 이동
        }
    }
	
	// Search 버튼 이벤트
    class SearchBtn implements ActionListener 
    { 
        public void actionPerformed(ActionEvent e) 
        {
            mf.change("Search", null); // MainFrame에 있는 change 함수를 사용하여 Shortcut_Search Panel로 이동
        }
    }
	
	// 종료 여부를 묻는 다이얼로그
	private class ExitDialog extends JDialog
	{
		public ExitDialog(JFrame fr, String title)
		{
			super(fr, title, true);
			
			sc_dig_bg = new ImageIcon("image/shortcut_digback.png");
			sc_dig_text = new ImageIcon("image/shortcut_digtext.png");
			JPanel background = new JPanel() 
	        {
	            public void paintComponent(Graphics g) 
	            {
	                g.drawImage(sc_dig_bg.getImage(), 0, 0, getWidth(), getHeight(), null); // 배경 사진
	                g.drawImage(sc_dig_text.getImage(), 45, 45, null); // 텍스트
	                setOpaque(false); // 이미지를 표시할 수 있도록 함
	                super.paintComponent(g);
	            }
	        };
			
			// 종료 버튼
			sc_dig_exit = new ImageIcon("image/shortcut_digexit.png");
			sc_dig_exitroll = new ImageIcon("image/shortcut_digexit2.png");
			
			JButton exit_btn = new JButton(sc_dig_exit);
			exit_btn.setPressedIcon(sc_dig_exitroll);
			exit_btn.setRolloverIcon(sc_dig_exitroll);
			exit_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			
			exit_btn.setBorderPainted(false);
			exit_btn.setContentAreaFilled(false);
			exit_btn.setFocusPainted(false);
			
			exit_btn.setSize(100, 40);
			exit_btn.setLocation(50, 135);
			
			exit_btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					// 종료 버튼을 눌렀을 때 시스템 종료
					System.exit(0);
				}
			});
			background.add(exit_btn);
			
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
					// 취소 버튼을 누를 경우 보이지 않게 하고, 객체 삭제
					setVisible(false);
					ed = null;
				}
			});
			background.add(cancle_btn);
			
			background.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e)
				{
					int keyCode = e.getKeyCode();
					if(keyCode == KeyEvent.VK_ENTER)
					{
						// 엔터를 누르면 시스템 종료
						System.exit(0);
					}
					else if(keyCode == KeyEvent.VK_ESCAPE)
					{
						// ESC를 누르면 보이지 않게 하고, 객체 삭제
						setVisible(false);
						ed = null;
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
