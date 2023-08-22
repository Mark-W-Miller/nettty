package com.moondance.nettty.utils.octree;

import lombok.SneakyThrows;

import static com.moondance.nettty.utils.DB.DB_OCTWALK;
import static com.moondance.nettty.utils.Handy.out;

abstract public class OctreeWalker<T extends OctMember> {
    OctNode<T> startNode ;
    public OctreeWalker(OctNode<T> startNode){
        this.startNode = startNode ;
        try {
            walk(startNode, 0);
        } catch(OctreeException ex){
            out(DB_OCTWALK,ex.getMessage());
        }
    }

    @SneakyThrows
    public void stop(String message){
        throw new OctreeException(message);
    }
    private void walk(OctNode<T> node,int level) {
        if(node.branchNode){
            if(visitBranch(node,level)) {
                for (OctNode<T> subNode : node.octants) {
                    if (subNode != null) {
                        walk(subNode, level + 1);
                    }
                }
            }
        } else {
            visitLeaf(node,level);
        }
    }

    abstract public void visitLeaf(OctNode<T> node, int level) ;
    public boolean visitBranch(OctNode<T> node, int level) {
        return true ;
    }

}
