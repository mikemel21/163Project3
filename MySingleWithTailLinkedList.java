package project3;

import java.io.Serializable;
import java.util.Random;
import java.util.Collections;
import java.util.LinkedList;

public class MySingleWithTailLinkedList implements Serializable
{
    private Node top;
    private Node tail;

    public MySingleWithTailLinkedList() {
        top = tail = null;
    }

    // This method has been provided and you are not permitted to modify
    public int size() {
        if (top == null)
            return 0;

        int total = 0;
        Node temp = top;
        while (temp.getNext() != null) {
            total++;
            temp = temp.getNext();
        }

        if (temp != tail)
            throw new RuntimeException("Tail is not pointing at the end of the list");
        else
            total++;

        return total;
    }

    // This method has been provided and you are not permitted to modify
    public void clear () {
        Random rand = new Random(13);
        while (size() > 0) {
            int number = rand.nextInt(size());
            remove(number);
        }
    }

    /********************************************************************************************
     *
     *    Your task is to complete this method.
     *
     *
     *
     * @param rental the unit being rented
     */
    
    public void add(Rental rental) {
    	/* EDGE CASES:
    	 * 
    	 */
    	
		// step 1: create new node
		Node newNode = new Node (rental, null);
		
		
		// step 2: add newNode to top of LinkedList before organizing list
		top = newNode;
		
		if (newNode == null || newNode.getNext() == null) {
    		tail = newNode;
			//return;
		} else {
			organize(newNode);
		}
		return; // KEEP OR DELETE?
    }
    
    /**
     * Helper function that organizes LinkedList by Games and their due date,
     * and then Consoles by their due date.
     */
    public Node organize(Node head) {
		// implements "merge sort" algorithm
		
		/*
		 * temp: equal to end of first half
		 * slow: traverses list one at a time
		 * fast: traverses list two at a time; switches to slow when it equals null
		 */
		Node temp = head;
		Node slow = head;
		Node fast = head;
		
		while (fast != null || fast.getNext() != null) {
			temp = slow;
			slow = slow.getNext();
			fast = fast.getNext().getNext();
		}
		
		temp.setNext(null);
		
		// recursion
		Node leftSide = organize(head);
		Node rightSide = organize(slow);
		
		return merge(leftSide, rightSide);
    }
    
    public Node merge(Node l1, Node l2) {
    	// implements merge sort
    	
    	Node temp = new Node (null, null);
    	Node curNode = temp;
    	
    	while (l1 != null && l2 != null) {
    		// if l1 is due before l2
    		if (get(1) == l1.getData()) {
    			
    		}
    		if (l1.getData().getDueBack().compareTo(l2.getData().getDueBack()) < 0) {
    			curNode.setNext(l1);
    			// continue going down list
    			l1 = l1.getNext();
    		} else {
    			curNode.setNext(l2);
    			// continue going down list
    			l2 = l2.getNext();
    		}
    		
    		curNode = curNode.getNext();
    		
    		// edge cases
    		if (l1 != null) {
    			curNode.setNext(l1);
    			l1 = l1.getNext();
    		}
    		if (l2 != null) {
    			curNode.setNext(l2);
    			l2 = l2.getNext();
    		}
    	}
    	
    	return temp;
    }

    /**
     * removes data of a rental at a specific index
     * 
     * @param index of what rental to remove
     * @return rental data of removed rental
     */
    public Rental remove(int index) {
        /* EDGE CASES:
         * index > size()
         * index < 0
         * empty list
         * index is 0
         * index is at the tail (MAYBE)
         */
    	if (top == null || index < 0 || index >= size()) {
    		return null;
    	} else if (index == 0) {
			// takes the data from top
			Rental removed = top.getData();
			
			// "deletes" top AKA switches top
			top = top.getNext(); 
			return removed; 
    	} else {
			int counter = 0;
			Node tNode = top;
			// return top if index is 0
				// 1. walk to index - 1
				while (counter < index-1) {
					counter++;
					tNode = tNode.getNext();
				}
				
				// 2. capture data at index
				Rental removed = tNode.getNext().getData();
				
				// 3. node at index-1 next points to node at index + 1
				tNode.setNext(tNode.getNext().getNext());
		
		        return removed;
    	}

    }
    
    /**
     * gets Rental data at certain index
     * @param index
     * @return the rental data from index
     */
    public Rental get(int index) {
        //  more code goes here. (Done?)
		
    	// LOOK FOR EXCEPTIONS AND EDGE CASES
        if (top == null) {
            return null;
        } else {
			int currentIndex = 0;
			Node current = top;
			
			while (currentIndex < index) {
				current = current.getNext();
				currentIndex++;
			}
			
			Rental gotten = current.getData();
			
			return gotten;
        }
    }

    public void display() {
        Node temp = top;
        while (temp != null) {
            System.out.println(temp.getData());
            temp = temp.getNext();
        }
    }

    @Override
    public String toString() {
        return "LL {" +
                "top=" + top +
                ", size=" + size() +
                '}';
    }
}

