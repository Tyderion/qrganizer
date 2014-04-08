package ch.isageek.ch.tyderion.qrganizer.daogenerator;
import java.io.IOException;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by gaku on 1/9/14.
 */
public class QRDaoGenerator {
    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(3, "ch.isageek.ch.tyderion.qrorganizer.dao");
        Entity user = schema.addEntity("User");
        user.addIdProperty();
        user.addStringProperty("username").unique();
        user.addStringProperty("password");
        new DaoGenerator().generateAll(schema, "src-gen/main/java");
    }
}
