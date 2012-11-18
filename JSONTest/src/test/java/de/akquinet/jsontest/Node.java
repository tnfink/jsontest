package de.akquinet.jsontest;

import java.util.HashSet;
import java.util.Set;

public class Node {
    private String name;
    private Set<Node> linkedNodes = new HashSet<Node>();

    public void setLinkedNodes(final Set<Node> linkedNodes) {
        this.linkedNodes = linkedNodes;
    }

    public void setName(final String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public Set<Node> getLinkedNodes() {
        return linkedNodes;
    }

    public Node(final String name) {
        this.name = name;
    }

    public void addLinkTo(Node node) {
        linkedNodes.add(node);


    }
}
