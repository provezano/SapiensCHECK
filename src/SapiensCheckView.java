import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;


public class SapiensCheckView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SapiensCheckView frame = new SapiensCheckView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SapiensCheckView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("/Users/coutinho/Documents/workspace/Sapiens/check30.png"));
		setResizable(false);
		setTitle("SapiensCHECK");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 387, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 2, 0, 0));
		
		JLabel lblMatricula = new JLabel(" Matrícula:");
		lblMatricula.setHorizontalAlignment(SwingConstants.LEFT);
		lblMatricula.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		panel.add(lblMatricula);
		
		textField = new JTextField();
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblSenha = new JLabel(" Senha:");
		lblSenha.setHorizontalAlignment(SwingConstants.LEFT);
		lblSenha.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		panel.add(lblSenha);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		panel.add(passwordField);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTitle = new JLabel("SapiensCHECK v2.0");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 26));
		panel_1.add(lblTitle);
		
		JSeparator separator_2 = new JSeparator();
		panel_1.add(separator_2, BorderLayout.SOUTH);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Salvar dados");
		panel_3.add(chckbxNewCheckBox);
		chckbxNewCheckBox.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JButton btnLogin = new JButton("Efetuar login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!SystemTray.isSupported()) {
		            System.out.println("SystemTray não é suportado.");
		            return;
		        }
		        final PopupMenu popup = new PopupMenu();
		        final TrayIcon trayIcon =
		                new TrayIcon(new ImageIcon("//Users//coutinho//Documents/workspace//Sapiens//check30.png", "tray icon").getImage(), "SapiensCheck");
		        final SystemTray tray = SystemTray.getSystemTray();
		       
		        // Create a pop-up menu components
		        MenuItem aboutItem = new MenuItem("Sobre");
		        MenuItem configItem = new MenuItem("Configurações");
		        //CheckboxMenuItem cb1 = new CheckboxMenuItem("Set auto size");
		        //CheckboxMenuItem cb2 = new CheckboxMenuItem("Set tooltip");
		        MenuItem exitItem = new MenuItem("Sair");
		       
		        //Add components to pop-up menu
		        popup.add(aboutItem);
		        popup.addSeparator();
		        //popup.add(cb1);
		        //popup.add(cb2);
		        //popup.addSeparator();
		        popup.add(configItem);
		        popup.add(exitItem);
		       
		        trayIcon.setPopupMenu(popup);
		       
		        try {
		            tray.add(trayIcon);
		        } catch (AWTException ex) {
		            System.out.println("Erro ao adicionar TrayIcon.");
		        }
				
			}
		});
		panel_3.add(btnLogin);
		btnLogin.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		
		JSeparator separator = new JSeparator();
		panel_2.add(separator, BorderLayout.NORTH);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textField, passwordField, chckbxNewCheckBox, btnLogin}));
	}

}
