
public class Node {
	
	protected T item;
	protected Node next;
	protected Node previous;
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
	}
	
	public Node getNext()
	{
		return next;
	}
	
	public Node getPrevious()
	{
		return previous;
	}
	
	public T get()
	{
		return item;
	}
	
	public setNext(Node nextNode)
	{
		this.next = nextNode;
	}
	
	public setPrevious(Node previousNode)
	{
		this.previous = previousNode;
	}
	
	public changeElement(T item)
	{
		this.item = item;
	}
	
	public int getCount()
	{
		return count;
	}
	
	public updateCount(int newCount)
	{
		count = newCount;
	}
}
