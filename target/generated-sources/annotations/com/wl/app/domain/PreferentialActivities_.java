package com.wl.app.domain;

import com.wl.app.domain.enumeration.Status;
import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PreferentialActivities.class)
public abstract class PreferentialActivities_ extends com.wl.app.domain.AbstractAuditingEntity_ {

	public static volatile SingularAttribute<PreferentialActivities, String> cover;
	public static volatile SingularAttribute<PreferentialActivities, Instant> endDate;
	public static volatile SingularAttribute<PreferentialActivities, String> name;
	public static volatile SingularAttribute<PreferentialActivities, Long> id;
	public static volatile SingularAttribute<PreferentialActivities, Instant> startDate;
	public static volatile SingularAttribute<PreferentialActivities, String> content;
	public static volatile SingularAttribute<PreferentialActivities, Status> status;

}

