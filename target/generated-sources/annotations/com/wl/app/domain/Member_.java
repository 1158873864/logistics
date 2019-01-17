package com.wl.app.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Member.class)
public abstract class Member_ extends com.wl.app.domain.AbstractAuditingEntity_ {

	public static volatile SingularAttribute<Member, UserInfo> userInfo;
	public static volatile SingularAttribute<Member, Instant> endDate;
	public static volatile SingularAttribute<Member, Long> id;
	public static volatile SingularAttribute<Member, Instant> startDate;

}

