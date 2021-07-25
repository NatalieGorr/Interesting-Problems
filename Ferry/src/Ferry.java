import java.lang.*;
import java.io.*;
import java.util.*;

public class Ferry {

	public static void main(String[] args) {
		
		int timeToTravel;
		int capacity;
		int numCars;	
		Queue left = new Queue();
		Queue right = new Queue();
			
		Scanner obj = new Scanner(System.in);
		
		capacity = obj.nextInt();
		timeToTravel = obj.nextInt();
		numCars = obj.nextInt();
		
		int[] array = new int[numCars];
			
		//queue all times into the appropriate side
		int at;
		String s;
		for(int i = 0; i< numCars; i++) {
			
			at = obj.nextInt();
			s = obj.next();
			if(s.equals("left")) {
				left.enqueue(at, i);
			}
			else {
				right.enqueue(at, i);	
			}		
		}
		
		int currentCap = 0;
		int time = 0;
		String side = "left";
		int newDockTime = 0;
		
		//!left.isEmpty() || !right.isEmpty() || currentCap>0
		while(!left.isEmpty() || !right.isEmpty() || currentCap>0) {
					
			if(side == "mleft") {
				if(time == newDockTime) {
					side = "left";
					//time++;
					//continue;
				}
				
			}
			if(side == "mright") {
				if(time == newDockTime) {
					side = "right";
					//time++;
					//continue;
				}		
			}
			
			//unload current boat
			if(time == newDockTime) {
				while(currentCap>0) {
					//System.out.println(time);
					currentCap--;
				}
			}
			
			//load if we are on the left side
			if(side.equals("left")) {
				//if we are on the left side and there are cars on the left side
				while(!left.isEmpty()&&currentCap<capacity&&left.peek()<=time) {
					int[] ord = left.dequeue();
					array[ord[1]] = time+timeToTravel;
					currentCap++;
					newDockTime = time + timeToTravel;
				}			
			}
			
			else if(side.equals("right")) {
				while(!right.isEmpty()&&currentCap<capacity&&right.peek()<=time) {
					int[] ord = right.dequeue();
					array[ord[1]] = time+timeToTravel;
					//System.out.println("order"+ord[1]);
					currentCap++;
					newDockTime = time + timeToTravel;
				}	
			}
			
			//decide wether or not to move the boat
			if(currentCap>0) {
				if(side == "left") {
					side = "mright";
				}
				if(side == "right") {
					side = "mleft";
				}		
			}
			
			if(side == "left") {
				if(right.isEmpty()) {
					time++;
					continue;
				}
				if(left.isEmpty()&&right.peek()<=time) {
					side = "mright";
					newDockTime = time+timeToTravel;
				}
				if(!left.isEmpty()&&left.peek()>=time&&right.peek()<=time) {
					side = "mright";
					newDockTime = time+timeToTravel;
				}
			}
			if(side == "right") {
				if(left.isEmpty()) {
					time++;
					continue;
				}
				if(right.isEmpty()&&left.peek()<=time) {
					side = "mleft";
					newDockTime = time+timeToTravel;
				}
				if(!right.isEmpty()&&right.peek()>=time&&left.peek()<=time) {
					side = "mleft";
					newDockTime = time+timeToTravel;
				}
			}
			
			//increment time
			time++;					
		}
		
		
		for(int i = 0; i< numCars; i++) {
			System.out.println(array[i]);
		}		
	}

	static class Queue{
		Node head = null;
		Node tail = null;
	
		
		//constructor
		public Queue() {
			
		}
		
		//class for nodes, has data and next
		private class Node{
			int arrivalTime;
			int order;
			//private String side;
			private Node next = null;
			
			//constructor
			public Node(int x, int i) {
				arrivalTime = x;
				order = i;
				//side = n;
			}	
		}
		
		
		//insert, always insert to the back of the list
		public void enqueue(int x, int i) {
			Node toAdd = new Node(x, i);
			
			//if the queue is empty
			if(head == null) {
				head = toAdd;
				tail = toAdd;
				return;
			}
			
			tail.next = toAdd;
			tail = toAdd;		
		}
		
		
		public int[] dequeue() {
			//dont check for empty q have an is empty fnct 
			Node temp = head;
			head = head.next;
			temp.next = null;
			int[] ret = {temp.arrivalTime, temp.order};
			return ret;
		}
		
		public int peek() {
			return head.arrivalTime;	
		}
		
		
		
		public boolean isEmpty() {
			if(head == null) {
				return true;
			}
			return false;			
		}
		
		public void print() {
			if(head == null) {
				System.out.println("empty");
			}
			
			Node current = head;
			while(current != null) {
				System.out.println(current.arrivalTime);
				current = current.next;
				
			}
						
		}		
		
	}
				
}		
