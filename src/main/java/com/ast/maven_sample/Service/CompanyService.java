package com.ast.maven_sample.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ast.maven_sample.dao.CompanyDao;
import com.ast.maven_sample.pojo.CompanyPojo;

@Service
public class CompanyService {

	@Autowired
CompanyDao companyDao;
	public List<CompanyPojo> getAllCompany(){
		return companyDao.comapnyList();
	}
	public CompanyPojo companyRegi(CompanyPojo companyPojo) {
		System.out.println("comapny regi Service Clled");
		return companyDao.companyRegi(companyPojo);
	}
}
