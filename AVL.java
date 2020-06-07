class AVLNode
{
	int data,height;
	AVLNode left;
	AVLNode right;	
	
	public AVLNode(int data)
	{
		this.data=data;
		this.height=0;
		this.left=null;
		this.right=null;
	}
}

class AVLOperations
{
	public int getHeight(AVLNode node)
	{
		return (node==null)?-1:node.height;
	}
	public int getBalance(AVLNode node)
	{
		return this.getHeight(node.left)-this.getHeight(node.right);
	}
	public AVLNode rotateRight(AVLNode node)	
	{
		AVLNode nodeToBeRotated=node.left;
		AVLNode temp=node.left.right;
		node.left.right=node;
		node.left=temp;
		//update height -->
		node.height=Math.max(this.getHeight(node.left), this.getHeight(node.right))+1;
		nodeToBeRotated.height=Math.max(this.getHeight(nodeToBeRotated.left), this.getHeight(nodeToBeRotated.right))+1;
		return nodeToBeRotated;
	}
	public AVLNode rotateLeft(AVLNode node)
	{
		AVLNode nodeToBeRotated=node.right;
		AVLNode temp=nodeToBeRotated.left;
		nodeToBeRotated.left=node;
		node.right=temp;
		node.height=Math.max(this.getHeight(node.left), this.getHeight(node.right))+1;
		nodeToBeRotated.height=Math.max(this.getHeight(nodeToBeRotated.left), this.getHeight(nodeToBeRotated.right))+1;
		return nodeToBeRotated;
		
	}
	public AVLNode build(AVLNode root,int key)
	{
		if(root==null)
		{
			return new AVLNode(key);
		}
		if(key<root.data)
		{
			root.left=this.build(root.left, key);
		}
		else if(key>root.data)
		{
			root.right=this.build(root.right, key);
		}
		root.height=Math.max(this.getHeight(root.left),this.getHeight(root.right))+1;
		int balance=this.getBalance(root);
		if(balance>1 && key<root.left.data)
		{
			//LL ROTATION
			return this.rotateRight(root);
		}
		else if(balance<-1 && key>root.right.data)
		{
			//RR rotation
			return this.rotateLeft(root);
		}
		else if(balance<-1 && key<root.right.data)
		{
			//RL rotation
			root.right=this.rotateRight(root.right);
			return this.rotateLeft(root);
		}
		else if(balance>1 && key>root.left.data )
		{
			//LR rotation
			root.left=this.rotateLeft(root.left);
			return this.rotateRight(root);
		}
			
		return root;
	}
	public void preOrder(AVLNode root)
	{
		if(root==null)
		{
			return;
		}
		System.out.println("DATA:"+root.data+"\t height:"+root.height+"\t balance:"+this.getBalance(root));
		preOrder(root.left);
		preOrder(root.right);
	}
}


public class AVL {
	public static void main(String args[])
	{
		AVLOperations avlOp=new AVLOperations();
		AVLNode root=null;
		root=avlOp.build(root, 18);
		root=avlOp.build(root, 12);
		root=avlOp.build(root, 14);
		root=avlOp.build(root, 16);
		root=avlOp.build(root, 20);
		root=avlOp.build(root, 22);		
		root=avlOp.build(root, 24);
		root=avlOp.build(root, 1);
		
		avlOp.preOrder(root);
		
		
	}
}
