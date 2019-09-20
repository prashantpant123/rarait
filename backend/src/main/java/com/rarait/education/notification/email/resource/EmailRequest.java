package com.rarait.education.notification.email.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Setter
@ToString
public class EmailRequest implements Serializable {

    private String receiver;
    private String template;
    private String attachment;
    private Map<String, String> content;
}
