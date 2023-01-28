import java.util.ArrayList;

public class Node {
    String name;
    ArrayList<String> follows = new ArrayList<>();

    public Node(String name) {

        this.name = name;
    }
}
