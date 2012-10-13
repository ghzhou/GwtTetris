package com.zhoujie.client;

public interface Matrix {
	public void setViewer(MatrixView mv);
	public int getXY(int x, int y);
	public void setXY(int x, int y, int v);
	public void updateViewer();
	public int getWidth();
	public int getHeight();
	public void removeCompletedRows();
}
