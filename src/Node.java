class Node {
    int[][]state;
    int level;
    int value;
    int cost;


    public Node(int[][]state, int level, int value, int cost) {
        this.state = state;
        this.level = level;
        this.value = value;
        this.cost=cost;
    }
}