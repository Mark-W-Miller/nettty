package com.moondance.nettty.utils.octtree;

abstract public class OctreeWalker<T> {
    OctNode<T> startNode ;
    public OctreeWalker(OctNode<T> startNode){
        this.startNode = startNode ;
        walk(startNode,0);
    }

    private void walk(OctNode<T> node,int level) {
        if(node.branchNode){
            visitBranch(node,level);
            for(OctNode<T> subNode:node.octants){
                if(subNode != null){
                    walk(subNode,level + 1);
                }
            }
        } else {
            visitLeaf(node,level);
        }
    }

    abstract public void visitLeaf(OctNode<T> node, int level) ;
    abstract public void visitBranch(OctNode<T> node, int level) ;
}
