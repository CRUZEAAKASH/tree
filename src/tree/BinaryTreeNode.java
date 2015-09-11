package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import jdk.nashorn.internal.parser.TokenKind;

class BinaryTreeNode {
    public int data;
    public BinaryTreeNode left;
    public BinaryTreeNode right;
    
    public BinaryTreeNode(int data)
    {
        this.data=data;
        left=null;
        right=null;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }
}

class  BinaryTree
{
    private static BinaryTreeNode root;
    
    public BinaryTreeNode insertBinaryTreeLevelOrder(int data)
    {
        if(root==null)
        {
            root=new BinaryTreeNode(data);
        }else
        {
            Queue<BinaryTreeNode> q = new LinkedList<>();
            q.offer(root);
            while(!q.isEmpty())
            {
                BinaryTreeNode temp = q.poll();
                if(temp!=null)
                {
                    if(temp.getLeft()!=null)
                    {
                        q.offer(temp.getLeft());
                    }else{
                        temp.left = new BinaryTreeNode(data);
                        return root;
                    }
                    if(temp.getRight()!=null)
                    {
                        q.offer(temp.getRight());
                    }else{
                        temp.right = new BinaryTreeNode(data);
                        return root;
                    }
                }
            }
        }
        return root;
    }
    
    public void PreorderRecursion(BinaryTreeNode root)
    {
        if(root!=null)
        {
            System.out.print(root.data);
            PreorderRecursion(root.left);
            PreorderRecursion(root.right);
        }
    }
    
    public void preOrderIterative(BinaryTreeNode root)
    {
        //ArrayList<Integer> res = new ArrayList<Integer>();
        if(root==null)
        {
            return;
        }
        Stack<BinaryTreeNode> s = new Stack<BinaryTreeNode>();
        s.push(root);
        while(!s.empty())
        {
            BinaryTreeNode temp = s.pop();
            //res.add(temp.data);
            System.out.print(temp.data);
            if(temp.right!=null)
            {
                s.push(temp.right);
            }if(temp.left!=null)
            {
                s.push(temp.left);
            }
        }
    }
    
    public void InorderRecursion(BinaryTreeNode root)
    {
        if(root!=null)
        {
            InorderRecursion(root.left);
            System.out.print(root.data);
            InorderRecursion(root.right);
        }
    }
    
    public void InorderIterative(BinaryTreeNode root)
    {
        if(root==null)
        {
            return;
        }else
        {
            Stack<BinaryTreeNode> s = new Stack<BinaryTreeNode>();
            BinaryTreeNode current =root;
            boolean done = false;
            while(!done)
            {
                if(current!=null)
                {
                    s.push(current);
                    current = current.left;
                }else
                {
                    if(s.isEmpty())
                    {
                        done = true;
                    }else
                    {
                        BinaryTreeNode temp = s.pop();
                        System.out.print(temp.data);
                        current = temp.right;
                    }
                }
            }
        }
    }
    
    public int DepthUsingQueue(BinaryTreeNode root)
    {
        if(root==null)
        {
            return 0;
        }
        Queue<BinaryTreeNode> q = new LinkedList<BinaryTreeNode>();
        int count =1;
        q.offer(root);
        q.offer(null);
        while (!q.isEmpty()) 
        {            
            BinaryTreeNode current = q.poll();
            if(current!=null)
            {
                if(current.left==null && current.right==null && q.isEmpty())
                    return count;
                if(current.left!=null)
                    q.offer(current.left);
                if(current.right!=null)
                    q.offer(current.right);
            }else
            {
                if(!q.isEmpty())
                {
                    count++;
                    q.offer(null);
                }
            }
        }
        return count;
    }
    
    public int DepthUsingRecursion(BinaryTreeNode root)
    {
        if(root==null)
            return 0;
        int leftDepth = DepthUsingRecursion(root.left);
        int RightDepth = DepthUsingRecursion(root.right);
        return (leftDepth>RightDepth?leftDepth+1:RightDepth+1);
    }
    
    public static void main(String[] args) throws IOException {
        System.out.println("Enter the no. of items");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N =Integer.parseInt(br.readLine());
        BinaryTree tree = new BinaryTree();
        for(int i=0; i<N;i++)
        {
            tree.insertBinaryTreeLevelOrder(Integer.parseInt(br.readLine()));
        }
        System.out.println("preorder using recursion=");
        tree.PreorderRecursion(root);
        System.out.println("\npreorder using iterative");
        tree.preOrderIterative(root);
        System.out.println("\nInorder using recursion");
        tree.InorderRecursion(root);
        System.out.println("\nInorder using iterative");
        tree.InorderIterative(root);
        System.out.println("depth of tree=");
        System.out.println(tree.DepthUsingQueue(root));
        System.out.println("depth of tree=");
        System.out.println(tree.DepthUsingRecursion(root));
    }
}
