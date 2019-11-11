package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField txtFormula;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
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
	public VentanaPrincipal() {
		setUndecorated(true);
		setAutoRequestFocus(false);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Logica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		contentPane = new JPanel();
		contentPane.setToolTipText("\r\n");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtFormula = new JTextField();
		txtFormula.setBounds(12, 99, 947, 41);
		contentPane.add(txtFormula);
		txtFormula.setColumns(10);
		
		JLabel lblArea = new JLabel("Area entrada");
		lblArea.setForeground(new Color(100, 149, 237));
		lblArea.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblArea.setBounds(420, 54, 220, 32);
		contentPane.add(lblArea);
		
		JLabel btnCerrar = new JLabel("");
		btnCerrar.addMouseListener(new MouseAdapter() {			
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		btnCerrar.setIcon(new ImageIcon("C:\\Users\\Danie\\Downloads\\cancel.png"));
		btnCerrar.setHorizontalAlignment(SwingConstants.CENTER);
		btnCerrar.setBounds(932, 0, 56, 41);
		contentPane.add(btnCerrar);
		
		JLabel informacion = new JLabel("");
		informacion.setHorizontalAlignment(SwingConstants.CENTER);
		informacion.setIcon(new ImageIcon("C:\\Users\\Danie\\Downloads\\info.png"));
		informacion.setBounds(882, 0, 56, 41);
		contentPane.add(informacion);
		
		table = new JTable();
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setBounds(12, 412, 976, 225);
		contentPane.add(table);
		
		JLabel lblLinea = new JLabel("");
		lblLinea.setIcon(new ImageIcon("C:\\Users\\Danie\\Downloads\\fondo-azul-claro.jpg"));
		lblLinea.setBackground(new Color(135, 206, 250));
		lblLinea.setHorizontalAlignment(SwingConstants.CENTER);
		lblLinea.setBounds(0, 0, 1000, 41);
		contentPane.add(lblLinea);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(12, 153, 976, 246);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label_5 = new JLabel("Atomos");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_5.setForeground(new Color(100, 149, 237));
		label_5.setBounds(145, 13, 110, 23);
		panel.add(label_5);
		
		JLabel lblOperadores = new JLabel("Operadores");
		lblOperadores.setHorizontalAlignment(SwingConstants.CENTER);
		lblOperadores.setForeground(new Color(100, 149, 237));
		lblOperadores.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblOperadores.setBounds(640, 13, 229, 23);
		panel.add(lblOperadores);
		
		JLabel label = new JLabel("Comandos");
		label.setForeground(new Color(100, 149, 237));
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		label.setBounds(423, 129, 116, 27);
		panel.add(label);
		
		JButton btnIzquierda = new JButton("");
		btnIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnIzquierda.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/iconos/left-arrow 36.png")));
		btnIzquierda.setBounds(351, 169, 70, 35);
		panel.add(btnIzquierda);
		
		JButton btnDerecha = new JButton("");
		btnDerecha.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/iconos/right-arrow (1).png")));
		btnDerecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDerecha.setBounds(538, 169, 70, 35);
		panel.add(btnDerecha);
		
		JButton btnAtras = new JButton("");
		btnAtras.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/iconos/return (2).png")));
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAtras.setBounds(269, 169, 70, 35);
		panel.add(btnAtras);
		
		JButton btnAdelante = new JButton("");
		btnAdelante.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/iconos/return (3).png")));
		btnAdelante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAdelante.setBounds(620, 169, 70, 35);
		panel.add(btnAdelante);
		
		JButton btnNegacion = new JButton("\u00AC");
		btnNegacion.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnNegacion.setForeground(new Color(100, 149, 237));
		btnNegacion.setBounds(568, 49, 70, 35);
		panel.add(btnNegacion);
		
		JButton btnO = new JButton("v");
		btnO.setForeground(new Color(100, 149, 237));
		btnO.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnO.setBounds(640, 49, 70, 35);
		panel.add(btnO);
		
		JButton btnY = new JButton("∧");
		btnY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnY.setForeground(new Color(100, 149, 237));
		btnY.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnY.setBounds(712, 49, 70, 35);
		panel.add(btnY);
		
		JButton btnEntonces = new JButton("\u2192");
		btnEntonces.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnEntonces.setForeground(new Color(100, 149, 237));
		btnEntonces.setBounds(784, 49, 70, 35);
		panel.add(btnEntonces);
		
		JButton btnSisoloSi = new JButton("\u2194");
		btnSisoloSi.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnSisoloSi.setForeground(new Color(100, 149, 237));
		btnSisoloSi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSisoloSi.setBounds(856, 49, 70, 35);
		panel.add(btnSisoloSi);
		
		JButton btnP = new JButton("p");
		btnP.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnP.setForeground(new Color(100, 149, 237));
		btnP.setBounds(20, 49, 70, 35);
		panel.add(btnP);
		
		JButton btnQ = new JButton("q");
		btnQ.setForeground(new Color(100, 149, 237));
		btnQ.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnQ.setBounds(92, 49, 70, 35);
		panel.add(btnQ);
		
		JButton btnR = new JButton("r");
		btnR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnR.setForeground(new Color(100, 149, 237));
		btnR.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnR.setBounds(164, 49, 70, 35);
		panel.add(btnR);
		
		JButton btnS = new JButton("s");
		btnS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnS.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnS.setForeground(new Color(100, 149, 237));
		btnS.setBounds(236, 49, 70, 35);
		panel.add(btnS);
		
		JButton btnT = new JButton("t");
		btnT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnT.setForeground(new Color(100, 149, 237));
		btnT.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnT.setBounds(310, 49, 70, 35);
		panel.add(btnT);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setForeground(new Color(100, 149, 237));
		btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAgregar.setBounds(433, 169, 93, 35);
		panel.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(new Color(100, 149, 237));
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEliminar.setBounds(702, 169, 103, 35);
		panel.add(btnEliminar);
		
		JButton btnVaciar = new JButton("Vaciar");
		btnVaciar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnVaciar.setForeground(new Color(100, 149, 237));
		btnVaciar.setBounds(158, 169, 97, 35);
		panel.add(btnVaciar);
		
		JLabel fondoComandos = new JLabel("");
		fondoComandos.setBounds(12, 13, 953, 220);
		panel.add(fondoComandos);
		fondoComandos.setIcon(new ImageIcon("C:\\Users\\Danie\\Downloads\\plain-white-background.jpg"));
		
		JLabel fondo = new JLabel("");
		fondo.setIcon(new ImageIcon("C:\\Users\\Danie\\Downloads\\plain-white-background.jpg"));
		fondo.setBounds(0, 42, 1000, 630);
		contentPane.add(fondo);
	}
}