package motdepasse.ctrl;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import motdepasse.view.MdpView;

public class MdpListListener extends KeyAdapter{

	private MdpView view;

	public MdpListListener(MdpView v){
		this.view = v;
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_DELETE){
			view.removeAction();
		}
	}
}