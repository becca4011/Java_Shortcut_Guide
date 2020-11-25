package main_page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Mainpg extends JFrame
{
	ImageIcon sc_bg, sc_logo, sc_exit; // 배경, 로고, 나가기
	ImageIcon sc_dict_btn, sc_dictroll_btn; // Dictionary 버튼
	ImageIcon sc_srh_btn, sc_srhroll_btn; // Search 버튼
	
	ImageIcon sc_dig_bg, sc_dig_text; // 다이얼로그 배경, 텍스트
	ImageIcon sc_dig_exit, sc_dig_exitroll; // 종료 버튼
	ImageIcon sc_dig_cancle, sc_dig_cancleroll; // 취소 버튼
	
	private ExitDialog ed;
	private Point initialClick;
	
	public Mainpg()
	{
		setTitle("Shortcut Guide");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		sc_bg = new ImageIcon("image/shortcut_background.png");
		sc_logo = new ImageIcon("image/shortcut_logo2.png");
 
        JPanel background = new JPanel() 
        {
            public void paintComponent(Graphics g) 
            {
                g.drawImage(sc_bg.getImage(), 0, 0, getWidth(), getHeight(), null); // 배경 사진
                g.drawImage(sc_logo.getImage(), 165, 155, null); // 로고
                setOpaque(false); //그림을 표시하게 설정,투명하게 조절
                super.paintComponent(g);
            }
        };
		
        // Dictionary 버튼
        sc_dict_btn = new ImageIcon("image/shortcut_dictbtn.png");
        sc_dictroll_btn = new ImageIcon("image/shortcut_dictbtn2.png");
        
		JButton dict = new JButton(sc_dict_btn); // 이미지 버튼
		dict.setPressedIcon(sc_dictroll_btn); // 버튼이 클릭된 경우
		dict.setRolloverIcon(sc_dictroll_btn); // 버튼에 마우스가 올라간 경우
		
		dict.setBorderPainted(false); // 외곽선을 없앰
		dict.setContentAreaFilled(false); // 내용영역 채우기를 하지 않음
		dict.setFocusPainted(false); // 포커스가 보이지 않도록 함
		
		dict.setSize(250, 70);
		dict.setLocation(170, 450);
		
		dict.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// Dictionary 버튼을 누르면 Shortcut_Dictionary 프레임으로 넘어가도록 함
				new Shortcut_Dictionary();
				setVisible(false);
			}
		});
		background.add(dict);
		
		// Search 버튼
		sc_srh_btn = new ImageIcon("image/shortcut_srhbtn.png");
        sc_srhroll_btn = new ImageIcon("image/shortcut_srhbtn2.png");
        
		JButton search = new JButton(sc_srh_btn);
		search.setPressedIcon(sc_srhroll_btn);
		search.setRolloverIcon(sc_srhroll_btn);
		
		search.setBorderPainted(false);
		search.setContentAreaFilled(false);
		search.setFocusPainted(false);
		
		search.setSize(250, 70);
		search.setLocation(680, 450);
		
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				
			}
		});
		background.add(search);
		
		// Exit 버튼
		ed = new ExitDialog(this, "Exit");
		sc_exit = new ImageIcon("image/shortcut_exitbtn.png");
		
		JButton exit = new JButton(sc_exit);
		
		exit.setBorderPainted(false);
		exit.setContentAreaFilled(false);
		exit.setFocusPainted(false);
		
		exit.setSize(70, 70);
		exit.setLocation(1005, 20);
		
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				ed.setVisible(true);
			}
		});
		background.add(exit);
		
		setContentPane(background); // 패널을 컨텐트팬으로 설정
		setLayout(null); // 배치관리자 제거
		setSize(1100, 700);
		setResizable(false); // 크기 변경 불가능하도록 함
		setUndecorated(true); // 프레임의 타이틀바를 없앰
		setVisible(true);
		
		this.addMouseListener(new moveWindows()); // 윈도우 이동하기 위해 설정
        this.addMouseMotionListener(new moveWindows()); // 윈도우 이동하기 위해 설정
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
			
			exit_btn.setBorderPainted(false);
			exit_btn.setContentAreaFilled(false);
			exit_btn.setFocusPainted(false);
			
			exit_btn.setSize(100, 40);
			exit_btn.setLocation(50, 135);
			
			exit_btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
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
			
			cancle_btn.setBorderPainted(false);
			cancle_btn.setContentAreaFilled(false);
			cancle_btn.setFocusPainted(false);
			
			cancle_btn.setSize(100, 40);
			cancle_btn.setLocation(200, 135);
			
			cancle_btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					setVisible(false);
				}
			});
			background.add(cancle_btn);
			
			setContentPane(background);
			setLayout(null);
			setUndecorated(true);
			setSize(350, 200);
			setLocation(getWidth() / 2 + 200, getHeight() / 2 + 120);
		}
	}
	
	class moveWindows extends MouseAdapter // 프레임 이동 (타이틀바를 없애서 이동 불가능한 것을 가능하도록 함)
	{ 
        public void mousePressed(MouseEvent e) 
        { 
            initialClick = e.getPoint(); // 현재 좌표 저장
            getComponentAt(initialClick); // 저장한 좌표를 포함한 컴포넌트를 리턴 받음
        }

        public void mouseDragged(MouseEvent e) 
        {
            JFrame jf = (JFrame) e.getSource(); // 드래그 된 JFrame의 정보를 받아옴
            
            int thisX = jf.getLocation().x; // jf의 x 값을 저장함
            int thisY = jf.getLocation().y; // jf의 y 값을 저장함

            int xMoved = e.getX() - initialClick.x; // 현재 마우스 위치의 x좌표와 첫 마우스 클릭 위치 x좌표를 빼줌
            int yMoved = e.getY() - initialClick.y; // 현재 마우스 위치의 y좌표와 첫 마우스 클릭 위치 y좌표를 빼줌

            int X = thisX + xMoved; // jf x값과 이동한 x 값을 더함
            int Y = thisY + yMoved; // jf y값과 이동한 y 값을 더함
            jf.setLocation(X, Y); // jf의 위치 변경
        }
    }

	
	public static void main(String[] args) 
	{
		new Mainpg();
	}
}
