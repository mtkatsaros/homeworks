package hw7;

import java.io.*;
import java.util.Scanner;


public class BinaryTree<E> implements Serializable{
	
	/*
	 public static void main(String[] args) {
    	Node<Integer> newNode = new Node<Integer>(1);
    	newNode.left = new Node<Integer>(2);
    	newNode.right = new Node<Integer>(3);
    	newNode.left.left = new Node<Integer>(4);
    	newNode.left.right = new Node<Integer>(5);
    	newNode.right.left = new Node<Integer>(6);
    	newNode.right.right = new Node<Integer>(7);
    	newNode.left.left.left = new Node<Integer>(8);
    	newNode.left.left.right = new Node<Integer>(9);
    	newNode.left.right.left = new Node<Integer>(10);
    	newNode.left.right.right = new Node<Integer>(11);
    	newNode.right.left.left = new Node<Integer>(12);
    	newNode.right.left.right = new Node<Integer>(13);
    	newNode.right.right.left = new Node<Integer>(14);
    	newNode.right.right.right = new Node<Integer>(15);
    	
    	BinaryTree<Integer> tree = new BinaryTree<>(newNode);
    	
    	System.out.print(tree.toString());
    	
    }
	*/
	
	

	protected static class Node<E> implements Serializable {
        protected E data;
        protected Node<E> left;
        protected Node<E> right;
       
        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }
        
        public String toString() {
            return data.toString();
        }
    }
	
	
    
    protected Node<E> root;
   
    public BinaryTree() {
        root = null;
    }
   
    protected BinaryTree(Node<E> root) {
        this.root = root;
    }
    
   

    public BinaryTree(E data, BinaryTree<E> leftTree,
                      BinaryTree<E> rightTree) {
        root = new Node<>(data);
        if (leftTree != null) {
            root.left = leftTree.root;
        } else {
            root.left = null;
        }
        if (rightTree != null) {
            root.right = rightTree.root;
        } else {
            root.right = null;
        }
    }

    public BinaryTree<E> getLeftSubtree() {
        if (root != null && root.left != null) {
            return new BinaryTree<>(root.left);
        } else {
            return null;
        }
    }
   
    public BinaryTree<E> getRightSubtree() {
        if (root != null && root.right != null) {
            return new BinaryTree<>(root.right);
        } else {
            return null;
        }
    }
   
    public boolean isLeaf() {
        return (root.left == null && root.right == null);
    }
   
    private void toString(Node<E> node, int depth, StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            //sb.append(" ");
        }
        if (node == null) {
            //sb.append("null\n");
        } else {
            sb.append(node.toString());
            sb.append(", ");
            toString(node.left, depth + 1, sb);
            toString(node.right, depth + 1, sb);
        }
    }
    
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	toString(root,1,sb);
    	return sb.toString();
    }

    public static BinaryTree<String> readBinaryTree(Scanner scan) {
        String data = scan.nextLine().trim();
        if (data.equals("null")) {
            return null;
        } else {
            BinaryTree<String> leftTree = readBinaryTree(scan);
            BinaryTree<String> rightTree = readBinaryTree(scan);
            return new BinaryTree<>(data, leftTree, rightTree);
        }
    }
    public Object getData() {
        return root.data;
    }
}
