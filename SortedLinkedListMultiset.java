import java.io.PrintStream;
import java.util.*;

//list is sorted in ascending order by number of the element in the bag


//change parent class to LinkedListMultiset from Multiset, as most methods and attributes can remain the same for this class
@SuppressWarnings("unused")
public class SortedLinkedListMultiset<T> extends LinkedListMultiset<T>
{
	public SortedLinkedListMultiset() {
		listCount = 0;
	} // end of SortedLinkedListMultiset()
	
	
	public void add(T item) {
		//if list is empty, add item to the head
		if (head == null)
		{
			head = new Node<T>(item);
			tail = head;
			listCount++;
		}
		else if (search(item) == 0)
		{	
			Node<T> tempNode = head;
			Node<T> newNode = new Node<T>(item);
			String itemString = (String) item;
			String tempNodeString = (String) tempNode.get();
			while (itemString.compareTo(tempNodeString) > 0 && tempNode.getNext() != null)
			{
				tempNode = tempNode.getNext();
				tempNodeString = (String) tempNode.get();
			}
			if (tempNode.getNext() == null)
			{
				tempNode.setNext(newNode);
				newNode.setPrevious(tempNode);
				tail = newNode;
			}
			else if (tempNode.getPrevious() == null)
			{
				tempNode.setPrevious(newNode);
				newNode.setNext(tempNode);
				head = newNode;
			}
			else
			{
				newNode.setPrevious(tempNode.getPrevious());
				newNode.setNext(tempNode);
				tempNode.getPrevious().setNext(newNode);
				tempNode.setPrevious(newNode);
			}
		}
		else
		{
			Node<T> tempNode = getNode(item);
			tempNode.updateCount(tempNode.getCount() + 1);
		}
	} // end of add()
	
	//unimplemented in this class: same as superclass
	//public int search(T item)
	
	//essentially the inverse of the add() method
	public void removeOne(T item) {
		Node<T> tempNode = getNode(item);
		//if tempNode doesn't exist, return
		if (tempNode == null)
		{
			return;
		}
		//if only one item left in node, remove all
		else if (tempNode.getCount() == 1)
		{
			removeAll(item);
		}
		//else move item along to correct place in list
		else
		{
			{
				while (!head.equals(tempNode) && tempNode.getCount() < tempNode.getPrevious().getCount())
				{
					tempNode.getNext().setPrevious(tempNode.getPrevious());
					tempNode.getPrevious().setNext(tempNode.getNext());
					
					tempNode.setNext(tempNode.getPrevious());
					tempNode.setPrevious(tempNode.getPrevious().getPrevious());
					tempNode.getPrevious().setNext(tempNode);
				}
				if (tempNode.getPrevious() == null)
				{
					head = tempNode;
				}
			}
		}
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		Node<T> tempNode = getNode(item);
		//if node is at head, move head along one
		if (head.equals(tempNode))
		{
			head = head.getNext();
			head.setPrevious(null);
		}
		//if node is at tail, cut tail short one in list
		else if (tail.equals(tempNode))
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
	
	//same as LinkedListMultiset: not implemented here as extended from superclass
	//public void print(PrintStream out) 
	
} // end of class SortedLinkedListMultiset
