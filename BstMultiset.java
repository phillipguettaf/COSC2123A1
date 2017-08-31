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
      
      BstNode<T> current = root;
      BstNode<T> parent = null;
      String itemString = (String)item;
      
      if (getNode(item, root) != null)
      {
    	  BstNode<T> foundNode = getNode(item, root);
    	  foundNode.updateCount(foundNode.getCount() + 1);
    	  return;
      }
      
      BstNode<T> newNode = new BstNode<T>(item);
      
      if(root == null)
      {
         root = newNode;
         count++;
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
      else if (remove.getCount() == 1)
      {
    	  removeAll(item);
      }
      else
      {
    	  remove.updateCount(remove.getCount() - 1);
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
	   else if (remove == root)
	   {
		   removeRoot();
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
		   BstNode<T> min = getMinNodeFromRight(remove.getRight());
		   remove.set(min.get());
		   remove.updateCount(min.getCount());
		   
		   removeNode(min);
	   }
	   count--;
   }
   // end of removeNode()
   
   public void removeRoot()
   {
	   BstNode<T> min = getMinNodeFromRight(root.getRight());
	   if (root.getRight() == null && root.getLeft() == null)
	   {
		   root = null;
	   }
	   else if (root.getRight() == null)
	   {
		   min = getMinNodeFromLeft(root.getLeft());
	   } 
	   root.set(min.get());
	   root.updateCount(min.getCount());
	   removeNode(min);
	   count--;
   }

   
   public void getPrint(BstNode<T> node, PrintStream out)
   {
      BstNode<T> printNode = node;
      
      if(node != null)
      {
         getPrint(node.getLeft(), out);
         System.out.println(printNode.get() + " | " + printNode.getCount());
         getPrint(node.getRight(), out);
      }
   }
   
   public void print(PrintStream out) {
      getPrint(root, out);
   }
   
   //return the instance of node containing 'item' from tree starting at 'root': return null if node doesn't exist
   public BstNode<T> getNode(T item, BstNode<T> root)
   {
	   String itemString = (String)item;
	   BstNode<T> current = root;
	   String currentString;
	   
	   while (current != null)
	   {
		   currentString = (String) current.get();
		   
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
		   
	   }
	   return null;   
   }
   
   //get the leftmost branch from a subtree
   public BstNode<T> getMinNodeFromRight(BstNode<T> node)
   {
	   if (node.getLeft() == null)
	   {
		   return node;
	   }
	   else
	   {
		   return getMinNodeFromRight(node.getLeft());
	   }
   }
   
   //get the rightmost branch from a subtree
   public BstNode<T> getMinNodeFromLeft(BstNode<T> node)
   {
	   if (node.getRight() == null)
	   {
		   return node;
	   }
	   else
	   {
		   return getMinNodeFromLeft(node.getRight());
	   }
   }
   
   
} // end of print()

// end of class BstMultiset
