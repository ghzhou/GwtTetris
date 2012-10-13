package com.zhoujie.client;

public class Square implements Shape {
	
	int x,y;
	MatrixImpl m=MatrixImpl.getInstance();

	public Square(int x, int y) {
		this.x=x;
		this.y=y;
	}

	@Override
	public Shape left() {
		Square s = new Square(x-1,y);
		return newSquareIfNotConflict(s);
	}

	private Shape newSquareIfNotConflict(Square s) {
		this.setMatrix(0);
		if (!s.conflict()) {
			s.setMatrix(1);
			return s;
		}
		else{
			this.setMatrix(1);
			return this;
		}
	}

	@Override
	public Shape right() {
		return newSquareIfNotConflict( new Square(x+1,y));
	}

	@Override
	public Shape down() {
		return newSquareIfNotConflict( new Square(x,y-1));
	}

	@Override
	public Shape up() {
		return newSquareIfNotConflict( new Square(x,y+1));
	}

	@Override
	public Shape clockWise() {
		return this;
	}

	@Override
	public Shape couterClockWise() {
		return this;
	}


	public boolean conflict() {
		return m.getXY(x,y)==1 || m.getXY(x+1, y)==1 || m.getXY(x,y+1)==1 || m.getXY(x+1, y+1)==1;
	}

	public void setMatrix(int v) {
		m.setXY(x,y,v);
		m.setXY(x+1, y,v);
		m.setXY(x,y+1,v);
		m.setXY(x+1, y+1,v);
	}

}
