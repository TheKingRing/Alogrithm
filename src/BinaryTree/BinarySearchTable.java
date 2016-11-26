package BinaryTree;

import java.util.List;

/**
 * Created by TheKingRing on 2016/9/22.
 */
public class BinarySearchTable<K extends Integer, V extends List<Integer>>
{

    private Node root;
    private class Node{
        private K key;
        private V value;
        private Node right;
        private Node left;
        private int numbers=0;

        public Node(K key, V value){
            this.key=key;
            this.value=value;
        }


        public int getNumbers() {
            return numbers;
        }

        public void setNumbers(int numbers) {
            this.numbers = numbers;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;

        }

        public void setRight(Node right) {
            this.right = right;
        }

        public K getKey() {
            return key;
        }
        public V getValue() {
            return value;
        }

    }

    public Node getRoot() {
        return root;
    }

    protected V Get(K key){
        Node node=root;
        while (node!=null){
            if (key.compareTo(node.getKey())>0) node=node.getRight();
            else if (key.compareTo(node.getKey())<0) node=node.getLeft();
            else return node.getValue();
        }
        return null;
    }
    protected  void addelement (K key, V value){
        if (root==null){
            root=new Node(key,value);
            return;
        }
        Node node=root;
        while (true){
            if (key.compareTo(node.getKey())>0 ) {
                if (node.getRight()==null){
                    node.setRight(new Node(key,value));
                    node.setNumbers(node.getNumbers()+1);
                    break;
                }
                node=node.getRight();
            } else if (key.compareTo(node.getKey())<0){
                if (node.getLeft()==null){
                    node.setLeft(new Node(key,value));
                    node.setNumbers(node.getNumbers()+1);
                    break;
                }
                node=node.getLeft();
            }
            else{
                node.getValue().add(value.get(0));
                break;
            }
        }
    }

}
