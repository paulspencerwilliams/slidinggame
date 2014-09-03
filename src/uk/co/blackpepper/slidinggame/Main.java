package uk.co.blackpepper.slidinggame;

import java.io.IOException;

public class Main {

    private static Square redSquare;
    private static Square emptySquare;
    private static boolean pulling = true;
    private static int moves;

    public static void main(String[] args) throws IOException, InterruptedException {

        redSquare = new Square();
        redSquare.x = 1;
        redSquare.y = 1;

        emptySquare = new Square();
        emptySquare.x = 5;
        emptySquare.y = 4;
        while (notFinished ())
        {
            if (pulling)
            {
                pull();
            }
            else
            {
                push ();
            }
            System.out.println(String.format("redSquare=[%s,%s],emptySquare=[%s,%s]", redSquare.x, redSquare.y, emptySquare.x, emptySquare.y));
            moves++;
        }
        System.out.println(String.format("It took %s moves. Type any key to stop", moves));
        System.in.read();
    }



    private static void push() {
        if (redSquare.x > redSquare.y)
        {
            pullRedDown();
        }
        else
        {
            pullRedRight();
        }
    }

    private static void pull() {
        if (emptySquareDirectlyNeighboursRed()){
            pulling = false;
            System.out.println("Stopped pulling");
        }
        else
        {
            pullEmptySpace ();
        }
    }

    private static void pullRedRight() {
        if (isEmptySquareToTheRight())
        {
            System.out.println("pulling red right");
            redSquare.x = redSquare.x + 1;
            emptySquare.x = emptySquare.x - 1;
        }
        else if (isEmptySquareAbove())
        {
            System.out.println("pulling empty right");
            pullEmptyRight();
        }
        else
        {
            System.out.println("pulling empty down");
            pullEmptyDown();
        }

    }

    private static void pullRedDown() {
        if (isEmptySquareBelow())
        {
            System.out.println("pulling red down");
            redSquare.y = redSquare.y + 1;
            emptySquare.y = emptySquare.y - 1;
        }
        else if (isEmptySquareToTheLeft())
        {
            System.out.println("pulling empty down");
            pullEmptyDown();
        }
        else
        {
            System.out.println("pulling empty right");
            pullEmptyRight();
        }
    }

    private static void pullEmptyLeft() {
        emptySquare.x--;
    }

    private static void pullEmptyUp() {
        emptySquare.y--;
    }

    private static void pullEmptyRight() {
        emptySquare.x++;
    }

    private static void pullEmptyDown() {
        emptySquare.y++;
    }


    private static boolean notFinished() {
        return !(redSquare.x == 5 && redSquare.y == 4);
    }

    private static boolean isEmptySquareToTheRight() {
        return emptySquare.y == redSquare.y && emptySquare.x == redSquare.x + 1;
    }

    private static boolean isEmptySquareBelow() {
        return emptySquare.x == redSquare.x && emptySquare.y == redSquare.y + 1;
    }


    private static boolean isEmptySquareAbove() {
        return emptySquare.x == redSquare.x && emptySquare.y == redSquare.y - 1;
    }

    private static boolean isEmptySquareToTheLeft() {
        return emptySquare.x == redSquare.x -1 && emptySquare.y == redSquare.y;
    }

    private static void pullEmptySpace() {
        if (emptySquare.x == emptySquare.y + 1)
        {
            pullEmptyUp();
        }
        else
        {
            pullEmptyLeft();
        }
    }



    private static boolean emptySquareDirectlyNeighboursRed() {
        return (horizontallyTouches() || verticallyTouches ());
    }

    private static boolean verticallyTouches() {
        return (emptySquare.y == redSquare.y + 1 && emptySquare.x == redSquare.x);
    }

    private static boolean horizontallyTouches() {
        return (emptySquare.x == redSquare.x + 1 && emptySquare.y == redSquare.y);
    }
}
