package com.wl.app.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TopicForward.class)
public abstract class TopicForward_ {

	public static volatile SingularAttribute<TopicForward, UserInfo> userInfo;
	public static volatile SingularAttribute<TopicForward, Topic> topic;
	public static volatile SingularAttribute<TopicForward, Long> id;
	public static volatile SingularAttribute<TopicForward, Instant> oDateTime;

}

