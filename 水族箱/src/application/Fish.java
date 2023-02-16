package application;

import java.util.Random;

import javafx.animation.PathTransition;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class Fish extends Creatures {
	private Image[][] fishImage = new Image[4][2];// 魚圖片 
	private int ranNum;// 魚隨機圖片參數
	private Path path = new Path();
	private PathTransition pathTransition = new PathTransition();// 路徑
	private Random ran = new Random();
	private MoveTo moveTo = new MoveTo(0, 0);// 起始路徑，以魚缸大小決定
	private LineTo lineTo;

	public Fish(Pane tank, Point2D loc, double size, double speed) {
		super(tank, size, speed);
		setFitHeight(size);
		setFitWidth(size);
		//增加圖像品質
		setPreserveRatio(true);
		setSmooth(true);
		setCache(true);
		

		ranNum = ran.nextInt(4);
		setFishImage(ranNum, 0);

		lineTo = new LineTo(loc.getX(), loc.getY());

		path.getElements().add(moveTo);
		pathTransition.setNode(this);
		pathTransition.setPath(path);

		move();

		pathTransition.setOnFinished(e -> {
			move();
		});
	}

	private void move() {

		pathTransition.setDuration(Duration.seconds(ran.nextInt(3) + 2));
		moveTo = new MoveTo(lineTo.getX(), lineTo.getY());
		lineTo = new LineTo(ran.nextInt(600 - (int)getFitWidth()) + getFitWidth()/2, ran.nextInt(300-(int)getFitHeight())+ getFitHeight()/2);

		if (lineTo.getX() - moveTo.getX() < 0) {
			setFishImage(ranNum, 1);
		} else {
			setFishImage(ranNum, 0);
		}

		path.getElements().clear();
		path.getElements().add(moveTo);
		path.getElements().add(lineTo);
		pathTransition.play();
	}

	public void setFishImage(int r, int a) {

		Image image1 = new Image(getClass().getResourceAsStream("/images/pufferR.png"));
		Image image2 = new Image(getClass().getResourceAsStream("/images/pufferL.png"));
		Image image3 = new Image(getClass().getResourceAsStream("/images/clownR.png"));
		Image image4 = new Image(getClass().getResourceAsStream("/images/clownL.png"));
		Image image5 = new Image(getClass().getResourceAsStream("/images/tigerR.png"));
		Image image6 = new Image(getClass().getResourceAsStream("/images/tigerL.png"));
		Image image4cR = new Image(getClass().getResourceAsStream("/images/4cR.png"));
		Image image4cL = new Image(getClass().getResourceAsStream("/images/4cL.png"));
		fishImage[0][0] = image1;
		fishImage[0][1] = image2;
		fishImage[1][0] = image3;
		fishImage[1][1] = image4;
		fishImage[2][0] = image5;
		fishImage[2][1] = image6;
		fishImage[3][0] = image4cR;
		fishImage[3][1] = image4cL;

		setImage(fishImage[r][a]);
	}

}
