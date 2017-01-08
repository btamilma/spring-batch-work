package com.balatamilmani.restangular.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.balatamilmani.restangular.model.Student;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext-dataaccess-test.xml"})
public class StudentDaoTest {

	@Autowired
	StudentDao studentDao = null;
	
	@Test
	public void testGetStudent(){
		List<Student> studentList = studentDao.findAll();
		Assert.assertEquals(1, studentList.size());
		Assert.assertEquals("Mari", studentList.get(0).getFirstName());
		System.out.println(studentDao);
	}
}
