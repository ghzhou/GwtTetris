package com.zhoujie.client;
public class MatrixImpl implements Matrix {
	int m[][] = null;
	int width,height;
	private MatrixView viewer;
	static MatrixImpl instance=null;
	
	private MatrixImpl(int width, int height){
		this.width=width;
		this.height=height;
		m = new int[width+2][height+2];
		initialize();
	};
	
	private void initialize() {
		for (int i=0;i<width+2;i++){
			for (int j=0;j<height+2;j++){
				m[i][j]=1;
			}
		}
		for (int i=1;i<width+1;i++){
			for (int j=1;j<height+1;j++){
				m[i][j]=0;
			}
		}
	}

	public static MatrixImpl getInstance(){
		if (null==instance){
			instance=new MatrixImpl(10,30);
		}
		return instance;
		
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setXY(int x, int y, int v){
		m[x+1][y+1]=v;
	}
	
	public int getXY(int x, int y){
		return m[x+1][y+1];
	}
	
	public void updateViewer(){
		viewer.update();
	}
	
	public void removeCompletedRows() {
		for (int y=height-1;y>=0;y--){
			boolean rowCompleted=true;
			for (int x=0;x<width;x++){
				if (getXY(x,y)==0){
					rowCompleted=false;
					break;
				}
			}
			if (rowCompleted){
				for (int i=y;i<height-1;i++){
					for(int x=0;x<width;x++){
						setXY(x,i,getXY(x,i+1));
					}
				}
				for(int x=0;x<width;x++){
					setXY(x,height-1,0);
				}

			}
		}
		updateViewer();
	}

	public void setViewer(MatrixView v){
		this.viewer=v;
		updateViewer();
	}
}
