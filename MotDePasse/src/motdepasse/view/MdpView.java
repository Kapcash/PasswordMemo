package motdepasse.view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EtchedBorder;

import motdepasse.ctrl.MdpListListener;
import motdepasse.ctrl.MdpListeners;
import motdepasse.data.Mdp;
import motdepasse.data.MdpData;

public class MdpView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel backgroundPanel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JLabel iconLabel;
	private JLabel loginLabel;
	private JLabel mdpLabel;
	private JTextField nameField;
	private JTextField loginField;
	private JTextField mdpField;
	private JPanel iconPanel;
	private JLabel addButton;
	private JLabel removeButton;
	private JLabel editButton;
	private JPanel buttonPanel;
	private JScrollPane scroll;
	private JList<Mdp> listmdp;
	private DefaultListModel<Mdp> listModel;
	private JButton okButton;
	private boolean inAdd,inEdit;
	private String beforeEdit;
	private MdpData data;
	private JButton cancelButton;
	private JPanel buttonOkPanel;

	public MdpView(){
		super();
		if(this.ask()){
			data = MdpData.getInstance(this);
			this.setMinimumSize(new Dimension(600,400));
			this.setPreferredSize(new Dimension(600,400));
			this.setResizable(false);
			this.init();
			this.setTitle("Mots de passe");
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(true);
			this.setIconImage(new ImageIcon("C:/Users/Florent/Dropbox/Cours/ProgPerso/ProgrammeMdp/Icon_mdp.png").getImage());
		}
	}

	public void init(){
		
		//Panel de fond
		backgroundPanel = new JPanel();
		//Panel de gauche
		leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(200,360));
		leftPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"Liste",0,0,new Font("Arial", 0, 15)));
		//Panel de droite
		rightPanel = new JPanel();
		rightPanel.setFocusable(true);
		rightPanel.setPreferredSize(new Dimension(380,360));
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
		rightPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"Informations",0,0,new Font("Arial", 0, 15)));
		//Panel haut du rightPanel
		iconPanel = new JPanel();
		iconPanel.setLayout(new BoxLayout(iconPanel, BoxLayout.LINE_AXIS));
		//Panel principal du leftPanel
		
		//Panel des buttons Add / Edit / Delete
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		
		//Panel des buttons Ok / Annuler
		buttonOkPanel = new JPanel();
		buttonOkPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		//JButton
		addButton = new JLabel();
		ImageIcon icon1 = new ImageIcon("C:/Users/Florent/Documents/Logiciels/MotDePasse/add_icon.png");
		Image img1 = icon1.getImage() ;  
		Image newimg1 = img1.getScaledInstance(25,25,Image.SCALE_SMOOTH ) ;  
		icon1 = new ImageIcon(newimg1);
		addButton.setIcon(icon1);
		addButton.setBorder(BorderFactory.createEmptyBorder());
		addButton.setBackground(new Color(0,0,0,0));
		
		removeButton = new JLabel();
		ImageIcon icon2 = new ImageIcon("C:/Users/Florent/Documents/Logiciels/MotDePasse/remove_icon.png");
		Image img2 = icon2.getImage() ;  
		Image newimg2 = img2.getScaledInstance(25,25,Image.SCALE_SMOOTH ) ;  
		icon2 = new ImageIcon(newimg2);
		removeButton.setIcon(icon2);
		removeButton.setBorder(BorderFactory.createEmptyBorder());
		removeButton.setBackground(new Color(0,0,0,0));
		
		editButton = new JLabel();
		ImageIcon icon3 = new ImageIcon("C:/Users/Florent/Documents/Logiciels/MotDePasse/edit_icon.png");
		Image img3 = icon3.getImage() ;  
		Image newimg3 = img3.getScaledInstance(25,25,Image.SCALE_SMOOTH ) ;  
		icon3 = new ImageIcon(newimg3);
		editButton.setIcon(icon3);
		editButton.setBorder(BorderFactory.createEmptyBorder());
		editButton.setBackground(new Color(0,0,0,0));
		//JLabel
		iconLabel = new JLabel();
		iconLabel.setPreferredSize(new Dimension(30,30));
		iconLabel.setMinimumSize(new Dimension(30,30));
		iconLabel.setMaximumSize(new Dimension(30,30));
		iconLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		iconLabel.setOpaque(true);
		iconLabel.setBorder(new DashedBorder());
		loginLabel = new JLabel("   Login");
		loginLabel.setFont(new Font("Arial",Font.BOLD, 15));
		loginLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		loginLabel.setPreferredSize(new Dimension(350,30));
		loginLabel.setMinimumSize(new Dimension(350,30));
		loginLabel.setMaximumSize(new Dimension(350,30));
		mdpLabel = new JLabel("   Mot de passe");
		mdpLabel.setFont(new Font("Arial", Font.BOLD, 15));
		mdpLabel.setPreferredSize(new Dimension(350,30));
		mdpLabel.setMinimumSize(new Dimension(350,30));
		mdpLabel.setMaximumSize(new Dimension(350,30));
		//JTextField
		nameField = new JTextField("Nom");
		nameField.setMinimumSize(new Dimension(300,30));
		nameField.setPreferredSize(new Dimension(300,30));
		nameField.setMaximumSize(new Dimension(300,30));
		nameField.setEditable(false);
		nameField.setFont(new Font("Arial", 0, 15));
		nameField.setBorder(BorderFactory.createCompoundBorder(
				nameField.getBorder(), 
		        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		loginField = new JTextField();
		loginField.setMinimumSize(new Dimension(350,30));
		loginField.setPreferredSize(new Dimension(350,30));
		loginField.setMaximumSize(new Dimension(350,30));
		loginField.setEditable(false);
		loginField.setFont(new Font("Arial", 0, 15));
		loginField.setBorder(BorderFactory.createCompoundBorder(
				loginField.getBorder(), 
		        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		mdpField = new JTextField();
		mdpField.setMinimumSize(new Dimension(350,30));
		mdpField.setPreferredSize(new Dimension(350,30));
		mdpField.setMaximumSize(new Dimension(350,30));
		mdpField.setEditable(false);
		mdpField.setFont(new Font("Arial", 0, 15));
		mdpField.setBorder(BorderFactory.createCompoundBorder(
				mdpField.getBorder(), 
		        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		//JList
		listmdp = new JList<Mdp>();
		listModel = new DefaultListModel<Mdp>();
		int size = data.getList().size();
		for(int index=0; index<size; index++){
		     listModel.addElement(data.getList().get(index));
		}
		listmdp.setModel(listModel);
		
		iconPanel.add(Box.createHorizontalGlue());
		iconPanel.add(iconLabel);
		iconPanel.add(Box.createHorizontalGlue());
		iconPanel.add(nameField);
		iconPanel.add(Box.createHorizontalGlue());
		
		rightPanel.add(Box.createRigidArea(new Dimension(10,10)));
		rightPanel.add(iconPanel);
		rightPanel.add(Box.createRigidArea(new Dimension(20,40)));
		
		/* Aligne les label à gauche */
		JPanel leftAlign = new JPanel();
		leftAlign.setLayout(new BoxLayout(leftAlign,BoxLayout.LINE_AXIS));
		leftAlign.add(loginLabel);
		leftAlign.add(Box.createHorizontalGlue());
		/* ------------------------- */
		rightPanel.add(leftAlign);
		
		rightPanel.add(loginField);
		rightPanel.add(Box.createRigidArea(new Dimension(20,40)));
		leftAlign = new JPanel();
		leftAlign.setLayout(new BoxLayout(leftAlign,BoxLayout.LINE_AXIS));
		leftAlign.add(mdpLabel);
		leftAlign.add(Box.createHorizontalGlue());
		rightPanel.add(leftAlign);
		rightPanel.add(mdpField);
		rightPanel.add(Box.createRigidArea(new Dimension(40,40)));
		
		buttonOkPanel.setLayout(new BoxLayout(buttonOkPanel,BoxLayout.LINE_AXIS));
		okButton = new JButton("Ok");
		cancelButton = new JButton("Annuler");
		okButton.setEnabled(false);
		cancelButton.setEnabled(false);
		buttonOkPanel.add(okButton);
		buttonOkPanel.add(Box.createRigidArea(new Dimension(10,10)));
		buttonOkPanel.add(cancelButton);
		rightPanel.add(buttonOkPanel);
		
		scroll = new JScrollPane(listmdp);
		scroll.setPreferredSize(new Dimension(180,300));
		
		leftPanel.setLayout(new BorderLayout());
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(addButton);
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(removeButton);
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(editButton);
		buttonPanel.add(Box.createHorizontalGlue());
		
		leftPanel.add(scroll, BorderLayout.CENTER);
		leftPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		
		backgroundPanel.add(leftPanel);
		backgroundPanel.add(rightPanel);
		this.add(backgroundPanel);
		this.addListeners();
	}
	
	private void addListeners(){
		MdpListeners listener = new MdpListeners(this,data);
		addButton.addMouseListener(listener);
		removeButton.addMouseListener(listener);
		editButton.addMouseListener(listener);
		listmdp.addListSelectionListener(listener);
		okButton.addMouseListener(listener);
		cancelButton.addMouseListener(listener);
		nameField.addKeyListener(listener);
		loginField.addKeyListener(listener);
		mdpField.addKeyListener(listener);
		MdpListListener listenerList = new MdpListListener(this);
		listmdp.addKeyListener(listenerList);
	}

	public boolean ask(){
		JPasswordField pwd = new JPasswordField(10);
	    JOptionPane.showConfirmDialog(null, pwd,"Enter Password",JOptionPane.OK_CANCEL_OPTION);
	    return new String(pwd.getPassword()).equals("110895");
	}
	
	public void addJList(Mdp mdp){
		int index = data.getList().indexOf(mdp);
		listModel.add(index,mdp);
	}
	public void removeJList(Mdp mdp){
		listModel.removeElement(mdp);
	}
	
	public void addAction(){
		nameField.setText("Nom");
		nameField.setEditable(true);
		loginField.setText("");
		loginField.setEditable(true);
		mdpField.setText("");
		mdpField.setEditable(true);
		okButton.setEnabled(true);
		cancelButton.setEnabled(true);
		rightPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"<html><font color=\"blue\">Informations</font></html>",0,0,new Font("Arial", 0, 15)));
		inAdd = true;
	}
	
	public void editAction(){
		nameField.setEditable(true);
		loginField.setEditable(true);
		mdpField.setEditable(true);
		okButton.setEnabled(true);
		cancelButton.setEnabled(true);
		rightPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"<html><font color=\"yellow\">Informations</font></html>",0,0,new Font("Arial", 0, 15)));
		inEdit = true;
		beforeEdit = nameField.getText();
	}
	public void removeAction(){
		data.removeMdp(listmdp.getSelectedValue().getNom());
	}
	
	public void okAction(){
		rightPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"Informations",0,0,new Font("Arial", 0, 15)));
		if(inAdd){
			data.addMdp(nameField.getText(), loginField.getText(), mdpField.getText());
		}
		else if(inEdit){
			data.editMdp(beforeEdit, nameField.getText(), loginField.getText(), mdpField.getText());
		}
		nameField.setText("Nom");
		nameField.setEditable(false);
		loginField.setText("");
		loginField.setEditable(false);
		mdpField.setText("");
		mdpField.setEditable(false);
		okButton.setEnabled(false);
		cancelButton.setEnabled(false);
		inAdd = false;
		inEdit = false;
	}
	
	public void cancelAction(){
		rightPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"Informations",0,0,new Font("Arial", 0, 15)));
		nameField.setText("Nom");
		nameField.setEditable(false);
		loginField.setText("");
		loginField.setEditable(false);
		mdpField.setText("");
		mdpField.setEditable(false);
		okButton.setEnabled(false);
		cancelButton.setEnabled(false);
		inAdd = false;
		inEdit = false;
	}
	
	
	private class DashedBorder extends AbstractBorder {
		private static final long serialVersionUID = 1L;

		@Override
	    public void paintBorder(Component comp, Graphics g, int x, int y, int w, int h) {
	        Graphics2D gg = (Graphics2D) g;
	        gg.setColor(Color.GRAY);
	        gg.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{1}, 0));
	        gg.drawRect(x, y, w - 1, h - 1);
	    }
	}
	
	public void sortList(){
		listModel.removeAllElements();
		int size = data.getList().size();
		for(int index=0; index<size; index++){
		     listModel.addElement(data.getList().get(index));
		}
		listmdp.setModel(listModel);
	}
	
	public JLabel getAddButton(){
		return addButton;
	}
	public JLabel getRemoveButton(){
		return removeButton;
	}
	public JLabel getEditButton(){
		return editButton;
	}
	public JButton getOkButton() {
		return okButton;
	}
	public JButton getCancelButton() {
		return cancelButton;
	}
	public JLabel getIconLabel() {
		return iconLabel;
	}
	public JTextField getNameField() {
		return nameField;
	}
	public JTextField getLoginField() {
		return loginField;
	}
	public JTextField getMdpField() {
		return mdpField;
	}
	public JList<Mdp> getListmdp() {
		return listmdp;
	}
	public DefaultListModel<Mdp> getListModel() {
		return listModel;
	}
	public JPanel getRightPanel() {
		return rightPanel;
	}
	
	public static void main(String[] args) {
		new MdpView();
	}
}