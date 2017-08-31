public class BstNode<T> {

	protected T item;
	protected BstNode<T> left;
	protected BstNode<T> right;
	protected BstNode<T> parent;
	protected int count;
   
   public BstNode()
	{
		this.item = null;
		this.left = null;
		this.right = null;
		this.parent = null;
	}
	
	public BstNode(T item)
	{
		this.item = item;
		this.count = 1;
		left = null;
		right = null;
	}

	public int getCount()
	{
		return count;
	}
	
	public void updateCount(int newCount)
	{
		count =+ newCount;
	}
	
	public BstNode<T> getLeft()
	{
		return this.left;
	}
	
	public BstNode<T> getRight()
	{
		return this.right;
	}
	
	public void setLeft(BstNode<T> left)
	{
		this.left = left;
	}
	
	public void setRight(BstNode<T> right)
	{
		this.right = right;
	}
	
	public T get()
	{
		return item;
	}
	
	public void setParent(BstNode<T> parent)
	{
		this.parent = parent;
	}
	
	public BstNode<T> getParent()
	{
		return parent;
	}
}
