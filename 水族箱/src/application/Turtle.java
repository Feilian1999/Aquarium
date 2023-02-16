package application;

import java.util.Random;

import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Turtle extends Creatures {
	Image image = new Image(getClass().getResourceAsStream("/images/turtleR.png"));
	Image image2 = new Image(getClass().getResourceAsStream("/images/turtleL.png"));
	private Path path = new Path();
	private MoveTo moveTo = new MoveTo(0, 0);// 起始路徑，以魚缸大小決定
	private LineTo lineTo;

	PathTransition transition = new PathTransition();
	Random ran = new Random();

	public Turtle(Pane tank, Point2D loc, double size, double speed) {
		super(tank, size, speed);
		setFitHeight(size * 0.904);
		setFitWidth(size * 1.16);
		setImage(image);
		
		lineTo = new LineTo(loc.getX(), loc.getY());
		preMove();


		transition.setOnFinished(e -> {
			move();
		});
	}
	
	private void preMove() {
		path.getElements().add(moveTo);
		transition.setNode(this);
		transition.setDuration(Duration.seconds(3));
		transition.setPath(path);
		
		
		moveTo = new MoveTo(lineTo.getX(), lineTo.getY());
		lineTo = new LineTo(lineTo.getX(), 300 - getFitHeight()/3);

		// 決定turtle的圖片是否轉向
		if (lineTo.getX() - moveTo.getX() < 0) {
			setImage(image2);
		} else {
			setImage(image);
		}

		path.getElements().clear();
		path.getElements().add(moveTo);
		path.getElements().add(lineTo);
		transition.play();
	}

	private void move() {
		transition.setDuration(Duration.seconds(ran.nextInt(4) + 2));
		moveTo = new MoveTo(lineTo.getX(), lineTo.getY());
		lineTo = new LineTo(ran.nextInt(600-(int)getFitWidth())+getFitWidth()/2, 300 - getFitHeight()/3);

		// 決定turtle的圖片是否轉向
		if (lineTo.getX() - moveTo.getX() < 0) {
			setImage(image2);
		} else {
			setImage(image);
		}

		path.getElements().clear();
		path.getElements().add(moveTo);
		path.getElements().add(lineTo);
		transition.play();
	}

}
