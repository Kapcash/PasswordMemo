package motdepasse.data;

import java.io.Serializable;

public class Mdp implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nom;
	private String login;
	private String mdp;
	private int id;
	private static int nb = 0;
	
	public Mdp(String n,String log, String m){
		if(log==null) throw new IllegalArgumentException("Login incorrecte");
		if(m==null) throw new IllegalArgumentException("Mot de passe incorrecte");
		if(n==null) throw new IllegalArgumentException("Nom incorrecte");
		
		this.nom = n;
		this.login = log;
		this.mdp = m;
		id = ++nb;
	}
	
	public void edit(String newname, String newlog, String newm){
		if(newlog==null) throw new IllegalArgumentException("Nouveau login incorrecte");
		if(newm==null) throw new IllegalArgumentException("Nouveau mot de passe incorrecte");
		if(newname==null) throw new IllegalArgumentException("Nom incorrecte");
		
		this.nom = newname;
		this.login = newlog;
		this.mdp = newm;
	}
	
	
	public String toString(){
		return nom;
	}
	
	public String display(){
		return nom+" "+login+ " "+mdp+" "+id+"\n";
	}
	
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
