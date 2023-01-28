import java.io.File;
import java.util.*;


public class Hw6_p5 {

    public static void main(String[] args) throws Exception {

        // implement an adjacency list to store the relationships
        ArrayList<Node> adjList = new ArrayList<>();
        Scanner scanner = new Scanner(new File("D:\\0 Hallie Jin\\0 Hallie\\0 CS\\0 BU CS526 Data Stucture and Algorithms\\Assignments\\Assignment 6\\follows_input.txt"));
        while (scanner.hasNextLine()) {
            String relation[] = scanner.nextLine().split(",");
            String person = relation[0].trim();

            Node node = new Node(person);
            for (int i = 1; i < relation.length; i++) {
                String temp = relation[i].trim();
                node.follows.add(temp);
            }
            adjList.add(node);
        }

        //allFollows for all nodes
        for (Node n: adjList){
            allFollows(n.name, adjList);
        }

    }

    static void allFollows(String x, ArrayList<Node> adjList) {
        PriorityQueue<String> pq = new PriorityQueue<>();

        // record the direct followers
        ArrayList<String> directFollow = new ArrayList<String>();
        for (Node n : adjList) {
            if (n.name.equals(x)) {
                directFollow = n.follows;
                for (String s : n.follows) {
                    pq.add(s);
                }
                break;
            }
        }
        System.out.println(x + " directly follows " + directFollow);


        HashSet<String> pre = new HashSet<>();
        HashSet<String> indirectFollow = new HashSet<>();

        // while true
        while (!pq.isEmpty()) {
            String cur = pq.remove();

            if (pre.add(cur)) {

                for (Node n : adjList) {

                    if (n.name.equals(cur)) {

                        for (String s : n.follows) {
                            pq.add(s);

                            if (!directFollow.contains(s)) {
                                indirectFollow.add(s);
                            }
                        }
                        break;
                    }

                }

            }
        }

        System.out.println(x + " indirectly follows " + indirectFollow);
    }
}