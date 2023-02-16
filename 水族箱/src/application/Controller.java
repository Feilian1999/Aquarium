package application;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import org.omg.CORBA.INITIALIZE;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class Controller {
	private enum Mode {
		ADD_FISH, ADD_TURTLE, REMOVE_SELECT, REMOVE_ALL, FEED
	}

	private Mode mode = Mode.ADD_FISH;
	private int fishAmount = 0;
	private int turtleAmount = 0;
	private ImageView imageView;
	private LinkedList<Creatures> list = new LinkedList<Creatures>();
	ExecutorService executorService = Executors.newCachedThreadPool();
	Random r = new Random();

	@FXML
	private Button newFish;
	@FXML
	private Button newTur;
	@FXML
	private Button rmvSelect;
	@FXML
	private Button rmvAll;
	@FXML
	private AnchorPane tank;
	@FXML
	private Button feed;
	@FXML
	private Button close;
	@FXML
	private Label functionLabel;
	@FXML
	private Label fishCount;
	@FXML
	private Label turtleCount;

	public Controller() {

	}

	@FXML
	private void addFish(MouseEvent e) {
		if (mode == Mode.ADD_FISH) {
			
			Fish fish = new Fish(tank, new Point2D(e.getX(), e.getY()), r.nextInt(75) + 50, r.nextInt(5) + 1);
			list.add(fish);
			tank.getChildren().add(fish);
			executorService.execute(fish); 

			fishCount.setText(Integer.toString(++fishAmount));
		}
	}

	@FXML
	private void addTurtle(MouseEvent e) {
		if (mode == Mode.ADD_TURTLE) {
			Turtle turtle = new Turtle(tank,new Point2D(e.getX(), e.getY()), r.nextInt(75) + 50, 0.5);
			list.add(turtle);
			executorService.execute(turtle); 

			tank.getChildren().add(turtle);

			turtleCount.setText(Integer.toString(++turtleAmount));
		}
	}
	@FXML
	private void addCookie(MouseEvent e) {
		if (mode == Mode.FEED) {
			Cookies cookies = new Cookies(new Point2D(e.getX(),e.getY()));
			tank.getChildren().add(cookies);
			
		}
	}

	@FXML
	private void fishButton() {
		functionLabel.setText("新增魚");
		tank.setOnMouseClicked((e) -> {
			addFish(e);
		});
		mode = Mode.ADD_FISH;
	}

	@FXML
	private void turtleButton() {
		functionLabel.setText("新增烏龜");
		tank.setOnMouseClicked((e) -> {
			addTurtle(e);
		});
		mode = Mode.ADD_TURTLE;
	}

	@FXML
	private void rmvslcButton() {
		functionLabel.setText("移除選取");
		mode = Mode.REMOVE_SELECT;
		
		list.forEach(c -> {
			if(c.selected) {
			tank.getChildren().remove(c);
			}
		});
		
		list.removeIf(c-> c.selected);
		fishCount.setText("" + list.stream().filter(c -> c instanceof Fish).count());
		turtleCount.setText("" + list.stream().filter(c -> c instanceof Turtle).count());
	}

	@FXML
	private void rmvallButton() {
		functionLabel.setText("移除全部");
		tank.getChildren().clear();
		list.clear();

		fishAmount = 0;
		fishCount.setText(Integer.toString(fishAmount));
		turtleAmount = 0;
		turtleCount.setText(Integer.toString(turtleAmount));

		mode = Mode.REMOVE_ALL;
	}
	@FXML
	private void feedButton() {
		functionLabel.setText("餵食");
		tank.setOnMouseClicked((e) -> {
			addCookie(e);
		});
		mode = Mode.FEED;
	}
	@FXML
	private void exit() {
		System.exit(0);
	}
}
