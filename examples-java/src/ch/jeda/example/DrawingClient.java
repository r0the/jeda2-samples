package ch.jeda.example;

import ch.jeda.Data;
import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.ui.*;

public class DrawingClient extends Program implements ActionListener,
                                                      PointerDownListener,
                                                      PointerMovedListener,
                                                      MessageReceivedListener {

    private static final int ACTION_CONNECT = 0;
    private View view;
    private StringInputField serverNameInput;
    private TextButton connectButton;
    private Text message;
    private TcpConnection connection;
    private Color color;
    private double x;
    private double y;
    private int radius = 10;

    @Override
    public void run() {
        view = new View();
        x = view.getWidthDp() / 2;
        y = view.getHeightDp() / 2;
        serverNameInput = new StringInputField(x, y, Alignment.CENTER);
        serverNameInput.setHintText("Server name");
        y = y - 80;
        connectButton = new TextButton(x, y, "Verbinden", ACTION_CONNECT, Alignment.CENTER);
        connectButton.setKey(Key.ENTER);
        y = y - 80;
        message = new Text(x, y, "", Alignment.CENTER);
        message.setTextColor(Color.PINK_A400);
        view.add(serverNameInput, connectButton, message);
        serverNameInput.select();
        view.addEventListener(this);
    }

    @Override
    public void onAction(ActionEvent event) {
        if (event.getId() == ACTION_CONNECT && connection == null) {
            connection = new TcpConnection();
            if (connection.open(serverNameInput.getValue(), 1248)) {
                view.remove(serverNameInput);
                view.remove(connectButton);
                clear();
            }
            else {
                connection = null;
                message.setText("Verbindung mit Server nicht möglich.");
                serverNameInput.select();
            }
        }
    }

    @Override
    public void onPointerDown(PointerEvent event) {
        draw(event);
    }

    @Override
    public void onPointerMoved(PointerEvent event) {
        draw(event);
    }

    @Override
    public void onMessageReceived(MessageEvent event) {
        color = event.getData().readObject("color");
    }

    private void clear() {
        view.getBackground().setColor(Color.WHITE);
        view.getBackground().fill();
        //JedaLogo.draw(fenster.getBackground());
    }

    private void draw(PointerEvent event) {
        if (connection != null) {
            Data data = new Data();
            data.writeFloat("x", event.getViewX());
            data.writeFloat("y", event.getViewY());
            connection.sendData(data);
            view.getBackground().setColor(color);
            view.getBackground().fillCircle(event.getViewX(), event.getViewY(), radius);
        }
    }
}
