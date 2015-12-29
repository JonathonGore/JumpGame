
package jumpgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author j.gore
 */
public class ScoreKeeper {
    
    public Timer levelUpTimer;
    private int score = 0;
    private int level = 1;
    private int count = 1;
    private int speed = 50;
    private final int timerDelay = 1000;
    
    public ScoreKeeper(){       
        startTimer();
        levelUpTimer.start();
    }

    private void startTimer() {
        ActionListener timerListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                score = score + (1 * level);
                count++;
                if (count == 10){
                    level++;
                    speed = 50 - (level * 2);
                    if (speed < 1){
                        speed = 1;
                    }
                    count = 1;
                }
            }
        };
        levelUpTimer = new Timer(timerDelay, timerListener);
    }
    public int getSpeed(){       
        return speed;
    }
    public int getScore(){
        return score;
    }

}
