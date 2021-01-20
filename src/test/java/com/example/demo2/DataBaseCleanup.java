package com.example.demo2;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Service
public class DataBaseCleanup implements InitializingBean {

	private Logger logger = LogManager.getLogger(DataBaseCleanup.class);

	@Autowired
	protected DataSource dataSource;
	@PersistenceContext
	private EntityManager entityManager;
	private List<String> tableNames; 

	@Transactional
	public void truncateAllTables() {
		entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();
		
		for (String tableName : tableNames) {
			entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
		}

		entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
	}
	public List<String> tablesName(){
		return tableNames;
	}

	public List<String> getNameTablesDB() {
		List<String> tableNames = new ArrayList<>();
		try {
			DatabaseMetaData metaData = dataSource.getConnection().getMetaData();
			ResultSet tables = metaData.getTables(null, null, null, new String[] { "TABLE" });
			while (tables.next()) {
				String tableName = tables.getString("TABLE_NAME");
				tableNames.add(tableName);

			}
		} catch (Exception e) {
			logger.error("NO SE PUDO OBTENER LOS NOMBRE DE LAS TABLAS DE LA BD, PARA RESETEAR LA BD");

		}
		return tableNames;
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		tableNames = getNameTablesDB();
		
	}

}
