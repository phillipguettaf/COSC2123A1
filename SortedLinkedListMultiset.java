import java.io.PrintStream;
import java.util.*;

//list is sorted in ascending order by number of the element in the bag


//change parent class to LinkedListMultiset from Multiset, as most methods and attributes can remain the same for this class
@SuppressWarnings("unused")
public class SortedLinkedListMultiset<T> extends LinkedListMultiset<T>
{
	public SortedLinkedListMultiset() {
		head = new Node<T>();
		tail = head;
		listCount = 0;
	} // end of SortedLinkedListMultiset()
	
	
	public void add(T item) {
		//if list is empty, add item to the head
		if (head.get() == null)
		{
			head.changeElement(item);
			head.updateCount(1);
			listCount = 1;
		}
		else
		{
			//if element is not in list, add one and set it to the head (one is the lowest value in the list)
			if (search(item) == 0)
			{
				Node<T> newNode = new Node<T>(item);
				head.setPrevious(newNode);
				newNode.setNext(head);
				head = newNode;
				listCount++;
			}
			else
			{
				Node<T> tempUpdateNode = getNode(item);
				tempUpdateNode.updateCount(tempUpdateNode.getCount() + 1);
				//move the node along in the list to the correct point
				//TODO: ensure no error when (tempUpdateNode.getNext() == null) for getCount()
				while (!tail.equals(tempUpdateNode) && tempUpdateNode.getCount() > tempUpdateNode.getNext().getCount())
				{
					//set the previous and next nodes to point to each other
					tempUpdateNode.getPrevious().setNext(tempUpdateNode.getNext());
					tempUpdateNode.getNext().setPrevious(tempUpdateNode.getPrevious());
					//set the node between the next, and one after the next
					tempUpdateNode.setPrevious(tempUpdateNode.getNext());
					tempUpdateNode.setNext(tempUpdateNode.getNext().getNext());
					tempUpdateNode.getNext().setPrevious(tempUpdateNode);
				}
				//if node has moved to the end of the list, update the tail
				if (tempUpdateNode.getNext() == null)
				{
					tail = tempUpdateNode;
				}
				
			}
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
