package boardgame;

public class Position {
	private Integer row, column;

	public Position(Integer row, Integer column) {
		this.row = row;
		this.column = column;
	}

	public Integer getRow() {
		return row;
	}
	
	public void setRow(int row) {
		this.row = row;
	}

	public Integer getColumn() {
		return column;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
	
	public void setPosition(int row, int column) {
		this.column = column;
		this.row = row;
	}

	@Override
	public String toString() {
		return this.row + ", " + this.column;
	}
}
