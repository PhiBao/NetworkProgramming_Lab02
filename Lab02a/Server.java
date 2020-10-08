package Lab02a;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {

	public static void main(String[] args) {
		DatagramSocket soc = null;

		try {
			soc = new DatagramSocket(5000);
			System.out.println("Server open!");
			while (true) {

				// Tạo và nhận gói tin
				DatagramPacket re = new DatagramPacket(new byte[4096], 4096);
				soc.receive(re);

				// Lấy dữ liệu từ gói tin
				String messege = new String(re.getData()).trim();

				// Tạo và gửi gói tin
				DatagramPacket se = new DatagramPacket((messege.toUpperCase()).getBytes(), messege.length(),
						re.getAddress(), re.getPort());
				soc.send(se);
				se.setData((messege.toLowerCase()).getBytes());
				soc.send(se);
				se.setData((String.valueOf(messege.length())).getBytes());
				se.setLength((String.valueOf(messege.length())).length());
				soc.send(se);
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