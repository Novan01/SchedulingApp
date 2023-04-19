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
}
