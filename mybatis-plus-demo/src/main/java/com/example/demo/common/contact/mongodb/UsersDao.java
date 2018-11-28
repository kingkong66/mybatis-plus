package com.example.demo.common.contact.mongodb;

import com.example.demo.entity.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;


@Component
public class UsersDao extends MongodbBaseDao<Users> {
	public static final Logger logger = LoggerFactory.getLogger(UsersDao.class);

	public void addRecord(Users entry) {
		super.save(entry);
	}

	@Override
	protected Class<Users> getEntityClass() {
		return Users.class;

	}



	@Autowired
	@Qualifier("mongoTemplate")
	@Override
	protected void setMongoTemplate(MongoTemplate mongoTemplate) {
		super.mongoTemplate = mongoTemplate;
	}

}
