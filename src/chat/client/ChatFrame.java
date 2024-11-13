package chat.client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Socket;

import javax.swing.*;



import chat.entity.ChatStatus;
import chat.entity.TransferInfo;
import chat.io.IOStream;

/*
 * 聊天主界面
 */

public class ChatFrame extends JFrame {
	private static final long serialVersionUID = 2226098761884953216L;
	//define the width and the height of the window
	public static final Integer FRAME_WIDTH=750;
	public static final Integer FRAME_HEIGHT=600;
	public JList Ulist;
	//接受框
	public JTextPane mesp;
	
	String userName;
	Socket socket;
	public ChatFrame(String userName,Socket socket) {
		// TODO Auto-generated constructor stub
		this.userName=userName;
		this.socket=socket;
		this.setTitle("Chat room");
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
		
		//accepting bar
		mesp=new JTextPane();
		mesp.setOpaque(false);
		mesp.setFont(new Font("宋体", 0, 16));
		
		JScrollPane scoPaneOne=new JScrollPane(mesp);
		scoPaneOne.setBounds(15, 20, 500, 332);
		scoPaneOne.setOpaque(false);
		scoPaneOne.getViewport().setOpaque(false);
		this.add(scoPaneOne);
		
		//online users list
		Ulist=new JList();
		Ulist.setFont(new Font("宋体",0,14));
		Ulist.setVisibleRowCount(17);
		Ulist.setFixedCellWidth(180);
		Ulist.setFixedCellHeight(18);
		JScrollPane spUser=new JScrollPane(Ulist);
		spUser.setFont(new Font("宋体",0,14));
		spUser.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		spUser.setBounds(530, 17, 200, 507);
		this.add(spUser);
		
		
		
		
		
		
		//input bar
		JTextPane sendp=new JTextPane();
		sendp.setOpaque(false);
		sendp.setFont(new Font("宋体", 0, 16));
		
		JScrollPane scoPane=new JScrollPane(sendp);
		scoPane.setBounds(15, 400, 500, 122);
		scoPane.setOpaque(false);
		scoPane.getViewport().setOpaque(false);
		this.add(scoPane);
		
		//add labels and sending button
		//load emoji.png
        ImageIcon icon = new ImageIcon("src/image/face/emoji.png");
        Image img = icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        JLabel lemoji = new JLabel(icon);
        lemoji.setBounds(16, 363, 25, 25);
        
        lemoji.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Emoji clicked!"); // 测试输出
                FaceFrame face = new FaceFrame(sendp);
            }
        });
        this.add(lemoji);
        //load file.png
        icon = new ImageIcon("src/image/face/file.png");
        img = icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        JLabel lshake = new JLabel(icon);
        lshake.setBounds(60, 363, 25, 25); 
        this.add(lshake);
        
        JButton send=new JButton("Send");
        send.setBounds(15, 525, 125, 25);
        send.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("you click the log button!");
				String content=sendp.getText();
				TransferInfo tif=new TransferInfo();
				tif.setContent(content);
				//发送人
				tif.setSender(userName);
				//接受人为ALL
				tif.setReciver("ALL");
				//设置本次处理的消息类型为CHAT
				tif.setStatusEnum(ChatStatus.CHAT);
				IOStream.writeMessage(socket, tif);
				sendp.setText("");
			}
		});
        
        this.add(send);
		//delete the Layout
		setLayout(null);
		setVisible(true);
		
		
	}
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new ChatFrame();
//	}

}
