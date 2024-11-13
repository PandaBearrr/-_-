package chat.server.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import chat.constants.Constants;
import chat.server.ServerFrame;

public class ServerInfoPanel {
	public JTextField txtServerName;
	public JTextField txtIP;
	public JTextField txtNumber;
	//日志
	public JTextPane txtLog;
	
	public JPanel getServerInfoPanel() {
		//first selection
		JPanel pnlServer = new JPanel();
		pnlServer.setOpaque(false);
		pnlServer.setLayout(null);
		pnlServer.setBounds(0, 0, ServerFrame.FRAME_WIDTH, ServerFrame.FRAME_HEIGHT);
		
//		
		//Log label
		JLabel lblLog = new JLabel("[Server Log]");
		lblLog.setForeground(Color.BLACK);
		lblLog.setFont(new Font("宋体",0,16));
		lblLog.setBounds(130, 5, 100, 30);
		pnlServer.add(lblLog);
		
		//log area
		txtLog=new JTextPane();
		txtLog.setOpaque(false);
		txtLog.setFont(new Font("宋体",0,12));
		
		JScrollPane scoPaneOne = new JScrollPane(txtLog);
		scoPaneOne.setBounds(130, 35, 340, 360);
		scoPaneOne.setOpaque(false);
		scoPaneOne.getViewport().setOpaque(false);
		
		pnlServer.add(scoPaneOne);
		
		
		//close the server
		pnlServer.add(stopBtn());
		
		
		//save the log
//		pnlServer.add(savBtn());
		
		pnlServer.add(getServerParam());
		return pnlServer;
	}
	
	public JPanel getServerParam() {
		//parameters panel of the server
		JPanel serverParamPanel=new JPanel();
		serverParamPanel.setOpaque(false);
		serverParamPanel.setBounds(5, 5, 100, 400);
		serverParamPanel.setFont(new Font("宋体",0,14));
		serverParamPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(""),
				BorderFactory.createEmptyBorder(1, 1, 1, 1)));
		JLabel lblNumber = new JLabel("Online Number:");
		lblNumber.setFont(new Font("宋体",0,14));
		serverParamPanel.add(lblNumber);
		
		txtNumber = new JTextField("0 ",10);
		txtNumber.setFont(new Font("宋体",0,14));
		txtNumber.setEditable(false);
		serverParamPanel.add(txtNumber);
		
		//服务器名称
		JLabel lblServerName = new JLabel("Server Name:");
		lblServerName.setFont(new Font("宋体",0,14));
		serverParamPanel.add(lblServerName);
		
		txtServerName = new JTextField(10);
		txtServerName.setFont(new Font("宋体",0,14));
		txtServerName.setEditable(false);
		serverParamPanel.add(txtServerName);
		
		JLabel lblIP = new JLabel("Server IP:");
		lblIP.setFont(new Font("宋体",0,14));
		serverParamPanel.add(lblIP);
		
		txtIP = new JTextField(10);
		txtIP.setFont(new Font("宋体",0,14));
		txtIP.setEditable(false);
		serverParamPanel.add(txtIP);
		
		JLabel lblPort = new JLabel("Server Port:");
		lblPort.setFont(new Font("宋体",0,14));
		serverParamPanel.add(lblPort);
		
		JTextField txtPort = new JTextField(Constants.SERVER_PORT+"",10);
		txtPort.setFont(new Font("宋体",0,14));
		txtPort.setEditable(false);
		serverParamPanel.add(txtPort);
		
		return serverParamPanel;
	}
	
	
	public JButton stopBtn() {
		//close the server
		JButton stopBtn = new JButton("close");
		stopBtn.setBackground(Color.WHITE);
		stopBtn.setFont(new Font("宋体",0,14));
		stopBtn.setBounds(200, 400, 110, 30);
		stopBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		return stopBtn;
	}
//	public JButton savBtn() {
//		//save the log
//		JButton savBtn = new JButton("save");
//		savBtn.setBackground(Color.WHITE);
//		savBtn.setFont(new Font("宋体",0,14));
//		savBtn.setBounds(320, 400, 110, 30);
//		return savBtn;
//	}
	
}
