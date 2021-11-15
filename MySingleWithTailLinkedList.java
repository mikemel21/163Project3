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

//        if (temp != tail)
//            throw new RuntimeException("Tail is not pointing at the end of the list");
//        else
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
		// step 1: create new node
    	/*
    	 * hint:
    	 * 
    	 * 
    	 * -> check if dueBack date is before top: create new top
    	 * -> traverse list
    	 * Tip: check conditions... should you be looking at temp or 
    	 * temp.getNext()? change some to temp.getNext()
    	 * 
    	 * Simple Cases:
    	 * -> list is empty
    	 * -> list has one element
    	 * -> newNode due before topNode
    	 * -> newNode due after tailNode
    	 */

		if (top == null) { // if list is empty
			Node newNode = new Node (rental, null);
			top = newNode;
			tail = top;
		} else if (top != null && size() == 1) { // if there is one node in list
			// if rental is due before the one element in the list
			if (rental.getDueBack().compareTo(top.getData().getDueBack()) < 0) {
				Node newNode = new Node (rental, null);
				newNode.setNext(top);
				tail = top;
				top = newNode;
			} else {
				Node newNode = new Node (rental, null);
				top.setNext(newNode);
				tail = newNode;
			}
		} else {
			// if the rental is due before the top node
			if (rental.getDueBack().compareTo(top.getData().
					getDueBack()) < 0) {
				Node newNode = new Node (rental, null);
				newNode.setNext(top);
				top = newNode;
				maintainTail();
			} else {
				int counter = 0;
				Node temp = top;
				Node newNode = new Node (rental, null);
				
				while (counter != size()  && temp.getNext() != null) {
					if (rental.getDueBack().compareTo(temp.getNext().getData().getDueBack()) < 0) {
						newNode.setNext(temp.getNext());
						temp.setNext(newNode);
						return;
					} else {
						counter++;
						temp = temp.getNext();
					}
				}
			}
		}
		return; // KEEP OR DELETE?
    }
    
    public void maintainTail() {
    	Node temp = top;
    	int counter = 0;

    	// traverse through Linked List
    	while (counter < size() && temp.getNext() != null) {
    		temp = temp.getNext();
    		counter++;
    	}
    	System.out.println("Tail = " + tail + "Size:" + size());
    	// set last node to tail
    	tail = temp.getNext();
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

