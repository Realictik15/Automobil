package com.automobil.backend.repository.query;

public class Utils {
    public static final String FIND_ALL_MODELS_BY_MARKS = "SELECT m.*" +
        "FROM models m where m.idmark=(Select ma.idmark " +
        "from marks ma where ma.title= :title)";

    public static final String FIND_ALL_MARKS_BY_COUNTRY_OF_MANUFCTURE = "SELECT m.*" +
        "FROM marks m where m.idcounrty=(Select c.idcounrty" +
        "from countries c where c.title = :title)";

    public static final String FIND_MARK_BY_NAME = "SELECT m.* FROM marks m where m.title= :title";

    public static final String FIND_MODEL_BY_NAME = "SELECT m.* FROM models m where m.title= :title";

    public static final String FIND_GENERATIONS_BY_NAME = "SELECT g.* FROM generations g where g.title= :title";

    public static final String FIND_CARBODY_BY_NAME = "SELECT c.* FROM carbody c where c.title= :title";

    public static final String FIND_AVAILABLE_ADS = "SELECT a.* FROM advertisments a where a.available = 'yes' order by a.idadvert";

    public static final String FIND_AVAILABLE_ADS_COUNT = "SELECT count(a.available) FROM advertisments a where a.available = 'yes'";

    public static final String FIND_ADVERTISMENT_REPORT = "SELECT a.* FROM advertisments a where a.vin = :vin and a.available = 'no'";

    public static final String FIND_COUNTRY_BY_TILE = "SELECT c.* FROM COUNTRIES c where c.title= :title";

    public static final String FIND_CLIENT_BY_LOGIN = "SELECT c.* FROM CLIENTS c where c.login= :login";

    public static final String CHECK_EXIST_LOGIN = "SELECT count(c.login) from clients c where c.login=:login";

    public static final String CHECK_EXIST_EMALE = "SELECT count(c.EMAIL) from clients c where c.EMAIL=:emale";

    public static final String FIND_ADVERT_BY_CLASS = "SELECT * FROM advertisments a WHERE (SELECT m.idclas FROM models m WHERE m.idmodel= a.idmodel) = :class and a.available = 'yes'";

   public static final String FILTERS="select a.* from advertisments a where a.available='yes' and (a.mileage<=:mileage or :mileage = -1) and (a.price>=:sprice and (a.price <:eprice or :eprice=-1)) and (a.idmark=(select m.idmark from marks m where(m.title=:mark)) or :mark='all') and (a.idmodel=(select mo.idmodel from models mo where(mo.title=:model)) or :model='all') and" +
        "(a.idcarbody=(select c.idcarbody from carbody c where(c.title=:carbody)) or :carbody='all') and (a.buyday >= to_date(:sdate, 'dd/mm/yyyy') and a.buyday <to_date(:edate, 'dd/mm/yyyy')) and" +
         "((select g.idgear from gearboxes g where g.title=:gear)=(select g.idgear from modifications m join transmissions t on t.idtrans=m.idtrans join gearboxes g on t.idgear=g.idgear where m.idmodif= a.idmodif) or :gear='all') order by a.idadvert ";

 public static final String FILTERS_COUNT="select count(a.available) from advertisments a where a.available='yes' and (a.mileage<=:mileage or :mileage = -1)and (a.price>=:sprice and (a.price <:eprice or :eprice=-1)) and (a.idmark=(select m.idmark from marks m where(m.title=:mark)) or :mark='all') and (a.idmodel=(select mo.idmodel from models mo where(mo.title=:model)) or :model='all') and" +
        "(a.idcarbody=(select c.idcarbody from carbody c where(c.title=:carbody)) or :carbody='all') and (a.buyday >= to_date(:sdate, 'dd/mm/yyyy') and a.buyday <to_date(:edate, 'dd/mm/yyyy')) and" +
        "((select g.idgear from gearboxes g where g.title=:gear)=(select g.idgear from modifications m join transmissions t on t.idtrans=m.idtrans join gearboxes g on t.idgear=g.idgear where m.idmodif= a.idmodif) or :gear='all') ";

}
