package com.wl.app.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TopicComment.class)
public abstract class TopicComment_ {

	public static volatile SingularAttribute<TopicComment, UserInfo> userInfo;
	public static volatile SingularAttribute<TopicComment, Topic> topic;
	public static volatile SingularAttribute<TopicComment, Long> id;
	public static volatile SingularAttribute<TopicComment, Instant> oDateTime;
	public static volatile SingularAttribute<TopicComment, String> content;

}

