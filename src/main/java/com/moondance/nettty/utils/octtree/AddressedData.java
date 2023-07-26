package com.moondance.nettty.utils.octtree;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "AddressedData{" +
                "octAddress=" + octAddress +
                ", data=" + data +
                '}';
    }
}
