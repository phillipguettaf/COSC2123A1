import java.io.PrintStream;
import java.util.*;

@SuppressWarnings("unused")
public class BstMultiset<T> extends Multiset<T>
{
   public BstNode<T> root;
   protected int count;
   
   public BstMultiset2() {
      
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
      
      while(true)
      {  
         String currentString = (String)current.get();
         parent = current;
         
         if(itemString.compareTo(currentString) == 0)
         {
            current.updateCount(current.getCount() + 1);
            return;
         }
         
         if(itemString.compareTo(currentString) < 0)
         {
            current = current.getLeft();
            
            if(itemString.compareTo(currentString) == 0)
            {
               current.updateCount(current.getCount() + 1);
               return;
            }
            
            if(current == null)
            {
               parent.setLeft(newNode);
               newNode.setParent(parent);
               return;
            }
         }
         
         else
         {
            current = current.getRight();

            if(itemString.compareTo(currentString) == 0)
            {
               current.updateCount(current.getCount() + 1);
               return;
            }
            if(current == null)
            {
               parent.setRight(newNode);
               newNode.setParent(parent);
               return;
            }
         }
      }
   }
   // end of add()


   public int search(T item) {
      
      BstNode<T> current = getNode(item);
      
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
      
      BstNode<T> remove = getNode(item);
      
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
	   BstNode<T> remove = getNode(item);
	   
	   
	   if (remove == null)
	   {
		   return;
	   }
	   
	   if (remove != root)
	   {
		   BstNode<T> parent = remove.getParent();
	   }
	   
	   //if node has no children
	   if (remove.getLeft() == null && remove.getRight() == null)
	   {
		   if (remove == remove.getParent().getRight())
		   {
			   remove.getParent().setRight(null);
		   }
		   else if (remove == remove.getParent().getLeft())
		   {
			   remove.getParent().setLeft(null);
		   }
	   }
	   //if node has one child
	   else if (remove.getLeft() == null)
	   {
		   if (remove == remove.getParent().getRight())
		   {
			   remove.getParent().setRight(remove.getRight());
		   }
		   else if (remove == remove.getParent().getLeft())
		   {
			   remove.getParent().setLeft(remove.getRight());
		   }
	   }
	   else if (remove.getRight() == null)
	   {
		   if (remove == remove.getParent().getRight())
		   {
			   remove.getParent().setRight(remove.getLeft());
		   }
		   else if (remove == remove.getParent().getLeft())
		   {
			   remove.getParent().setLeft(remove.getLeft());
		   }
	   }
	   //if node has two children
	   else
	   {
		   
	   }
   }
   // end of removeAll()
   
   
   public BstNode<T> getReplacement(BstNode<T> remove)
   {
      BstNode<T> replacement = null;
      BstNode<T> parent = null;
      BstNode<T> current = remove.right;
      
      while(current != null)
      {
         parent = replacement;
         replacement = current;
         current = current.left;
      }
      
      if(replacement != remove.right)
      {
         parent.left = replacement.right;
         replacement.right = remove.right;
      }
      
      return replacement;
         
   }

   public void getPrint(BstNode<T> root)
   {
      BstNode<T> printNode = root;
      
      if(root != null)
      {
         getPrint(root.getLeft());
         System.out.println(printNode.get() + " | " + printNode.getCount());
         getPrint(root.getRight());
      }
   }
   
   public void print(PrintStream out) {
      
      getPrint(root);
   }
   
   public BstNode<T> getNode(T item)
   {
	   String itemString = (String)item;
	   BstNode<T> current = root;
	   String currentString = (String) current.get();
	   
	   while (itemString.compareTo(currentString) != 0)
	   {
		   if (itemString.compareTo(currentString) > 0 && current.getRight() != null)
		   {
			   current=current.getRight();
		   }
		   else if (itemString.compareTo(currentString) < 0 && current.getLeft() != null)
		   {
			   current=current.getLeft();
		   }
		   else
		   {
			   return null;
		   }
		   currentString = (String) current.get();
	   }
	   return current;
	   
   }
   
   
} // end of print()

// end of class BstMultiset
