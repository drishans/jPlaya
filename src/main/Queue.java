package main;
/**
 * Queue.java
 * @author Drishan
 */

import java.util.NoSuchElementException;

public class Queue<T> {
	private class Node {
		private T data;
		private Node next;

		public Node(T data) {
			this.data = data;
			this.next = null;
		}
	}

	private int length;
	private Node front;
	private Node end;

	/**** CONSTRUCTORS ****/

	/**
	 * Default constructor for the Queue class
	 * 
	 * @postcondition a new Queue object with all fields assigned default values
	 */
	public Queue() {
		length = 0;
		front = null;
		end = null;
	}

	/**
	 * Copy constructor for the Queue class
	 * 
	 * @param original
	 *            the Queue to copy
	 * @postcondition a new Queue object which is an identical, but distinct,
	 *                copy of original
	 */
	public Queue(Queue<T> original) {
		if (original.isEmpty()) {
			this.front = this.end = null;
			length = 0;
		} else {
			Node temp = original.front;
			while (temp != null) {
				enqueue(temp.data);
				temp = temp.next;
				this.length++;
			}
			this.front = original.front;
			this.end = original.end;
		}
	}

	/**** ACCESSORS ****/

	/**
	 * Returns the value stored at the front of the Queue
	 * 
	 * @return the value at the front of the queue
	 * @precondition !isEmpty()
	 * @throws NoSuchElementException
	 *             when the precondition is violated
	 */
	public T getFront() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException(
			        "getFront(): No element in queue." + "Cannot return value");
		}
		return front.data;
	}

	/**
	 * Returns the length of the Queue
	 * 
	 * @return the length from 0 to n
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Determines whether a Queue is empty
	 * 
	 * @return whether the Queue is emtpy
	 */
	public boolean isEmpty() {
		return (front == null);
	}

	/**
	 * Determines whether two Queues contain the same values in the same order
	 * 
	 * @param o
	 *            the Object to compare to this
	 * @return whether o and this are equal
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		Queue<T> L = (Queue<T>) o;
		if (o == this) {
			return true;
		} else if (!(o instanceof Queue)) {

			return false;
		} else if (this.length != L.length) {

			return false;
		} else {
			Node temp1 = this.front;
			Node temp2 = L.front;
			while (temp1 != null) {
				if (temp1.data != temp2.data) {

					return false;
				}
				temp1 = temp1.next;
				temp2 = temp2.next;
			}
			return true;
		}
	}

	/**** MUTATORS ****/

	/**
	 * Inserts a new value at the end of the Queue
	 * 
	 * @param data
	 *            the new data to insert
	 * @postcondition a new node at the end of the Queue
	 */
	public void enqueue(T data) {
		if (isEmpty()) {
			front = end = new Node(data);
		} else {
			Node N = new Node(data);
			end.next = N;
			end = N;
		}
		length++;
	}

	/**
	 * Removes the front element in the Queue
	 * 
	 * @precondition !isEmpty()
	 * @throws NoSuchElementException
	 *             when the precondition is violated
	 * @postcondition the front element has been removed
	 */
	public void dequeue() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException(
			        "dequeue(): Queue is empty. " + "Cannot remove element.");
		} else if (this.length == 1) {
			front = end = null;
			length--;
		} else {
			front = front.next;
			length--;
		}
	}

	/**** ADDITONAL OPERATIONS ****/

	/**
	 * Returns the values stored in the Queue as a String, separated by a blank
	 * space with a new line character at the end
	 * 
	 * @return a String of Queue values
	 */
	@Override
	public String toString() {
		String result = "";
		Node temp = front;

		while (temp != null) {
			result += temp.data + " \n";
			temp = temp.next;
		}
		result += "\n";
		return result;
	}
}