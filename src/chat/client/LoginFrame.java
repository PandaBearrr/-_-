package chat.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import chat.entity.ChatStatus;
import chat.entity.TransferInfo;
import chat.io.IOStream;
/*
 * 登陆界面
 * 
 * 
 */
public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 957746375591826315L;

	
	public LoginFrame() {
		//set the basic format
		this.setTitle("Log in");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		//get the size of screen 
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width=screenSize.width;
		int height=screenSize.height;
		
		//locate the window at the center
		setLocation((width-500)/2, (height-500)/2);
		
		//set the title of the window
		JLabel logtitle  = new JLabel("Welcome to my Chatapp!"); 
		logtitle.setBounds(70, 25, 400, 100);
		logtitle.setFont(new Font("宋体",12,30));
		logtitle.setForeground(Color.BLACK);
		this.add(logtitle);
		
		JLabel wellab  = new JLabel("Please sign in!"); 
		wellab.setBounds(70, 70, 400, 100);
		wellab.setFont(new Font("宋体",12,30));
		wellab.setForeground(Color.BLACK);
		this.add(wellab);
		
		//set the title of the window
		JLabel loglab = new JLabel("Log in  :"); 
		loglab.setBounds(80, 190, 120, 30);
		loglab.setFont(new Font("宋体",0,16));
		loglab.setForeground(Color.BLACK);
		this.add(loglab);
		
		//add a text area
		JTextField logText=new JTextField();
		logText.setBounds(160, 190, 160, 30);
		this.add(logText);
		
		//set the title of the window
		JLabel pslab = new JLabel("Password:"); 
		pslab.setBounds(80, 230, 120, 30);
		pslab.setFont(new Font("宋体",0,16));
		pslab.setForeground(Color.BLACK);
		this.add(pslab);
		
		//add a text area (hide the text)
		JPasswordField psText=new JPasswordField();
		psText.setBounds(160, 230, 160, 30);
		this.add(psText);
		
		//set the buttons
		JButton logb=new JButton("Log in");
		JButton cancel=new JButton("Cancel");
		//add the log action
		logb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("you click the log button!");
				String userName=logText.getText();
				String passWord=psText.getText();
				TransferInfo tif=new TransferInfo();
				tif.setUserName(userName);
				tif.setPassWord(passWord);
				//设置本次处理的消息类型为登录
				tif.setStatusEnum(ChatStatus.LOGIN);
				connectServer(tif);
				
				
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		logb.setBounds(110, 320, 80, 25);
		cancel.setBounds(215, 320, 80, 25);
		this.add(cancel);
		this.add(logb);
		//set the layout
		setLayout(null);
		setVisible(true);
	}
	
	
	public void connectServer(TransferInfo tif) {
		try {
			Socket socket=new Socket("127.0.0.1",8888);
			//实例化并开启client线程
			ClientHandler clientHandler = new ClientHandler(socket,this);
			clientHandler.start();
			
			System.out.println("client thread started!");
			//直接把tif对象写出
			IOStream.writeMessage(socket, tif);
		}  catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new LoginFrame();
	}
}
