package com.zhoujie.client;

import java.util.Random;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gwtblocks implements EntryPoint {
	
	static Shape shape = null;
	static Matrix m = MatrixImpl.getInstance();
	
	private static void generateNewShape() {
		Random random = new Random();
		switch (Math.abs((random.nextInt()%7))){
		case 0:
			shape = new Bar(5,29,true);
			break;
		case 1:
			shape = new Square(5,29);
			break;
		case 2:
			shape = new TShape(5,28,0);
			break;
		case 3:
			shape = new LShape(5,28,0);
			break;
		case 4:
			shape = new JShape(5,28,0);
			break;
		case 5:
			shape = new ZShape(5,28,true);
			break;
		case 6:
			shape = new SShape(5,28,true);
			break;
		}
	}


	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final MatrixCanvas canvas=new MatrixCanvas();
		canvas.setMatrix(m);
		m.setViewer(canvas);
        generateNewShape();
		
		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("blockDiv").add(canvas);
		canvas.update();
		
		final Timer timer = new Timer() {           
            @Override
            public void run() {
            	shape=shape.down();
        		tryDownAgain();
        		m.updateViewer();
            }
        };
        timer.scheduleRepeating(500);
        canvas.getCanvas().addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				
				switch (event.getNativeKeyCode()){
				case KeyCodes.KEY_UP:
					shape=shape.couterClockWise();
					tryDownAgain();
					m.updateViewer();
					break;
				case KeyCodes.KEY_DOWN:
						shape=shape.down();
						tryDownAgain();
					m.updateViewer();
					break;
				case KeyCodes.KEY_LEFT:
					shape=shape.left();
					tryDownAgain();
					m.updateViewer();
					break;
				case KeyCodes.KEY_RIGHT:
					shape=shape.right();
					tryDownAgain();
					m.updateViewer();
					break;
				case KeyCodes.KEY_DELETE:
				case KeyCodes.KEY_PAGEDOWN:
					shape=shape.clockWise();
					tryDownAgain();
					m.updateViewer();
					break;
				}
			}});
        canvas.getCanvas().setFocus(true);
	}
	
	private static void tryDownAgain(){
		Shape oldP=shape;
		shape=shape.down();
		if (oldP==shape){
			// reach the bottom, create a new Postion
			m.removeCompletedRows();
			generateNewShape();
		}
		else {
			shape=shape.up();
		}
	}
}
