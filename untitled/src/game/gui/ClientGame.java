package game.gui;

import game.Connection;
import game.Game;
import game.packets.ClientPlayPacket;
import game.packets.UpdatePacket;

import java.io.IOException;
import java.net.Socket;

public class ClientGame extends Game {

    private Socket socket;

    private Connection connection;

    public ClientGame(){
        super (Game.PLAYER_TWO);

        try {
            socket = new Socket("localhost",Game.PORT);
            connection = new Connection(this,socket);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void inputReceived(int x, int y) {
        if(isMyTurn()){
            connection.sendPacket(new ClientPlayPacket(x,y));
        }
    }

    @Override
    public void packetReceived(Object object) {
        if (object instanceof UpdatePacket){
            UpdatePacket packet = (UpdatePacket) object;
            fields = packet.getFields();
            currentPlayer = packet.getCurrentPlayer();

        }
        gameWindow.repaint();

    }

    @Override
    public void close() {
        try {
            connection.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
