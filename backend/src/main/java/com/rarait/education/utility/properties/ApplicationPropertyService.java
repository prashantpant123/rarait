package com.rarait.education.utility.properties;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface ApplicationPropertyService{

    String getActiveValueByType(PropertyType type);

    ApplicationPropertyConfig getActiveByType(PropertyType type);

    void addProp(AppPropCreateRequest request);
}
