package interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.RenderingHints.Key;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.undo.UndoManager;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.undo.UndoManager;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.ScrollPane;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import excepciones.ContinuasException;
import excepciones.ParentesisException;
import excepciones.PremisaException;
import mundo.Validaciones;

public class VentanaPrincipal extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NEGACION = "¬";
	public static final String CONJUNCION = "∧";
	public static final String DISYUNCION = "∨";
	public static final String CONDICIONAL = "→";
	public static final String EQUIVALENCIA = "↔";
	private UndoManager manager;
	private int pos = 0;
	private JPanel contentPane;
	private JButton btnNegacion;
	private JTextField txtFormula;
	private JButton btnP;
	private JButton btnQ;
	private JButton btnR;
	private JButton btnS;
	private JButton btnT;
	private JButton btnY;
	private JButton btnEntonces;
	private JButton btnSisoloSi;
	private JButton btnVaciar;
	private JButton btnAtras;
	private JButton btnIzquierda;
	private JButton btnAgregar;
	private JButton btnDerecha;
	private JButton btnAdelante;
	private JButton btnEliminar;
	private JTable table;
	private JButton btnO;
	private JButton btnResolver;

	private ArrayList<String> listaFormulas;
	private JScrollPane scrollPane;
	private DefaultTableModel modelotabla = new DefaultTableModel();
	private JLabel lblProyectoLogica;

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

		setUndecorated(false);
		setAutoRequestFocus(false);
		setTitle("Logica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		contentPane = new JPanel();
		contentPane.setToolTipText("\r\n");
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblProyectoLogica = new JLabel("Proyecto Logica");
		lblProyectoLogica.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblProyectoLogica.setForeground(new Color(255, 255, 255));
		lblProyectoLogica.setBounds(0, 13, 202, 28);
		contentPane.add(lblProyectoLogica);

		txtFormula = new JTextField();
		txtFormula.setBounds(12, 99, 947, 41);
		contentPane.add(txtFormula);
		txtFormula.setColumns(10);

		JLabel lblListaDeFormulas = new JLabel("Lista de Formulas");
		lblListaDeFormulas.setForeground(new Color(100, 149, 237));
		lblListaDeFormulas.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblListaDeFormulas.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaDeFormulas.setBounds(211, 412, 590, 16);
		contentPane.add(lblListaDeFormulas);
		manager = new UndoManager();

		txtFormula = new JTextField();
		txtFormula.setBounds(12, 99, 947, 41);
		contentPane.add(txtFormula);
		txtFormula.setColumns(10);
		txtFormula.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				String texto = txtFormula.getText();
				int pos = txtFormula.getCaretPosition();
				if (pos > 0) {
					if (texto.charAt(pos - 1) == '(' && texto.charAt(pos) == ')') {
						return;
					}
				}
				txtFormula.setCaretPosition(posicionCaretSiguiente(texto, 0));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});

		txtFormula.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				String actual = txtFormula.getText();
				int pos = txtFormula.getCaretPosition();
				if (!Character.isLetter(arg0.getKeyChar())) {

					if (arg0.getKeyCode() == 8) {
						actual += " ";
					}
					mostrarError("La tecla ingresada no corresponde a una letra");
					txtFormula.setText(actual);
					return;
				} else {
					if (actual.charAt(pos - 1) != '(' || actual.charAt(pos) != ')') {
						mostrarError("El nuevo atomo no se encontraría entre paréntesis");
						txtFormula.setText(actual);
					}

				}
			}
		});

		txtFormula.getDocument().addUndoableEditListener(manager);
		new JLabel("Area entrada");

		JLabel lblArea = new JLabel("Area entrada");
		lblArea.setForeground(new Color(100, 149, 237));
		lblArea.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblArea.setBounds(420, 54, 220, 32);
		contentPane.add(lblArea);
		listaFormulas = new ArrayList<>();
		JLabel btnCerrar = new JLabel("");
		btnCerrar.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		btnCerrar.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/cancel.png")));
		btnCerrar.setHorizontalAlignment(SwingConstants.CENTER);
		btnCerrar.setBounds(932, 0, 56, 41);
		contentPane.add(btnCerrar);

		JLabel btnInformacion = new JLabel("");
		btnInformacion.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				try {
					abrirPDF();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					mostrarError("No se ha podido abrir el documento");
				}
			}
		});
		btnInformacion.setHorizontalAlignment(SwingConstants.CENTER);
		btnInformacion.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/info.png")));
		btnInformacion.setBounds(882, 0, 56, 41);
		contentPane.add(btnInformacion);

		table = new JTable();
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setBounds(12, 430, 976, 207);

		contentPane.add(table);
		modelotabla.addColumn("Lista Formulas");
		table.setModel(modelotabla);

		JLabel lblLinea = new JLabel("");
		lblLinea.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/fondo-azul-claro.jpg")));
		lblLinea.setBackground(new Color(135, 206, 250));
		lblLinea.setHorizontalAlignment(SwingConstants.CENTER);
		lblLinea.setBounds(0, 0, 1000, 41);
		contentPane.add(lblLinea);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(12, 153, 976, 246);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel label_1 = new JLabel("Atomos");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_1.setForeground(new Color(100, 149, 237));
		label_1.setBounds(145, 13, 110, 23);
		panel.add(label_1);

		JLabel lblOperadores = new JLabel("Operadores");
		lblOperadores.setHorizontalAlignment(SwingConstants.CENTER);
		lblOperadores.setForeground(new Color(100, 149, 237));
		lblOperadores.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblOperadores.setBounds(640, 13, 229, 23);
		panel.add(lblOperadores);

		JLabel label = new JLabel("Comandos");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(100, 149, 237));
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		label.setBounds(420, 98, 124, 27);
		panel.add(label);

		btnIzquierda = new JButton("");
		btnIzquierda.addActionListener(this);
		btnIzquierda.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/izquierda.png")));
		btnIzquierda.setBounds(337, 137, 70, 35);
		panel.add(btnIzquierda);

		btnDerecha = new JButton("");
		btnDerecha.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/derecha.png")));
		btnDerecha.addActionListener(this);
		btnDerecha.setBounds(555, 137, 70, 35);

		panel.add(btnDerecha);

		btnAtras = new JButton("");
		btnAtras.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/retroceder.png")));
		btnAtras.addActionListener(this);
		btnAtras.setBounds(255, 137, 70, 35);
		panel.add(btnAtras);

		btnAdelante = new JButton("");
		btnAdelante.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/adelantar.png")));
		btnAdelante.addActionListener(this);

		btnAdelante.setBounds(637, 138, 70, 35);
		panel.add(btnAdelante);

		btnNegacion = new JButton("\u00AC");
		btnNegacion.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnNegacion.setForeground(new Color(100, 149, 237));
		btnNegacion.setBounds(568, 49, 70, 35);
		btnNegacion.addActionListener(this);
		panel.add(btnNegacion);

		btnO = new JButton("v");
		btnO.setForeground(new Color(100, 149, 237));
		btnO.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnO.addActionListener(this);
		btnO.setBounds(640, 49, 70, 35);
		panel.add(btnO);

		btnY = new JButton("");
		btnY.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/back.png")));
		btnY.setSelectedIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/back.png")));
		btnY.addActionListener(this);
		btnY.setForeground(new Color(100, 149, 237));
		btnY.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnY.setBounds(712, 49, 70, 35);
		panel.add(btnY);

		btnEntonces = new JButton("\u2192");
		btnEntonces.addActionListener(this);
		btnEntonces.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnEntonces.setForeground(new Color(100, 149, 237));
		btnEntonces.setBounds(784, 49, 70, 35);
		panel.add(btnEntonces);

		btnSisoloSi = new JButton("\u2194");
		btnSisoloSi.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnSisoloSi.setForeground(new Color(100, 149, 237));
		btnSisoloSi.addActionListener(this);
		btnSisoloSi.setBounds(856, 49, 70, 35);
		panel.add(btnSisoloSi);

		btnP = new JButton("p");
		btnP.addActionListener(this);
		btnP.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnP.setForeground(new Color(100, 149, 237));
		btnP.setBounds(20, 49, 70, 35);
		panel.add(btnP);

		btnQ = new JButton("q");
		btnQ.addActionListener(this);
		btnQ.setForeground(new Color(100, 149, 237));
		btnQ.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnQ.setBounds(92, 49, 70, 35);
		panel.add(btnQ);

		btnR = new JButton("r");
		btnR.addActionListener(this);
		btnR.setForeground(new Color(100, 149, 237));
		btnR.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnR.setBounds(164, 49, 70, 35);
		panel.add(btnR);

		btnS = new JButton("s");
		btnS.addActionListener(this);
		btnS.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnS.setForeground(new Color(100, 149, 237));
		btnS.setBounds(236, 49, 70, 35);
		panel.add(btnS);

		btnT = new JButton("t");
		btnT.addActionListener(this);
		btnT.setForeground(new Color(100, 149, 237));
		btnT.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnT.setBounds(310, 49, 70, 35);
		panel.add(btnT);

		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(this);
		btnAgregar.setForeground(new Color(100, 149, 237));
		btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAgregar.setBounds(420, 138, 124, 35);

		panel.add(btnAgregar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setForeground(new Color(100, 149, 237));
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEliminar.setBounds(719, 138, 103, 35);
		panel.add(btnEliminar);

		btnVaciar = new JButton("Vaciar");
		btnVaciar.addActionListener(this);
		btnVaciar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnVaciar.setForeground(new Color(100, 149, 237));
		btnVaciar.setBounds(144, 137, 97, 35);
		panel.add(btnVaciar);

		btnResolver = new JButton("Resolver");
		btnResolver.setForeground(new Color(100, 149, 237));
		btnResolver.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnResolver.setBounds(337, 185, 288, 35);
		panel.add(btnResolver);

		JLabel fondoComandos = new JLabel("");
		fondoComandos.setBounds(12, 13, 953, 220);
		panel.add(fondoComandos);
		fondoComandos.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/fondo-blanco.jpg")));

		scrollPane = new JScrollPane();
		scrollPane.setBounds(-51, -124, 948, 168);
		panel.add(scrollPane);

		JLabel fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/fondo-blanco.jpg")));
		fondo.setBounds(0, 42, 1000, 630);
		contentPane.add(fondo);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String formula = txtFormula.getText();

		if (e.getSource() == btnNegacion) {
			try {
				insertarNegacion(formula);
			} catch (ParentesisException e1) {
				// TODO Auto-generated catch block
				mostrarError(e1.getMessage());
			}

		}
		if (e.getSource() == btnP) {
			try {
				insertarAtomo(formula, "p");
			} catch (ParentesisException e1) {
				// TODO Auto-generated catch block
				mostrarError(e1.getMessage());
			}
		}
		if (e.getSource() == btnQ) {
			try {
				insertarAtomo(formula, "q");
			} catch (ParentesisException e1) {
				// TODO Auto-generated catch block
				mostrarError(e1.getMessage());
			}
		}
		if (e.getSource() == btnR) {
			try {
				insertarAtomo(formula, "r");
			} catch (ParentesisException e1) {
				// TODO Auto-generated catch block
				mostrarError(e1.getMessage());
			}
		}
		if (e.getSource() == btnS) {
			try {
				insertarAtomo(formula, "s");

			} catch (ParentesisException e1) {
				// TODO Auto-generated catch block
				mostrarError(e1.getMessage());
			}
		}
		if (e.getSource() == btnT) {
			try {
				insertarAtomo(formula, "t");
			} catch (ParentesisException e1) {
				// TODO Auto-generated catch block
				mostrarError(e1.getMessage());
			}
		}

		if (e.getSource() == btnY) {
			try {
				insertarY(formula);
			} catch (ParentesisException e1) {
				// TODO Auto-generated catch block
				mostrarError(e1.getMessage());
			}
		}

		if (e.getSource() == btnEntonces) {
			try {
				insertarEntonces(formula);
			} catch (ParentesisException e1) {
				// TODO Auto-generated catch block
				mostrarError(e1.getMessage());
			}
		}
		if (e.getSource() == btnSisoloSi) {
			try {
				insertarSiSoloSi(formula);
			} catch (ParentesisException e1) {
				// TODO Auto-generated catch block
				mostrarError(e1.getMessage());
			}
		}
		if (e.getSource() == btnO) {
			try {
				insertarO(formula);
			} catch (ParentesisException e1) {
				// TODO Auto-generated catch block
				mostrarError(e1.getMessage());
			}
		}
		if (e.getSource() == btnVaciar) {
			txtFormula.setText("");
			txtFormula.requestFocus();
		}
		if (e.getSource() == btnIzquierda) {
			txtFormula.setCaretPosition(posicionCaretAnterior(formula, txtFormula.getCaretPosition()));
			txtFormula.requestFocus();
		}
		if (e.getSource() == btnDerecha) {
			txtFormula.setCaretPosition(posicionCaretSiguiente(formula, txtFormula.getCaretPosition()));
			txtFormula.requestFocus();
		}
		if (e.getSource() == btnAtras) {
			txtFormula.requestFocus();
			Action deshacer = new Deshacer(manager);
			deshacer.actionPerformed(e);

		}
		if (e.getSource() == btnAgregar) {
			try {
				agregarFormula(formula);
			} catch (ContinuasException | PremisaException e1) {
				// TODO Auto-generated catch block
				mostrarError(e1.getMessage());
			}
		}
		if (e.getSource() == btnAdelante) {
			txtFormula.requestFocus();
			Action rehacer = new Rehacer(manager);
			rehacer.actionPerformed(e);
		}
		if (e.getSource() == btnEliminar) {
			txtFormula.setText("");
			pos = 0;

		}

	}

	public String insertar(String formula, String ingreso, int n) throws ParentesisException {

		if (formula.charAt(pos - 1) == '(' && formula.charAt(pos) == ')') {
			String salida = formula.substring(0, n) + ingreso + formula.substring(n);
			txtFormula.setCaretPosition(posicionCaretSiguiente(formula, n));
			return salida;
		} else {
			throw new ParentesisException("El nuevo operador no se encuentra dentro de parentésis");
		}

	}

	public void insertarNegacion(String formula) throws ParentesisException {
		String n = NEGACION + "()";
		pos = txtFormula.getCaretPosition();

		if (formula.equals("")) {

			pos += 2;
			txtFormula.setText(n.trim());
			txtFormula.setCaretPosition(pos);
			txtFormula.requestFocus();
			return;
		} else {
			txtFormula.setText(insertar(formula, n, pos));
		}
		txtFormula.setCaretPosition(pos + 2);
		txtFormula.requestFocus();
		// txtFormula.moveCaretPosition(pos);
	}

	public void insertarY(String formula) throws ParentesisException {
		String n = " () " + CONJUNCION + " () ";
		pos = txtFormula.getCaretPosition();

		if (formula.equals("")) {

			txtFormula.setText(n.trim());
			pos += 1;
			txtFormula.setCaretPosition(pos);
			txtFormula.requestFocus();
			return;
		} else {
			txtFormula.setText(insertar(formula, n, pos));
		}
		pos += 2;
		txtFormula.setCaretPosition(pos);
		txtFormula.requestFocus();
		// txtFormula.moveCaretPosition(pos);
	}

	public void insertarO(String formula) throws ParentesisException {

		String n = " () " + DISYUNCION + " () ";
		pos = txtFormula.getCaretPosition();
		if (formula.equals("")) {
			txtFormula.setText(n.trim());
			pos += 1;
			txtFormula.setCaretPosition(pos);
			txtFormula.requestFocus();
			return;
		} else {
			txtFormula.setText(insertar(formula, n, pos));
		}
		pos += 2;
		txtFormula.setCaretPosition(pos);
		txtFormula.requestFocus();
	}

	public void insertarAtomo(String formula, String atomo) throws ParentesisException {
		char[] n = txtFormula.getText().toCharArray();
		pos = txtFormula.getCaretPosition();
		if (formula.equals("")) {

			txtFormula.setText(atomo);
		} else if (n[pos - 1] == '(' && n[pos] == ')') {
			txtFormula.setText(insertar(formula, atomo, txtFormula.getCaretPosition()));
		} else {
			throw new ParentesisException("El nuevo átomo no esta entre parentésis");
		}
		txtFormula.setCaretPosition(posicionCaretSiguiente(formula, pos) + 1);
		txtFormula.requestFocus();
	}

	public void insertarSiSoloSi(String formula) throws ParentesisException {
		String n = " () " + EQUIVALENCIA + " () ";
		pos = txtFormula.getCaretPosition();
		if (formula.equals("")) {

			txtFormula.setText(n.trim());
			pos += 1;
			txtFormula.setCaretPosition(pos);
			txtFormula.requestFocus();
			return;
		} else {
			txtFormula.setText(insertar(formula, n, pos));
		}
		pos += 2;
		txtFormula.setCaretPosition(pos);
		txtFormula.requestFocus();

	}

	public void insertarEntonces(String formula) throws ParentesisException {
		String n = " () " + CONDICIONAL + " () ";
		pos = txtFormula.getCaretPosition();
		if (formula.equals("")) {

			txtFormula.setText(n.trim());
			pos += 1;
			txtFormula.setCaretPosition(pos);
			txtFormula.requestFocus();
			return;
		} else {
			txtFormula.setText(insertar(formula, n, txtFormula.getCaretPosition()));
		}
		pos += 2;
		txtFormula.setCaretPosition(pos);
		txtFormula.requestFocus();
	}

	public void mostrarError(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.WARNING_MESSAGE);
	}

	public int posicionCaretSiguiente(String formula, int pos) {
		for (int i = pos; i < formula.length() - 1; i++) {
			char actual = formula.charAt(i);
			char siguiente = formula.charAt(i + 1);
			if (actual == '(' && siguiente == ')') {
				return i + 1;
			}

		}
		return 0;
	}

	public int posicionCaretAnterior(String formula, int pos) {
		for (int i = pos - 1; i > 1; i--) {
			char actual = formula.charAt(i);
			char siguiente = formula.charAt(i - 1);
			if (actual == ')' && siguiente == '(') {
				return i;
			}

		}
		return 0;
	}

	public void abrirPDF() throws IOException {
		File file = new File("");
		String helper = file.getAbsolutePath() + "\\src\\";
		String currentDir = helper + "Impresión de página completa.pdf";
		Runtime.getRuntime().exec(" rundll32 url.dll, FileProtocolHandler " + currentDir);
	}

	public void agregarFormula(String formula) throws ContinuasException, PremisaException {

		try {
			Validaciones.verificarPremisa(formula);
		} catch (ContinuasException e) {
			// TODO Auto-generated catch block
			throw new ContinuasException(e.getMessage());
		} catch (PremisaException e) {
			// TODO Auto-generated catch block
			throw new PremisaException(e.getMessage());
		}
		listaFormulas.add(formula);
		modelotabla.setRowCount(0);
		for (int i = 0; i < listaFormulas.size(); i++) {
			Object[] fila = { listaFormulas.get(i) };
			modelotabla.addRow(fila);
		}
		txtFormula.setText("");
		pos = 0;
	}
}
