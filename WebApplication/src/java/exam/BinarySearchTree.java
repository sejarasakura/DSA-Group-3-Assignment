/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam;

/**
 * Local project import
 */
import adt.XArrayList;
import adt.XTreeDictionary;
import main.*;
import cilent.*;
import cilent.filter.*;
import cilent.pages.*;
import cilent.servlet.*;
import entity.*;
import adt.node.*;
import adt.interfaces.*;
import csv.converter.*;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import xenum.*;

/**
 *
 * @author ITSUKA KOTORI
 */
public class BinarySearchTree<K, V> implements InterDictionary<K, V>, Cloneable, java.io.Serializable {

    /**
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     *
     * =========================== Field declaration ===========================
     *
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */
    /**
     * Comparator
     */
    Comparator<? super K> compare;

    /**
     * The first/root node
     */
    Node<K, V> rootNode;

    /**
     * Sizes of the index
     */
    int dictionarySize = 0;

    /**
     * Modify count
     */
    int xModCount = 0;

    /**
     * Used to preserve iteration order
     */
    final Node<K, V> startNode = new Node<K, V>();

    /**
     * To prevent nested comparator
     */
    private static final Comparator<Comparable> NATURAL_ORDER_NUM = new Comparator<Comparable>() {
        @Override
        public int compare(Comparable a, Comparable b) {
            return a.compareTo(b);
        }
    };

    /**
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     *
     * ============================== Constructor ==============================
     *
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */
    /**
     * No argument constructor
     */
    public BinarySearchTree() {
        this((Comparator<? super K>) NATURAL_ORDER_NUM, true);
    }

    /**
     * Argument constructor
     *
     * @param comparator
     * @param convert
     */
    public BinarySearchTree(Comparator<? super K> comparator, boolean convert) {
        this.compare = comparator != null
                ? comparator
                : (Comparator) NATURAL_ORDER_NUM;
    }

    /**
     * Convert object to map
     *
     * @param map
     */
    public BinarySearchTree(Object map) {
        this((java.util.Map) map);
    }

    /**
     * Convert java.util.map to my own adt type
     *
     * @param map
     */
    public BinarySearchTree(java.util.Map<K, V> map) {
        this();
        map.keySet().forEach((s) -> {
            add((K) s, (V) map.get(s));
        });
    }

    /**
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     *
     * ======================= InterList Override Method =======================
     *
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */
    /**
     * Get the sizes of the dictionary
     *
     * @return
     */
    @Override
    public int getSize() {
        return dictionarySize;
    }

    /**
     * Get the value by using key
     *
     * @param key K
     * @return
     */
    @Override
    public V getValue(Object key) {
        Node<K, V> node = findByObject(key);
        return node != null ? node.value : null;
    }

    /**
     * see in the map contain the particular value or not
     *
     * @param key K
     * @return
     */
    @Override
    public boolean contains(Object key) {
        return findByObject(key) != null;
    }

    /**
     * add new value and the key<br>
     * if key excite change value
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public V add(K key, V value) {
        if (key == null) {
            throw new NullPointerException("key == null");
        }
        Node<K, V> created = find(key, true);
        V result = created.value;
        created.value = value;
        return result;
    }

    /**
     * Clear the record in the map
     */
    @Override
    public void clear() {
        rootNode = null;
        dictionarySize = 0;
        xModCount++;

        // Clear iteration order
        Node<K, V> header = this.startNode;
        header.next = header.prev = header;
    }

    /**
     * Remove the value by using the key
     *
     * @param key
     * @return
     */
    @Override
    public V remove(Object key) {
        Node<K, V> node = removeInternalByKey(key);
        return node != null ? node.value : null;
    }

    /**
     * Track the dictionary is empty or not
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return this.dictionarySize == 0;
    }

    /**
     * check the dictionary is full or not <br>
     * for linked tree dictionary is full or not
     *
     * @return
     */
    @Override
    public boolean isFull() {
        return false;
    }

