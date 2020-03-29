package ai.assignment.pkg1;
public class Node {
    
    int state;
    int action;
    int cost;
    Node Parent;
    public Node() {
        this.state=-1;
        this.Parent=null;
        this.cost=0;
        this.action=0;
    }

    public int getAction() {
        return action;
    }

    public int getCost() {
        return cost;
    }

    public int getState() {
        return state;
    }

    public Node getParent() {
        return Parent;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setParent(Node Parent) {
        this.Parent = Parent;
    }

    public void setState(int state) {
        this.state = state;
    }
     
    public Node(int state, int action, int cost, Node Parent) {
        this.state = state;
        this.action = action;
        this.cost = cost;
        this.Parent = Parent;
    }
    
}
