package top.fzqblog.utils;

import com.mongodb.Mongo;

public class MongoManger {

    private static Mongo mg = null;

    /**
     * 获取mongodb
     *
     * @return
     */
    public synchronized static Mongo getMongo() {
        if (mg == null) {
            try {
                mg = new Mongo("localhost", 27017);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mg;
    }
}