    /**
     * return the key iterator group
     *
     * @return
     */
    @Override
    public Iterator<K> newKeyIterator() {
        return new XTreeDictionaryIterator<K>() {
            @Override
            public K next() {
                return nextNode().key;
            }
        };
    }

    /**
     * return the value iterator group
     *
     * @return
     */
    @Override
    public Iterator<V> newValueIterator() {
        return new XTreeDictionaryIterator<V>() {
            @Override
            public V next() {
                return nextNode().value;
            }
        };
    }

    /**
     * return the entry iterator value
     *
     * @return
     */
    @Override
    public Iterator<Node<K, V>> newEntryIterator() {
        return new XTreeDictionaryIterator<Node<K, V>>() {
            @Override
            public Node<K, V> next() {
                return nextNode();
            }
        };
    }

    /**
     * Convert the linked ordered dictionary string output
     *
     * @return
     */
    @Override
    public String toString() {
        return this.toString("\n");
    }

    /**
     * Convert the linked ordered dictionary to HTML output
     *
     * @return
     */
    public String toHtml() {
        return this.toString("<br />");
    }

    /**
     * Return true if both is same
     *
     * @param a
     * @param b
     * @return
     */
    public boolean equal(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }

    /**
     * Get the key list
     *
     * @return
     */
    @Override
    public InterList<K> getKeyList() {
        return new XArrayList<K>(this.newKeyIterator());
    }

    /**
     * get the values list
     *
     * @return
     */
    @Override
    public InterList<V> getValueList() {
        return new XArrayList<V>(this.newValueIterator());
    }

    /**
     * get the entry list
     *
     * @return
     */
    @Override
    public InterList<? extends Entry<K, V>> getEntryList() {
        return new XArrayList<Node<K, V>>(this.newEntryIterator());
    }

    /**
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     *
     * ============================= private Method ============================
     *
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */
    /**
     * find the key <br>
     * Check create to true will create new entry
     *
     * @param key
     * @param create
     * @return
     */
    private Node<K, V> find(K key, boolean create) {
        Comparator<? super K> comparator = this.compare;
        Node<K, V> nearest = rootNode;
        int comparison = 0;

        if (nearest != null) {
            // Micro-optimization: avoid polymorphic calls to Comparator.compare().
            @SuppressWarnings("unchecked") // Throws a ClassCastException below if there's trouble.
            Comparable<Object> comparableKey = (comparator == NATURAL_ORDER_NUM)
                    ? (Comparable<Object>) key
                    : null;

            while (true) {
                comparison = (comparableKey != null)
                        ? comparableKey.compareTo(nearest.key)
                        : comparator.compare(key, nearest.key);

                // We found the requested key.
                if (comparison == 0) {
                    return nearest;
                }

                // If it exists, the key is in a subtree. Go deeper.
                Node<K, V> child = (comparison < 0) ? nearest.left : nearest.right;
                if (child == null) {
                    break;
                }

                nearest = child;
            }
        }

        // The key doesn't exist in this tree.
        if (!create) {
            return null;
        }

        // Create the node and add it to the tree or the table.
        Node<K, V> header = this.startNode;
        Node<K, V> created;
        if (nearest == null) {
            // Check that the value is comparable if we didn't do any comparisons.
            if (comparator == NATURAL_ORDER_NUM && !(key instanceof Comparable)) {
                throw new ClassCastException(key.getClass().getName() + " is not Comparable");
            }
            created = new Node<K, V>(nearest, key, header, header.prev);
            rootNode = created;
        } else {
            created = new Node<K, V>(nearest, key, header, header.prev);
            if (comparison < 0) { // nearest.key is higher
                nearest.left = created;
            } else { // comparison > 0, nearest.key is lower
                nearest.right = created;
            }
            rebalance(nearest, true);
        }
        dictionarySize++;
        xModCount++;

        return created;
    }

