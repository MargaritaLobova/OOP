package com.lobova.snake;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.lobova.snake.Main.ROWS;
import static com.lobova.snake.Main.LEFT;
import static com.lobova.snake.Main.RIGHT;
import static com.lobova.snake.Main.UP;
import static com.lobova.snake.Main.WIDTH;
import static com.lobova.snake.Main.HEIGHT;
import static com.lobova.snake.Main.SQUARE_SIZE;


/**
 * Class for Snake control.
 */
public class Snake {
    private List<Point> snakeBody = new ArrayList();
    private Point snakeHead;

    /**
     * Snake creation - it'll be created on the center-left of the screen.
     * The snake's body in initial stage consists of one Point.
     * It's initial direction - RIGHT.
     */
    Snake() {
        for (int i = 0; i < 2; i++) {
            addSnakeBody(new Point(4, ROWS / 2));
        }
        setSnakeHead(snakeBody.get(0));
    }

    /**
     * @param snakeHead new Point for snake's head.
     */
    public void setSnakeHead(final Point snakeHead) {
        this.snakeHead = snakeHead;
    }

    /**
     * @return getter for snake's head.
     */
    public Point getSnakeHead() {
        return snakeHead;
    }

    /**
     * @param somewhere the direction - where the snake should move.
     */
    public void moveHead(final int somewhere) {
        switch (somewhere) {
            case LEFT:
                snakeHead.x--;
                break;
            case RIGHT:
                snakeHead.x++;
                break;
            case UP:
                snakeHead.y--;
                break;
            default:
                snakeHead.y++;
                break;
        }
    }

    /**
     * The method for snake's body to move behind it's head.
     */
    public void moveBody() {
        for (int i = getLength() - 1; i >= 1; i--) {
            snakeBody.get(i).x = snakeBody.get(i - 1).x;
            snakeBody.get(i).y = snakeBody.get(i - 1).y;
        }
    }

    /**
     * @param point new Point to be added.
     */
    public void addSnakeBody(final Point point) {
        snakeBody.add(point);
    }

    /**
     * @return current snake's body length.
     */
    public int getLength() {
        return snakeBody.size();
    }

    /**
     * @param gc Graphics context to draw the snake.
     */
    public void drawSnake(final GraphicsContext gc) {
        gc.setFill(Color.web("32CD32"));
        gc.fillRoundRect(snakeHead.getX() * SQUARE_SIZE, snakeHead.getY() * SQUARE_SIZE,
                SQUARE_SIZE - 1, SQUARE_SIZE - 1, 35, 35);

        for (int i = 1; i < snakeBody.size(); i++) {
            gc.fillRoundRect(snakeBody.get(i).getX() * SQUARE_SIZE, snakeBody.get(i).getY() * SQUARE_SIZE,
                    SQUARE_SIZE - 1, SQUARE_SIZE - 1, 10, 10);
        }
    }

    /**
     * Method for snake to grow, while eating food.
     */
    public void eatFood() {
        Point tail = new Point();
        tail.setLocation(snakeBody.get(snakeBody.size() - 1).getX(), snakeBody.get(snakeBody.size() - 1).getY());
        addSnakeBody(tail);
    }

    /**
     * @return true if there was a crash, false if everything's fine
     */
    public boolean crashCheck() {
        return snakeHead.x < 0 || snakeHead.y < 0 || snakeHead.x * SQUARE_SIZE >= WIDTH
                || snakeHead.y * SQUARE_SIZE >= HEIGHT;
    }
}
