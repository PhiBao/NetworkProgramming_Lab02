package Lab02a;

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

			String str = sc.nextLine();
			// Tạo và gửi gói tin
			DatagramPacket se = new DatagramPacket(str.getBytes(), str.length(), InetAddress.getByName("localhost"),
					5000);
			soc.send(se);

			// Tạo và nhận gói tin
			while (true) {
				DatagramPacket re = new DatagramPacket(new byte[4096], 4096);
				soc.receive(re);
				System.out.println(new String(re.getData()));
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