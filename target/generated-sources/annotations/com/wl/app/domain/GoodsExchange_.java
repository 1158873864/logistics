package com.wl.app.domain;

import com.wl.app.domain.enumeration.ExchangeStatus;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GoodsExchange.class)
public abstract class GoodsExchange_ extends com.wl.app.domain.AbstractAuditingEntity_ {

	public static volatile SingularAttribute<GoodsExchange, Integer> exchangeCount;
	public static volatile SingularAttribute<GoodsExchange, UserInfo> userInfo;
	public static volatile SingularAttribute<GoodsExchange, String> address;
	public static volatile SingularAttribute<GoodsExchange, String> mobilePhone;
	public static volatile SingularAttribute<GoodsExchange, ExchangeStatus> exchangeStatus;
	public static volatile SingularAttribute<GoodsExchange, Goods> goods;
	public static volatile SingularAttribute<GoodsExchange, String> remark;
	public static volatile SingularAttribute<GoodsExchange, Long> id;
	public static volatile SingularAttribute<GoodsExchange, String> fullname;
	public static volatile SingularAttribute<GoodsExchange, String> oddNumbers;

}

