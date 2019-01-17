package com.wl.app.domain;

import com.wl.app.domain.enumeration.Status;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Goods.class)
public abstract class Goods_ extends com.wl.app.domain.AbstractAuditingEntity_ {

	public static volatile SingularAttribute<Goods, String> cover;
	public static volatile SingularAttribute<Goods, Integer> total;
	public static volatile SingularAttribute<Goods, String> introduce;
	public static volatile SingularAttribute<Goods, Integer> integral;
	public static volatile SingularAttribute<Goods, Integer> peopleCount;
	public static volatile SingularAttribute<Goods, String> name;
	public static volatile SingularAttribute<Goods, String> payment;
	public static volatile SingularAttribute<Goods, Long> id;
	public static volatile SingularAttribute<Goods, Status> status;

}

