package chat.server;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import chat.server.ui.OnlineUserPanel;
import chat.server.ui.ServerInfoPanel;

public class ServerFrame extends JFrame {
	private static final long serialVersionUID = 2987874324270451756L;
	//define the width and the height of the window
	public static final Integer FRAME_WIDTH=550;
	public static final Integer FRAME_HEIGHT=510;
	
	List<String> onlineUsers=new ArrayList<>();
	List<Socket> onlineSockets=new ArrayList<>();
	
	//用户列表界面
	public OnlineUserPanel onlineUserPanel;
	//主参数面板
	public ServerInfoPanel SIP;
	public ServerFrame() {
		// TODO Auto-generated constructor stub
		this.setTitle("Chat room server");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		//non-resizable
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//get the size of screen 
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width=screenSize.width;
		int height=screenSize.height;
		//locate window into the center
		setLocation((width-FRAME_WIDTH)/2, (height-FRAME_HEIGHT)/2);
		
		//selection
		JTabbedPane tpServer = new JTabbedPane(JTabbedPane.TOP);
		tpServer.setBackground(Color.WHITE);
		tpServer.setFont(new Font("宋体",0,18));
		
		SIP=new ServerInfoPanel(); 
		
		tpServer.add("Server information", SIP.getServerInfoPanel());
		onlineUserPanel=new OnlineUserPanel();
		tpServer.add("Online User List", onlineUserPanel.getUserPanel());
		
		
		
		add(tpServer);
		setVisible(true);
	}
	

}
