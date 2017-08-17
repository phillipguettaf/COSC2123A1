import java.io.PrintStream;
import java.util.*;

//list is sorted in ascending order by number of the element in the bag


//change parent class to LinkedListMultiset from Multiset, as most methods and attributes can remain the same for this class
@SuppressWarnings("unused")
public class SortedLinkedListMultiset<T> extends LinkedListMultiset<T>
{
	public SortedLinkedListMultiset() {
		head = new Node();
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
				Node newNode = new Node(item);
				head.setPrevious(newNode);
				newNode.setNext(head);
				head = newNode;
				listCount++;
			}
			else
			{
				Node tempUpdateNode = getNode(item);
				tempUpdateNode.updateCount(tempUpdateNode.getCount() + 1);
				//move the node along in the list to the correct point
				//TODO: ensure no error when (tempUpdateNode.getNext() == null) for getCount()
				while (tempUpdateNode.getCount() > tempUpdateNode.getNext().getCount())
				{
					//set the previous and next nodes to point to each other
					tempUpdateNode.getPrevious().setNext(tempUpdateNode.getNext());
					tempUpdateNode.getNext().setPrevious(tempUpdateNode.getPrevious());
					//set the node between the next, and one after the next
					tempUpdateNode.setPrevious(tempUpdateNode.getNext());
					tempUpdateNode.setNext(tempUpdateNode.getNext().getNext());
					tempUpdateNode.getNext().setPrevious(tempUpdateNode);
					

					//if node has moved to the end of the list, update the tail and break the loop
					if (tempUpdateNode.getNext() == null)
					{
						tail = tempUpdateNode;
						break;
					}
				}
				
			}
		}
	} // end of add()
	
	//unimplemented in this class: same as superclass
	//public int search(T item)
	
	//essentially the inverse of the add() method
	public void removeOne(T item) {
		Node tempNode = getNode(item);
		if (tempNode == null)
		{
			return;
		}
		else if (tempNode.getCount() == 1)
		{
			if (head == tempNode)
			{
				
			}
		}
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		// Implement me!
	} // end of removeAll()
	
	//same as LinkedListMultiset: not implemented here as extended from superclass
	//public void print(PrintStream out) 
	
} // end of class SortedLinkedListMultiset