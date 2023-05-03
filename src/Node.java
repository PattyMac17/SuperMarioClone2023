class Node<T>{
    T data;
    Node<T> prev;
    public Node(T data){
        this.data = data;
    }
}

/*
public class DS<T> {
    Node end;

    public DS() {
        end = null;
    }

    public void update() {
        Node n = end;
        while (n != null) {
            //n.data.update();
            n = n.prev;
        }
    }

    public void append(T toAppend){
        Node toAdd = new Node(toAppend);
        toAdd.prev = end;
        end = toAdd;
    }

 */


