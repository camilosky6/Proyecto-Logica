package interfaz;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

public class Deshacer extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UndoManager manager;

	public Deshacer(UndoManager manager) {
		this.manager = manager;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			manager.undo();
			manager.undo();
		} catch (CannotUndoException e2) {
			// TODO: handle exception
			Toolkit.getDefaultToolkit().beep();
		}
	}

}
