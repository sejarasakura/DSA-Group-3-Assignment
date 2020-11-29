/*
 * To change this license startNode, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

import adt.interfaces.InterDictionary;
import adt.node.Entry;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This dictionary is to make the Dictionary arrange properly
 *
 * @author Lim sai keat
 * @param <K>
 * @param <V>
 */
public final class XOrderedDictionary<K, V> implements InterDictionary<K, V> {

    /**
     *
     */
    Comparator<? super K> compare;

    /**
     *
     */
    Node<K, V> rootNode;

    /**
     *
     */
    int dictionarySize = 0;

    /**
     *
     */
    int xModCount = 0;

    // Used to preserve iteration order
    final Node<K, V> startNode = new Node<K, V>();

    private static final Comparator<Comparable> NATURAL_ORDER_NUM = new Comparator<Comparable>() {
        @Override
        public int compare(Comparable a, Comparable b) {
            return a.compareTo(b);
        }
    };

    public XOrderedDictionary() {
        this((Comparator<? super K>) NATURAL_ORDER_NUM, true);
    }

    public XOrderedDictionary(Comparator<? super K> comparator, boolean convert) {
        this.compare = comparator != null
                ? comparator
                : (Comparator) NATURAL_ORDER_NUM;
    }

    public XOrderedDictionary(Object map) {
        this((java.util.Map) map);
    }

    public XOrderedDictionary(java.util.Map<K, V> map) {
        this();
        map.keySet().forEach((s) -> {
            add((K) s, (V) map.get(s));
        });
    }

    @Override
    public int getSize() {
        return dictionarySize;
    }

    @Override
    public V getValue(Object key) {
        Node<K, V> node = findByObject(key);
        return node != null ? node.value : null;
    }

    @Override
    public boolean contains(Object key) {
        return findByObject(key) != null;
    }

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

    @Override
    public void clear() {
        rootNode = null;
        dictionarySize = 0;
        xModCount++;

        // Clear iteration order
        Node<K, V> header = this.startNode;
        header.next = header.prev = header;
    }

    @Override
    public V remove(Object key) {
        Node<K, V> node = removeInternalByKey(key);
        return node != null ? node.value : null;
    }

    @Override
    public boolean isEmpty() {
        return this.dictionarySize == 0;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public Iterator<K> newKeyIterator() {
        return new XOrderedDictionaryIterator<K>() {
            @Override
            public K next() {
                return nextNode().key;
            }
        };
    }

    @Override
    public Iterator<V> newValueIterator() {
        return new XOrderedDictionaryIterator<V>() {
            @Override
            public V next() {
                return nextNode().value;
            }
        };
    }

    @Override
    public Iterator<Node<K, V>> newEntryIterator() {
        return new XOrderedDictionaryIterator<Node<K, V>>() {
            public Node<K, V> next() {
                return nextNode();
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Iterator i = newEntryIterator(); i.hasNext();) {
            sb.append(i.next()).append("\n");
        }
        return sb.toString();
    }

    public boolean equal(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }

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

    private void removeInternal(Node<K, V> node, boolean unlink) {
        if (unlink) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        Node<K, V> left = node.left;
        Node<K, V> right = node.right;
        Node<K, V> originalParent = node.parent;
        if (left != null && right != null) {

            /*
       * To remove a node with both left and right subtrees, move an
       * adjacent node from one of those subtrees into this node's place.
       *
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

    Node<K, V> removeInternalByKey(Object key) {
        Node<K, V> node = findByObject(key);
        if (node != null) {
            removeInternal(node, true);
        }
        return node;
    }

    private Node<K, V> findByObject(Object key) {
        try {
            return key != null ? find((K) key, false) : null;
        } catch (ClassCastException e) {
            return null;
        }
    }

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

    @Override
    public ArrList<K> getKeyList() {
        return new ArrList<K>(this.newKeyIterator());
    }

    @Override
    public ArrList<V> getValueList() {
        return new ArrList<V>(this.newValueIterator());
    }

    @Override
    public ArrList<? extends Entry<K, V>> getEntryList() {
        return new ArrList<Node<K, V>>(this.newEntryIterator());
    }

    static final class Node<K, V> implements adt.node.Entry<K, V>, java.util.Map.Entry<K, V> {

        Node<K, V> parent;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> next;
        Node<K, V> prev;
        final K key;
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
            return key + "=" + value;
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

    private abstract class XOrderedDictionaryIterator<T> implements Iterator<T> {

        Node<K, V> next = startNode.next;
        Node<K, V> lastReturned = null;
        int expectedModCount = xModCount;

        XOrderedDictionaryIterator() {
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
}
