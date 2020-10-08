package Lab02c;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

import javax.swing.JFrame;
import javax.swing.JLabel;

import Lab01c.ChatRoom;

public class Server extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	ChatRoom chatRoom;
	DatagramSocket serverSocket = null;
	DatagramPacket receivePacket = null;

	public static void main(String[] args) {
		new Server();
	}

	public Server() {
		try {
			serverSocket = new DatagramSocket(5000);
			System.out.println("Server open!");

		} catch (Exception e) {
			e.printStackTrace();
		}

		chatRoom = new ChatRoom("Server");
		chatRoom.btnSendMessage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					// Gui tin nhan ve cho Client
					String messege = chatRoom.textMessage.getText();
					DatagramPacket sendPacket = new DatagramPacket(messege.getBytes(StandardCharsets.UTF_8), messege.length(),
							receivePacket.getAddress(), receivePacket.getPort());
					serverSocket.send(sendPacket);

					// Add the messege on the GUI
					chatRoom.textMessage.setText("");
					JLabel labelMessege = new JLabel(messege, JLabel.LEFT);
					labelMessege.setForeground(Color.BLACK);
					chatRoom.boardChat.add(labelMessege, chatRoom.cons);
					chatRoom.boardChat.validate();
					chatRoom.boardChat.repaint();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		new Thread(this).start();
	}

	public void run() {

		try {
			byte[] receiveMessege = new byte[4096];
			// Tạo gói tin
			receivePacket = new DatagramPacket(receiveMessege, receiveMessege.length);
			while (true) {
				// Nhan du lieu tu Client
				serverSocket.receive(receivePacket);
				String messege = new String(receivePacket.getData(), receivePacket.getOffset(), receivePacket.getLength(), StandardCharsets.UTF_8);

				// Add the messege on the GUI
				JLabel labelMessege = new JLabel(messege, JLabel.RIGHT);
				labelMessege.setForeground(Color.BLUE);
				chatRoom.boardChat.add(labelMessege, chatRoom.cons);
				chatRoom.boardChat.validate();
				chatRoom.boardChat.repaint();
				
				// Clear the buffer after every message.
				receivePacket.setLength(4096);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}