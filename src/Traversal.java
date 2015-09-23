import java.util.LinkedList;
import java.util.ListIterator;


public class Traversal {

	static final Ocean ocean = new Ocean(0);
	static final int pathColor = 200;
	static final int deadEndColor = 100;
	
	static Cell fish;
	
	
	static void slow(){
		try {
			Thread.sleep(2);
		} catch (InterruptedException e) {}
	}
	

    ///////////// Metodos a serem completados ///////////// 
	
		
	static void q21() {
		
		boolean foundNemo = false;

		//list of all elements that we have cross
		LinkedList<Cell> l = new LinkedList<Cell>();
		
		//list of elements to process
		LinkedList<Cell> q = new LinkedList<Cell>();
		
		//fish that walks around
		Cell fish = new Cell(-1, -1);
		
		//cell to process
		LinkedList<Cell> neighbors = new LinkedList<Cell>();
		
		//initial position
		Cell s = new Cell(ocean.marlin.x, ocean.marlin.y);
		
		//add first element
		l.add(s);
		q.add(s);
		
		//west,south,east,north

		while(!foundNemo) {
			fish = q.pop();
			
			neighbors = fish.neighbors();
			slow();
			for(int i = 0; i < 4 && !foundNemo; i++) {
				Cell water = neighbors.get(i);
				
				if(ocean.isNemo(water)) {
					foundNemo = true;
					fish = water;
					System.out.println("Nemo is at: " + fish.toString());
				}
				else if((!ocean.isWall(water) && !contains(l, water))) {
					q.add(water);
					l.add(water);
				}
			}
		}		
	}

	static void q22() {
		
		boolean foundNemo = false;

		//list of all elements that we have cross
		LinkedList<Cell> l = new LinkedList<Cell>();
		
		//list of elements to process
		LinkedList<Cell> q = new LinkedList<Cell>();
		
		//fish that walks around
		Cell fish = new Cell(-1, -1);
		
		//cell to process
		LinkedList<Cell> neighbors = new LinkedList<Cell>();
		
		//initial position
		Cell s = new Cell(ocean.marlin.x, ocean.marlin.y);
		
		//add first element
		l.add(s);
		q.add(s);
		
		//west,south,east,north

		while(!foundNemo) {
			fish = q.pop();
			
			neighbors = fish.neighbors();
			slow();
			
			for(int i = 0; i < 4 && !foundNemo; i++) {
				Cell water = neighbors.get(i);
				
				if(ocean.isNemo(water)) {
					foundNemo = true;
					ocean.setMark(water, ocean.getMark(fish) + 1);
					fish = water;
					System.out.println("Nemo is at: " + fish.toString());
					System.out.println("It took us: " + ocean.getMark(fish) + " steps");
				}
				else if((!ocean.isWall(water) && !contains(l, water))) {
					q.add(water);
					l.add(water);
					ocean.setMark(water, ocean.getMark(fish) + 1);
				}
			}
		}
	}

	// convenções de marca: 1 (WEST), 2(SOUTH), 3(EAST), 4(NORTH)
	static final int WEST = 1, SOUTH = 2, EAST = 3, NORTH = 4, NORTH__WEST= 5, NORTH__EAST= 6, SOUTH__WEST = 7, SOUTH__EAST = 8;
	
	
	static void q23() {
		boolean foundNemo = false;

		//list of all elements that we have cross
		LinkedList<Cell> l = new LinkedList<Cell>();
		
		//list of elements to process
		LinkedList<Cell> q = new LinkedList<Cell>();
		
		
		
		//fish that walks around
		fish = new Cell(-1, -1);
		
		//cell to process
		LinkedList<Cell> neighbors = new LinkedList<Cell>();
		
		//initial position
		Cell s = new Cell(ocean.marlin.x, ocean.marlin.y);
		
		//add first element
		l.add(s);
		q.add(s);
		
		//west,south,east,north

		while(!foundNemo) {
			fish = q.pollLast();  //retorna o primeiro elemento da  pilha ( ultimo da fila)
			
			neighbors = fish.neighbors();
			slow();
			
			for(int i = 1; i < 5 && !foundNemo; i++) {
				Cell water = neighbors.get(i - 1);
				
				if(ocean.isNemo(water)) {
					foundNemo = true;
					ocean.setMark(water, i);
					fish = water;
				}
				else if((!ocean.isWall(water) && !contains(l, water))) {
					q.add(water);
					l.add(water);
					ocean.setMark(water, i);			
										
				}				
			}			
		}	
		
	}
	
	


	static void backTrack() {
		q23();
		int mark = 0;
		LinkedList<Cell> path = new LinkedList<Cell>();
		float  peso = 0;		
		while(!ocean.isMarlin(fish))
		{
			slow();
			path.add(fish);
			mark = ocean.getMark(fish);
			switch(mark) {
				case WEST:
					peso=peso+1;
					ocean.setMark(fish, 200);
					fish = fish.east();
					break;
				case EAST:
					peso=peso+1;
					ocean.setMark(fish, 200);
					fish = fish.west();
					break;
				case NORTH:
					peso=peso+1;
					ocean.setMark(fish, 200);
					fish = fish.south();
					break;
				case SOUTH:
					peso=peso+1;
					ocean.setMark(fish, 200);
					fish = fish.north();
					break;
				case NORTH__WEST:
					peso=(float) (peso+1.41);
					ocean.setMark(fish, 200);
					fish = fish.south_east();
					break;
				case NORTH__EAST:
					peso=(float) (peso+1.41);
					ocean.setMark(fish, 200);
					fish = fish.south_west();
					break;
				case SOUTH__WEST:
					peso=(float) (peso+1.41);
					ocean.setMark(fish, 200);
					fish = fish.north_east();
					break;
				case SOUTH__EAST:
					peso=(float) (peso+1.41);
					ocean.setMark(fish, 200);
					fish = fish.north_east();
					break;		
			}
		}
		System.out.println("Caminho: ");
		ListIterator itr = path.listIterator();
		   // print list with the iterator
		   while (itr.hasNext()) {
		   System.out.println(itr.next());
		   }
		   System.out.println();
		   System.out.print("Pesos: ");
		   System.out.println(peso);
	}
		
	static boolean contains(LinkedList<Cell> l, Cell c) {
		boolean b = false;
		ListIterator<Cell> iterator = l.listIterator();
		while(iterator.hasNext() && !b) {
			if(iterator.next().equals(c))
				b = true;
		}
		return b;
	}
	public static void main(String[] args) {
	    backTrack();
	}

}	