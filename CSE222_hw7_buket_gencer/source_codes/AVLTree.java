public class AVLTree { // AVL Tree implementation for Stock objects. This tree is used to store the stock data in a balanced manner.
    private class Node { // Node class for AVL Tree
        Stock stock; // Stock object
        Node left, right; // Left and right children
        int height; // Height of the node

        // Constructor
        Node(Stock stock) { 
            this.stock = stock; // Initialize the stock object
            this.height = 1; // Initialize the height to 1
        }
    }


    private Node root; // Root node of the AVL Tree
    private int size; // Size of the AVL Tree

    // Constructor for AVL Tree
    public AVLTree() {
        this.size = 0;
    }

    // Insertion method for AVL Tree
    public void insert(Stock stock) {
        root = insert(root, stock); // Insert the stock object into the AVL Tree
        size++; // Increment the size of the AVL Tree after insertion
    }

    private Node insert(Node node, Stock stock) { // Helper method for insertion
        if (node == null) { // If the node is null, create a new node with the stock object
            return new Node(stock);
        }

        if (stock.getSymbol().compareTo(node.stock.getSymbol()) < 0) { // If the stock symbol is less than the current node's stock symbol, insert into the left subtree
            node.left = insert(node.left, stock); // Recursively insert into the left subtree
        } else if (stock.getSymbol().compareTo(node.stock.getSymbol()) > 0) { // If the stock symbol is greater than the current node's stock symbol, insert into the right subtree
            node.right = insert(node.right, stock); // Recursively insert into the right subtree
        } else {  // If the stock symbol is equal to the current node's stock symbol, update the stock object
            node.stock = stock; // Update the stock object
            return node; // Return the node
        } 

        node.height = 1 + Math.max(height(node.left), height(node.right)); // Update the height of the node after insertion

        int balance = getBalance(node); // Get the balance factor of the node to check for imbalance

        if (balance > 1 && stock.getSymbol().compareTo(node.left.stock.getSymbol()) < 0) // Left Left Case
            return rightRotate(node); // Perform a right rotation

        if (balance < -1 && stock.getSymbol().compareTo(node.right.stock.getSymbol()) > 0) // Right Right Case
            return leftRotate(node); // Perform a left rotation

        if (balance > 1 && stock.getSymbol().compareTo(node.left.stock.getSymbol()) > 0) { // Left Right Case
            node.left = leftRotate(node.left); // Perform a left rotation on the left child
            return rightRotate(node); // Perform a right rotation on the node
        }

        if (balance < -1 && stock.getSymbol().compareTo(node.right.stock.getSymbol()) < 0) { // Right Left Case
            node.right = rightRotate(node.right); // Perform a right rotation on the right child
            return leftRotate(node); // Perform a left rotation on the node
        }

        return node; // Return the node
    }

    // Deletion method for AVL Tree
    public void delete(String symbol) { // Delete a stock object with the given symbol
        root = delete(root, symbol); // Delete the stock object from the AVL Tree
    }

    private Node delete(Node root, String symbol) { // Helper method for deletion
        if (root == null) { // If the root is null, return null
            return root; // Return null
        }

        if (symbol.compareTo(root.stock.getSymbol()) < 0) { // If the symbol is less than the current node's stock symbol, delete from the left subtree
            root.left = delete(root.left, symbol); // Recursively delete from the left subtree
        } else if (symbol.compareTo(root.stock.getSymbol()) > 0) { // If the symbol is greater than the current node's stock symbol, delete from the right subtree
            root.right = delete(root.right, symbol); // Recursively delete from the right subtree
        } else { // If the symbol is equal to the current node's stock symbol, delete the node
            size--; // Decrement the size of the AVL Tree after deletion
            if ((root.left == null) || (root.right == null)) { // If the node has one child or no child
                Node temp = null; // Temporary node to store the child
                if (temp == root.left) { // If the left child is null
                    temp = root.right; // Set the right child as the temporary node
                } else { // If the right child is null
                    temp = root.left; // Set the left child as the temporary node
                }

                if (temp == null) { // If the temporary node is null
                    temp = root; // Set the root as the temporary node
                    root = null; // Set the root as null
                } else { // If the temporary node is not null
                    root = temp; // Set the temporary node as the root
                }
            } else { // If the node has two children
                Node temp = minValueNode(root.right); // Get the inorder successor of the node to be deleted
                root.stock = temp.stock; // Copy the data of the inorder successor to the node to be deleted
                root.right = delete(root.right, temp.stock.getSymbol()); // Delete the inorder successor
            }
        }

        if (root == null) { // If the root is null, return null
            return root; // Return null
        }

        root.height = Math.max(height(root.left), height(root.right)) + 1; // Update the height of the node after deletion

        int balance = getBalance(root); // Get the balance factor of the node to check for imbalance 

        if (balance > 1 && getBalance(root.left) >= 0) { // Left Left Case 
            return rightRotate(root); // Perform a right rotation
        }

        if (balance > 1 && getBalance(root.left) < 0) { // Left Right Case
            root.left = leftRotate(root.left); // Perform a left rotation on the left child
            return rightRotate(root); // Perform a right rotation on the node
        }

        if (balance < -1 && getBalance(root.right) <= 0) { // Right Right Case
            return leftRotate(root); // Perform a left rotation
        }

        if (balance < -1 && getBalance(root.right) > 0) { // Right Left Case
            root.right = rightRotate(root.right); // Perform a right rotation on the right child
            return leftRotate(root); // Perform a left rotation on the node
        }

        return root; // Return the root
    }

    private Node minValueNode(Node node) { // Helper method to find the inorder successor of a node to be deleted
        Node current = node; // Start from the given node 
        while (current.left != null) { // Find the leftmost leaf node
            current = current.left; // Update the current node to the left child
        }
        return current; // Return the leftmost leaf node 
    }

    private int height(Node node) { // Helper method to get the height of a node
        return (node == null) ? 0 : node.height; // Return the height of the node
    }

    private int getBalance(Node node) { // Helper method to get the balance factor of a node
        return (node == null) ? 0 : height(node.left) - height(node.right); // Return the balance factor of the node
    }

    private Node rightRotate(Node y) { // Helper method to perform a right rotation
        Node x = y.left; // Store the left child of y
        Node T2 = x.right; // Store the right child of x

        x.right = y; // Perform the rotation
        y.left = T2; // Perform the rotation

        y.height = Math.max(height(y.left), height(y.right)) + 1; // Update the height of y
        x.height = Math.max(height(x.left), height(x.right)) + 1; // Update the height of x

        return x; // Return the new root
    }

    private Node leftRotate(Node x) { // Helper method to perform a left rotation 
        Node y = x.right; // Store the right child of x
        Node T2 = y.left; // Store the left child of y

        y.left = x; // Perform the rotation
        x.right = T2; // Perform the rotation

        x.height = Math.max(height(x.left), height(x.right)) + 1; // Update the height of x
        y.height = Math.max(height(y.left), height(y.right)) + 1; // Update the height of y

        return y; // Return the new root
    }

    public Stock search(String symbol) { // Search method to find a stock object with the given symbol
        Node result = search(root, symbol); // Search for the stock object in the AVL Tree
        return (result != null) ? result.stock : null; // Return the stock object if found, otherwise return null
    }

    private Node search(Node root, String symbol) { // Helper method for searching
        if (root == null || root.stock.getSymbol().equals(symbol)) { // If the root is null or the stock symbol matches the given symbol, return the root
            return root; // Return the root
        }

        if (symbol.compareTo(root.stock.getSymbol()) < 0) { // If the symbol is less than the current node's stock symbol, search in the left subtree
            return search(root.left, symbol); // Recursively search in the left subtree
        }

        return search(root.right, symbol); // Otherwise, search in the right subtree
    }

    public void update(String oldSymbol, Stock newStock) { // Update method to update a stock object with the given symbol
        update(root, oldSymbol, newStock);
    }

    private void update(Node root, String oldSymbol, Stock newStock) { // Helper method for updating
        if (root == null) { // If the root is null,
            return; // Return
        }

        if (oldSymbol.compareTo(root.stock.getSymbol()) < 0) {  // If the old symbol is less than the current node's stock symbol, update in the left subtree
            update(root.left, oldSymbol, newStock); // Recursively update in the left subtree
        } else if (oldSymbol.compareTo(root.stock.getSymbol()) > 0) {   // If the old symbol is greater than the current node's stock symbol, update in the right subtree
            update(root.right, oldSymbol, newStock);    // Recursively update in the right subtree
        } else {
            root.stock = newStock;  // If the old symbol matches the current node's stock symbol, update the stock object
        }
    }

    public int size() { // Get the size of the AVL Tree
        return size;    // Return the size
    }
}
