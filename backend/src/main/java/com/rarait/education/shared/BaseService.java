package com.rarait.education.shared;

import com.rarait.education.institute.impl.Institute;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public abstract class BaseService {

    protected final InstituteLoginSession instituteLoginSession;

    protected BaseService(InstituteLoginSession instituteLoginSession) {
        this.instituteLoginSession = instituteLoginSession;
    }

    protected Institute getInstitute(){
        return instituteLoginSession.getCurrentInstitute();
    }

}
