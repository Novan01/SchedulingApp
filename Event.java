import java.sql.Date;
import java.sql.*;
import java.time.*;

public class Event {
    private int id, priority;
    private Date time;
    private String name, description;

    public Event() {

    }

    public Event(int i, int prio, Date t, String n, String d) {
        int id = i;
        int priority = prio;
        Date time = t;
        String name = n;
        String description = d;

    }

    public int getID() {
        return this.id;
    }

    public void setID(int ID) {
        this.id = ID;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int prio) {
        this.priority = prio;
    }

    public Date getDate() {
        return this.time;
    }

    public void setDate(Date t) {
        this.time = t;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String n) {
        this.name = n;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }
}