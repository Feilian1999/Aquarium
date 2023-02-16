package application;

import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Creatures extends ImageView implements Runnable {

	private double size;
	private double speed;
	boolean selected = false;
	private Pane tank;
	Rectangle rectangle = new Rectangle(getFitWidth(), getFitHeight());

	protected Creatures(Pane tank, double size, double speed) {
		this.tank = tank;
		this.size = size;
		this.speed = speed;
		
		tank.setClip(new Rectangle(600, 300));

		addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			if (selected == true) {
				System.out.println("false");
				selected = false;
				setEffect(null);
			} else if (selected == false) {
				System.out.println("true");
				selected = true;
		        BoxBlur bb = new BoxBlur();
		        bb.setWidth(5);
		        bb.setHeight(5);
		        bb.setIterations(3);
		 
		        setEffect(bb);
			}
			event.consume();
		});
	}

	public double getSize() {
		return size;
	}

	@Override
	public void run() {// µ¹¤©¿W¥ß°õ¦æ§Ç:P
		double size=1;
		while (true) {
			try {
				if(size <= 3) {
				Thread.sleep(10000);
				size*=1.1;
				setScaleX(size);
				setScaleY(size);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
