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
		if(log==null || log.length()<2) throw new IllegalArgumentException("Login incorrecte");
		if(m==null || m.length()<2) throw new IllegalArgumentException("Mot de passe incorrecte");
		if(n==null) throw new IllegalArgumentException("Nom incorrecte");
		
		this.nom = n;
		this.login = log;
		this.mdp = m;
		id = nb++;
	}
	
	public void edit(String newname, String newlog, String newm){
		if(newlog==null || newlog.length()<2) throw new IllegalArgumentException("Nouveau login incorrecte");
		if(newm==null || newm.length()<2) throw new IllegalArgumentException("Nouveau mot de passe incorrecte");
		if(newname==null) throw new IllegalArgumentException("Nom incorrecte");
		
		this.nom = newname;
		this.login = newlog;
		this.mdp = newm;
	}
	
	
	public String toString(){
		String ret = "";
		ret = nom;
		return ret;
	}
	
	public String display(){
		String ret = "";
		ret = nom+" "+login + " "+mdp+" "+id+"\n";
		return ret;
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
