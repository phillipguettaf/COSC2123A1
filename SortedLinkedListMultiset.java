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
			while (itemString.compareTo(tempNodeString) > 0)
			{
				tempNode = tempNode.getNext();
				tempNodeString = (String) tempNode.get();
			}
			if (tempNode == null)
			{
				tail.setNext(newNode);
				newNode.setPrevious(tail);
				tail = newNode;
			}
			else if (tempNode == head)
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
			listCount++;
		}
		else
		{
			Node<T> tempNode = getNode(item);
			tempNode.updateCount(tempNode.getCount() + 1);
		}
	} // end of add()
	
	public int search(T item) {
		Node<T> tempNode = getNode(item);
		
		if (tempNode == null)
		{
			return 0;
		}
		else
		{
			return tempNode.getCount();
		}
	} // end of search()
	
	
	//inherited from superclass
	//public void removeOne(T item)
	
	
	//inherited from superclass
	//public void removeAll(T item)
	
	//inherited from superclass
	//public void print(PrintStream out)
	
	public Node<T> getNode(T item)
	{
		Node<T> tempNode = head;
		int start = 0;
		int end = listCount - 1;
		int m;
		String itemString = (String) item;
		String tempNodeString;
		
		if (tempNode == null)
		{
			return null;
		}
		
		//binary search
		while (!tempNode.get().equals(item))
		{
			m = (int) Math.floor((start + end)/2);
			tempNode = getNodeAt(m);
			if (start > end || tempNode == null)
			{
				return null;
			}
			tempNodeString = (String) tempNode.get();
			if (itemString.compareTo(tempNodeString) < 0)
			{
				end = m-1;
			}
			else if (itemString.compareTo(tempNodeString) > 0)
			{
				start = m+1;
			}
		}
		return tempNode;
	}
	
	public Node<T> getNodeAt(int index)
	{
		if (index >= listCount)
		{
			return null;
		}
		Node<T> tempNode = head;
		for (int i = 0; i < index; i++)
		{
			tempNode = tempNode.getNext();
		}
		return tempNode;
	}
	
} // end of class SortedLinkedListMultiset
