package Lab02c;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

import javax.swing.JFrame;
import javax.swing.JLabel;

import Lab01c.ChatRoom;

// Client phải gửi tin nhắn trước để Server lấy địa chỉ.
public class Client extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	ChatRoom chatRoom;
	DatagramSocket clientSocket = null;
	DatagramPacket receivePacket = null;

	public static void main(String[] args) {
		new Client();
	}

	public Client() {
		try {
			clientSocket = new DatagramSocket();
			System.out.println("This is client side.");

		} catch (Exception e) {
			e.printStackTrace();
		}

		chatRoom = new ChatRoom("Client");
		chatRoom.btnSendMessage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					// Gui tin nhan len cho Server
					String messege = chatRoom.textMessage.getText();
					DatagramPacket sendPacket = new DatagramPacket(messege.getBytes(StandardCharsets.UTF_8), messege.length(),
							InetAddress.getByName("localhost"), 5000);
					clientSocket.send(sendPacket);

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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			byte[] receiveMessege = new byte[4096];
			// Tạo gói tin
			receivePacket = new DatagramPacket(receiveMessege, receiveMessege.length);
			while (true) {
				// Nhan du lieu tra ve tu server
				clientSocket.receive(receivePacket);
				String messege = new String(receivePacket.getData(), receivePacket.getOffset(),
						receivePacket.getLength(), StandardCharsets.UTF_8);

				// Add the messege on the GUI
				JLabel labelMessege = new JLabel(messege, JLabel.RIGHT);
				labelMessege.setForeground(Color.BLUE);
				chatRoom.boardChat.add(labelMessege, chatRoom.cons);
				chatRoom.boardChat.validate();
				chatRoom.boardChat.repaint();

				// Clear the buffer after every message.
				receivePacket.setLength(4096);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}