package com.rarait.education.institute;

import java.io.Serializable;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public enum InstituteType implements Serializable {

    KINDER_GARDEN("Kindergarten"),
    SCHOOL("School"),
    COLLEGE("College");

    private String value;

    InstituteType(String value) {
        this.value = value;
    }

    public boolean equalsName(String otherName) {
        return value.equals(otherName);
    }

    public String toString() {
        return this.value;
    }

    public static InstituteType getEnum(String name){
        for(InstituteType v : values()){
            if( v.equalsName(name)){
                return v;
            }
        }
    return null;
    }
}
