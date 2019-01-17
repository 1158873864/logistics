package com.wl.app.domain;

import com.wl.app.domain.enumeration.Status;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SysRecruitmentInformation.class)
public abstract class SysRecruitmentInformation_ extends com.wl.app.domain.AbstractAuditingEntity_ {

	public static volatile SingularAttribute<SysRecruitmentInformation, String> addrCity;
	public static volatile SingularAttribute<SysRecruitmentInformation, String> salaryStart;
	public static volatile SingularAttribute<SysRecruitmentInformation, String> education;
	public static volatile SingularAttribute<SysRecruitmentInformation, String> salaryEnd;
	public static volatile SingularAttribute<SysRecruitmentInformation, String> nature;
	public static volatile SingularAttribute<SysRecruitmentInformation, Integer> peopleCount;
	public static volatile SingularAttribute<SysRecruitmentInformation, String> description;
	public static volatile SingularAttribute<SysRecruitmentInformation, Long> id;
	public static volatile SingularAttribute<SysRecruitmentInformation, String> category;
	public static volatile SingularAttribute<SysRecruitmentInformation, String> experience;
	public static volatile SingularAttribute<SysRecruitmentInformation, String> categoryName;
	public static volatile SingularAttribute<SysRecruitmentInformation, Status> status;

}

