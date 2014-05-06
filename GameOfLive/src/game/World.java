package game;

import java.util.Vector;

import graphics2.Canvas;
import graphics2.Text;
import graphics2.Color;
import graphics2.Rectangle;

public class World {
	
	private Organism[][] world;
	private int generation = 0;
	private Text textGeneration = new Text(10, 4, "Generación: N");
	private Vector<Rectangle> pixels = new Vector<Rectangle>();
	private final int SHOW_SIZE = 20;
	private final Color[] COLOR_STATUS = {
		new Color(225,225,225),	// Gris
		new Color(216,14,14),	// Rojo
		new Color(251,251,9),	// Amarillo
		new Color(24,203,36)};	// Verde
	
	/**
	 * Generamos el mundo
	 * @param width Ancho del mundo
	 * @param height Alto del mundo
	 * @param initialLive Probabilidad de vida inical (de 0 a 1)
	 */
	public World(int width, int height, double initialLive){
		if(width <= 1) width = 1;
		if(height <= 1) height = 1;
		this.world = new Organism[width][height];
		
		for(int x=0; x<width; x++)
			for(int y=0; y<height; y++){
				this.world[x][y] = new Organism();
				if(Math.random() < initialLive)
					this.world[x][y].organismIsPrimitiveLive();
			}
	}
	
	public void initShow(){
		Canvas canvas = Canvas.getInstance();
		while(true){
			showWorld();
			nextGeneration();
			canvas.waitMouseClick();
		}
	}
	
	private void showWorld(){
		
		this.textGeneration.setText("Generación: " + this.generation);
		this.textGeneration.draw();
		
		deleteCells();
		
		for(int x=0; x<world.length; x++)
			for(int y=0; y<world[x].length; y++)
				//System.out.printf(" %s", this.world[x][y].getStatus());
				showCell(x, y, this.world[x][y].getStatus());
	}

	private void showCell(int x, int y, int status){
		// SHOW_SIZE
		
		Rectangle cell = new Rectangle(
			10 + (SHOW_SIZE * x),
			30 + (SHOW_SIZE * y),
			SHOW_SIZE, SHOW_SIZE);
		cell.setColor(COLOR_STATUS[status]);
		cell.fill();
		
		this.pixels.add(cell);
	}
	
	private void deleteCells(){
		for(int i=0; i<this.pixels.size(); i++)
			this.pixels.elementAt(i).undraw();
		
		this.pixels = new Vector<Rectangle>();
	}
	
	private void nextGeneration(){
		updateWorldAdjacent();
		updateWorldStatus();
		this.generation++;
	}
	
	private void updateWorldAdjacent(){
		for(int x=0; x<world.length; x++)
			for(int y=0; y<world[x].length; y++)
				this.world[x][y].setAdjacent(countAdjacent(x, y));
	}
	
	private void updateWorldStatus(){
		for(int x=0; x<world.length; x++)
			for(int y=0; y<world[x].length; y++)
				this.world[x][y].updateStatus();
	}

	private int countAdjacent(int x, int y) {
		int a = 0;
		
		if(getIsAliveByCell(x-1, y-1)) a++;
		if(getIsAliveByCell(x-1, y)) a++;
		if(getIsAliveByCell(x-1, y+1)) a++;
		if(getIsAliveByCell(x+1, y-1)) a++;
		if(getIsAliveByCell(x+1, y)) a++;
		if(getIsAliveByCell(x+1, y+1)) a++;
		if(getIsAliveByCell(x, y-1)) a++;
		if(getIsAliveByCell(x, y+1)) a++;
		
		return a;
	}

	private boolean getIsAliveByCell(int x, int y) {
		try{
			return this.world[x][y].isAlive();
		}catch(Exception e){
			return false;
		}
	}

}
