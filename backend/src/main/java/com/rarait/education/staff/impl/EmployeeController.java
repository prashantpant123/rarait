package com.rarait.education.staff.impl;

import com.rarait.education.shared.PagedResponse;
import com.rarait.education.shared.route.InstituteRoute;
import com.rarait.education.staff.EmployeeService;
import com.rarait.education.staff.resource.*;
import com.rarait.education.utility.nationality.NationalityResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(InstituteRoute.EMPLOYEE)
    public PagedResponse<EmployeeListResponse> getAllEmployee(
            @RequestParam("page") Integer pageNumber,
            @RequestParam(value = "employee_type", required = false) Integer typeId) {
        if (typeId == null)
            typeId = 0;
        return employeeService.findAllEmployeeWithFilter(pageNumber, typeId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(InstituteRoute.EMPLOYEE_TYPE)
    public List<EmployeeTypeDropdownResource> getAllEmployeeType() {
        return employeeService.findAllEmployeeType();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(InstituteRoute.EMPLOYEE)
    public EmployeeCreateResponse addNewEmployee(@RequestBody EmployeeCreateRequest request) {
        return employeeService.addEmployee(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(InstituteRoute.EMPLOYEE_ID)
    public EmployeeDetailResource getEmployeeDetail(@PathVariable("employee_id") Integer employeeId) {
        return employeeService.getEmployeeDetail(employeeId);
    }

}
