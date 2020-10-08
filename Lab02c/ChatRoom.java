package Lab02c;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class ChatRoom extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JPanel boardChat;
	public GridBagConstraints cons;
	public JTextArea textMessage;
	public JButton btnSendMessage;
	
	public ChatRoom(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 480);

		this.textMessage = new JTextArea();
		this.textMessage.setBounds(10, 388, 410, 50);
		this.textMessage.setColumns(10);
		this.textMessage.setAutoscrolls(true);

		this.btnSendMessage = new JButton("Send");
		this.btnSendMessage.setBounds(430, 388, 70, 50);

		this.boardChat = new JPanel();
		this.boardChat.setBounds(10, 10, 485, 370);
		this.boardChat.setLayout(new GridBagLayout());
		
		this.cons = new GridBagConstraints();
		this.cons.fill = GridBagConstraints.HORIZONTAL;
		this.cons.weightx = 1;
		this.cons.gridx = 0;

		this.contentPane = new JPanel();
		this.contentPane.setToolTipText("");
		setContentPane(this.contentPane);
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(null);

		this.contentPane.add(this.boardChat);
		this.contentPane.add(this.textMessage);
		this.contentPane.add(this.btnSendMessage);

		this.setVisible(true);
	}
}
