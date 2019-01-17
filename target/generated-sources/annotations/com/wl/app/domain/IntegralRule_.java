package com.wl.app.domain;

import com.wl.app.domain.enumeration.IntegralRuleType;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(IntegralRule.class)
public abstract class IntegralRule_ extends com.wl.app.domain.AbstractAuditingEntity_ {

	public static volatile SingularAttribute<IntegralRule, String> code;
	public static volatile SingularAttribute<IntegralRule, String> name;
	public static volatile SingularAttribute<IntegralRule, String> remark;
	public static volatile SingularAttribute<IntegralRule, Long> id;
	public static volatile SingularAttribute<IntegralRule, Integer> value;
	public static volatile SingularAttribute<IntegralRule, IntegralRuleType> integralRuleType;

}

