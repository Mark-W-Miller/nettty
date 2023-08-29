package com.moondance.nettty.utils.octree;

import com.moondance.nettty.utils.MapOfLists;
import lombok.Getter;
import lombok.Setter;
import org.jogamp.vecmath.Point3d;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.moondance.nettty.utils.DB.DB_OCTWALK;
import static com.moondance.nettty.utils.DB.DB_THREEBOX;
import static com.moondance.nettty.utils.Handy.out;

@Getter
@Setter
public class ThreeBox<T extends OctMember> {
    private Octree<T> octree;
    private OctAddress addressOfCenter;//address of 'center'
    private MapOfLists<OctAddress, T> boxMap = new MapOfLists<>();

    public ThreeBox(Octree<T> octree, OctAddress addressOfCenter) {
        this.octree = octree;
        this.addressOfCenter = addressOfCenter;
        walkTree();
    }

    public List<T> getDataCenter(){
        return boxMap.get(addressOfCenter);
    }
    private void walkTree() {
        out(DB_THREEBOX,"ThreeBox walkTree:" + addressOfCenter);
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                for (int z = -1; z < 2; z++) {
                    OctAddress octAddress = new OctAddress(addressOfCenter, x, y, z);
                    List<T> list = octree.lookup(octAddress);
                    if(!list.isEmpty()) {
                        boxMap.put(octAddress, list);
                    }
                }
            }
        }
    }
    @SuppressWarnings("unused")
    public void removeTheDead(){
        List<OctAddress> theDeadAddresses = new ArrayList<>();
        for(OctAddress add: getBoxMap().keySet()){
            List<T> members = getBoxMap().get(add);
            List<T> theDeadMembers = members.stream().filter(OctMember::isKill).collect(Collectors.toList());
            out(DB_OCTWALK,"theDeadMembers:" + theDeadMembers);
            members.removeAll(theDeadMembers);
            if(members.isEmpty()){
                theDeadAddresses.add(add);
            }
        }
        theDeadAddresses.forEach(add->getBoxMap().remove(add));
    }
    @Override
    public String toString() {
        return "ThreeBox @:" + addressOfCenter +
                "\n" + buildBoxMap(boxMap);
    }

    private String buildBoxMap(MapOfLists<OctAddress, T> boxMap) {
        StringBuilder b = new StringBuilder();
        for (int y = 1; y > -2; y--) {
            for (int z = 1; z > -2; z--) {
                for (int x = -1; x < 2; x++) {
                    OctAddress octAddress = new OctAddress(new Point3d(x, y, z));
                    octAddress.address.add(addressOfCenter.address);
                    String spinStr ;
                    if(boxMap.containsKey(octAddress)) {
                        List<T> data = boxMap.get(octAddress);
                        spinStr = data.stream().map(OctMember::shortHand).collect(Collectors.joining());
                    } else {
                        spinStr = "---";
                    }
                    String finalString = String.format(" %s:%-9s ", octAddress.shortHand(), spinStr);
                    b.append(finalString);
                }
                b.append('\n');
            }
            b.append('\n');
        }
        return b.toString();
    }
}
