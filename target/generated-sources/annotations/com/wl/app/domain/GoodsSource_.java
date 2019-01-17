package com.wl.app.domain;

import com.wl.app.domain.enumeration.GoodsSourceFreight;
import com.wl.app.domain.enumeration.GoodsSourcePacking;
import com.wl.app.domain.enumeration.GoodsSourceProperty;
import com.wl.app.domain.enumeration.Status;
import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GoodsSource.class)
public abstract class GoodsSource_ extends com.wl.app.domain.AbstractAuditingEntity_ {

	public static volatile SingularAttribute<GoodsSource, UserInfo> userInfo;
	public static volatile SingularAttribute<GoodsSource, GoodsSourceFreight> goodsSourceFreight;
	public static volatile SingularAttribute<GoodsSource, Instant> layTime;
	public static volatile SingularAttribute<GoodsSource, String> start;
	public static volatile SingularAttribute<GoodsSource, GoodsSourcePacking> goodsSourcePacking;
	public static volatile SingularAttribute<GoodsSource, String> volume;
	public static volatile SingularAttribute<GoodsSource, String> ton;
	public static volatile SingularAttribute<GoodsSource, String> mobilePhone;
	public static volatile SingularAttribute<GoodsSource, GoodsSourceProperty> goodsSourceProperty;
	public static volatile SingularAttribute<GoodsSource, String> name;
	public static volatile SingularAttribute<GoodsSource, String> end;
	public static volatile SingularAttribute<GoodsSource, Long> id;
	public static volatile SingularAttribute<GoodsSource, Instant> effectiveDate;
	public static volatile SingularAttribute<GoodsSource, Status> status;

}

