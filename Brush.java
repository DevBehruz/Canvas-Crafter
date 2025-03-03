package org.openjfx.shortfx;

import javafx.scene.paint.Color;

public class Brush {
	private int size;
	private Color color;
	public enum BrushType {CIRCLE, SQUARE, TRIANGLE}
	private BrushType brushType;
	
	public Brush() {
		size = 1;
		color = color.BLACK;
		brushType = BrushType.CIRCLE;
		
	}
	
	// Setters
	
	public void setColor(Color newColor) {
		color = newColor;
	}
	
	public void setBrushType(BrushType newType) {
		brushType = newType;
	}
	
	public void incrementSize() {
		size += 1;
	}
	
	public void decrementSize() {
		if(size == 0) size = 1;
		size -= 1;
		
	}
	
	// Getters
	
	public Color getColor() {
		return color;
	}
	
	public BrushType getBrushType() {
		return brushType;
	}
	
	public int getSize() {
		return size;
	}
}
