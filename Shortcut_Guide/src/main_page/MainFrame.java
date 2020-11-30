package main_page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame
{
	private Mainpg m_pg = null;
	private Shortcut_Dictionary s_dict = null;
	private Shortcut_Search s_srh = null;
	
	private Point initialClick;
	
	public MainFrame()
	{
		setResizable(false); // 크기 변경 불가능하도록 함
		setUndecorated(true); // 프레임의 타이틀바를 없앰
		
		setSize(1100, 700);
		setVisible(true);
		
		this.addMouseListener(new moveWindows()); // 윈도우 이동하기 위해 설정
        this.addMouseMotionListener(new moveWindows()); // 윈도우 이동하기 위해 설정
	}
	
	public void change(String panelName, String srh_sc[]) // 패널 변경
	{ 
        getContentPane().removeAll(); // 현재 생성된 패널들을 모두 삭제시킴
        switch (panelName) 
        {
        case "BackToMain": // 메인으로 돌아갈 시
            getContentPane().add(m_pg); // 메인 패널 추가
            m_pg.requestFocusInWindow(); // 메인 패널에 포커스 강제 설정
            break;
            
        case "Dictionary": // 메인에서 단축키 사전 패널로 이동시
            s_dict = new Shortcut_Dictionary(this, srh_sc); // 단축키 사전 패널 생성(현재 JFrame 정보 전달)
            getContentPane().add(s_dict); // 단축키 사전 패널 추가
            s_dict.requestFocusInWindow(); // 단축키 사전 패널에 포커스 강제 설정
            break;
            
        case "Search": // 메인에서 프로필 패널로 이동시
            s_srh = new Shortcut_Search(this); // 프로필 패널 생성(현재 JFrame 정보 전달)
            getContentPane().add(s_srh); // 프로필 패널 추가
            s_srh.requestFocusInWindow(); // 프로필 패널에 포커스 강제 설정
            break;
        }
        revalidate(); // 프레임 새로고침
        repaint(); // 프레임 다시 그리기
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
            
            // initialClick를 하지 않으면 (0, 0)에 포인터가 고정된채로 움직임
            int xMoved = e.getX() - initialClick.x; // 현재 마우스 위치의 x좌표와 첫 마우스 클릭 위치 x좌표를 빼줌
            int yMoved = e.getY() - initialClick.y; // 현재 마우스 위치의 y좌표와 첫 마우스 클릭 위치 y좌표를 빼줌

            int X = thisX + xMoved; // jf x값과 이동한 x 값을 더함
            int Y = thisY + yMoved; // jf y값과 이동한 y 값을 더함
            jf.setLocation(X, Y); // jf의 위치 변경
        }
    }
	
	public static void main(String[] args) 
	{
		MainFrame mf = new MainFrame(); // MainFrame 생성
        mf.m_pg = new Mainpg(mf); // Mainpg 생성
        mf.add(mf.m_pg); // MainFrame에 Mainpg 추가
        mf.revalidate(); // 프레임 새로 고침
        mf.repaint(); // 프레임 다시 그리기
        mf.m_pg.requestFocusInWindow(); // Mainpg 포커스 강제 설정
	}
}
