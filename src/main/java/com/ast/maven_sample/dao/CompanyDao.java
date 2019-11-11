package com.ast.maven_sample.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.ast.maven_sample.pojo.CompanyPojo;

@Repository
public class CompanyDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	//LoginDemoPojo demoPojo;
	CompanyPojo  companyPojo;
	
	
	 public List<CompanyPojo> comapnyList(){String sql="SELECT * FROM ATSLOG";
		
		return jdbcTemplate.query(sql,new ResultSetExtractor<List<CompanyPojo>>() {

			@Override
			public List<CompanyPojo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				
				List <CompanyPojo> list = new ArrayList<CompanyPojo>();
				while(rs.next()) {
					CompanyPojo com= new CompanyPojo();
					com.setId(rs.getInt("id"));
					com.setCompany(rs.getString("company"));
					com.setLocation(rs.getString("location"));
					com.setNote(rs.getString("note"));
					com.setDelivaryDate(rs.getDate("Delverydate"));
					System.out.println(rs.getInt("id")+"DAte"+" ___test"+rs.getDate("Delverydate") );
					list.add(com);
				}
				return list;
			}
			
			
		});
		
	}
	 public CompanyPojo companyRegi(CompanyPojo pojo) {
		 System.out.println("inside of comapnyreg");
		 String insercompany ="INSERT INTO ATSLOG"
		 		+ " (COMPANY ,LOCATION , Delverydate ,NOTE ) VALUES ('"+pojo.getCompany()+"','"
		 		+pojo.getLocation()+"','"+pojo.getDelivaryDate()+
		 		"','"+pojo.getNote()+"')";
		 System.out.println("comapny dao inser quray"+insercompany);
		 jdbcTemplate.execute(insercompany);
		 return pojo;

}
}
	 
