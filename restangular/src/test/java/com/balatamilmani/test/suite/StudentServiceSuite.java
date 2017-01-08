package com.balatamilmani.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.balatamilmani.restangular.service.StudentServiceImplTest;

@RunWith(Suite.class)
@SuiteClasses({StudentServiceImplTest.class})
public class StudentServiceSuite {

}
