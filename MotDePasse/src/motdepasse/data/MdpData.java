package motdepasse.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;

import motdepasse.view.MdpView;

public class MdpData {
	
	private static MdpData instance;
	private final String savePath = "./Mdp.out";
	private ArrayList<Mdp> list;
	private MdpView view;
	private boolean inAdd,inEdit;
	private final File CONFIG = new File("./passwordmemo.config");
	
	private MdpData(MdpView v){
		if(!this.load()){
			list = new ArrayList<Mdp>();
		}
		view = v;
		inAdd=false;
		inEdit=false;
	}
	
	public static MdpData getInstance(MdpView v){
		if(instance == null)
			instance = new MdpData(v);
		return instance;
	}
	
	public void addMdp(String n, String log, String m){
		if(this.alreadyExist(n)) throw new IllegalArgumentException("Ce nom existe d�j�");
		Mdp newMdp = new Mdp(n,log,m);
		list.add(newMdp);
		this.trierList();
		this.save();
		int index = this.getList().indexOf(newMdp);
		view.getListModel().add(index,newMdp);
	}
	
	public void removeMdp(String name){
		Mdp removed = this.findByName(name);
		list.remove(removed);
		this.save();
		view.getListModel().removeElement(removed);
	}
	
	public void editMdp(String name, String newname, String newlog, String newmdp){
		Mdp edited = this.findByName(name);
		edited.edit(newname,newlog,newmdp);
		this.trierList();
		this.save();
	}
	
	
	public Mdp findByName(String name){
		Mdp ret=null;
		if(this.alreadyExist(name)){
			for(int i=0;i<list.size();i++){
				Mdp current = list.get(i);
				if(current.getNom().equals(name)) ret = current;
			}
		}
		else{
			throw new NoSuchElementException(name+" n'existe pas");
		}
		return ret;
	}
	
	private boolean alreadyExist(String n){
		boolean ret=false;
		for(int i=0;i<list.size();i++){
			if(list.get(i).getNom().equals(n)) ret = true;
		}
		return ret;
	}

	private void trierList(){
		Collections.sort(list, new Comparator<Mdp>() {
	        public int compare(Mdp  m1, Mdp  m2){
	            return  m1.getNom().compareTo(m2.getNom());
	        }
	    });
	}
	
	public void save(){
		try{
			ObjectOutputStream flux = new ObjectOutputStream(new FileOutputStream(savePath));
			flux.writeObject(list);
			flux.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean load(){
		ArrayList<Mdp> mdpOut = null;
		boolean ret = true;
		try{
			ObjectInputStream flux = new ObjectInputStream(new FileInputStream(savePath));
			mdpOut = (ArrayList<Mdp>) flux.readObject();
			flux.close();
		}
		catch(Exception e){
			e.printStackTrace();
			ret = false;
		}
		list = mdpOut;
		return ret;
	}
	
	public void setMdp(String mdp){
		if (mdp.equals("")) throw new IllegalArgumentException("Le mot de passe de l'application ne peut �tre vide !");
		ObjectOutputStream flux;
		try {
			flux = new ObjectOutputStream(new FileOutputStream(CONFIG));
			flux.writeObject(mdp);
			flux.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getMdp(){
		String ret = null;
		try{
			ObjectInputStream flux = new ObjectInputStream(new FileInputStream(CONFIG));
			ret = (String) flux.readObject();
			flux.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return ret;
	}
	
	public boolean isFirstLaunch(){
		return !CONFIG.exists();
	}
	
	public void displayList(){
		String ret ="";
		for(int i=0;i<list.size();i++){
			ret += list.get(i).display();
		}
		System.out.println(ret);
	}
	
	
	// --- Getters & Setters --- //
	
	public ArrayList<Mdp> getList() {
		return list;
	}

	public boolean isInAdd() {
		return inAdd;
	}

	public void setInAdd(boolean inAdd) {
		this.inAdd = inAdd;
	}

	public boolean isInEdit() {
		return inEdit;
	}

	public void setInEdit(boolean inEdit) {
		this.inEdit = inEdit;
	}
}
