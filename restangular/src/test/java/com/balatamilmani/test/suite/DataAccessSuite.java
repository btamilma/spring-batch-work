package com.balatamilmani.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.balatamilmani.restangular.dao.StudentDaoTest;

@RunWith(Suite.class)
@SuiteClasses({StudentDaoTest.class})
public class DataAccessSuite {

}
