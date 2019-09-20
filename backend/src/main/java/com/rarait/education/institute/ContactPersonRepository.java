package com.rarait.education.institute;

import com.rarait.education.institute.impl.ContactPerson;
import com.rarait.framework.repository.BaseJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface ContactPersonRepository extends BaseJpaRepository<ContactPerson, Integer> {

}
