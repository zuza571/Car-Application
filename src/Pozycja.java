public class Pozycja {
    private double x;
    private double y;

    public Pozycja(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }

    // zmiana pozycji
    public void przemieść(double v, double dt, Pozycja cel, Pozycja aktualnaPozycja) {
        double odlX = cel.x - aktualnaPozycja.x;
        double odlY = cel.y - aktualnaPozycja.y;
        double odległość = Math.sqrt(Math.pow(odlX, 2) + Math.pow(odlY, 2));

        double dx = (v * dt * odlX)/odległość;
        double dy = (v * dt * odlY)/odległość;

        if (aktualnaPozycja.x != cel.x || aktualnaPozycja.y != cel.y){
            setX(aktualnaPozycja.x + dx);
            setY(aktualnaPozycja.y + dy);

            if (dx > Math.abs(cel.x - aktualnaPozycja.x)) {
                setX(cel.x);
            }

            if (dy > Math.abs(cel.y - aktualnaPozycja.y)) {
                setY(cel.y);
            }
        }
    }
}
