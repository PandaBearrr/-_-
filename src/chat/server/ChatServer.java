package chat.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import chat.constants.Constants;
import chat.entity.ServerInfoBean;

/*
 * 服务器启动入口
 */

public class ChatServer {
	ServerFrame serverFrame;
	public ChatServer() {
		
		try {
			// 建立socket监听
			ServerSocket sso=new ServerSocket(Constants.SERVER_PORT);
			serverFrame = new ServerFrame();
			//初始化服务器信息
			ServerInfoBean serverInfo = getServerIP();
			loadServerInfo(serverInfo);
			//等待连接，阻塞实现，能够得到一个客户端的连接
			//使用循环，每当接收到一个新的socket就开辟一个新的线程
			while(true) {
				Socket socket=sso.accept();
				ServerHandler serverHandler = new ServerHandler(socket,serverFrame);
				serverHandler.start();
				System.out.println(" Got it!\n"+socket);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void loadServerInfo(ServerInfoBean serverInfo) {
		serverFrame.SIP.txtIP.setText(serverInfo.getIp());
		serverFrame.SIP.txtServerName.setText(serverInfo.getHostName());
		serverFrame.SIP.txtLog.setText("Server started...");
	}

	//写一个用来读取服务器IP的函数
	public ServerInfoBean getServerIP() {
		ServerInfoBean sib=null;
		try {
			InetAddress serverAddress=InetAddress.getLocalHost();
			byte[] ipAddress = serverAddress.getAddress();
			sib=new ServerInfoBean();
			sib.setIp(serverAddress.getHostAddress());
			sib.setHostName(serverAddress.getHostName());
			sib.setPort(Constants.SERVER_PORT+"");
			
			System.out.println("Server IP is:"+(ipAddress[0]&0xff)
					+"."+(ipAddress[1]&0xff)+"."+(ipAddress[2]&0xff)
					+"."+(ipAddress[3]&0xff));
		} catch (Exception e) {
			System.out.println("###Couldn't get Server IP."+e);
		}
		
		return sib;
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ChatServer();
		
	}

}
