package interfaz;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;

public class Rehacer extends AbstractAction {

	private UndoManager manager;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		try {
			manager.redo();
			manager.redo();
		} catch (CannotRedoException e) {
			// TODO: handle exception
			Toolkit.getDefaultToolkit().beep();
		}
	}

	public Rehacer(UndoManager manager) {
		super();
		this.manager = manager;
	}

}
