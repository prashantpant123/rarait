package com.rarait.education.staff;

import com.rarait.education.document.DocumentType;
import com.rarait.education.shared.PagedResponse;
import com.rarait.education.staff.resource.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface EmployeeService {

    List<EmployeeTypeDropdownResource> findAllEmployeeType();

    EmployeeCreateResponse addEmployee(EmployeeCreateRequest request);

    PagedResponse<EmployeeListResponse> findAllEmployeeWithFilter(int pageNumber, int typeId);

    EmployeeDetailResource getEmployeeDetail(long employeeId);

    void uploadDocument(MultipartFile file, DocumentType type, Long employeeId);
}
