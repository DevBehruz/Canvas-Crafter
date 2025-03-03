package org.openjfx.shortfx;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;



public class App extends Application {

    private static Scene scene;

    public static void main(String[] args) {
        launch();
    }

    public void drawDot(int x, int y, Group group, Brush brush) {
        Circle circle = new Circle();
        Color myColor = brush.getColor();
        int mySize = brush.getSize();
        circle.setFill(myColor);
        circle.setCenterX(x);
        circle.setCenterY(y);
        circle.setRadius(mySize);
        group.getChildren().add(circle);
    }
    
    private void drawDifferent(int x, int y, Group group, Brush brush) {
    	drawDot(x,y,group,brush);
    }
    
    private void addColor(Group group, Brush brush, Color color, double x, double y) {
        Rectangle rectangle = new Rectangle();
        rectangle.setFill(color);
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setWidth(25);
        rectangle.setHeight(25);

        rectangle.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                brush.setColor(color);
            }
        });

        group.getChildren().add(rectangle);
    }
    
    private void addColors(Group group, Brush brush) {
        addColor(group, brush, Color.PINK, 20, 20); 
        addColor(group, brush, Color.BLUE, 60, 20); 
        addColor(group, brush, Color.GREEN, 100, 20); 
    }
    
    private void clearBoard(Group group, Brush brush) {
        
        group.getChildren().removeIf(node -> 
            !(node instanceof Button || node instanceof Rectangle)
        );
    }


    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Behruz's App");

        // set up the canvas
        Group root = new Group();
        Brush myBrush = new Brush();
//        drawDot(10, 10, root);

        scene = new Scene(root, 640, 480);
        
        addColors(root, myBrush);
        
        // Clear Button
        Button clear = new Button();
        clear.setText("Clear");
        clear.setLayoutX(540);
        clear.setLayoutY(20);
        
        // Increment Button
        Button increment = new Button();
        increment.setText("+");
        increment.setLayoutX(260);
        increment.setLayoutY(20);
        
        // Dicrement Button
        Button decrement = new Button();
        decrement.setText("-");
        decrement.setLayoutX(300);
        decrement.setLayoutY(20);
        
        clear.setOnAction((e) ->{
        	clearBoard(root, myBrush);
        });
        
        increment.setOnAction((e) ->{
        	myBrush.incrementSize();
        });
        
        decrement.setOnAction((e) ->{
        	myBrush.decrementSize();
        });
        
        
        root.getChildren().add(clear);
        root.getChildren().add(increment);
        root.getChildren().add(decrement);
        
        scene.addEventFilter(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
        	@Override
        	public void handle(MouseEvent e) {
        		int xCord = (int) e.getX();
        		int yCord = (int) e.getY();
        		drawDifferent(xCord, yCord, root, myBrush);
        	}
        });
        
        stage.setScene(scene);
        stage.show();
    }
   
}
