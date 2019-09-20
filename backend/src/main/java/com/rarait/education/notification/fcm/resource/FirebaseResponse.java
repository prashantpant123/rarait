package com.rarait.education.notification.fcm.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FirebaseResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("multicast_id")
    private Object multicastId;

    @JsonProperty("success")
    private Integer success;

    @JsonProperty("failure")
    private Integer failure;

    @JsonProperty("canonical_ids")
    private Integer canonicalIds;

    @JsonProperty("results")
    private List<MessageIdResponse> results;

    public FirebaseResponse() {
    }

    @Override
    public String toString() {
        return "FirebaseResponse [multicastId=" + multicastId + ", success=" + success + ", failure=" + failure + "]";
    }
}