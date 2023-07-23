package com.moondance.nettty.utils.octtree;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
class AddressedData<T> {
    OctAddress address;
    T object ;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressedData<T> that = (AddressedData<T>) o;
        return Objects.equals(address.address, that.address.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }
}
