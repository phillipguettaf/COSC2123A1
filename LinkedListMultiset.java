import java.io.PrintStream;
import java.util.*;

@SuppressWarnings("unused")
public class LinkedListMultiset<T> extends Multiset<T>
{
	
	protected Node<T> head;
	protected Node<T> tail;
	protected int listCount;
	
	public LinkedListMultiset() {
		listCount = 0;
	} // end of LinkedListMultiset()
	
	
	public void add(T item) {
		//if list is empty, add the new element to the head
		if (head == null)
		{
			head = new Node<T>(item);
			tail = head;
			listCount++;
		}
		else
		{
			//if element not in list, add new node and update tail, else update count in existing node
			if (search(item) == 0)
			{
				Node<T> newNode = new Node<T>(item);
				tail.setNext(newNode);
				newNode.setPrevious(tail);
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
	
		Node<T> tempNode = head;
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
			return;
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
		Node<T> tempNode = getNode(item);
		//if node is at head, move head along one
		if (head == tempNode)
		{
			head = head.getNext();
			head.setPrevious(null);
		}
		//if node is at tail, cut tail short one in list
		else if (tail == tempNode)
		{
			tail = tempNode.getPrevious();
			tail.setNext(null);
		}
		else
		{
			//remove node from list by changing pointers in surrounding nodes
			tempNode.getPrevious().setNext(tempNode.getNext());
			tempNode.getNext().setPrevious(tempNode.getPrevious());
		}
	} // end of removeAll()
	
	public void print(PrintStream out) {
		
		Node<T> printNode  = head;
		
		//starting with head, iterate through list and print, then print the tail
		while (!printNode.equals(tail))
		{
			out.println(printNode.get() + " | " + printNode.getCount());
			printNode = printNode.getNext();
		}
		out.println(tail.get() + " | " + tail.getCount());
		
		/*print in format:
		*  <element>     | <number of elements in set>
		*  <nextelement> | <number of elements in set>
		*/
	} // end of print()
	
	//returns the instance of a node if found in the list: returns null if node not found
	protected Node<T> getNode(T item)
	{
		Node<T> tempNode = head;
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
