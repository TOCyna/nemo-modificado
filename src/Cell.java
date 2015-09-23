import java.util.Iterator;
import java.util.LinkedList;

public class Cell{

  int x;
  int y;
  int hashCode;
  

  Cell(int xx, int yy){
    x=xx;
    y=yy;
  }
  
  // Convenção: West=(-1,0), South=(0,+1), East=(+1,0), North=(0,-1)
  Cell west(){return new Cell(x-1,y);}
  Cell south(){return new Cell(x,y+1);}
  Cell east(){return new Cell(x+1,y);}
  Cell north(){return new Cell(x,y-1);}
  Cell north_west(){return new Cell(x-1,y-1);}
  Cell north_east(){return new Cell(x+1,y-1);}
  Cell south_west(){return new Cell(x-1,y+1);}
  Cell south_east(){return new Cell(x+1,y+1);}
  
  // Retorna a lista de vizinhos na ordem West, South, East, North
  LinkedList<Cell> neighbors() {
	  LinkedList<Cell> l=new LinkedList<Cell>();
	  l.add(west());
	  l.add(south());
	  l.add(east());
	  l.add(north());
	  l.add(north_west());
	  l.add(north_east());
	  return l;
  }

  public String toString() {
	  return "("+x+","+y+")";
  }
  
  public boolean equals(Cell obj) {
	    return ((obj instanceof Cell && x == ((Integer) obj.x) && y == ((Integer) obj.y)));
  }
}