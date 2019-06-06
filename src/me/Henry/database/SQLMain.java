package me.Henry.database;

import me.Henry.config.SQLConfig;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SQLMain {

    private JavaPlugin plugin;
    private SQLConfig sqlConfig;

    private Connection connection;
    public String host, database, username, password, table;
    public int port;

    public SQLMain(JavaPlugin plugin, SQLConfig sqlConfig) {
        this.plugin = plugin;
        this.sqlConfig = sqlConfig;
        loadConfig();
        setup();
    }

    public void loadConfig() {
        sqlConfig = new SQLConfig(plugin);
    }

    public void setup() {
        host = sqlConfig.getConfig().getString("host");
        database = sqlConfig.getConfig().getString("database");
        username = sqlConfig.getConfig().getString("username");
        password = sqlConfig.getConfig().getString("password");
        table = sqlConfig.getConfig().getString("table");

        try {
            synchronized (this) {
                if (getConnection() != null && !getConnection().isClosed())
                    return;
                Class.forName("com.mysql.jdbc.Driver");
                setConnection(DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.username, this.password));
                plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Connected to database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
