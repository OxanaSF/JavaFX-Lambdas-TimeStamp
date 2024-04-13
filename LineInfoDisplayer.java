import javafx.scene.shape.Line;


@FunctionalInterface
public interface LineInfoDisplayer {

    String getInfo(Line line);

    public static enum InfoType {
        DISTANCE, MIDPOINT, VERT_HORZ, SLOPE_INTERCEPT;
    }

    public static LineInfoDisplayer createLineInfoDisplayer(InfoType type) {
        switch (type) {
            case DISTANCE:
                return line -> {
                    double distance = calculateDistance(line);
                    return "Distance: " + distance;
                };
            case MIDPOINT:
                return line -> {
                    String midpoint = calculateMidpoint(line);
                    return "Midpoint: " + midpoint;
                };
            case VERT_HORZ:
                return line -> {
                    String verticalOrHorizontal = determineVerticalOrHorizontal(line);
                    return verticalOrHorizontal;
                };
            case SLOPE_INTERCEPT:
                return line -> {
                    String slopeAndIntercept = calculateSlopeAndIntercept(line);
                    return slopeAndIntercept;
                };
            default:
                throw new IllegalArgumentException("Type might be chosen!");
        }
    }

    private static double calculateDistance(Line line) {
        double differenceX = line.getEndX() - line.getStartX();
        double differenceY = line.getEndY() - line.getStartY();
        double result = Math.sqrt(differenceX * differenceX + differenceY * differenceY);
        double resultRounded = Math.round(result) * 10 / 10;
        return resultRounded;
    }

    private static String calculateMidpoint(Line line) {
        double midX = (line.getStartX() + line.getEndX()) / 2.0;
        double midY = (line.getStartY() + line.getEndY()) / 2.0;
        return "( " + midX + " , " + midY + " )";
    }


    private static String determineVerticalOrHorizontal(Line line) {

        if (line.getStartX() == line.getEndX()) {
            return "This is a Vertical line";
        } else if (line.getStartY() == line.getEndY()) {
            return "This is a Horizontal line";
        }
        return "This is neither Vertical nor Horizontal line";
    }

    private static String calculateSlopeAndIntercept(Line line) {
        double slope = (double) (line.getEndY() - line.getStartY()) / (line.getEndX() - line.getStartX());
        double intercept = line.getStartY() - slope * line.getStartX();
        double slopeRounded = Math.round(slope * 10.0) / 10.0;
        double interceptRounded = Math.round(intercept * 10.0) / 10.0;
        return "Slope: " + slopeRounded + ", Intercept: " + interceptRounded;
    }


}