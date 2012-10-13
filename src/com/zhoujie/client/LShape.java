package com.zhoujie.client;

public class LShape implements Shape {
	
	int x,y;
	Matrix m = MatrixImpl.getInstance();
	int dir; // " L is North"
	
	

	public LShape(int x, int y, int dir) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
	}

	private Shape newTShapeIfNotConflict(LShape t) {
		this.setMatrix(0);
		if (!t.conflict()) {
			t.setMatrix(1);
			return t;
		}
		else{
			this.setMatrix(1);
			return this;
		}
	}
	
	private void setMatrix(int v) {
		m.setXY(x, y, v);
		switch(dir) {
		case Direction.North:
			m.setXY(x, y+1, v);
			m.setXY(x, y+2, v);
			m.setXY(x+1,y, v);
			break;
		case Direction.East:
			m.setXY(x+1, y, v);
			m.setXY(x+2, y, v);
			m.setXY(x,y-1, v);
			break;
		case Direction.South:
			m.setXY(x, y-1, v);
			m.setXY(x, y-2, v);
			m.setXY(x-1,y, v);
			break;
		case Direction.West:
			m.setXY(x-1, y, v);
			m.setXY(x-2, y, v);
			m.setXY(x,y+1, v);
			break;
		}
	}

	private boolean conflict() {
		if (m.getXY(x, y)==1) return true;
		switch(dir) {
		case Direction.North:
			if (m.getXY(x, y+1)==1) return true;
			if (m.getXY(x, y+2)==1) return true;
			if (m.getXY(x+1,y)==1) return true;
			break;
		case Direction.East:
			if (m.getXY(x+1, y)==1) return true;
			if (m.getXY(x+2, y)==1) return true;
			if (m.getXY(x,y-1)==1) return true;
			break;
		case Direction.South:
			if (m.getXY(x, y-1)==1) return true;
			if (m.getXY(x, y-2)==1) return true;
			if (m.getXY(x-1,y)==1) return true;
			break;
		case Direction.West:
			if (m.getXY(x-1, y)==1) return true;
			if (m.getXY(x-2, y)==1) return true;
			if (m.getXY(x,y+1)==1) return true;
			break;
		}
		return false;
	}

	@Override
	public Shape left() {
		return newTShapeIfNotConflict(new LShape(x-1,y,dir));
	}

	@Override
	public Shape right() {
		return newTShapeIfNotConflict(new LShape(x+1,y,dir));
	}

	@Override
	public Shape down() {
		return newTShapeIfNotConflict(new LShape(x,y-1,dir));
	}

	@Override
	public Shape up() {
		return newTShapeIfNotConflict(new LShape(x,y+1,dir));
	}

	@Override
	public Shape clockWise() {
		return newTShapeIfNotConflict(new LShape(x,y,(dir+1)%4));
	}

	@Override
	public Shape couterClockWise() {
		return newTShapeIfNotConflict(new LShape(x,y,(dir+3)%4));
	}
}
