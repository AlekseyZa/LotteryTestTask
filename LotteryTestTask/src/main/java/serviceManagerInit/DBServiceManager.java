package serviceManagerInit;

import dbService.DBService;
import dbService.DBServiceInterface;

public class DBServiceManager {

    private static DBServiceInterface dbService;

    public static DBServiceInterface getDBService() {
        if (dbService==null){
            dbService = new DBService();
        }
        return dbService;
    }

}
