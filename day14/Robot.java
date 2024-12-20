public class Robot {
    int posX;
    int posY;
    int velX;
    int velY;

    public Robot(String input) {
        initRobot(input);
    }

    private void initRobot(String input) {
        var position = input.split(" ")[0];
        this.posX = Integer.parseInt(position.split(",")[0].split("=")[1].trim());
        this.posY = Integer.parseInt(position.split(",")[1].trim());
        var velocity = input.split(" ")[1];
        this.velX = Integer.parseInt(velocity.split(",")[0].split("=")[1].trim());
        this.velY = Integer.parseInt(velocity.split(",")[1].trim());
    }

    public void makeMove(int seconds, int boardWidth, int boardHeight) {
        this.posX = (posX + velX * seconds) % boardWidth;
        this.posY = (posY + velY * seconds) % boardHeight;

        //modulus vs remainder!!!
        // https://stackoverflow.com/questions/5385024/mod-in-java-produces-negative-numbers
        if (posX < 0 ){
            posX += boardWidth;
        }
        if (posY < 0 ){
            posY += boardHeight;
        }
    }
}
