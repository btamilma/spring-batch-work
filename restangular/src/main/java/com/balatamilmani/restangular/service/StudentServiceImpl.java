package com.balatamilmani.restangular.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balatamilmani.restangular.dao.StudentDao;
import com.balatamilmani.restangular.model.Student;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	StudentDao studentDao;
	
	@Override
	public List<Student> getStudent() {
		return studentDao.findAll();
	}

}
