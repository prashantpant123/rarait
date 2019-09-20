package com.rarait.education.account.payment;

import com.rarait.education.account.payment.impl.BillNarration;
import com.rarait.framework.repository.BaseJpaRepository;
import com.rarait.framework.shared.Status;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface BillNarrationRepository extends BaseJpaRepository<BillNarration, Integer> {

    @Query("SELECT b.display FROM BillNarration b WHERE b.institute.id=?1 AND b.status=?2 ORDER BY b.display ASC")
    List<BillNarration> findAllNarrationByInstitute(int instituteId, Status status);

    @Query("SELECT b FROM BillNarration b WHERE b.institute.id=?1 AND b.id=?2")
    BillNarration findOneByInstituteAndId(int instituteId, int narrationId);

}