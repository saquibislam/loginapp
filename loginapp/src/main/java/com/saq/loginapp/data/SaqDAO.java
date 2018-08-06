package com.saq.loginapp.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.saq.loginapp.mvcbeans.Address;
import com.saq.loginapp.mvcbeans.Employee;

@Repository
public class SaqDAO extends NamedParameterJdbcDaoSupport {

	private static final class EmployeeRowMapper implements RowMapper<Employee> {

		@Override
		public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
			Employee emp = new Employee();
			Address address = new Address();
			address.setCity(rs.getString("CITY"));
			address.setPinCode(rs.getInt("PINCODE"));
			emp.setId(rs.getInt("EMPLOYEE_ID"));
			emp.setName(rs.getString("EMPLOYEE_NAME"));
			emp.setSalary(rs.getFloat("EMPLOYEE_SALARY"));
			emp.setAddress(address);
			return emp;
		}
	}

	public Employee getEmployeeForId(int employeeId) {
		String sql = "SELECT "
				+ "EMPLOYEE.EMPLOYEE_ID, EMPLOYEE.EMPLOYEE_NAME, EMPLOYEE.EMPLOYEE_SALARY, "
				+ "ADDRESS.CITY, ADDRESS.PINCODE FROM EMPLOYEE "
				+ "INNER JOIN ADDRESS ON EMPLOYEE.EMPLOYEE_ID=ADDRESS.ADDRESS_ID WHERE EMPLOYEE.EMPLOYEE_ID=:id";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", employeeId);
		return getNamedParameterJdbcTemplate().queryForObject(sql, paramSource, new EmployeeRowMapper());
	}
	
	public int  insertAddress(Address address) {
		String sql = "INSERT INTO ADDRESS(ADDRESS_ID, CITY, PINCODE) VALUES(:a_id, :city, :pcode)";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("a_id", address.getAddressId());
		paramSource.addValue("city", address.getCity());
		paramSource.addValue("pcode", address.getPinCode());
		return getNamedParameterJdbcTemplate().update(sql, paramSource);
	}

	public int insertEmployee(Employee emp) {
		String sql = "INSERT INTO EMPLOYEE(EMPLOYEE_ID, EMPLOYEE_NAME, EMPLOYEE_SALARY) VALUES(:id, :name, :sal)";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", emp.getId());
		paramSource.addValue("name", emp.getName());
		paramSource.addValue("sal", emp.getSalary());
		return getNamedParameterJdbcTemplate().update(sql, paramSource);
	}

	public List<Employee> getAllEmployee() {
		String sql = "SELECT "
				+ "EMPLOYEE.EMPLOYEE_ID, EMPLOYEE.EMPLOYEE_NAME, EMPLOYEE.EMPLOYEE_SALARY, "
				+ "ADDRESS.CITY, ADDRESS.PINCODE FROM EMPLOYEE "
				+ "INNER JOIN ADDRESS ON EMPLOYEE.EMPLOYEE_ID=ADDRESS.ADDRESS_ID ORDER BY EMPLOYEE.EMPLOYEE_ID ASC";
		return getNamedParameterJdbcTemplate().query(sql, new EmployeeRowMapper());
	}
	
	public boolean isValidUser(String username, String password) {
		String sql = "SELECT * FROM APPUSERS WHERE USERNAME=:uname AND PASSWORD=:pass";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("uname", username);
		paramSource.addValue("pass", password);
		return getNamedParameterJdbcTemplate().queryForRowSet(sql, paramSource).next();
	}
	
	public int getNextUniqueId() {
		String sql = "SELECT EMPLOYEE_SEQ.NEXTVAL FROM DUAL";
		return getNamedParameterJdbcTemplate().queryForObject(sql, new HashMap<String, String>(), Integer.class);
	}
	
	public void addUser(Employee emp) {
		int nSuccessCode = 0;
		int uniqueId = getNextUniqueId();
		emp.setId(uniqueId);
		emp.getAddress().setAddressId(uniqueId);
		
		String sql = "INSERT INTO APPUSERS(USER_ID, USERNAME, PASSWORD) VALUES(:uid, :uname, :pass)";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("uid", uniqueId);
		paramSource.addValue("uname", emp.getUsername());
		paramSource.addValue("pass", emp.getPassword());		
		nSuccessCode = getNamedParameterJdbcTemplate().update(sql, paramSource);
		
		if(nSuccessCode > 0) {
			nSuccessCode = insertEmployee(emp);
		}
		if(nSuccessCode > 0) {
			nSuccessCode = insertAddress(emp.getAddress());
		}
	}
}