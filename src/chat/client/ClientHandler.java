package chat.client;

import java.net.Socket;

import chat.entity.ChatStatus;
import chat.entity.TransferInfo;
import chat.io.IOStream;

public class ClientHandler extends Thread {
	Socket socket;
	//登陆窗体
	LoginFrame loginFrame;
	//聊天主窗体
	ChatFrame chatFrame;
	
	public ClientHandler(Socket socket, LoginFrame loginFrame) {
		// TODO Auto-generated constructor stub
		this.socket=socket;
		this.loginFrame=loginFrame;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while(true) {
			try {
				//阻塞拿消息
				Object obj=IOStream.readMessage(socket);
				if(obj instanceof TransferInfo) {
					TransferInfo tfi=(TransferInfo)obj;
					
					if (tfi.getStatusEnum()==ChatStatus.LOGIN) {
						
						loginResult(tfi);
					} else if(tfi.getStatusEnum()==ChatStatus.CHAT){
						chatResult(tfi);
						
					}else if(tfi.getStatusEnum()==ChatStatus.NOTICE) {
						noticeResult(tfi);
						
					}else if(tfi.getStatusEnum()==ChatStatus.DO) {
					}else if(tfi.getStatusEnum()==ChatStatus.ULIST) {
						onlineUsersResult(tfi);
					}
					
				}else {
					
				}
				
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//处理登陆结果
	public void loginResult(TransferInfo tfi) {
		if(tfi.getLogSuccessFlag()) {
			//从整体实体类中取到用户名
			String userName = tfi.getUserName();
			//登陆成功,打开主界面
			chatFrame = new ChatFrame(userName,socket);
			//关闭登陆界面
			loginFrame.dispose();
		}else {
			//登陆失败
			System.out.println("Client: Error!");
			
		}
		
	}
	
	public void chatResult(TransferInfo tfi) {
		String sender=tfi.getSender();
		String text=chatFrame.mesp.getText();
		System.out.println(text);
		chatFrame.mesp.setText(text+"\n"+sender+" says : "+tfi.getContent());
	}
	
	public void noticeResult(TransferInfo tfi) {
		//在公屏上面投射系统消息
		String text=chatFrame.mesp.getText();
		chatFrame.mesp.setText(text+"\n"+tfi.getNotice());
	}
	//用来刷新当前界面
	public void onlineUsersResult(TransferInfo tfi) {
		String[] userOnlineArray = tfi.getUserOnlineArray();
		chatFrame.Ulist.setListData(userOnlineArray);
	}
	
}
