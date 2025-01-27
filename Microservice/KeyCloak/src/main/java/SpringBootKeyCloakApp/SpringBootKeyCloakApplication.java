package SpringBootKeyCloakApp;

import SpringBootKeyCloakApp.entity.Employee;
import SpringBootKeyCloakApp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/employees")
public class SpringBootKeyCloakApplication {

    @Autowired
    private EmployeeService employeeService;

    //This method should be accessible by user
    @RolesAllowed("user")
    @GetMapping("/{empId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable int empId) {
        return ResponseEntity.ok(employeeService.getEmployee(empId));
    }

    //This method should be accessible by admin
    @RolesAllowed("admin")
    @GetMapping
    public ResponseEntity<List<Employee>> findAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootKeyCloakApplication.class, args);
    }
}
