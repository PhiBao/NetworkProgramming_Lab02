package Lab02b;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		DatagramSocket soc = null;

		try {
			soc = new DatagramSocket();
			Scanner sc = new Scanner(System.in);
			System.out.println("This is client side.");

			while (true) {
				String str = sc.nextLine();
				// Tạo và gửi gói tin
				DatagramPacket se = new DatagramPacket(str.getBytes(), str.length(), InetAddress.getByName("localhost"),
						5000);
				soc.send(se);

				// Tạo và nhận gói tin
				DatagramPacket re = new DatagramPacket(new byte[4096], 4096);
				soc.receive(re);
				System.out.println("Result: " + new String(re.getData()));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (soc != null) {
				soc.close();
			}
		}
	}

}