    /**
     * Remove the node<br>
     * Check unlink to true will stop linking to the value
     *
     * @param node
     * @param unlink
     */
    private void removeInternal(Node<K, V> node, boolean unlink) {
        if (unlink) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        Node<K, V> left = node.left;
        Node<K, V> right = node.right;
        Node<K, V> originalParent = node.parent;
        if (left != null && right != null) {

            /**
             * Removing the adjacent node may change this node's subtrees. This
             * node may no longer have two subtrees once the adjacent node is
             * gone!
             */
            Node<K, V> adjacent = (left.height > right.height) ? left.last() : right.first();
            removeInternal(adjacent, false); // takes care of rebalance and dictionarySize--

            int leftHeight = 0;
            left = node.left;
            if (left != null) {
                leftHeight = left.height;
                adjacent.left = left;
                left.parent = adjacent;
                node.left = null;
            }

            int rightHeight = 0;
            right = node.right;
            if (right != null) {
                rightHeight = right.height;
                adjacent.right = right;
                right.parent = adjacent;
                node.right = null;
            }

            adjacent.height = Math.max(leftHeight, rightHeight) + 1;
            replaceInParent(node, adjacent);
            return;
        } else if (left != null) {
            replaceInParent(node, left);
            node.left = null;
        } else if (right != null) {
            replaceInParent(node, right);
            node.right = null;
        } else {
            replaceInParent(node, null);
        }

        rebalance(originalParent, false);
        dictionarySize--;
        xModCount++;
    }

    /**
     * Remove internal by the key value
     *
     * @param key
     * @return
     */
    Node<K, V> removeInternalByKey(Object key) {
        Node<K, V> node = findByObject(key);
        if (node != null) {
            removeInternal(node, true);
        }
        return node;
    }

    /**
     * Find the node by using object key
     *
     * @param key
     * @return
     */
    private Node<K, V> findByObject(Object key) {
        try {
            return key != null ? find((K) key, false) : null;
        } catch (ClassCastException e) {
            return null;
        }
    }

    /**
     * replace in parent
     *
     * @param node
     * @param replacement
     */
    private void replaceInParent(Node<K, V> node, Node<K, V> replacement) {
        Node<K, V> parent = node.parent;
        node.parent = null;
        if (replacement != null) {
            replacement.parent = parent;
        }

        if (parent != null) {
            if (parent.left == node) {
                parent.left = replacement;
            } else {
                assert (parent.right == node);
                parent.right = replacement;
            }
        } else {
            rootNode = replacement;
        }
    }

    /**
     * Rebalances the tree by making AVL rotation
     *
     * @param unbalanced
     * @param insert -> true unbalance by insert
     */
    private void rebalance(Node<K, V> unbalanced, boolean insert) {
        OUTER:
        for (Node<K, V> node = unbalanced; node != null; node = node.parent) {
            Node<K, V> left = node.left;
            Node<K, V> right = node.right;
            int leftHeight = left != null ? left.height : 0;
            int rightHeight = right != null ? right.height : 0;
            int delta = leftHeight - rightHeight;
            switch (delta) {
                case -2:
                    Node<K, V> rightLeft = right.left;
                    Node<K, V> rightRight = right.right;
                    int rightRightHeight = rightRight != null ? rightRight.height : 0;
                    int rightLeftHeight = rightLeft != null ? rightLeft.height : 0;
                    int rightDelta = rightLeftHeight - rightRightHeight;
                    if (rightDelta == -1 || (rightDelta == 0 && !insert)) {
                        rotateLeft(node); // AVL right right
                    } else {
                        assert (rightDelta == 1);
                        rotateRight(right); // AVL right left
                        rotateLeft(node);
                    }
                    if (insert) {
                        break OUTER; // no further rotations will be necessary
                    }
                    break;
                case 2:
                    Node<K, V> leftLeft = left.left;
                    Node<K, V> leftRight = left.right;
                    int leftRightHeight = leftRight != null ? leftRight.height : 0;
                    int leftLeftHeight = leftLeft != null ? leftLeft.height : 0;
                    int leftDelta = leftLeftHeight - leftRightHeight;
                    if (leftDelta == 1 || (leftDelta == 0 && !insert)) {
                        rotateRight(node); // AVL left left
                    } else {
                        assert (leftDelta == -1);
                        rotateLeft(left); // AVL left right
                        rotateRight(node);
                    }
                    if (insert) {
                        break OUTER; // no further rotations will be necessary
                    }
                    break;
                case 0:
                    node.height = leftHeight + 1; // leftHeight == rightHeight
                    if (insert) {
                        break OUTER; // the insert caused balance, so rebalancing is done!
                    }
                    break;
                default:
                    assert (delta == -1 || delta == 1);
                    node.height = Math.max(leftHeight, rightHeight) + 1;
                    if (!insert) {
                        break OUTER; // the height hasn't changed, so rebalancing is done!
                    }
                    break;
            }
        }
    }

