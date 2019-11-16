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

public class VentanaPrincipal extends JFrame implements ActionListener {

	public static final String NEGACION = "¬";
	public static final String CONJUNCION = "∧";
	public static final String DISYUNCION = "∨";
	public static final String CONDICIONAL = "→";
	public static final String EQUIVALENCIA = "↔";
	private int pos = 0;
	private JPanel contentPane;
	private JLabel lblArea;
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

		lblArea = new JLabel("Area entrada");
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
		btnCerrar.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/cancel.png")));
		btnCerrar.setHorizontalAlignment(SwingConstants.CENTER);
		btnCerrar.setBounds(932, 0, 56, 41);
		contentPane.add(btnCerrar);

		JLabel informacion = new JLabel("");
		informacion.setHorizontalAlignment(SwingConstants.CENTER);
		informacion.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/info.png")));
		informacion.setBounds(882, 0, 56, 41);
		contentPane.add(informacion);

		table = new JTable();
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setBounds(12, 412, 976, 225);
		contentPane.add(table);

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
		label.setForeground(new Color(100, 149, 237));
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		label.setBounds(423, 129, 116, 27);
		panel.add(label);

		btnIzquierda = new JButton("");
		btnIzquierda.addActionListener(this);
		btnIzquierda.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/izquierda.png")));
		btnIzquierda.setBounds(351, 169, 70, 35);
		panel.add(btnIzquierda);

		btnDerecha = new JButton("");
		btnDerecha.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/derecha.png")));
		btnDerecha.addActionListener(this);
		btnDerecha.setBounds(538, 169, 70, 35);
		panel.add(btnDerecha);

		btnAtras = new JButton("");
		btnAtras.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/retroceder.png")));
		btnAtras.addActionListener(this);
		btnAtras.setBounds(269, 169, 70, 35);
		panel.add(btnAtras);

		btnAdelante = new JButton("");
		btnAdelante.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/adelantar.png")));
		btnAdelante.addActionListener(this);

		btnAdelante.setBounds(620, 169, 70, 35);
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

		btnY = new JButton("∧");
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
		btnAgregar.setBounds(433, 169, 93, 35);
		panel.add(btnAgregar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setForeground(new Color(100, 149, 237));
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEliminar.setBounds(702, 169, 103, 35);
		panel.add(btnEliminar);

		btnVaciar = new JButton("Vaciar");
		btnVaciar.addActionListener(this);
		btnVaciar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnVaciar.setForeground(new Color(100, 149, 237));
		btnVaciar.setBounds(158, 169, 97, 35);
		panel.add(btnVaciar);

		JLabel fondoComandos = new JLabel("");
		fondoComandos.setBounds(12, 13, 953, 220);
		panel.add(fondoComandos);
		fondoComandos.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/fondo-blanco.jpg")));

		JLabel fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/fondo-blanco.jpg")));
		fondo.setBounds(0, 42, 1000, 630);
		contentPane.add(fondo);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String formula = txtFormula.getText();
		
		if (e.getSource() == btnNegacion) {
			insertarNegacion(formula);
		}
		if (e.getSource() == btnP) {
			insertarAtomo(formula,"p");
		}
		if (e.getSource() == btnQ) {
			insertarAtomo(formula,"q");
		}
		if (e.getSource() == btnR) {
			insertarAtomo(formula,"r");
		}
		if (e.getSource() == btnS) {
			insertarAtomo(formula,"s");
		}
		if (e.getSource() == btnT) {
			insertarAtomo(formula,"t");
		}
		
		 if (e.getSource() == btnY) {
			 insertarConjuncion(formula);	        
		}
			     
		 if (e.getSource() == btnEntonces) { 
		  insertarEntonces(formula);
		  } 
		  if (e.getSource() == btnSisoloSi) {
		  insertarSiSoloSi(formula);
		  } 
		  if (e.getSource() == btnO) {
			  insertarDisyuncion(formula);
		  } 
		  if (e.getSource() == btnVaciar) {
		  
		  } 
		  if (e.getSource() == btnIzquierda) {
		  
		  } 
		  if (e.getSource() == btnDerecha) {
			  int aux = 1+ txtFormula.getCaretPosition();
			  if (formula.length() > aux) {
				   for (int i = aux; i < formula.length(); i++) {
					if (formula.charAt(i) == ')' && !esAtomo(formula.charAt(i-1))) {
						txtFormula.setCaretPosition(i);
						break;
					}
				}
			}
		  } 
		  if (e.getSource() == btnAtras) {
		  
		  } 
		  if (e.getSource() == btnAgregar) {
		  
		  } 
		  if (e.getSource() == btnAdelante) {
		  
		  } 
		  if (e.getSource() == btnEliminar) {
			  txtFormula.setText("");
			  pos=0;
		  
		  }
		 
	}

private boolean esAtomo(char charAt) {
		// TODO Auto-generated method stub
		return false;
	}

public String insertar(String formula, String ingreso, int n) {
		String salida = formula.substring(0, n) + ingreso + formula.substring(n);

		return salida;
	}
public void insertarNegacion (String formula)
{
	String n = NEGACION + "()";
	if (formula.equals("")) {
		txtFormula.setText(n);
	} else {
		txtFormula.setText(insertar(formula, n, txtFormula.getCaretPosition()));
	}
	pos += 2+ txtFormula.getCaretPosition();

}
public void insertarConjuncion(String formula) {
	String n = "()" + CONJUNCION + "()";
    if (formula.equals("")) {
        txtFormula.setText(n);
    } else
       txtFormula.setText(insertar(formula, n,txtFormula.getCaretPosition()));
    pos += txtFormula.getCaretPosition()+1;
}
public void insertarAtomo(String formula,String atomo) {
	 if (formula.equals("")) {
         txtFormula.setText("(p)");
     } else {

         insertar(formula,atomo,txtFormula.getCaretPosition() );
         pos += 1+ txtFormula.getCaretPosition();
     }
	
}
public void insertarSiSoloSi(String formula)
{
	        String n = "()" + EQUIVALENCIA+ "()";
	        if (formula.equals("")) {
	           txtFormula.setText(n);

	        } else
	         insertar(formula,n,txtFormula.getCaretPosition());
	        pos += 1+txtFormula.getCaretPosition() ;
	        
}
public void insertarEntonces(String formula)
{
          String n = "()" +CONDICIONAL + "()";
          if (formula.equals("")) {
              txtFormula.setText(n);
          } else
              insertar(formula,n,txtFormula.getCaretPosition());
          pos += 1+txtFormula.getCaretPosition();      
}
public void insertarDisyuncion(String formula)
{
	
        String n = "()" + DISYUNCION+ "()";
        if (formula.equals("")) {
            txtFormula.setText(n);
        } else {
          insertar(formula,n,txtFormula.getCaretPosition());
        pos += 1+txtFormula.getCaretPosition();
        }
    
}
}
