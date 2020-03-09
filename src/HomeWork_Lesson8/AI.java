package HomeWork_Lesson8;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AI {

    public void analiseLine(Line line, char symbol, int winLength, int defaultWeight) {
        String[] patterns = {String.format("(\\s[%c]+)", symbol), String.format("([%c]+\\s)", symbol),
                String.format("(\\s[%c]{%d,})", symbol, winLength - 1), String.format("([%c]{%d,}\\s)", symbol, winLength - 1)};
        int cellIndex;
        int addCorrection;
        Pattern pattern;
        Matcher matcher;

        for (int i = 0; i < patterns.length; i++) {
            pattern = Pattern.compile(patterns[i]);
            matcher = pattern.matcher(line.toString());

            while (matcher.find()) {
                cellIndex = i == 0 || i == 2 ? matcher.start() : matcher.end() - 1;
                addCorrection = i > 1 ? defaultWeight : 1;

                Cell cell = line.getCell(cellIndex);
                cell.applyWeight((int) Math.pow(defaultWeight, matcher.group().length() - 1) * addCorrection);
            }
        }
    }
}

