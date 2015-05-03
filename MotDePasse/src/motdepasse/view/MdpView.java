package motdepasse.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
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
import javax.swing.LayoutStyle;
import javax.swing.border.EtchedBorder;

import motdepasse.ctrl.MdpListeners;
import motdepasse.data.GhostText;
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
	private MdpData data;
	private JButton cancelButton;
	private JPanel buttonOkPanel;
	private JTextField searchField;
	private JPanel pwdPanel;
	private JButton okpwdButton;
	private JPasswordField pwdField;
	private JLabel pwdLabel;

	public MdpView(){
		super();
		data = MdpData.getInstance(this);
		this.setMinimumSize(new Dimension(600,400));
		this.setPreferredSize(new Dimension(600,400));
		this.setResizable(false);
		this.init();
		this.setTitle("Mots de passe");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(new ImageIcon("./Icon_mdp.png").getImage());
		this.setLocationRelativeTo(null);
		this.setVisible(true);
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
		rightPanel.setPreferredSize(new Dimension(380,360));
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
		rightPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"Informations",0,0,new Font("Arial", 0, 15)));

		//Password
		pwdPanel = new JPanel();
		pwdPanel.setPreferredSize(new Dimension(380,360));
		pwdPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"Authentification",0,0,new Font("Arial", 0, 15)));

		//Panel haut du rightPanel
		iconPanel = new JPanel();
		iconPanel.setLayout(new BoxLayout(iconPanel, BoxLayout.LINE_AXIS));
		//Panel principal du leftPanel

		//Panel des buttons Add / Edit / Delete
		buttonPanel = new JPanel();
		buttonPanel.setMaximumSize(new Dimension(200,30));
		GroupLayout grp = new GroupLayout(buttonPanel);
		buttonPanel.setLayout(grp);

		//Panel des buttons Ok / Annuler
		buttonOkPanel = new JPanel();
		buttonOkPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));


		//JButton
		addButton = new JLabel();
		ImageIcon icon1 = new ImageIcon("./add_icon.png");
		Image img1 = icon1.getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH ) ;  
		addButton.setIcon(new ImageIcon(img1));
		addButton.setBorder(BorderFactory.createEmptyBorder());
		addButton.setBackground(new Color(0,0,0,0));

		removeButton = new JLabel();
		ImageIcon icon2 = new ImageIcon("./remove_icon.png");
		Image img2 = icon2.getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH ) ;  
		removeButton.setIcon(new ImageIcon(img2));
		removeButton.setBorder(BorderFactory.createEmptyBorder());
		removeButton.setBackground(new Color(0,0,0,0));

		editButton = new JLabel();
		ImageIcon icon3 = new ImageIcon("./edit_icon.png");
		Image img3 = icon3.getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH ) ;  
		editButton.setIcon(new ImageIcon(img3));
		editButton.setBorder(BorderFactory.createEmptyBorder());
		editButton.setBackground(new Color(0,0,0,0));

		okpwdButton = new JButton("S'identifier");

		//JLabel
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
		nameField.setMinimumSize(new Dimension(350,30));
		nameField.setPreferredSize(new Dimension(350,30));
		nameField.setMaximumSize(new Dimension(350,30));
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

		searchField = new JTextField();
		new GhostText(searchField,"Rechercher");
		searchField.setMinimumSize(new Dimension(200,25));
		searchField.setPreferredSize(new Dimension(200,25));
		searchField.setMaximumSize(new Dimension(200,25));

		pwdField = new JPasswordField();
		pwdField.setMinimumSize(new Dimension(300,25));
		pwdField.setPreferredSize(new Dimension(300,25));
		pwdField.setMaximumSize(new Dimension(300,25));

		//JList
		listmdp = new JList<Mdp>();
		listModel = new DefaultListModel<Mdp>();
		iconPanel.add(nameField);

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

		pwdLabel = new JLabel(" ");

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

		grp.setHorizontalGroup(
				grp.createSequentialGroup()
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
						GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(addButton)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(removeButton)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(editButton)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		grp.setVerticalGroup(
				grp.createParallelGroup()
				.addComponent(addButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(removeButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(editButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.PAGE_AXIS));
		leftPanel.add(searchField,BorderLayout.NORTH);
		leftPanel.add(Box.createRigidArea(new Dimension(10,10)));
		leftPanel.add(scroll, BorderLayout.CENTER);
		leftPanel.add(buttonPanel, BorderLayout.SOUTH);

		pwdPanel.setLayout(new BoxLayout(pwdPanel,BoxLayout.PAGE_AXIS));
		pwdPanel.add(Box.createVerticalGlue());
		pwdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		pwdPanel.add(pwdLabel);
		pwdPanel.add(Box.createRigidArea(new Dimension(10,10)));
		pwdPanel.add(pwdField);
		okpwdButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		pwdPanel.add(Box.createRigidArea(new Dimension(10,10)));
		pwdPanel.add(okpwdButton);
		pwdPanel.add(Box.createVerticalGlue());

		backgroundPanel.add(leftPanel);
		backgroundPanel.add(pwdPanel);
		this.add(backgroundPanel);
		this.addListeners();
	}

	public void initData(){
		backgroundPanel.remove(pwdPanel);
		backgroundPanel.add(rightPanel);

		int size = data.getList().size();
		for(int index=0; index<size; index++){
			listModel.addElement(data.getList().get(index));
		}
		listmdp.setModel(listModel);
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
		searchField.getDocument().addDocumentListener(listener);
		listmdp.addKeyListener(listener);

		pwdField.addKeyListener(listener);
		okpwdButton.addMouseListener(listener);
	}


	public boolean ask(){
		JPasswordField pwd = new JPasswordField(10);
		JOptionPane.showConfirmDialog(null, pwd,"Enter Password",JOptionPane.OK_CANCEL_OPTION);
		return new String(pwd.getPassword()).equals("110895");
	}

	public void sortList(){
		listModel.removeAllElements();
		int size = data.getList().size();
		for(int i=0; i<size; i++){
			listModel.addElement(data.getList().get(i));
		}
		this.listmdp.revalidate();
	}


	// --- Getters & Setters --- //

	public JLabel getAddButton(){
		return addButton;
	}
	public JLabel getRemoveButton(){
		return removeButton;
	}
	public JLabel getEditButton(){
		return editButton;
	}
	public JLabel getPwdLabel(){
		return pwdLabel;
	}
	public JButton getOkButton() {
		return okButton;
	}
	public JButton getCancelButton() {
		return cancelButton;
	}
	public JButton getOkPwdButton() {
		return okpwdButton;
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
	public JTextField getSearchField() {
		return searchField;
	}
	public JPasswordField getPwdField() {
		return pwdField;
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
	public JPanel getBackgroundPanel() {
		return backgroundPanel;
	}
	public JPanel getPwdPanel() {
		return pwdPanel;
	}

	/* --- MAIN --- */

	public static void main(String[] args) {
		new MdpView();
	}

}