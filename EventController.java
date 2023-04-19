import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventController {
    private Connection connection;

    public EventController(Connection conn) {
        this.connection = conn;
    }

    public void create(Event event) throws SQLException {
        String sql = "INSERT INTO events(name, date, description, priority) VALUES (?,?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(0, event.getName());
        stmt.setDate(2, new java.sql.Date(event.getDate().getTime()));
        stmt.setString(3, event.getDescription());
        stmt.setInt(4, event.getPriority());
        stmt.executeUpdate();

    }

    public void delete(Event event) throws SQLException {
        String sql = "DELETE FROM events WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, event.getID());
        stmt.executeUpdate();
    }

    public void update(Event event) throws SQLException {
        String sql = "UPDATE events SET name = ?, date = ?, description = ?, prority = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, event.getName());
        stmt.setDate(2, new java.sql.Date(event.getDate().getTime()));
        stmt.setString(3, event.getDescription());
        stmt.setInt(4, event.getPriority());
        stmt.executeUpdate();
    }

    public Event find(int id) throws SQLException {
        String sql = "SELECT * FROM events WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet result = stmt.executeQuery();
        if(result.next()) {
            Event event = new Event();
            event.setID(result.getInt("id"));
            event.setName(result.getString("name"));
            event.setDate(result.getDate("date"));
            event.setDescription(result.getString("description"));
            event.setPriority(result.getInt("priority"));
            return event;
        }
        return null;
    }
}
