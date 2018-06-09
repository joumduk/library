package entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-10T06:14:01")
@StaticMetamodel(Rent.class)
public class Rent_ { 

    public static volatile SingularAttribute<Rent, Date> rentDate;
    public static volatile SingularAttribute<Rent, Date> expireDate;
    public static volatile SingularAttribute<Rent, Integer> id;
    public static volatile SingularAttribute<Rent, Integer> idStudent;
    public static volatile SingularAttribute<Rent, Boolean> status;

}