package com.zhoujie.client;

public class TShape implements Shape {
	
	int x,y;
	Matrix m = MatrixImpl.getInstance();
	int dir; // " T is South"
	
	

	public TShape(int x, int y, int dir) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
	}

	private Shape newTShapeIfNotConflict(TShape t) {
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
		if (dir==Direction.North || dir == Direction.South){
			m.setXY(x, y, v);
			m.setXY(x-1, y, v);
			m.setXY(x+1, y, v);
			if (dir==Direction.North){
				m.setXY(x, y+1, v);
			}
			else{
				m.setXY(x, y-1, v);
			}
		}
		else{
			m.setXY(x, y-1, v);
			m.setXY(x, y, v);
			m.setXY(x, y+1, v);
			if (dir==Direction.East){
				m.setXY(x+1, y, v);
			}
			else{
				m.setXY(x-1, y, v);
			}
		}
	}

	private boolean conflict() {
		if (dir==Direction.North || dir == Direction.South){
			if (m.getXY(x, y)==1) return true;
			if (m.getXY(x-1, y)==1) return true;
			if (m.getXY(x+1, y)==1) return true;
			if (dir==Direction.North){
				if (m.getXY(x, y+1)==1) return true;
			}
			else{
				if (m.getXY(x, y-1)==1) return true;
			}
		}
		else{
			if (m.getXY(x, y-1)==1) return true;
			if (m.getXY(x, y)==1) return true;
			if (m.getXY(x, y+1)==1) return true;
			if (dir==Direction.East){
				if (m.getXY(x+1, y)==1) return true;
			}
			else{
				if (m.getXY(x-1, y)==1) return true;
			}
		}
		return false;
	}

	@Override
	public Shape left() {
		return newTShapeIfNotConflict(new TShape(x-1,y,dir));
	}

	@Override
	public Shape right() {
		return newTShapeIfNotConflict(new TShape(x+1,y,dir));
	}

	@Override
	public Shape down() {
		return newTShapeIfNotConflict(new TShape(x,y-1,dir));
	}

	@Override
	public Shape up() {
		return newTShapeIfNotConflict(new TShape(x,y+1,dir));
	}

	@Override
	public Shape clockWise() {
		return newTShapeIfNotConflict(new TShape(x,y,(dir+1)%4));
	}

	@Override
	public Shape couterClockWise() {
		return newTShapeIfNotConflict(new TShape(x,y,(dir+3)%4));
	}

}
