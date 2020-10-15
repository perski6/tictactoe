package game.gui;

import game.Connection;
import game.Game;
import game.packets.ClientPlayPacket;
import game.packets.UpdatePacket;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerGame extends Game {

    private ServerSocket serverSocket;
    private Socket socket;

    private Connection connection;

    public ServerGame() {
        super(Game.PLAYER_ONE);
        try {
            serverSocket = new ServerSocket(Game.PORT);
            socket = serverSocket.accept();
            connection = new Connection(this, socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void inputReceived(int x,int y){
        if(isMyTurn()){
            updateField(x,y);
        }
        gameWindow.repaint();
    }

    @Override
    public void packetReceived(Object object) {
        if(object instanceof ClientPlayPacket){
            ClientPlayPacket packet = (ClientPlayPacket) object;

            updateField(packet.getX(), packet.getY());
        }
        gameWindow.repaint();


    }

    private void updateField(int x, int y){
        if (fields[x][y] == Game.NOBODY){
            fields[x][y] = currentPlayer;
            if (currentPlayer == Game.PLAYER_ONE){
                currentPlayer = Game.PLAYER_TWO;
            } else if(currentPlayer == Game.PLAYER_TWO){
                currentPlayer = Game.PLAYER_ONE;
            }

            connection.sendPacket(new UpdatePacket(fields,currentPlayer));
        }
    }


    @Override
    public void close() {
        try {
            connection.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
