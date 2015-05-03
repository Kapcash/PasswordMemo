package motdepasse.ctrl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import motdepasse.data.Mdp;
import motdepasse.data.MdpData;
import motdepasse.view.MdpView;

public class MdpListeners implements DocumentListener, MouseListener, KeyListener, ListSelectionListener {

	MdpView view;
	MdpData data;
	private JTextField search,name,login,mdp;
	private JButton ok,cancel;
	private String beforeEdit;
	private JPanel rightP;
	private boolean inPwd;

	public MdpListeners(MdpView v, MdpData d){
		this.view = v;
		this.data = d;
		search = view.getSearchField();
		name = view.getNameField();
		login = view.getLoginField();
		mdp = view.getMdpField();
		ok = view.getOkButton();
		cancel = view.getCancelButton();
		rightP = view.getRightPanel();
		inPwd = true;
	}

	public void mouseClicked(MouseEvent e) {
		Object src = e.getSource();
		if(src == view.getOkButton()){
			okAction();
		}
		else if(src == view.getCancelButton()){
			cancelAction();
		}
		else if(src == view.getOkPwdButton()){
			passwordAction();
		}
	}

	public void mousePressed(MouseEvent e) {
		Object src = e.getSource();
		if(src==view.getAddButton()){
			ImageIcon icon = new ImageIcon("./add_icon_click.png");
			Image img = icon.getImage() ;  
			Image newimg = img.getScaledInstance(25,25,Image.SCALE_SMOOTH ) ;  
			icon = new ImageIcon(newimg);
			view.getAddButton().setIcon(icon);
		}
		if(src==view.getRemoveButton()){
			ImageIcon icon = new ImageIcon("./remove_icon_click.png");
			Image img = icon.getImage() ;  
			Image newimg = img.getScaledInstance(25,25,Image.SCALE_SMOOTH ) ;  
			icon = new ImageIcon(newimg);
			view.getRemoveButton().setIcon(icon);
		}
		if(src==view.getEditButton()){
			ImageIcon icon = new ImageIcon("./edit_icon_click.png");
			Image img = icon.getImage() ;  
			Image newimg = img.getScaledInstance(25,25,Image.SCALE_SMOOTH ) ;  
			icon = new ImageIcon(newimg);
			view.getEditButton().setIcon(icon);
		}
	}

	public void mouseReleased(MouseEvent e) {
		Object src = e.getSource();
		if(src==view.getAddButton()){
			ImageIcon icon = new ImageIcon("./add_icon.png");
			Image img = icon.getImage() ;  
			Image newimg = img.getScaledInstance(25,25,Image.SCALE_SMOOTH ) ;  
			icon = new ImageIcon(newimg);
			view.getAddButton().setIcon(icon);
			addAction();
		}
		else if(src==view.getRemoveButton()){
			ImageIcon icon = new ImageIcon("./remove_icon.png");
			Image img = icon.getImage() ;  
			Image newimg = img.getScaledInstance(25,25,Image.SCALE_SMOOTH ) ;  
			icon = new ImageIcon(newimg);
			view.getRemoveButton().setIcon(icon);
			removeAction();
		}
		else if(src==view.getEditButton()){
			ImageIcon icon = new ImageIcon("./edit_icon.png");
			Image img = icon.getImage() ;  
			Image newimg = img.getScaledInstance(25,25,Image.SCALE_SMOOTH ) ;  
			icon = new ImageIcon(newimg);
			view.getEditButton().setIcon(icon);
			editAction();
		}
	}

	public void mouseEntered(MouseEvent e) {
		Object src = e.getSource();
		if(src==view.getAddButton()){
			ImageIcon icon = new ImageIcon("./add_icon_selected.png");
			Image img = icon.getImage() ;  
			Image newimg = img.getScaledInstance(25,25,Image.SCALE_SMOOTH ) ;  
			icon = new ImageIcon(newimg);
			view.getAddButton().setIcon(icon);
		}
		if(src==view.getRemoveButton()){
			ImageIcon icon = new ImageIcon("./remove_icon_selected.png");
			Image img = icon.getImage() ;  
			Image newimg = img.getScaledInstance(25,25,Image.SCALE_SMOOTH ) ;  
			icon = new ImageIcon(newimg);
			view.getRemoveButton().setIcon(icon);
		}
		if(src==view.getEditButton()){
			ImageIcon icon = new ImageIcon("./edit_icon_selected.png");
			Image img = icon.getImage() ;  
			Image newimg = img.getScaledInstance(25,25,Image.SCALE_SMOOTH ) ;  
			icon = new ImageIcon(newimg);
			view.getEditButton().setIcon(icon);
		}
	}

