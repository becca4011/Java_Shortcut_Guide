package main_page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Mainpg extends JFrame
{
	ImageIcon sc_bg;
	ImageIcon sc_logo;
	ImageIcon sc_dict_btn;
	ImageIcon sc_dictroll_btn;
	ImageIcon sc_srh_btn;
	ImageIcon sc_srhroll_btn;
	
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
		
        sc_dict_btn = new ImageIcon("image/shortcut_dictbtn.png");
        sc_dictroll_btn = new ImageIcon("image/shortcut_dictbtn2.png");
        
		JButton dict = new JButton(sc_dict_btn);
		dict.setPressedIcon(sc_dictroll_btn);
		dict.setRolloverIcon(sc_dictroll_btn);
		
		dict.setBorderPainted(false);
		dict.setContentAreaFilled(false);
		dict.setSize(250, 70);
		//dict.setFont(new Font("Calibri", Font.PLAIN, 30));
		dict.setLocation(150, 450);
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
		
		sc_srh_btn = new ImageIcon("image/shortcut_srhbtn.png");
        sc_srhroll_btn = new ImageIcon("image/shortcut_srhbtn2.png");
        
		JButton search = new JButton(sc_srh_btn);
		search.setPressedIcon(sc_srhroll_btn);
		search.setRolloverIcon(sc_srhroll_btn);
		
		search.setBorderPainted(false);
		search.setContentAreaFilled(false);
		search.setSize(250, 70);
		//search.setFont(new Font("Calibri", Font.PLAIN, 30));
		search.setLocation(700, 450);
		background.add(search);
		
		setContentPane(background);
		setLayout(null);
		setSize(1100, 700);
		setResizable(false); // 크기 변경 불가능하도록 함
		setVisible(true);
	}
	
	public static void main(String[] args) 
	{
		new Mainpg();
	}
}
