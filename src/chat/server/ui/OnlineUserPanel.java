package chat.server.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

/*
 * the second selection of the ServerPage
 */

public class OnlineUserPanel {
	public JList<String> Ulist;
	
	public JPanel getUserPanel() {
		
		JPanel pnlUser =new JPanel();
		pnlUser.setBackground(new Color(52, 130, 203));
		pnlUser.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(""),
				BorderFactory.createEmptyBorder(1, 1, 1, 1)));
		pnlUser.setBounds(50, 5, 300, 400);
		pnlUser.setOpaque(false);
		
		JLabel lblUser=new JLabel("Online User List");
		lblUser.setFont(new Font("宋体",0,16));
		lblUser.setBounds(50, 10, 200, 3);
		pnlUser.add(lblUser);
		
		
		
		//online users list
		Ulist=new JList<>();
		Ulist.setFont(new Font("宋体",0,14));
		Ulist.setVisibleRowCount(17);
		Ulist.setFixedCellWidth(180);
		Ulist.setFixedCellHeight(18);
		
		
		JScrollPane spUser=new JScrollPane(Ulist);
		spUser.setFont(new Font("宋体",0,14));
		spUser.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		spUser.setBounds(530, 17, 200, 507);
		pnlUser.add(spUser);
		
		return pnlUser;
		
	}
}
