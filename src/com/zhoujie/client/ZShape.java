package com.zhoujie.client;
/*
 * 11
 *  11
 */
public class ZShape implements Shape {
	
	int x,y;
	boolean vertical;
	MatrixImpl m=MatrixImpl.getInstance();

	public ZShape(int x, int y, boolean vertical) {
		super();
		this.x = x;
		this.y = y;
		this.vertical = vertical;
	}

	@Override
	public Shape left() {
		ZShape b = new ZShape(x-1,y,vertical);
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
		ZShape b = new ZShape(x+1,y,vertical);
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
		ZShape b = new ZShape(x,y-1,vertical);
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
		ZShape b = new ZShape(x,y+1,vertical);
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
		ZShape b = null;
		if (vertical){
			b = new ZShape(x-1,y,false);
		}
		else{
			b = new ZShape(x+1,y,!vertical);
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
		ZShape b = null;
		if (vertical){
			b = new ZShape(x-1,y,false);
		}
		else{
			b = new ZShape(x+1,y,!vertical);
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
			return m.getXY(x,y)==1||m.getXY(x,y+1)==1||m.getXY(x-1,y)==1||m.getXY(x-1,y-1)==1;
		}
		else {
			return m.getXY(x,y)==1||m.getXY(x-1,y)==1||m.getXY(x,y-1)==1||m.getXY(x+1,y-1)==1;
		}
	}


	public void clear() {
		if(vertical){
			m.setXY(x, y, 0);
			m.setXY(x, y+1, 0);
			m.setXY(x-1, y, 0);
			m.setXY(x-1, y-1, 0);		}
		else{
			m.setXY(x, y, 0);
			m.setXY(x-1, y, 0);
			m.setXY(x, y-1, 0);
			m.setXY(x+1, y-1, 0);		}
	}

	public void occupy() {
		if(vertical){
			m.setXY(x, y, 1);
			m.setXY(x, y+1, 1);
			m.setXY(x-1, y, 1);
			m.setXY(x-1, y-1, 1);
		}
		else{
			m.setXY(x, y, 1);
			m.setXY(x-1, y, 1);
			m.setXY(x, y-1, 1);
			m.setXY(x+1, y-1, 1);
		}
	}
}
