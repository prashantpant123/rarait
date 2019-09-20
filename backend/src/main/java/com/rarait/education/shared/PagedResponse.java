package com.rarait.education.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@ToString
public class PagedResponse<T> implements Serializable {

    @JsonProperty("total_data")
    private long totalData;

    @JsonProperty("total_page")
    private int totalPage;

    @JsonProperty("current_page")
    private int currentPage;

    private List<T> content;

    public PagedResponse(long totalData, int totalPage, int currentPage, List<T> content) {
        this.totalData = totalData;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.content = content;
    }
}
