package com.wl.app.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserDdnFavorites.class)
public abstract class UserDdnFavorites_ extends com.wl.app.domain.AbstractAuditingEntity_ {

	public static volatile SingularAttribute<UserDdnFavorites, UserInfo> userInfo;
	public static volatile SingularAttribute<UserDdnFavorites, Long> id;
	public static volatile SingularAttribute<UserDdnFavorites, LogisticsDdn> ddn;

}