	public void mouseExited(MouseEvent e) {
		Object src = e.getSource();
		if(src==view.getAddButton()){
			ImageIcon icon = new ImageIcon("./add_icon.png");
			Image img = icon.getImage() ;  
			Image newimg = img.getScaledInstance(25,25,Image.SCALE_SMOOTH ) ;  
			icon = new ImageIcon(newimg);
			view.getAddButton().setIcon(icon);
		}
		if(src==view.getRemoveButton()){
			ImageIcon icon = new ImageIcon("./remove_icon.png");
			Image img = icon.getImage() ;  
			Image newimg = img.getScaledInstance(25,25,Image.SCALE_SMOOTH ) ;  
			icon = new ImageIcon(newimg);
			view.getRemoveButton().setIcon(icon);
		}
		if(src==view.getEditButton()){
			ImageIcon icon = new ImageIcon("./edit_icon.png");
			Image img = icon.getImage() ;  
			Image newimg = img.getScaledInstance(25,25,Image.SCALE_SMOOTH ) ;  
			icon = new ImageIcon(newimg);
			view.getEditButton().setIcon(icon);
		}
	}

	public void keyTyped(KeyEvent e) {;}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER && ok.isEnabled()){
			ok.doClick();
			okAction();
		}
		else if(e.getKeyCode() == KeyEvent.VK_ENTER && inPwd){
			view.getOkPwdButton().doClick();
			this.passwordAction();
		}
		else if(e.getSource() == view.getListmdp() && e.getKeyCode() == KeyEvent.VK_DELETE){
			this.removeAction();
		}
		else if(e.getSource() == view.getSearchField() && e.getKeyCode() == KeyEvent.VK_DOWN){
			view.getListmdp().requestFocusInWindow();
			view.getListmdp().setSelectedIndex(0);
		}
	}

	public void keyReleased(KeyEvent e) {;}

	public void valueChanged(ListSelectionEvent e) {
		try{
				Mdp tmp = view.getListmdp().getSelectedValue();
				name.setText(tmp.getNom());
				login.setText(tmp.getLogin());
				mdp.setText(tmp.getMdp());
		}
		catch(Exception ex){
			//Nothing to do
		}
	}

	public void changedUpdate(DocumentEvent arg0) {;}

	public void insertUpdate(DocumentEvent e) {
		String s = search.getText();
		if(!s.equals("Rechercher")){
			this.research();
		}
	}

	public void removeUpdate(DocumentEvent e) {
		String s = search.getText();
		if(!s.equals("Rechercher")){
			this.research();
		}
	}


	private void addAction(){
		name.setText("Nom");
		name.setEditable(true);
		login.setText("");
		login.setEditable(true);
		mdp.setText("");
		mdp.setEditable(true);
		ok.setEnabled(true);
		cancel.setEnabled(true);
		rightP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"<html><font color=\"blue\">Informations</font></html>",0,0,new Font("Arial", 0, 15)));
		data.setInAdd(true);
	}


	private void editAction(){
		name.setEditable(true);
		login.setEditable(true);
		mdp.setEditable(true);
		ok.setEnabled(true);
		cancel.setEnabled(true);
		rightP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"<html><font color=\"yellow\">Informations</font></html>",0,0,new Font("Arial", 0, 15)));
		data.setInEdit(true);
		beforeEdit = name.getText();
	}


	private void removeAction(){
		int selectedOption = JOptionPane.showConfirmDialog(view, 
				"Êtes-vous sûr de vouloir supprimer "+view.getListmdp().getSelectedValue().getNom()+" ?", 
				"Choix", 
				JOptionPane.YES_NO_OPTION); 
		if (selectedOption == JOptionPane.YES_OPTION) {
			data.removeMdp(view.getListmdp().getSelectedValue().getNom());
		}
	}

	private void okAction(){
		rightP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"Informations",0,0,new Font("Arial", 0, 15)));
		if(data.isInAdd()){
			data.addMdp(name.getText(), login.getText(), mdp.getText());
			view.getListmdp().setSelectedValue(data.findByName(name.getText()),true);
		}
		else if(data.isInEdit()){
			data.editMdp(beforeEdit, name.getText(), login.getText(), mdp.getText());
		}
		emptyFields();
		view.sortList();
		view.getSearchField().setText("");
	}


	private void cancelAction(){
		rightP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"Informations",0,0,new Font("Arial", 0, 15)));
		emptyFields();
	}
	
	private void passwordAction(){
		char[] pwd = view.getPwdField().getPassword();
		String password = new String(pwd);
		if(password.equals("110895")){
			view.getPwdLabel().setText("Authentification réussie");
			view.initData();
			inPwd = false;
		}
		else{
			new Thread(){
				@Override
				public void run(){
					try {
						view.getPwdLabel().setText("Mot de passe incorrecte !");
						view.getPwdLabel().setForeground(Color.red);
						Thread.sleep(5000);
						view.getPwdLabel().setText(" ");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
	}

	private void research(){
		String s = search.getText();
		ArrayList<Mdp> list = data.getList();
		if(!s.equals("Rechercher")){
			view.getListModel().removeAllElements();
			for(int i=0;i<list.size();i++){
				Mdp mdp = list.get(i);
				if(mdp.getNom().toUpperCase().contains(s.toUpperCase())){
					view.getListModel().addElement(list.get(i));
				}
			}
		}
	}


	private void emptyFields(){
		name.setText("Nom");
		name.setEditable(false);
		login.setText("");
		login.setEditable(false);
		mdp.setText("");
		mdp.setEditable(false);
		ok.setEnabled(false);
		cancel.setEnabled(false);
		data.setInAdd(false);
		data.setInEdit(false);
	}

}