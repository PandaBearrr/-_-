package chat.server;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JList;
import javax.swing.JTextPane;

import chat.entity.ChatStatus;
import chat.entity.TransferInfo;
import chat.io.IOStream;

/*
 * build a thread in server to handle the is
 */

public class ServerHandler extends Thread {
	Socket socket;
	ServerFrame serverFrame;
	public ServerHandler(Socket socket,ServerFrame serverFrame) {
		// TODO Auto-generated constructor stub
		this.socket=socket;
		this.serverFrame=serverFrame;
	}
	

	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while(true) {
			try {
				//模拟一直拿消息,产生阻塞
				Object obj=IOStream.readMessage(socket);
				System.out.println("Server : "+obj);
				
				if(obj instanceof TransferInfo) {
					TransferInfo tfi=(TransferInfo)obj;
					if(tfi.getStatusEnum()==ChatStatus.LOGIN) {
						//这是登录,处理登录请求
						loginHandler(tfi);
						
					}else if(tfi.getStatusEnum()==ChatStatus.CHAT){
						//这是聊天消息
						chatHandler(tfi);
					}
					
				}
				
				Thread.sleep(1000);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	private void chatHandler(TransferInfo tfi) {
		//转发给其他用户
		String reciver = tfi.getReciver();
		if ("ALL".equals(reciver)) {
			//发送给所有人
			sendAll(tfi);
			log(tfi.getSender()+" send message to ALL : "+tfi.getContent());
		}else {
			//私聊
		}
	}
	
	
	private void loginHandler(TransferInfo tfi) {
		boolean flag=checkUserLogin(tfi);
		tfi.setLogSuccessFlag(false);
		if(flag) {
			//返回登陆成功给登录端
			tfi.setLogSuccessFlag(true);
			IOStream.writeMessage(socket, tfi);
			//统计在线人数
			String userName=tfi.getUserName();
			System.out.println("1111111 is "+userName);
			serverFrame.onlineUsers.add(userName);
			serverFrame.onlineSockets.add(socket);
			
			
			//刷新在线用户列表
			flushOnlineUserList();
			
			
			//发送公告消息
			//此时这个tfi中的有效信息应该仅有notice
			TransferInfo tfiN=new TransferInfo();
			tfiN.setStatusEnum(ChatStatus.NOTICE);
			String notice=" >>> welcome "+tfi.getUserName()+" to the chat room!";
			tfiN.setNotice(notice);
			sendAll(tfiN);
			
			
			//发送最新用户列表给客户端
			TransferInfo tfiF=new TransferInfo();
			tfiF.setUserOnlineArray(serverFrame.onlineUsers.toArray(
					new String [serverFrame.onlineUsers.size()]));
			tfiF.setStatusEnum(ChatStatus.ULIST);
			sendAll(tfiF);
			
			log(notice);
		}else {
			
			System.out.println("Failed to log in!");
			IOStream.writeMessage(socket, tfi);
			log(tfi.getUserName()+" failed to log in!");
		}
	}
	
	//记录日志的方法
	private void log(String log) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr=sdf.format(date);
		JTextPane txtLog=serverFrame.SIP.txtLog;
		txtLog.setText(txtLog.getText()+"\n"+dateStr+" "+ log);
	}
	
	
	//刷新用户列表
	public void flushOnlineUserList() {
		JList<String> lstUser=serverFrame.onlineUserPanel.Ulist;
		String[] userArray=serverFrame.onlineUsers.toArray(
				new String [serverFrame.onlineUsers.size()]);
		
		lstUser.setListData(userArray);
		serverFrame.SIP.txtNumber.setText(userArray.length+"");
	}
	
	public void sendAll(TransferInfo tfi) {
		
		for(int i=0;i<serverFrame.onlineSockets.size();i++) {
			Socket tempSocket = serverFrame.onlineSockets.get(i);
			IOStream.writeMessage(tempSocket, tfi);
		}
		
	}
	
	public boolean checkUserLogin(TransferInfo tfi) {
		try {
			String userName=tfi.getUserName();
			String passWord=tfi.getPassWord();
			FileInputStream fis;
			fis = new FileInputStream(new File("src/user.txt"));
			DataInputStream dis=new DataInputStream(fis);
			String fileUser;
			while((fileUser = dis.readLine())!=null) {
				//从文件中逐行读取检验
				if((userName+"-"+passWord).equals(fileUser)) {
					System.out.println("用户名，密码正确");
					return true;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}
}
