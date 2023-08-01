package com.moondance.nettty.utils.octree;

import javafx.geometry.Point3D;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

import static com.moondance.nettty.graphics.GraphicsUtils.p3dToP3D;

@Setter
@Getter
@AllArgsConstructor
public class AddressedData<T> {
    OctAddress octAddress;
    T data;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressedData<T> that = (AddressedData<T>) o;
        return Objects.equals(octAddress.address, that.octAddress.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(octAddress);
    }

    public Point3D addressP3D(){
        return p3dToP3D(octAddress.getAddress());
    }
    @Override
    public String toString() {
        return "AddressedData{" +
                "octAddress=" + octAddress +
                ", data=" + data +
                '}';
    }
}
