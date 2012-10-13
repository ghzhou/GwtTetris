package com.zhoujie.client;

public class Bar implements Shape {
	
	int x,y;
	boolean vertical;
	MatrixImpl m=MatrixImpl.getInstance();

	public Bar(int x, int y, boolean vertical) {
		super();
		this.x = x;
		this.y = y;
		this.vertical = vertical;
	}

	@Override
	public Shape left() {
		Bar b = new Bar(x-1,y,vertical);
		this.clear();
		if (!b.conflict()) {
			b.occupy();
			return b;
		}
		else{
			this.occupy();
			return this;
		}
	}

	@Override
	public Shape right() {
		Bar b = new Bar(x+1,y,vertical);
		this.clear();
		if (!b.conflict()) {
			b.occupy();
			return b;
		}
		else{
			this.occupy();
			return this;
		}
	}

	@Override
	public Shape down() {
		Bar b = new Bar(x,y-1,vertical);
		this.clear();
		if (!b.conflict()) {
			b.occupy();
			return b;
		}
		else{
			this.occupy();
			return this;
		}
	}

	@Override
	public Shape up() {
		Bar b = new Bar(x,y+1,vertical);
		this.clear();
		if (!b.conflict()) {
			b.occupy();
			return b;
		}
		else{
			occupy();
			return this;
		}
	}

	@Override
	public Shape clockWise() {
		Bar b = null;
		if (vertical){
			b = new Bar(x-1,y-1,false);
		}
		else{
			b = new Bar(x+1,y+1,!vertical);
		}
		this.clear();
		if (!b.conflict()) {
			b.occupy();
			return b;
		}
		else{
			this.occupy();
			return this;
		}
	}

	@Override
	public Shape couterClockWise() {
		Bar b = null;
		if (vertical){
			b = new Bar(x-1,y-1,false);
		}
		else{
			b = new Bar(x+1,y+1,!vertical);
		}
		this.clear();
		if (!b.conflict()) {
			b.occupy();
			return b;
		}
		else{
			this.occupy();
			return this;
		}
	}

	public boolean conflict() {
		if (vertical){
			return m.getXY(x,y)==1||m.getXY(x,y-1)==1||m.getXY(x,y-2)==1||m.getXY(x,y-3)==1;
		}
		else {
			return m.getXY(x,y)==1||m.getXY(x+1,y)==1||m.getXY(x+2,y)==1||m.getXY(x+3,y)==1;
		}
	}


	public void clear() {
		if(vertical){
			m.setXY(x, y, 0);
			m.setXY(x, y-1, 0);
			m.setXY(x, y-2, 0);
			m.setXY(x, y-3, 0);
		}
		else{
			m.setXY(x, y, 0);
			m.setXY(x+1, y, 0);
			m.setXY(x+2, y, 0);
			m.setXY(x+3, y, 0);
		}
	}

	public void occupy() {
		if(vertical){
			m.setXY(x, y, 1);
			m.setXY(x, y-1, 1);
			m.setXY(x, y-2, 1);
			m.setXY(x, y-3, 1);
		}
		else{
			m.setXY(x, y, 1);
			m.setXY(x+1, y, 1);
			m.setXY(x+2, y, 1);
			m.setXY(x+3, y, 1);
		}
	}
}
