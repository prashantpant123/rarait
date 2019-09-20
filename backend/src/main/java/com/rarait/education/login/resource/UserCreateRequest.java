package com.rarait.education.login.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rarait.framework.security.resource.BaseUserCreateRequest;
import com.rarait.education.login.impl.RoleName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Setter
@ToString(callSuper = true)
public class UserCreateRequest extends BaseUserCreateRequest implements Serializable{

    private List<RoleName> roleNames;

}