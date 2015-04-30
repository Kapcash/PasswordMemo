package motdepasse.ctrl;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import motdepasse.data.Mdp;
import motdepasse.data.MdpData;
import motdepasse.view.MdpView;

public class MdpListeners implements MouseListener, KeyListener, ListSelectionListener {
	
	MdpView view;
	MdpData data;
	
	public MdpListeners(MdpView v, MdpData d){
		this.view = v;
		this.data = d;
	}

	public void mouseClicked(MouseEvent e) {;}

	public void mousePressed(MouseEvent e) {
		Object src = e.getSource();
		if(src==view.getAddButton()){
			ImageIcon icon = new ImageIcon("C:/Users/Florent/Documents/Logiciels/MotDePasse/add_icon_click.png");
			Image img = icon.getImage() ;  
			Image newimg = img.getScaledInstance(25,25,Image.SCALE_SMOOTH ) ;  
			icon = new ImageIcon(newimg);
			view.getAddButton().setIcon(icon);
		}
		if(src==view.getRemoveButton()){
			ImageIcon icon = new ImageIcon("C:/Users/Florent/Documents/Logiciels/MotDePasse/remove_icon_click.png");
			Image img = icon.getImage() ;  
			Image newimg = img.getScaledInstance(25,25,Image.SCALE_SMOOTH ) ;  
			icon = new ImageIcon(newimg);
			view.getRemoveButton().setIcon(icon);
		}
		if(src==view.getEditButton()){
			ImageIcon icon = new ImageIcon("C:/Users/Florent/Documents/Logiciels/MotDePasse/edit_icon_click.png");
			Image img = icon.getImage() ;  
			Image newimg = img.getScaledInstance(25,25,Image.SCALE_SMOOTH ) ;  
			icon = new ImageIcon(newimg);
			view.getEditButton().setIcon(icon);
		}
	}

	public void mouseReleased(MouseEvent e) {
		Object src = e.getSource();
		if(src==view.getAddButton()){
			ImageIcon icon = new ImageIcon("C:/Users/Florent/Documents/Logiciels/MotDePasse/add_icon.png");
			Image img = icon.getImage() ;  
			Image newimg = img.getScaledInstance(25,25,Image.SCALE_SMOOTH ) ;  
			icon = new ImageIcon(newimg);
			view.getAddButton().setIcon(icon);
			view.addAction();
		}
		else if(src==view.getRemoveButton()){
			ImageIcon icon = new ImageIcon("C:/Users/Florent/Documents/Logiciels/MotDePasse/remove_icon.png");
			Image img = icon.getImage() ;  
			Image newimg = img.getScaledInstance(25,25,Image.SCALE_SMOOTH ) ;  
			icon = new ImageIcon(newimg);
			view.getRemoveButton().setIcon(icon);
			view.removeAction();
		}
		else if(src==view.getEditButton()){
			ImageIcon icon = new ImageIcon("C:/Users/Florent/Documents/Logiciels/MotDePasse/edit_icon.png");
			Image img = icon.getImage() ;  
			Image newimg = img.getScaledInstance(25,25,Image.SCALE_SMOOTH ) ;  
			icon = new ImageIcon(newimg);
			view.getEditButton().setIcon(icon);
			view.editAction();
		}
		else if(src == view.getOkButton()){
			view.okAction();
		}
		else if(src == view.getCancelButton()){
			view.cancelAction();
		}
	}

	public void mouseEntered(MouseEvent e) {
		Object src = e.getSource();
		if(src==view.getAddButton()){
			ImageIcon icon = new ImageIcon("C:/Users/Florent/Documents/Logiciels/MotDePasse/add_icon_selected.png");
			Image img = icon.getImage() ;  
			Image newimg = img.getScaledInstance(25,25,Image.SCALE_SMOOTH ) ;  
			icon = new ImageIcon(newimg);
			view.getAddButton().setIcon(icon);
		}
		if(src==view.getRemoveButton()){
			ImageIcon icon = new ImageIcon("C:/Users/Florent/Documents/Logiciels/MotDePasse/remove_icon_selected.png");
			Image img = icon.getImage() ;  
			Image newimg = img.getScaledInstance(25,25,Image.SCALE_SMOOTH ) ;  
			icon = new ImageIcon(newimg);
			view.getRemoveButton().setIcon(icon);
		}
		if(src==view.getEditButton()){
			ImageIcon icon = new ImageIcon("C:/Users/Florent/Documents/Logiciels/MotDePasse/edit_icon_selected.png");
			Image img = icon.getImage() ;  
			Image newimg = img.getScaledInstance(25,25,Image.SCALE_SMOOTH ) ;  
			icon = new ImageIcon(newimg);
			view.getEditButton().setIcon(icon);
		}
	}

	public void mouseExited(MouseEvent e) {
		Object src = e.getSource();
		if(src==view.getAddButton()){
			ImageIcon icon = new ImageIcon("C:/Users/Florent/Documents/Logiciels/MotDePasse/add_icon.png");
			Image img = icon.getImage() ;  
			Image newimg = img.getScaledInstance(25,25,Image.SCALE_SMOOTH ) ;  
			icon = new ImageIcon(newimg);
			view.getAddButton().setIcon(icon);
		}
		if(src==view.getRemoveButton()){
			ImageIcon icon = new ImageIcon("C:/Users/Florent/Documents/Logiciels/MotDePasse/remove_icon.png");
			Image img = icon.getImage() ;  
			Image newimg = img.getScaledInstance(25,25,Image.SCALE_SMOOTH ) ;  
			icon = new ImageIcon(newimg);
			view.getRemoveButton().setIcon(icon);
		}
		if(src==view.getEditButton()){
			ImageIcon icon = new ImageIcon("C:/Users/Florent/Documents/Logiciels/MotDePasse/edit_icon.png");
			Image img = icon.getImage() ;  
			Image newimg = img.getScaledInstance(25,25,Image.SCALE_SMOOTH ) ;  
			icon = new ImageIcon(newimg);
			view.getEditButton().setIcon(icon);
		}
	}

	public void keyTyped(KeyEvent e) {;}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER && view.getOkButton().isEnabled()){
			view.getOkButton().doClick();
			view.okAction();
			Mdp tmp = view.getListmdp().getSelectedValue();
			view.getNameField().setText(tmp.getNom());
			view.getLoginField().setText(tmp.getLogin());
			view.getMdpField().setText(tmp.getMdp());
		}
	}

	public void keyReleased(KeyEvent e) {;}

	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()){
			Mdp tmp = view.getListmdp().getSelectedValue();
			view.getNameField().setText(tmp.getNom());
			view.getLoginField().setText(tmp.getLogin());
			view.getMdpField().setText(tmp.getMdp());
		}
	}
}