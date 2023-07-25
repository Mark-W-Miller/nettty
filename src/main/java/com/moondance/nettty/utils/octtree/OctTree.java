package com.moondance.nettty.utils.octtree;

import com.moondance.nettty.model.Nett;
import com.moondance.nettty.utils.MapOfLists;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class OctTree<T> {

    OctNode<T> root ;
    int voxelSize ;
    public OctTree(int voxelSize){
        this.voxelSize = voxelSize ;
        root = new OctNode<>(new OctAddress(),this, voxelSize);
    }

    public void add(AddressedData<T> addressedData){
        root.add(addressedData);
    }
    public void add(OctAddress address,T data){
        root.add(new AddressedData<>(address,data));
    }


    /**
     *
     * @param address i,j,k of the address of the cell
     * @param radius how big of a sphere. which translates to how deep into the tree will it search, and then return all below.
     * @return a map of all objects within the radius keyed on their address.
     */
    public MapOfLists<OctAddress, T> findNeighbors(OctAddress address, double radius){
        return null;
    }

    public List<T> getAllData() {
        List<T> allData = new ArrayList<>();
        new OctTreeWalker<T>(root) {

            @Override
            public void visitLeaf(OctNode<T> node, int level) {
                for(AddressedData<T> ad: node.getData()){
                    allData.add(ad.getData());
                }
            }

            @Override
            public void visitBranch(OctNode<T> node, int level) {
            }
        };
        return allData ;
    }
}
