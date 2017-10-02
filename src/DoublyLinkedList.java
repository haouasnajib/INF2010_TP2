public class DoublyLinkedList<AnyType>
{
    // Un noeud de la liste.
    @SuppressWarnings("hiding")
    private class Node<AnyType>
    {
        private AnyType value;
        private Node prev;
        private Node next;

        public Node(AnyType value, Node prev, Node next)
        {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

        public void setPrev(Node prev) { this.prev = prev; }

        public Node<AnyType> getPrev() { return this.prev; }

        public void setNext(Node next)
        {
            this.next = next;
        }

        public Node<AnyType> getNext()
        {
            return next;
        }

        public AnyType getValue()
        {
            return value;
        }
    }

    private int size = 0;		 // Nombre d'éléments dans la liste.
    private Node<AnyType> front; // Premier élément de la liste.
    private Node<AnyType> back;  // Dernier élément de la liste.

    // Indique si la liste est vide.
    public boolean empty()
    {
        return size == 0;
    }

    // Retourne la taille de la liste.
    public int size()
    {
        return size;
    }

    // Retourne l'élément à la fin de la liste.
    // Retourne null si la liste est vide.
    // Complexité asymptotique: O(1)
    public AnyType peekBack()
    {
        if (size == 0)
            return null; // Vérifier si la liste est vide
        else
            return this.back.value;
    }

    // Retourne l'élément au début de la liste.
    // Retourne null si la liste est vide.
    // Complexité asymptotique: O(1)
    public AnyType peekFront()
    {
        if (this.size() == 0)
            return null;
        else
            return this.front.value;
    }

    // Retourne le noeud à l'indice donné.
    // Complexité asymptotique: O(n)
    private Node<AnyType> getNodeAt(int index)
    {
        Node<AnyType> returnNode = this.front.getNext(); // Variable temporaire du Node en considération, index 0

        for (int i=1; i <= index; i++) {
            returnNode = this.front.getNext();
        }

        return returnNode;
    }

    // Retourne l'élément à l'indice donné.
    // Complexité asymptotique: O(n)
    public AnyType getAt(int index) throws IndexOutOfBoundsException // NN try/catch?
    {
        if (index > (this.size()-1) || index < 0)
            throw new IndexOutOfBoundsException(); // Cas out of bounds

        return this.getNodeAt(index).value; // Arriver au node "index" puis retourner sa valeur
    }

    // Retire l'élément à la fin de la liste.
    // Complexité asymptotique: O(1)
    public void popBack() throws EmptyListException
    {
        if (this.size() == 0)
            throw new EmptyListException(); // Cas liste vide

        this.size--; // réduction de la taille de la liste de 1

        this.back.getPrev().setNext(null); // changement du pointeur vers next de l'avant dernier élément à null

        this.back = this.back.getPrev(); // référencement de back à cet avant dernier élément
    }

    // Retire l'élément au début de la liste.
    // Complexité asymptotique: O(1)
    public void popFront() throws EmptyListException
    {
        if (this.size() == 0)
            throw new EmptyListException(); // Cas liste vide

        this.size--; // réduction de la taille de la liste de 1

        this.front.getNext().setPrev(null); // changement du pointeur vers previous du deuxième élément à null

        this.front = this.front.getNext(); // changement de front à ce deuxième élément
    }

    // Retire l'élément à l'indice donné.
    // Complexité asymptotique: O(n)
    public void removeAt(int index) throws IndexOutOfBoundsException
    {
        if (index > (this.size()-1) || index < 0)
            throw new IndexOutOfBoundsException(); // Cas out of bounds

        this.size--; // réduction de la taille de la liste de 1

        this.getNodeAt(index).getPrev().setNext(this.getNodeAt(index).getNext());
        this.getNodeAt(index).getNext().setPrev(this.getNodeAt(index).getPrev()); // I have no idea what I'm doing here lol
    }

    // Ajoute un élément à la fin de la liste.
    // Complexité asymptotique: O(1)
    public void pushBack(AnyType item)
    {
        Node<AnyType> newNode = new Node<AnyType>(item,this.back,null); // does all of this work here??

        this.size++; // augmentation de la taille de la liste de 1

        this.back.setNext(newNode);
        this.back = newNode;
    }

    // Ajoute un élément au début de la liste.
    // Complexité asymptotique: O(1)
    public void pushFront(AnyType item)
    {
        Node<AnyType> newNode = new Node<AnyType>(item,null,this.front); // same ^^^^???

        this.size++; // augmentation de la taille de la liste de 1

        this.front.setPrev(newNode);
        this.front = newNode;
    }

    // Ajoute un élément à l'indice donné.
    // Complexité asymtotique: O(n)
    public void insertAt(AnyType item, int index) throws IndexOutOfBoundsException
    {
        Node<AnyType> newNode = new Node<AnyType>(item,this.getNodeAt(index).getPrev(),this.getNodeAt(index));

        this.size++; // augmentation de la taille de la liste de 1

        this.getNodeAt(index).setPrev(newNode);
        this.getNodeAt(index).value = newNode.value;
        this.getNodeAt(index).next = newNode.next;
        this.getNodeAt(index).prev = newNode.prev;
    }
}