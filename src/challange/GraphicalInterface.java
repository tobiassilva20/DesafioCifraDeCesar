package challange;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * This class implements a graphical interface to program of Desafio Cifra de Cesar
 * @see javax.swing.JLabel
 * @see javax.swing.JFrame
 * @see javax.swing.JTextField
 * @see javax.swing.JButton
 * @see java.awt.Container
 * @see java.awt.event.ActionEvent
 */
public class GraphicalInterface extends JFrame {
		
	private static final long serialVersionUID = 1L;
	private JLabel lbl1, lbl2, lbl3, lbl4;
	private JTextField txt1, txt2, txt3, txt4;
	private JButton btnGet,btnDecode, btnHash, btnSend;
	
	public GraphicalInterface() {
		Container container = getContentPane();
		
		txt1 = new JTextField();
		lbl1 = new JLabel("Encoded Text:");
		txt2 = new JTextField();
		lbl2 = new JLabel("Decoded Text:");
		txt3 = new JTextField();
		lbl3 = new JLabel("Hash Code:");
		txt4 = new JTextField();
		lbl4 = new JLabel("Your Score:");
		btnGet = new JButton("GET FILE");
		btnDecode = new JButton("DECODE");
		btnHash = new JButton("GEN. HASH");
		btnSend = new JButton("SEND FILE");
		
		//Labels
		lbl1.setBounds(20, 25, 150, 20);
		txt1.setBounds(110, 20, 430, 30);
		lbl2.setBounds(20, 65, 150, 20);
		txt2.setBounds(110, 60, 430, 30);
		lbl3.setBounds(20, 105, 150, 20);
		txt3.setBounds(110, 100, 430, 30);
		lbl4.setBounds(20, 145, 150, 20);
		txt4.setBounds(110, 140, 430, 30);
		
		//Buttons
		btnGet.setBounds(110, 200, 100, 30);
		btnDecode.setBounds(220, 200, 100, 30);
		btnHash.setBounds(330, 200, 100, 30);
		btnSend.setBounds(440, 200, 100, 30);
		
		txt1.setEditable(false);
		txt2.setEditable(false);
		txt3.setEditable(false);
		txt4.setEditable(false);
		btnDecode.setEnabled(false);
		btnHash.setEnabled(false);
		btnSend.setEnabled(false);
		
		container.add(lbl1);
		container.add(txt1);
		container.add(lbl2);
		container.add(txt2);
		container.add(lbl3);
		container.add(txt3);
		container.add(lbl4);
		container.add(txt4);
		container.add(btnGet);
		container.add(btnDecode);
		container.add(btnHash);
		container.add(btnSend);
		
		setSize(600, 320);
		setTitle("Desafio CodeNation");
		setLayout(null);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Action to connect to API and get the Json files
		btnGet.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					txt1.setText(new Connection().connectToApi());
					btnDecode.setEnabled(true);
					btnGet.setEnabled(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.err.println("Erro na conversão!");
				}
				
			}
		});
		
		//Action to decode the message sent for the API
		btnDecode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				txt2.setText(new CaesarCipher().decode(txt1.getText(), new Files().readFile()));
				btnGet.setEnabled(false);
				btnDecode.setEnabled(false);
				btnHash.setEnabled(true);
			}
		});
		
		//Action to generate a HASH SHA-1 from the decode message
		btnHash.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				txt3.setText(new CaesarCipher().createHashSha1(txt2.getText()));
				new Files().updateFile(txt2.getText(), txt3.getText());
				btnSend.setEnabled(true);
				btnHash.setEnabled(false);
				
				
			}
		});
		
		//Action to submit the json file to API for evaluation
		btnSend.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(btnSend.getText().equals("SEND FILE")) {
					txt4.setText(new Connection().sendFile());
					btnSend.setText("CLOSE");
				}else {
					
					System.exit(0);
				}
				
				//btnSend.setEnabled(false);
				
			}
		});
		
	}
}
