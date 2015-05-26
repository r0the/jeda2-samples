package ch.jeda.example;

import ch.jeda.Data;
import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.ui.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrawingServer extends Program implements ServerListener {

    private View view;
    private TcpServer server;
    private List<Connection> connections;
    private Map<Connection, Color> clientColors;
    private List<Color> availableColors;
    private int radius = 10;

    @Override
    public void run() {
        connections = new ArrayList<Connection>();
        clientColors = new HashMap<Connection, Color>();
        availableColors = new ArrayList<Color>();
        availableColors.add(Color.RED_900);
        availableColors.add(Color.LIGHT_BLUE_900);
        availableColors.add(Color.LIGHT_GREEN_900);
        availableColors.add(Color.PINK_900);
        server = new TcpServer();
        server.start(1248);
        view = new View();
        view.addEventListener(this);
    }

    @Override
    public void onConnectionAccepted(ConnectionEvent event) {
        Connection newConnection = event.getConnection();
        Color newColor = availableColors.get(0);
        availableColors.remove(0);
        connections.add(newConnection);
        clientColors.put(newConnection, newColor);
        Data data = new Data();
        data.writeObject("color", newColor);
        newConnection.sendData(data);
    }

    @Override
    public void onConnectionClosed(ConnectionEvent event) {
        connections.remove(event.getConnection());
        availableColors.add(clientColors.get(event.getConnection()));
        clientColors.remove(event.getConnection());
    }

    @Override
    public void onMessageReceived(MessageEvent event) {
        float x = event.getData().readFloat("x", -1);
        float y = event.getData().readFloat("y", -1);
        Color color = clientColors.get(event.getConnection());
        if (x >= 0 && y >= 0) {
            view.getBackground().setColor(color);
            view.getBackground().fillCircle(x, y, radius);
        }
    }
}
