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
import javax.swing.border.LineBorder;
import excepciones.ContinuasException;
import excepciones.ParentesisException;
import excepciones.ParentesisVacioException;
import excepciones.PremisaException;
import mundo.Validaciones;

public class VentanaPrincipal extends JFrame implements ActionListener {

	/**
	 * @author Daniel Loaiza Camilo Quiceno Diana Montoya
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
	private JLabel lblComandos;
	private JLabel lblArea;
	private JLabel Español;
	private JLabel Ingles;
	private JLabel lblAtomos;
	private JLabel lblOperadores;
	private JLabel lblListaDeFormulas;

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

		setResizable(false);
		

		setUndecorated(false);
		setAutoRequestFocus(false);
		setTitle("");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		contentPane = new JPanel();
		contentPane.setToolTipText("\r\n");
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		lblProyectoLogica = new JLabel("Proyecto Logica");
		lblProyectoLogica.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		Ingles = new JLabel("");
		Ingles.setHorizontalAlignment(SwingConstants.CENTER);
		Ingles.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/united-kingdom.png")));
		Ingles.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				pasarIngles();
			}
		});
		Ingles.setBounds(830, 0, 53, 41);
		contentPane.add(Ingles);

		
		lblProyectoLogica = new JLabel("Lógica Proposicional ");
		lblProyectoLogica.setFont(new Font("Tahoma", Font.BOLD, 22));

		lblProyectoLogica.setForeground(new Color(255, 255, 255));
		lblProyectoLogica.setBounds(12, 13, 244, 28);
		contentPane.add(lblProyectoLogica);
		
		Español = new JLabel("");
		Español.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/spain.png")));
		Español.setHorizontalAlignment(SwingConstants.CENTER);
		Español.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				pasarEspañol();
			}
		});
		Español.setBounds(777, 0, 53, 41);
		contentPane.add(Español);

		lblListaDeFormulas = new JLabel("Lista de Formulas");
		lblListaDeFormulas.setForeground(new Color(100, 149, 237));
		lblListaDeFormulas.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblListaDeFormulas.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaDeFormulas.setBounds(197, 412, 590, 16);
		contentPane.add(lblListaDeFormulas);
		manager = new UndoManager();

		txtFormula = new JTextField();
		txtFormula.setBounds(12, 99, 947, 41);
		contentPane.add(txtFormula);
		txtFormula.setColumns(10);
		txtFormula.addMouseListener(new MouseListener() {

		
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
					if (!actual.isEmpty()) {
						if (pos - 1 > 0) {
							if (actual.charAt(pos - 1) != '(' || actual.charAt(pos) != ')') {
								mostrarError("El nuevo atomo no se encontraría entre paréntesis");
								txtFormula.setText(actual);
							}
						} else {
							mostrarError("El nuevo atomo no se encontraría entre paréntesis");
							txtFormula.setText(actual);
						}
					}

				}
			}
		});

		txtFormula.getDocument().addUndoableEditListener(manager);
		new JLabel("Area entrada");

		lblArea = new JLabel("Area Entrada");
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
		btnCerrar.setBounds(938, 0, 44, 41);
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
		btnInformacion.setBounds(882, 0, 44, 41);
		contentPane.add(btnInformacion);

		table = new JTable();

		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		table.setBorder(new LineBorder(new Color(100, 149, 237), 2));
		table.setBounds(12, 430, 958, 160);
		contentPane.add(table);
		modelotabla.addColumn("Lista Formulas");
		table.setModel(modelotabla);

		JLabel lblLinea = new JLabel("");
		lblLinea.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/fondo-azul-claro.jpg")));
		lblLinea.setBackground(new Color(135, 206, 250));
		lblLinea.setHorizontalAlignment(SwingConstants.CENTER);
		lblLinea.setBounds(0, 0, 982, 41);
		contentPane.add(lblLinea);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(12, 153, 970, 246);
		contentPane.add(panel);
		panel.setLayout(null);

		lblAtomos = new JLabel("Atomos");
		lblAtomos.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtomos.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAtomos.setForeground(new Color(100, 149, 237));
		lblAtomos.setBounds(145, 13, 110, 23);
		panel.add(lblAtomos);

		lblOperadores = new JLabel("Operadores");
		lblOperadores.setHorizontalAlignment(SwingConstants.CENTER);
		lblOperadores.setForeground(new Color(100, 149, 237));
		lblOperadores.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblOperadores.setBounds(640, 13, 229, 23);
		panel.add(lblOperadores);

		lblComandos = new JLabel("Comandos");
		lblComandos.setHorizontalAlignment(SwingConstants.CENTER);
		lblComandos.setForeground(new Color(100, 149, 237));
		lblComandos.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblComandos.setBounds(420, 98, 124, 27);
		panel.add(lblComandos);

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

		btnO = new JButton("∨");
		btnO.setForeground(new Color(100, 149, 237));
		btnO.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnO.addActionListener(this);
		btnO.setBounds(640, 49, 70, 35);
		panel.add(btnO);

		btnY = new JButton("∧");
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
		fondoComandos.setBounds(12, 13, 946, 220);
		panel.add(fondoComandos);
		fondoComandos.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/fondo-blanco.jpg")));


		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 13, 948, 220);
		panel.add(scrollPane);

		JLabel fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/fondo-blanco.jpg")));
		fondo.setBounds(0, 42, 982, 561);
		contentPane.add(fondo);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 448, 958, 142);
		contentPane.add(scrollPane);
		

	}
	/**
	 * Se llaman todas las acciones de los botones
	 */
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
//		if (e.getSource() == btnAtras) {
//			txtFormula.requestFocus();
//			
//			Action deshacer = new Deshacer(manager);
//			deshacer.actionPerformed(e);
//
//		}
		if (e.getSource() == btnAgregar) {
			try {
				agregarFormula(formula);
			} catch (ContinuasException | PremisaException | ParentesisVacioException e1) {
				// TODO Auto-generated catch block
				mostrarError(e1.getMessage());
			}
		}
//		if (e.getSource() == btnAdelante) {
//			txtFormula.requestFocus();
//			Action rehacer = new Rehacer(manager);
//			rehacer.actionPerformed(e);
//		}
		if (e.getSource() == btnEliminar) {
			txtFormula.setText("");
			pos = 0;

		}
	}
	
	
	/**
	  * Agrega a la formula nuevas cadenas en una posicion indicada
	  * @param String formula  
	  * es la formula que se introduce por el jtexfield
	  * @param String ingreso 
	  * indica cual es la cadena que se le agregara a la formula
	  * @param int n
	  * nos indica la posicion donde sera agregada la nueva cadena
	  */
	public String insertar(String formula, String ingreso, int n) throws ParentesisException {
		if (formula.charAt(pos - 1) == '(' && formula.charAt(pos) == ')') {
			String salida = formula.substring(0, n) + ingreso + formula.substring(n);
			txtFormula.setCaretPosition(posicionCaretSiguiente(formula, n));
			return salida;
		} else {
			throw new ParentesisException("El nuevo operador no se encuentra dentro de parentésis");
		}
	}

	
		/**
	  * Inserta la negacion en la formula
	  * @param String formula  
	  * es la formula que se introduce por el jtexfield
	  */	
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

	/**
	  * Inserta la conjuncion a la formula
	  * @param String formula  
	  * es la formula que se introduce por el jtexfield
	  */
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


