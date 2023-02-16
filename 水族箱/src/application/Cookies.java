package application;

import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

//烏龜跟魚才不會吃餅乾呢
public class Cookies extends ImageView{
	private Image image = new Image(getClass().getResourceAsStream("/images/cookie.png"));
	private Path path = new Path();
	private MoveTo moveTo = new MoveTo(0, 0);// 起始路徑，以魚缸大小決定
	private LineTo lineTo;
	PathTransition pathTransition = new PathTransition();
	RotateTransition rotateTransition = new RotateTransition();
	ParallelTransition pt = new ParallelTransition();

	public Cookies(Point2D loc) {

		setFitHeight(30);
		setFitWidth(30);
		setImage(image);
		
		rotateTransition.setNode(this);
		rotateTransition.setDuration(Duration.seconds(5));
		rotateTransition.setToAngle(360);
		pt.getChildren().add(rotateTransition);
		pt.getChildren().add(pathTransition);

		lineTo = new LineTo(loc.getX(), loc.getY());
		preMove();
	}

	private void preMove() {
		path.getElements().add(moveTo);
		pathTransition.setNode(this);
		pathTransition.setDuration(Duration.seconds(5));
		pathTransition.setPath(path);

		moveTo = new MoveTo(lineTo.getX(), lineTo.getY());
		lineTo = new LineTo(lineTo.getX(), 300 - getFitHeight() / 2);

		path.getElements().clear();
		path.getElements().add(moveTo);
		path.getElements().add(lineTo);
		pt.play();
	}


}
