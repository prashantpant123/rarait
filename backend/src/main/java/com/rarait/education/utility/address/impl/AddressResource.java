package com.rarait.education.utility.address.impl;

import lombok.*;

import java.io.Serializable;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Builder
@ToString
public class AddressResource implements Serializable {

    private int id;
    private String name;
}