/**
  * Inserta la disyuncion a la formula 
  * ()v()
  * @param String formula  
  * es la formula que se introduce por el jtexfield
  */	
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


	/**
	  * Agrega los atomos a las formulas
	  * @param String formula  
	  * es la formula que se introduce por el jtexfield
	  */
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

			


	

	/**
	  * Inserta  el bicondicional en la formula
	  * de forma ()<->()
	  * @param String formula  
	  * es la formula que se introduce por el jtexfield
	  */
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

	/**
	  * Escribe el  condicional en la formula
	  * con sus respectivos ()->()
	  * @param String formula  
	  * es la formula que se introduce por el jtexfield
	  */
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
	
	/**
	 * 
	 * @throws IOException
	 */

	public void abrirPDF() throws IOException {
		File file = new File("");
		String helper = file.getAbsolutePath() + "\\src\\";
		String currentDir = helper + "MANUAL DE USUARIO-convertido.pdf";
		Runtime.getRuntime().exec(" rundll32 url.dll, FileProtocolHandler " + currentDir);
	}
	 /**
	  * Captura la formula que esta en el cuadro de texto
	  * la guarda en un arraylist
	  * y posteriormente la carga en la tabla
	  * @param String formula  
	  * es la formula que se introduce por el jtexfield
	  */
	public void agregarFormula(String formula) throws ContinuasException, PremisaException, ParentesisVacioException {
{
		try {
			Validaciones.verificarPremisa(formula);
			listaFormulas.add(formula);
			modelotabla.setRowCount(0);
			for (int i = 0; i < listaFormulas.size(); i++) {
				Object[] fila = { listaFormulas.get(i) };
				modelotabla.addRow(fila);
			}
			txtFormula.setText("");
			pos = 0;
		} catch (ContinuasException e) {
			// TODO Auto-generated catch block
			throw new ContinuasException(e.getMessage());
		} catch (PremisaException e) {
			// TODO Auto-generated catch block
			throw new PremisaException(e.getMessage());
		} catch (ParentesisVacioException e) {
			// TODO Auto-generated catch block
			throw new ParentesisVacioException(e.getMessage());
		}
		
	}


}
	/**
	 * Metodo que traduce a ingles.
	 */
	public void pasarIngles () {
		
		lblArea.setText("Entry Area");
		lblProyectoLogica.setText("Propositional Logic");
		lblAtomos.setText("Atoms");
		lblOperadores.setText("Operators");
		lblComandos.setText("Commands");
		btnVaciar.setText("Empty");
		btnEliminar.setText("Delete");
		btnAgregar.setText("Add");
		btnResolver.setText("Resolve");
		lblListaDeFormulas.setText("List of Formulas");
		
	}
	/**
	 * Metodo que traduce a Español.
	 */
public void pasarEspañol () {
		
		lblArea.setText("Area Entrada");
		lblProyectoLogica.setText("Lógica Proposicional");
		lblAtomos.setText("Atomos");
		lblOperadores.setText("Operadores");
		lblComandos.setText("Comandos");
		btnVaciar.setText("Vaciar");
		btnEliminar.setText("Eliminar");
		btnAgregar.setText("Agregar");
		btnResolver.setText("Resolver");
		lblListaDeFormulas.setText("Lista de Formulas");
		
	}
}