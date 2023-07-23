package com.moondance.nettty.utils.octtree;

import com.moondance.nettty.utils.MapOfLists;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}