    /**
     * Rotates the subtree so that its rootNode's right child is the new
     * rootNode.
     */
    private void rotateLeft(Node<K, V> root) {
        Node<K, V> left = root.left;
        Node<K, V> pivot = root.right;
        Node<K, V> pivotLeft = pivot.left;
        Node<K, V> pivotRight = pivot.right;

        // move the pivot's left child to the rootNode's right
        root.right = pivotLeft;
        if (pivotLeft != null) {
            pivotLeft.parent = root;
        }

        replaceInParent(root, pivot);

        // move the rootNode to the pivot's left
        pivot.left = root;
        root.parent = pivot;

        // fix heights
        root.height = Math.max(left != null ? left.height : 0,
                pivotLeft != null ? pivotLeft.height : 0) + 1;
        pivot.height = Math.max(root.height,
                pivotRight != null ? pivotRight.height : 0) + 1;
    }

    /**
     * Rotates the subtree so that its rootNode's left child is the new
     * rootNode.
     */
    private void rotateRight(Node<K, V> root) {
        Node<K, V> pivot = root.left;
        Node<K, V> right = root.right;
        Node<K, V> pivotLeft = pivot.left;
        Node<K, V> pivotRight = pivot.right;

        // move the pivot's right child to the rootNode's left
        root.left = pivotRight;
        if (pivotRight != null) {
            pivotRight.parent = root;
        }

        replaceInParent(root, pivot);

        // move the rootNode to the pivot's right
        pivot.right = root;
        root.parent = pivot;

        // fixup heights
        root.height = Math.max(right != null ? right.height : 0,
                pivotRight != null ? pivotRight.height : 0) + 1;
        pivot.height = Math.max(root.height,
                pivotLeft != null ? pivotLeft.height : 0) + 1;
    }

    private String toString(String seperator) {
        StringBuilder sb = new StringBuilder();
        for (Iterator i = newEntryIterator(); i.hasNext();) {
            sb.append(i.next()).append(seperator);
        }
        return sb.toString();
    }

    /**
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     *
     * ============================= Private Class =============================
     *
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */
    static final class Node<K, V> implements adt.node.Entry<K, V>, java.util.Map.Entry<K, V> {

        Node<K, V> parent;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> next;
        Node<K, V> prev;
        K key;
        V value;
        int height;

        /**
         * Create the startNode entry
         */
        Node() {
            key = null;
            next = prev = this;
        }

        /**
         * Create a regular entry
         */
        Node(Node<K, V> parent, K key, Node<K, V> next, Node<K, V> prev) {
            this.parent = parent;
            this.key = key;
            this.height = 1;
            this.next = next;
            this.prev = prev;
            prev.next = this;
            next.prev = this;
        }

