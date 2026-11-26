package graphics.text;
import graphics.Texture;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;


public final class TextHandler{
    private static HashMap<String, Font> fonts = new HashMap<>();

    private static Font loadFont(String font_name){

        try (var in = TextHandler.class.getResourceAsStream("/fonts/"+font_name+".ttf")){
            if (in == null){System.err.println("Font '"+font_name+"' doesn't exist.");}
            return Font.createFont(Font.TRUETYPE_FONT, in);
        }
        catch (FontFormatException | IOException e){
            System.err.println("Something went wrong loading Font '"+font_name+"'");
            return new Font(Font.SANS_SERIF, Font.PLAIN, 14);
        }
    }

    public static Texture createTextTexture(String message, String font_name, FontSizes size, Color color){ // säg det snabbt fem gånger
        Font font = fonts.computeIfAbsent(font_name, TextHandler::loadFont);

        var temp = new BufferedImage(1,1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = temp.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();
        int width = fm.getHeight();
        int height = fm.getHeight();
        int baseline = fm.getAscent();
        g.dispose();

        var image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setFont(font);
        g.setColor(color);
        g.drawString(message, 0, baseline);
        g.dispose();
        return new Texture(image);
    }
}
