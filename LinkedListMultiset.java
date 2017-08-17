import java.io.PrintStream;
import java.util.*;

public class LinkedListMultiset<T> extends Multiset<T>
{
	
	protected Node head;
	protected Node tail;
	protected int listCount;
	
	public LinkedListMultiset() {
		head = new Node();
		tail = head;
		listCount = 0;
	} // end of LinkedListMultiset()
	
	
	public void add(T item) {
		//if list is empty, add the new element to the head
		if (head.get() == null)
		{
			head.changeElement(item);
			head.updateCount(1);
			listCount++;
		}
		else
		{
			//if element not in list, add new node and update tail, else update count in existing node
			if (search(item) == 0)
			{
				Node newNode = new Node(item);
				tail = newNode;
				listCount++;
			}
			else
			{
				getNode(item).updateCount(getNode(item).getCount()+1);
			}
		}
	} // end of add()
	
	//search for number of instances of "item" in the list: return this
	public int search(T item) {
	
		Node tempNode = head;
		if (tail.get().equals(item))
		{
			return tail.getCount();
		}
		while (!tempNode.equals(tail))
		{
			if (tempNode.get().equals(item))
			{
				return tempNode.getCount();
			}
			tempNode = tempNode.getNext();
		}
		return 0;
	} // end of search()
	
	
	public void removeOne(T item) {
		if (getNode(item) == null)
		{
			System.out.println("Cannot delete " + item + ". Does not exist.");
		}
		
		//if only one exists, remove node, else decrement element count
		if (search(item) == 1)
		{
			removeAll(item);
		}
		else
		{
			getNode(item).updateCount(getNode(item).getCount() - 1);
		}
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		
		if (getNode(item) == null)
		{
			System.out.println("Cannot delete " + item + ". Does not exist.");
		}
		else
		//change the surrounding nodes to point to each other, and decrement the list counter
		{
			getNode(item).getNext().setPrevious(getNode(item).getPrevious());
			getNode(item).getPrevious().setNext(getNode(item).getNext());
			listCount--;
		}

	} // end of removeAll()
	
	
	public void print(PrintStream out) {
		
		Node printNode  = head;
		
		//starting with head, iterate through list and print, then print the tail
		while (!printNode.equals(tail))
		{
			out.println(printNode.get() + "\t| " + printNode.getCount());
			printNode = printNode.getNext();
		}
		out.println(tail.get() + "\t| " + tail.getCount());
		
		/*print in format:
		*  <element>     | <number of elements in set>
		*  <nextelement> | <number of elements in set>
		*/
	} // end of print()
	
	//returns the instance of a node if found in the list: returns null if node not found
	private Node getNode(T item)
	{
		Node tempNode = head;
		if (tail.get().equals(item))
		{
			return tail;
		}
		
		while (!tempNode.equals(tail))
		{
			if (tempNode.get().equals(item))
			{
				return tempNode;
			}
			tempNode = tempNode.getNext();
		}
		return null;
	}
	
} // end of class LinkedListMultiset
