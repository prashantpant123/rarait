package com.rarait.education.utility.properties;

import com.rarait.framework.shared.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Component
public class ApplicationPropertyServiceImpl implements ApplicationPropertyService {

    private final ApplicationPropertyConfigRepository appPropConfigRepository;

    @Autowired
    public ApplicationPropertyServiceImpl(ApplicationPropertyConfigRepository appPropConfigRepository) {
        this.appPropConfigRepository = appPropConfigRepository;
    }

    @Override
    public String getActiveValueByType(PropertyType type){
        return appPropConfigRepository.findValueByTypeAndStatus(type, Status.ACTIVE);
    }

    @Override
    public ApplicationPropertyConfig getActiveByType(PropertyType type){
        return appPropConfigRepository.findByTypeAndStatus(type, Status.ACTIVE);
    }

    @Override
    public void addProp(AppPropCreateRequest request){
        ApplicationPropertyConfig prop = new ApplicationPropertyConfig();
        prop.setType(request.getType());
        prop.setValue(request.getValue());
        prop.setDescription(request.getDescription());
        prop.setStatus(Status.ACTIVE);
        appPropConfigRepository.save(prop);
    }
}