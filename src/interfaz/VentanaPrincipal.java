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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame implements ActionListener {

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
		setTitle("Logica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1064, 766);
		contentPane = new JPanel();
		contentPane.setToolTipText("\r\n");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtFormula = new JTextField();
		txtFormula.setBounds(12, 99, 526, 41);
		contentPane.add(txtFormula);
		txtFormula.setColumns(10);

		JLabel lblArea = new JLabel("Area entrada");
		lblArea.setForeground(new Color(100, 149, 237));
		lblArea.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblArea.setBounds(12, 54, 110, 32);
		contentPane.add(lblArea);

		JLabel btnInfo = new JLabel("i");
		btnInfo.setHorizontalAlignment(SwingConstants.CENTER);
		btnInfo.setBounds(889, 617, 96, 63);
		contentPane.add(btnInfo);

		JLabel btnCancelar = new JLabel("Cancelar");
		btnCancelar.setHorizontalAlignment(SwingConstants.CENTER);
		btnCancelar.setBounds(22, 643, 96, 63);
		contentPane.add(btnCancelar);

		JLabel btnGuadar = new JLabel("Guardar");
		btnGuadar.setHorizontalAlignment(SwingConstants.CENTER);
		btnGuadar.setBounds(150, 643, 96, 63);
		contentPane.add(btnGuadar);

		JLabel btnCerrar = new JLabel("");
		btnCerrar.setIcon(new ImageIcon("C:\\Users\\Danie\\Downloads\\cancel.png"));
		btnCerrar.setHorizontalAlignment(SwingConstants.CENTER);
		btnCerrar.setBounds(990, 0, 56, 41);
		contentPane.add(btnCerrar);

		table = new JTable();
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setBounds(604, 85, 408, 533);
		contentPane.add(table);

		JLabel lblLinea = new JLabel("");
		lblLinea.setIcon(new ImageIcon("C:\\Users\\Danie\\Downloads\\fondo-azul-claro.jpg"));
		lblLinea.setBackground(new Color(135, 206, 250));
		lblLinea.setHorizontalAlignment(SwingConstants.CENTER);
		lblLinea.setBounds(0, 0, 1046, 41);
		contentPane.add(lblLinea);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(12, 175, 567, 417);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel label_1 = new JLabel("Izquierda");
		label_1.setBounds(24, 33, 60, 60);
		panel.add(label_1);

		JLabel label_2 = new JLabel("Derecha");
		label_2.setBounds(96, 36, 60, 60);
		panel.add(label_2);

		JLabel label_3 = new JLabel("Atras");
		label_3.setBounds(168, 33, 60, 60);
		panel.add(label_3);

		JLabel label_4 = new JLabel("Adelante");
		label_4.setBounds(230, 33, 60, 60);
		panel.add(label_4);

		JLabel label_5 = new JLabel("Atomos");
		label_5.setBounds(22, 109, 56, 16);
		panel.add(label_5);

		JLabel label_6 = new JLabel("P");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setForeground(Color.BLACK);
		label_6.setBackground(Color.LIGHT_GRAY);
		label_6.setBounds(12, 138, 96, 75);
		panel.add(label_6);

		JLabel label_7 = new JLabel("q");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setBounds(120, 138, 96, 75);
		panel.add(label_7);

		JLabel label_8 = new JLabel("r");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setBounds(228, 138, 96, 75);
		panel.add(label_8);

		JLabel label_9 = new JLabel("s");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setBounds(336, 138, 96, 75);
		panel.add(label_9);

		JLabel label_10 = new JLabel("t");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setBounds(444, 138, 96, 75);
		panel.add(label_10);

		JLabel label_11 = new JLabel("Operador unario");
		label_11.setBounds(14, 285, 110, 16);
		panel.add(label_11);

		JLabel label_12 = new JLabel("Operadores Binarios");
		label_12.setBounds(207, 285, 160, 16);
		panel.add(label_12);

		JLabel label_13 = new JLabel("Negacion");
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setBounds(14, 329, 88, 75);
		panel.add(label_13);

		JLabel label_14 = new JLabel("o");
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		label_14.setBounds(122, 329, 88, 75);
		panel.add(label_14);

		JLabel label_15 = new JLabel("y");
		label_15.setHorizontalAlignment(SwingConstants.CENTER);
		label_15.setBounds(222, 329, 88, 75);
		panel.add(label_15);

		JLabel label_16 = new JLabel("->");
		label_16.setHorizontalAlignment(SwingConstants.CENTER);
		label_16.setBounds(322, 329, 88, 75);
		panel.add(label_16);

		JLabel label_17 = new JLabel("<->");
		label_17.setHorizontalAlignment(SwingConstants.CENTER);
		label_17.setBounds(408, 329, 88, 75);
		panel.add(label_17);

		JLabel label = new JLabel("Comandos");
		label.setForeground(new Color(100, 149, 237));
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		label.setBounds(12, 13, 90, 16);
		panel.add(label);

		JLabel fondoComandos = new JLabel("");
		fondoComandos.setIcon(new ImageIcon("C:\\Users\\Danie\\Downloads\\plain-white-background.jpg"));
		fondoComandos.setBounds(12, 13, 543, 391);
		panel.add(fondoComandos);

		JLabel fondo = new JLabel("");
		fondo.setIcon(new ImageIcon("C:\\Users\\Danie\\Downloads\\plain-white-background.jpg"));
		fondo.setBounds(0, 42, 1046, 677);
		contentPane.add(fondo);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
//		if (e.getSource() == btnNegacion) {
//			String texto = txtIngresoDatos.getText();
//			int pos = txtIngresoDatos.getCaretPosition();
//			texto = texto.substring(0, pos) + operador[0].charAt(1) + "()" + texto.substring(pos);
//			txtIngresoDatos.setText(texto);
//			txtIngresoDatos.setCaretPosition(pos + 2);
//			txtIngresoDatos.requestFocus();
//		}
//
//		if (e.getSource() == btnConjuncion) {
//			String texto = txtIngresoDatos.getText();
//			int pos = txtIngresoDatos.getCaretPosition();
//			texto = texto.substring(0, pos) + " () " + operador[1].charAt(0) + " () " + texto.substring(pos);
//			txtIngresoDatos.setText(texto);
//			txtIngresoDatos.setCaretPosition(pos + 2);
//			txtIngresoDatos.requestFocus();
//		}
//
//		if (e.getSource() == btnDisyuncion) {
//			String texto = txtIngresoDatos.getText();
//			int pos = txtIngresoDatos.getCaretPosition();
//			texto = texto.substring(0, pos) + " () " + operador[2].charAt(0) + " () " + texto.substring(pos);
//			txtIngresoDatos.setText(texto);
//			txtIngresoDatos.setCaretPosition(pos + 2);
//			txtIngresoDatos.requestFocus();
//		}
//
//		if (e.getSource() == btnCondicional) {
//			String texto = txtIngresoDatos.getText();
//			int pos = txtIngresoDatos.getCaretPosition();
//			texto = texto.substring(0, pos) + " () " + operador[3].charAt(0) + " () " + texto.substring(pos);
//			txtIngresoDatos.setText(texto);
//			txtIngresoDatos.setCaretPosition(pos + 2);
//			txtIngresoDatos.requestFocus();
//		}
//
//		if (e.getSource() == btnBicondicional) {
//			String texto = txtIngresoDatos.getText();
//			int pos = txtIngresoDatos.getCaretPosition();
//			texto = texto.substring(0, pos) + " () " + operador[4].charAt(0) + " () " + texto.substring(pos);
//			txtIngresoDatos.setText(texto);
//			txtIngresoDatos.setCaretPosition(pos + 2);
//			txtIngresoDatos.requestFocus();
//		}
	}
}