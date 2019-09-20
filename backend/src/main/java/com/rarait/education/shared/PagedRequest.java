package com.rarait.education.shared;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public final class PagedRequest {

    private static final int MED_SIZE = 20;
    private static final int LARGE_SIZE = 50;

    private PagedRequest() {
    }

    public static PageRequest get(int pageNumber) {
        return PageRequest.of(pageNumber - 1, MED_SIZE);
    }

    public static PageRequest get(int pageNumber, Sort sort) {
        return PageRequest.of(pageNumber - 1, MED_SIZE, sort);
    }

    public static PageRequest getLarge(int pageNumber) {
        return PageRequest.of(pageNumber - 1, LARGE_SIZE);
    }

    public static PageRequest getLarge(int pageNumber, Sort sort) {
        return PageRequest.of(pageNumber - 1, LARGE_SIZE, sort);
    }
}
