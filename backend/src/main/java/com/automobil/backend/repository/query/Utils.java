package com.automobil.backend.repository.query;

public class Utils {
    public static final String FIND_ALL_MODELS_BY_MARKS = "SELECT m.*" +
        "FROM models m where m.idmark=(Select ma.idmark " +
        "from marks ma where ma.title= :title)";

    public static final String FIND_ALL_MARKS_BY_COUNTRY_OF_MANUFCTURE = "SELECT m.*" +
        "FROM marks m where m.idcounrty=(Select c.idcounrty" +
        "from countries c where c.title = :title)";

    public static final String FIND_MARK_BY_NAME = "SELECT m.* FROM marks m where m.title= :title";

    public static final String FIND_MODEL_BY_NAME="SELECT m.* FROM models m where m.title= :title";

    public static final String FIND_GENERATIONS_BY_NAME="SELECT g.* FROM generations g where gx.title= :title";

}
