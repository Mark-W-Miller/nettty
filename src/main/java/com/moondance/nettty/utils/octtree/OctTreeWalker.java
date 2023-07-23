package com.moondance.nettty.utils.octtree;

abstract public class OctTreeWalker<T extends Object & Comparable> {
    OctNode startNode ;
    public OctTreeWalker(OctNode<T> startNode){
        this.startNode = startNode ;
        walk(startNode);
    }

    private void walk(OctNode<T> startNode) {
        if(startNode.branchNode){
            for(OctNode<T> node:startNode.octants){
                if(node != null){
                    walk(node);
                }
            }
        } else {
            visit(startNode);
        }
    }

    abstract void visit(OctNode<T> startNode) ;
}
