package com.jaxrs.cxf.rest.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.jaxrs.cxf.rest.bo.Employee;
import com.jaxrs.cxf.rest.bo.EmployeeMapper;
import com.jaxrs.cxf.rest.bo.NewEmployee;

@Repository("employeeDAO")
public class EmployeeDAOImpl extends JdbcDaoSupport implements EmployeeDAO {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeDAOImpl.class);

	@Autowired
	private DataSource employeeDataSource;

	@PostConstruct
	private void initialize() {
		logger.info("*START****EmployeeDAOImpl.initialize()*******");
		setDataSource(employeeDataSource);
		logger.info("*END****EmployeeDAOImpl.initialize()*******");
	}

	@Override
	public Employee getEmployeeByID(Integer id) {
		Employee employee = null;
		String SQL = "select * from employee_department.employee where id = ?";
		List<Employee> employees = getJdbcTemplate().query(SQL, new Object[] { id },
				new BeanPropertyRowMapper<Employee>(Employee.class));
		if (employees != null && (!employees.isEmpty() && employees.size() == 1)) {
			employee = employees.get(0);
		}
		return employee;
	}

	@Override
	public List<Employee> getEmployees() {
		String SQL = "select * from employee_department.employee";
		return getJdbcTemplate().query(SQL, new EmployeeMapper());
	}

	@Override
	public int[] createEmployee(final List<NewEmployee> newEmployees) {
		String SQL = "insert into employee_department.employee (name, location, did) values (?, ?, ?)";
		return getJdbcTemplate().batchUpdate(SQL, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				NewEmployee newEmployee = newEmployees.get(i);
				ps.setString(1, newEmployee.getName());
				ps.setString(2, newEmployee.getLocation());
				ps.setInt(3, newEmployee.getdId());
			}
			@Override
			public int getBatchSize() {
				return newEmployees.size();
			}
		});
	}

	@Override
	public int updateEmployee(Employee employee) {
		Employee employeeByID = getEmployeeByID(employee.getId());
		if (employeeByID != null) {
			String SQL = "update employee_department.employee set name = ?, location = ?, did = ? where id = ?";
			return getJdbcTemplate().update(SQL,
					new Object[] { employee.getName(), employee.getLocation(),employee.getdId(), employee.getId() });
		} else {
			String SQL = "insert into employee_department.employee  (name, location, did) values (?, ?, ?)";
			return getJdbcTemplate().update(SQL, new Object[] { employee.getName(), employee.getLocation(), employee.getdId() });
		}
	}

	@Override
	public int deleteEmployeeByID(Integer id) {
		String SQL = "delete from employee_department.employee where id = ?";
		return getJdbcTemplate().update(SQL, new Object[] { id });
	}
}
