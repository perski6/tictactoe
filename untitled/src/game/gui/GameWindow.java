package game.gui;
import game.Game;
import game.res.Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameWindow extends JPanel {

    private Game game;
    public GameWindow(Game game) {
       this.game = game;
       addMouseListener(new Input());
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(10));
//  Renderowanie Linii planszy
        for(int i = Game.FIELD_WIDTH; i <= Game.FIELD_WIDTH * 2; i+=Game.FIELD_WIDTH){
            g2D.drawLine(i, 0, i, Game.HEIGHT);
        }
        for(int i = Game.FIELD_HEIGHT; i <= Game.FIELD_HEIGHT * 2; i+=Game.FIELD_HEIGHT){
            g2D.drawLine(0,i,Game.WIDTH,i);
        }
        for (int x = 0; x < 3; x++){
            for (int y = 0; y < 3; y++){
                int field = game.getFields()[x][y];
                if(field != Game.NOBODY) {
                    g2D.drawImage(Resources.letters[field - 1], x * Game.FIELD_WIDTH, y * Game.FIELD_HEIGHT, Game.FIELD_WIDTH-10, Game.FIELD_HEIGHT-10, null);
                }
            }
        }


    }
    class Input extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e){
            if(e.getButton() == MouseEvent.BUTTON1){
                game.inputReceived(e.getX() / Game.FIELD_WIDTH, e.getY() / Game.FIELD_HEIGHT);
            }
        }
    }


}
