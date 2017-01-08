package com.balatamilmani.restangular.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.balatamilmani.restangular.dao.StudentDao;
import com.balatamilmani.restangular.model.Student;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext-dataaccess-test.xml"})
public class StudentServiceImplTest {
    
	@Value(value="${name}")
	String name;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	TestBean tb;
	
	@Mock
	StudentDao studentDao;
	
	@Before
	public void initialize(){
		System.out.println(name);
		System.out.println(tb.getSetValue().size());
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(studentService, "studentDao", studentDao);
	}
	
	private List<Student> getMockStudent(){
		List<Student> mockStudentList = null;
		Student student = null;
		
		mockStudentList = new ArrayList<>();
		student = new Student();
		student.setStudentId(123);
		student.setFirstName("Silvester");
		mockStudentList.add(student);
		return mockStudentList;
	}
	
	@Test
	public void testGetStudent(){
		Mockito.when(studentDao.findAll()).thenReturn(getMockStudent());
		List<Student> studentList = studentService.getStudent();
		Assert.assertEquals(1, studentList.size());
		Assert.assertEquals("Silvester", studentList.get(0).getFirstName());
	}
	
	@Test(expected=Exception.class)
	public void testGetStudentThrowsException(){
		Mockito.when(studentDao.findAll()).thenThrow(Exception.class);
		List<Student> studentList = studentService.getStudent();

	}
}
