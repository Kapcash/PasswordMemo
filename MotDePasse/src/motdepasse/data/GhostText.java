package motdepasse.data;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

public class GhostText implements FocusListener, DocumentListener, PropertyChangeListener{
    private final JTextComponent textComp;
    private boolean isEmpty;
    private Color ghostColor;
    private Color foregroundColor;
    private final String ghostText;

    public GhostText(final JTextComponent textComp, String ghostText){
        this.textComp = textComp;
        this.ghostText = ghostText;
        this.ghostColor = Color.LIGHT_GRAY;
        textComp.addFocusListener(this);
        registerListeners();
        updateState();
        if (!this.textComp.hasFocus()){
            focusLost(null);
        }
    }

    public void delete(){
        unregisterListeners();
        textComp.removeFocusListener(this);
    }

    private void registerListeners(){
        textComp.getDocument().addDocumentListener(this);
        textComp.addPropertyChangeListener("foreground", this);
    }

    private void unregisterListeners(){
        textComp.getDocument().removeDocumentListener(this);
        textComp.removePropertyChangeListener("foreground", this);
    }

    public Color getGhostColor(){
        return ghostColor;
    }

    public void setGhostColor(Color ghostColor){
        this.ghostColor = ghostColor;
    }

    private void updateState(){
        isEmpty = textComp.getText().length() == 0;
        foregroundColor = textComp.getForeground();
    }

	public void focusGained(FocusEvent e) {
		 if (isEmpty){
	            unregisterListeners();
	            try{
	                textComp.setText("");
	                textComp.setForeground(foregroundColor);
	            }
	            finally{
	                registerListeners();
	            }
	        }
	}
	
	public void focusLost(FocusEvent e) {
		 if (isEmpty){
	            unregisterListeners();
	            try{
	                textComp.setText(ghostText);
	                textComp.setForeground(ghostColor);
	            }
	            finally{
	                registerListeners();
	            }
	        }		
	}

	public void propertyChange(PropertyChangeEvent evt) {
		updateState();		
	}

	public void changedUpdate(DocumentEvent e) {
		updateState();		
	}

	public void insertUpdate(DocumentEvent e) {
		updateState();		
	}

	public void removeUpdate(DocumentEvent e) {
		updateState();		
	}
}