        /**
         * Create a regular entry
         */
        Node(K key) {
            this.key = key;
            this.height = 1;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        @Override
        @SuppressWarnings("rawtypes")
        public boolean equals(Object o) {
            if (o instanceof adt.MapConverter.Entry) {
                adt.MapConverter.Entry other = (adt.MapConverter.Entry) o;
                return (key == null ? other.getKey() == null : key.equals(other.getKey()))
                        && (value == null ? other.getValue() == null : value.equals(other.getValue()));
            }
            return false;
        }

        @Override
        public int hashCode() {
            return (key == null ? 0 : key.hashCode())
                    ^ (value == null ? 0 : value.hashCode());
        }

        @Override
        public String toString() {
            return "[" + key + "] = " + value;
        }

        /**
         * Returns the first node in this subtree.
         */
        public Node<K, V> first() {
            Node<K, V> node = this;
            Node<K, V> child = node.left;
            while (child != null) {
                node = child;
                child = node.left;
            }
            return node;
        }

        /**
         * Returns the last node in this subtree.
         */
        public Node<K, V> last() {
            Node<K, V> node = this;
            Node<K, V> child = node.right;
            while (child != null) {
                node = child;
                child = node.right;
            }
            return node;
        }
    }

    private abstract class XTreeDictionaryIterator<T> implements Iterator<T> {

        Node<K, V> next = startNode.next;
        Node<K, V> lastReturned = null;
        int expectedModCount = xModCount;

        XTreeDictionaryIterator() {

        }

        @Override
        public final boolean hasNext() {
            return next != startNode;
        }

        final Node<K, V> nextNode() {
            Node<K, V> e = next;
            if (e == startNode) {
                throw new NoSuchElementException();
            }
            if (xModCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            next = e.next;
            return lastReturned = e;
        }

        @Override
        public final void remove() {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            removeInternal(lastReturned, true);
            lastReturned = null;
            expectedModCount = xModCount;
        }
    }

    /* Given a binary tree, print its nodes according to the
      "bottom-up" postorder traversal. */
    void printPostorder(Node node) {
        if (node == null) {
            return;
        }

        // first recur on left subtree
        printPostorder(node.left);

        // then recur on right subtree
        printPostorder(node.right);

        // now deal with the node
        System.out.print(node.key + ", ");
    }

    /* Given a binary tree, print its nodes in inorder*/
    void printInorder(Node node) {
        if (node == null) {
            return;
        }

        /* first recur on left child */
        printInorder(node.left);

        /* then print the data of node */
        System.out.print(node.key + ", ");

        /* now recur on right child */
        printInorder(node.right);
    }

    /* Given a binary tree, print its nodes in preorder*/
    void printPreorder(Node node) {
        if (node == null) {
            return;
        }

        /* first print data of node */
        System.out.print(node.key + ", ");

        /* then recur on left sutree */
        printPreorder(node.left);

        /* now recur on right subtree */
        printPreorder(node.right);
    }

    // Wrappers over above recursive functions
    void printPostorder() {
        System.out.print("Post Order -> ");
        printPostorder(rootNode);
        System.out.println();
    }

    void printInorder() {
        System.out.print("In Order -> ");
        printInorder(rootNode);
        System.out.println();
    }

    void printPreorder() {
        System.out.print("Pre Order -> ");
        printPreorder(rootNode);
        System.out.println();
    }

    public static void main(String[] args) {
        BinarySearchTree t = new BinarySearchTree();
        //                           rootNode
        //                       /                  \
        //                  /                          \
        //             Left                             Right
        //          /        \                        /       \
        //    Left.Left    Left.Right          Left.Left    Left.Right
        //               /            \
        //     Left.Right.Left    Left.Right.Right
        t.rootNode = new Node(25);
        t.rootNode.left = new Node(15);
        t.rootNode.left.left = new Node(10);
        t.rootNode.left.right = new Node(22);
        t.rootNode.left.right.left = new Node(18);
        t.rootNode.left.right.right = new Node(24);
        t.rootNode.right = new Node(50);
        t.rootNode.right.right = new Node(70);

        t.printInorder();
        t.printPreorder();
        t.printPostorder();

        System.out.println(t.toString());
    }
}
