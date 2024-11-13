package chat.client;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class FaceFrame extends JFrame{
	private static final long serialVersionUID = -8771118855300313975L;
	
	public FaceFrame(JTextPane textPane) {
		JPanel panel=(JPanel)getContentPane();
		panel.setLayout(null);
		//使用双重循环来摆放图片
		for(int row=0;row<6;row++) {
			for (int col = 0; col < 6; col++) {
				//获取图片
				ImageIcon icon=new ImageIcon("src/image/face/"+(6*row+col+1)+".png");
		        Image img = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		        icon = new ImageIcon(img);
				//见图片放在JLabel里
				JLabel lblIcon = new JLabel(icon);
				lblIcon.setSize(50,50);
				lblIcon.setLocation(0+col*50, 0+row*50);
				//为lbl添加鼠标事件
				lblIcon.addMouseListener(new MouseListener() {
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						JLabel jLabel=(JLabel)e.getSource();
						Icon icon2=jLabel.getIcon();
						textPane.insertIcon(icon2);
						FaceFrame.this.dispose();
					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
				panel.add(lblIcon);
			}
			setSize(320, 300);
			setLocation(800, 400);
			setTitle("Emoji");
			setVisible(true);
		}
		
		
		
	}
	
	
}
