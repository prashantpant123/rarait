package com.rarait.education.login.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Setter
@ToString
public class UserStatusUpdateRequest implements Serializable {

    private long id;
    private String status;
    private String remarks;
}
