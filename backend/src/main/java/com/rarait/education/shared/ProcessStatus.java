package com.rarait.education.shared;

import java.io.Serializable;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Deprecated
public enum ProcessStatus implements Serializable {

    QUEUED,
    PROCESSING,
    DELIVERED,
    FAILED;
}
