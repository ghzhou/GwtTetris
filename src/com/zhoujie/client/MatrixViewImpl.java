package com.zhoujie.client;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;


public class MatrixViewImpl extends JComponent implements MatrixView{
	
	public MatrixViewImpl(Matrix m) {
		super();
		this.m = m;
		m.setViewer(this);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Matrix m = null;
	public static int pixelPerBlock=20;

	public void paint(Graphics g) {
		int width=m.getWidth();
		int height=m.getHeight();
		g.drawRect (10, 10,  width*pixelPerBlock,height*pixelPerBlock);
		for (int i=0;i<width;i++){
			for (int j=0;j<height;j++){
				if (m.getXY(i, j)==1){
					g.drawRect ( pixelPerBlock*(i)+10,pixelPerBlock*(height-j-1)+10, pixelPerBlock, pixelPerBlock);
					g.setColor(Color.BLUE);
					g.fillRect ( pixelPerBlock*(i)+10+1,pixelPerBlock*(height-j-1)+10+1, pixelPerBlock-2, pixelPerBlock-2);
				}
			}
		}
	}

	@Override
	public void setMatrix(Matrix m) {
		this.m=m;
		
	}

	@Override
	public void update() {
		repaint();
		
	}
}


