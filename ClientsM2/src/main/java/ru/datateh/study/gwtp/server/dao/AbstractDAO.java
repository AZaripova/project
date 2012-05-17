/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.datateh.study.gwtp.server.dao;

import org.dozer.Mapper;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author rshamsutdinov
 */
public class AbstractDAO {
	
	@Autowired
	private SqlSessionFactoryBean sqlSessionFactory;
	
	@Autowired
	private Mapper mapper;

	protected final Mapper getMapper() {
		return mapper;
	}
	
	protected final SqlSessionFactoryBean getSqlSessionFactoryBean() {
		return sqlSessionFactory;
	}
	
	protected final <T extends Object> T getMapper(Class<T> type) throws Exception {
		return sqlSessionFactory.getObject().openSession().getMapper(type);
	}
}
