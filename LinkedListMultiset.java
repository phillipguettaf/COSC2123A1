import java.io.PrintStream;
import java.util.*;

public class LinkedListMultiset<T> extends Multiset<T>
{
	
	protected Node<T> head;
	protected Node<T> tail;
	
	public LinkedListMultiset() {
		head = new Node<T>();
		tail = head;
	} // end of LinkedListMultiset()
	
	
	public void add(T item) {
		if (head.get() == null)
		{
			head.changeElement(item);
			head.updateCount(1);
		}
		else
		{
			if(search(item) == 0)
			{
				Node<T> newNode = new Node<T>(item);
			}
			else
			{
				//update count on returned node (requires implementation of search)
				Node<T> newNode;
			}
			tail.setNext(newNode);
			newNode.setPrevious(tail);
			tail = newNode;
		}
	} // end of add()
	
	
	public int search(T item) {
		// Implement me!		
		
		// default return, please override when you implement this method
		return 0;
	} // end of add()
	
	
	public void removeOne(T item) {
		// Implement me!
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		// Implement me!
	} // end of removeAll()
	
	
	public void print(PrintStream out) {
		/*print in format:
		*  <element> | <number of elements in set>
		*  <diffelement> | <number of elements in set>
		*/
	} // end of print()
	
} // end of class LinkedListMultiset
