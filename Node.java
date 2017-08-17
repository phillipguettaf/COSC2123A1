
public class Node<T> {
	
	protected T item;
	protected Node<T> next;
	protected Node<T> previous;
	protected int count;
	
	public Node()
	{
		this.item = null;
		this.next = null;
		this.previous = null;
	}
	
	public Node(T item)
	{
		this.item = item;
		this.count = 1;
	}
	
	public Node<T> getNext()
	{
		return next;
	}
	
	public Node<T> getPrevious()
	{
		return previous;
	}
	
	public T get()
	{
		return item;
	}
	
	public void setNext(Node<T> nextNode)
	{
		this.next = nextNode;
	}
	
	public void setPrevious(Node<T> previousNode)
	{
		this.previous = previousNode;
	}
	
	public void changeElement(T item)
	{
		this.item = item;
	}
	
	public int getCount()
	{
		return count;
	}
	
	public void updateCount(int newCount)
	{
		count = newCount;
	}
}
