package com.moondance.nettty.utils.octtree;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jogamp.vecmath.Point3d;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OctNode<T> {

    OctAddress center;
    OctTree<T> tree ;
    int voxelSize ;
    boolean branchNode = false; //when true this will only hac=ve children and no data
    List<AddressedData<T>> data = new ArrayList<>();
    List<OctNode<T>> octants = new ArrayList<>(8);
    public OctNode(OctAddress  center,OctTree<T>  tree, int voxelSize){
        this.center = center ;
        this.tree = tree ;
        this.voxelSize = voxelSize ;
        for(int ix=0; ix < 8; ix++){
            octants.add(null);
        }
    }

   public void add(AddressedData<T> addressedData) {
        if(voxelSize <= 1 || !branchNode && data.isEmpty() || data.stream().anyMatch(ad->ad.equals(addressedData))){
            data.add(addressedData);
        } else {
            branchNode = true ;
            //voxel is full, find a new sub voxels for all
            for(AddressedData<T>  ad: data) {
                SubNode sn = SubNode.findSubNode(center, ad.getAddress());
                OctNode<T> octant = getOctant(sn, voxelSize);
                octant.add(ad);
            }
            data.clear();
            SubNode sn = SubNode.findSubNode(center, addressedData.getAddress());
            OctNode<T> octant = getOctant(sn, voxelSize);
            octant.add(addressedData);
        }
    }

    private OctNode<T> getOctant(SubNode sn, int voxelSize) {
        if(octants.get(sn.index) == null){
            int newVoxelSize = voxelSize/2;
            Point3d scaledOffset = sn.scaledOffset(newVoxelSize) ;
            Point3d newCenterPoint = (Point3d) center.address.clone();
            newCenterPoint.add(scaledOffset);
            OctAddress newCenter = new OctAddress(newCenterPoint);
            octants.set(sn.index,new OctNode<T>(newCenter,tree,newVoxelSize));
            return octants.get(sn.index);
        } else {
            return octants.get(sn.index);
        }
    }

    private String octantsStr(){
        StringBuilder builder = new StringBuilder();
        for(OctNode<T> oct: octants){
            if(oct == null){
                builder.append("-");
            } else {
                builder.append(oct.branchNode?"B":"L");
            }
        }
        return builder.toString() ;
    }
    @Override
    public String toString() {
        return "OctNode{" +
                "center=" + center +
                ", voxelSize=" + voxelSize +
                ", branchNode=" + branchNode +
                ", data=" + data.size() +
                ", octants=" + octantsStr() +
                '}';
    }
}
