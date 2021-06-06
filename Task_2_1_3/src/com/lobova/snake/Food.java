package com.lobova.snake;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.lobova.snake.Main.*;
import static com.lobova.snake.Main.SQUARE_SIZE;
import static java.lang.Math.abs;

/**
 * The food generation class.
 */
public class Food {
    List<Point> foodList = new ArrayList<>();

    /**
     * Fill in the initial foodList.
     */
    Food() {
        for (int i = 0; i < FOOD; i++) {
            foodList.add(generateRandomPoint());
        }
    }

    /**
     * @param wasEaten the piece of food that was eaten by the snake.
     */
    public void generateFood(final Point wasEaten) {
        setRandomPoint(wasEaten);
    }

    /**
     * To generate food that was just eaten in the new place.
     *
     * @param wasEaten the piece of food that was eaten by the snake.
     */
    private void setRandomPoint(final Point wasEaten) {
        Random random = new Random();
        int randX = abs(random.nextInt() % (COLS - 1));
        int randY = abs(random.nextInt() % (ROWS - 1));
        wasEaten.setLocation(randX, randY);
    }

    /**
     * @return Point to be added in the foodList.
     */
    private Point generateRandomPoint() {
        Random random = new Random();
        int randX = abs(random.nextInt() % (COLS - 1));
        int randY = abs(random.nextInt() % (ROWS - 1));
        return new Point(randX, randY);
    }

    /**
     * @param gc Graphics Context to food to be drawn.
     */
    public void drawFood(final GraphicsContext gc) {
        gc.setFill(Color.web("FA8072"));
        for (int i = 0; i < foodList.size(); i++) {
            gc.fillRoundRect(foodList.get(i).x * SQUARE_SIZE, foodList.get(i).y * SQUARE_SIZE,
                    SQUARE_SIZE - 1, SQUARE_SIZE - 1, 35, 35);
        }
    }
}
