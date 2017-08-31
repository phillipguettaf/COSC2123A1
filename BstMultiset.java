import java.io.PrintStream;
import java.util.*;

@SuppressWarnings("unused")
public class BstMultiset<T> extends Multiset<T>
{
   public BstNode<T> root;
   protected int count;
   
   public BstMultiset() {
      
   } // end of BstMultiset()

   public void add(T item) {
      
      BstNode<T> newNode = new BstNode<T>(item);
      
      if(root == null)
      {
         root = newNode;
         count++;
         return;
      }
      
      BstNode<T> current = root;
      BstNode<T> parent = null;
      String itemString = (String)item;
      
      if (getNode(item, root) != null)
      {
    	  getNode(item, root).updateCount(getNode(item, root).getCount() + 1);
    	  return;
      }
      
      while(true)
      {  
         String currentString = (String)current.get();
         parent = current;
         
         if (itemString.compareTo(currentString) < 0)
         {
            current = current.getLeft();
            
            if(current == null)
            {
               parent.setLeft(newNode);
               newNode.setParent(parent);
               count++;
               return;
            }
         }
         
         else
         {
            current = current.getRight();

            if(current == null)
            {
               parent.setRight(newNode);
               newNode.setParent(parent);
               count++;
               return;
            }
         }
      }
   }
   // end of add()


   public int search(T item) {
      
      BstNode<T> current = getNode(item, root);
      
      if (current == null)
      {
    	  return 0;
      }
      else
      {
    	  return current.getCount();
      }
   } // end of search()


   public void removeOne(T item) {
      
      BstNode<T> remove = getNode(item, root);
      
      if (remove == null)
      {
    	  return;
      }
      else if (remove.getCount() > 1)
      {
    	  remove.updateCount(remove.getCount() - 1);
      }
      else if (remove.getCount() == 1)
      {
    	  removeAll(item);
      }
   } // end of removeOne()
   
   
   public void removeAll(T item) {
	   BstNode<T> remove = getNode(item, root);
	   removeNode(remove);
   }
   
   public void removeNode(BstNode<T> remove)
   {
	   
	   if (remove == null)
	   {
		   return;
	   }
	   
	   BstNode<T> parent = remove.getParent();
	   
	   //if node has no children
	   if (remove.getLeft() == null && remove.getRight() == null)
	   {
		   //cut off node from branch
		   if (remove == parent.getRight())
		   {
			   parent.setRight(null);
		   }
		   else if (remove == parent.getLeft())
		   {
			   parent.setLeft(null);
		   }
	   }
	   //if node has one child
	   else if (remove.getLeft() == null)
	   {
		   if (remove == parent.getRight())
		   {
			   parent.setRight(remove.getRight());
			   remove.getRight().setParent(parent);
		   }
		   else if (remove == parent.getLeft())
		   {
			   parent.setLeft(remove.getRight());
			   remove.getRight().setParent(parent);
		   }
	   }
	   else if (remove.getRight() == null)
	   {
		   if (remove == parent.getRight())
		   {
			   parent.setRight(remove.getLeft());
			   remove.getLeft().setParent(parent);
		   }
		   else if (remove == parent.getLeft())
		   {
			   parent.setLeft(remove.getLeft());
			   remove.getLeft().setParent(parent);
		   }
	   }
	   //if node has two children
	   else
	   {
		   BstNode<T> min = getMinNode(remove.getRight());
		   remove.item = min.get();
		   remove.updateCount(min.getCount());
		   
		   removeNode(min);
	   }
   }
   // end of removeAll()

   public void getPrint(BstNode<T> root, PrintStream out)
   {
      BstNode<T> printNode = root;
      
      if(root != null)
      {
         getPrint(root.getLeft(), out);
         out.println(printNode.get() + " | " + printNode.getCount());
         getPrint(root.getRight(), out);
      }
   }
   
   public void print(PrintStream out) {
      
      getPrint(root, out);
   }
   
   public BstNode<T> getNode(T item, BstNode<T> root)
   {
	   String itemString = (String)item;
	   BstNode<T> current = root;
	   String currentString = (String) current.get();
	   
	   while (current != null)
	   {
		   if (itemString.compareTo(currentString) > 0)
		   {
			   current=current.getRight();
		   }
		   else if (itemString.compareTo(currentString) < 0)
		   {
			   current=current.getLeft();
		   }
		   else if (itemString.compareTo(currentString) == 0)
		   {
			   return current;
		   }
		   currentString = (String) current.get();
	   }
	   return null;   
   }
   
   public BstNode<T> getMinNode(BstNode<T> node)
   {
	   if (node.getLeft() == null)
	   {
		   return node;
	   }
	   else
	   {
		   return getMinNode(node.getLeft());
	   }
   }
   
   
} // end of print()

// end of class BstMultiset
