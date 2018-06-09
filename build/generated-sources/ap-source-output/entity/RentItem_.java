package entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-10T06:14:01")
@StaticMetamodel(RentItem.class)
public class RentItem_ { 

    public static volatile SingularAttribute<RentItem, Integer> idRent;
    public static volatile SingularAttribute<RentItem, Integer> quantity;
    public static volatile SingularAttribute<RentItem, Integer> idBook;
    public static volatile SingularAttribute<RentItem, Integer> idRentItem;
    public static volatile SingularAttribute<RentItem, String> bookName;
    public static volatile SingularAttribute<RentItem, Boolean> status;

}