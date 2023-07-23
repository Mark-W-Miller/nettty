package com.moondance.nettty.utils.octtree;

import lombok.Getter;
import lombok.Setter;
import org.jogamp.vecmath.Point3i;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OctNode<T> {

    OctAddress center;
    OctTree tree ;
    int voxelSize ;
    boolean branchNode = false; //when true this will only hac=ve children and no data
    List<AddressedData> data = new ArrayList<>();
    OctNode<T> octants[] = new OctNode[8];
    public OctNode(OctAddress  center,OctTree<T>  tree, int voxelSize){
        this.center = center ;
        this.tree = tree ;
        this.voxelSize = voxelSize ;
    }

   public void add(AddressedData<T> addressedData) {
        if(!branchNode && data.isEmpty() || data.stream().anyMatch(ad->ad.equals(addressedData))){
            data.add(addressedData);
        } else {
            branchNode = true ;
            //voxel is full, find a new sub voxels for all
            for(AddressedData<T>  ad: data) {
                SubNode sn = SubNode.findSubNode(center, ad.getAddress());
                OctNode<T> octant = getOctants(sn, voxelSize);
                octant.add(addressedData);
            }
            data.clear();
            SubNode sn = SubNode.findSubNode(center, addressedData.getAddress());
            OctNode<T> octant = getOctants(sn, voxelSize);
            octant.add(addressedData);
        }
    }

    private OctNode<T> getOctants(SubNode sn, int voxelSize) {
        if(octants[sn.index] == null){
            int newVoxelSize = voxelSize/2;
            Point3i scaledOffset = sn.scaledOffset(newVoxelSize) ;
            Point3i newCenterPoint = (Point3i) center.address.clone();
            newCenterPoint.add(scaledOffset);
            OctAddress newCenter = new OctAddress(newCenterPoint);
            return octants[sn.index] = new OctNode<>(newCenter,tree,newVoxelSize);
        } else {
            return octants[sn.index];
        }
    }
}
