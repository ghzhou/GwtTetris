package com.zhoujie.client;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.user.client.ui.Composite;

public class MatrixCanvas extends Composite implements MatrixView{
	
	public static int pixelPerBlock=20;

	private Matrix m;
	private Canvas canvas =null;

	private Context2d context;
	
	public MatrixCanvas(){
		canvas = Canvas.createIfSupported();
		canvas.setCoordinateSpaceHeight(600);
		canvas.setCoordinateSpaceWidth(200);
		canvas.setHeight("600px");
		canvas.setWidth("200px");
		initWidget(canvas);
		context = canvas.getContext2d();
		context.setStrokeStyle(CssColor.make("Green"));
		context.rect(0, 0, 200, 600);
		context.stroke();
	}

	@Override
	public void setMatrix(Matrix m) {
		this.m=m;
	}

	@Override
	public void update() {
		context.save();
		context.clearRect(1, 1, 198, 598);
		int width=m.getWidth();
		int height=m.getHeight();
		context.setFillStyle(CssColor.make("Green"));
		for (int i=0;i<width;i++){
			for (int j=0;j<height;j++){
				if (m.getXY(i, j)==1){
					context.fillRect( pixelPerBlock*(i)+1,pixelPerBlock*(height-j-1)+1, pixelPerBlock-2, pixelPerBlock-2);
				}
			}
		}
		context.restore();
	}
	
	public Canvas getCanvas(){
		return canvas;
	}
}
