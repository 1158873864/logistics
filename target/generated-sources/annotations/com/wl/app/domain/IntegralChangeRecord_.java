package com.wl.app.domain;

import com.wl.app.domain.enumeration.IntegralRuleType;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(IntegralChangeRecord.class)
public abstract class IntegralChangeRecord_ extends com.wl.app.domain.AbstractAuditingEntity_ {

	public static volatile SingularAttribute<IntegralChangeRecord, UserInfo> userInfo;
	public static volatile SingularAttribute<IntegralChangeRecord, String> name;
	public static volatile SingularAttribute<IntegralChangeRecord, Long> id;
	public static volatile SingularAttribute<IntegralChangeRecord, Integer> value;
	public static volatile SingularAttribute<IntegralChangeRecord, IntegralRuleType> integralRuleType;
	public static volatile SingularAttribute<IntegralChangeRecord, IntegralRule> integralRule;

}

