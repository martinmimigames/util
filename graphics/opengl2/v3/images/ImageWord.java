package com.martinmimiGames.util.graphics.opengl2.v3.images;

import android.content.Context;

import com.martinmimiGames.util.graphics.gl2d.DrawType;
import com.martinmimiGames.util.graphics.gl2d.Prop;
import com.martinmimiGames.util.graphics.opengl2.v3.Draw;
import com.martinmimiGames.util.graphics.opengl2.v3.Images;
import com.martinmimiGames.util.graphics.opengl2.v3.Location;
import com.martinmimiGames.util.graphics.opengl2.v3.Object;
import com.martinmimiGames.util.graphics.opengl2.v3.images.shapes.Rectangle;

import java.util.ArrayList;

public class ImageWord extends Object {
    
    byte[] byteList;
    float font_size;
    Object object;

    public ImageWord(Draw draw, Context context, int imageRId) {
        location = new Location();
        object = new Object();
        object.drawable = drawable = new Rectangle(draw, context, imageRId);
    }

    @Override
    public void draw(Draw draw) {
        final float y = location.coor[Location.Y];
        final float string_size = byteList.length * font_size;
        float x = location.coor[Location.X] - string_size / 2 + font_size / 2;
        ((Rectangle) drawable).setWidth(font_size);
        ((Rectangle) drawable).setHeight(font_size);
        for (byte value : byteList) {
            byte b = value;
            if (b < 11 || b > 36) {

                if (b == 10) {
                } else if (b < 10) {
                    b += 3;
                    int row = 4;
                    drawWords(draw, b, row, x, y);
                }

            } else {
                b -= 10;

                int row = 0;

                drawWords(draw, b, row, x, y);

            }
            x += font_size;
        }
    }

    private void drawWords(Draw draw, byte b, int row, float x, float y) {
        while (b > 6) {
            b -= 6;
            row += 1;
        }
        final float cx = b * 0.15625f + 0.01f;
        final float cy = row * 0.15625f + 0.01f;
        Rectangle rectangle = (Rectangle) drawable;
        rectangle.setImageCrop(
                //col
                cx - 0.15625f,
                cx,
                //row
                cy,
                cy + 0.15625f);
        object.location.coor[Location.X] = x;
        object.location.coor[Location.Y] = y;
        object.draw(draw);
    }

    public void setText(CharSequence string) {

        char[] text = new char[string.length()];
        for (int i = 0; i < string.length(); i++){
            text[i] = string.charAt(i);
        }
        byteList = new byte[text.length];
        for (int i = 0; i < text.length; i++) {
            byte n = 0;
            switch (text[i]) {

                // numbers

                case '0':
                    break;
                case '1':
                    n = 1;
                    break;
                case '2':
                    n = 2;
                    break;
                case '3':
                    n = 3;
                    break;
                case '4':
                    n = 4;
                    break;
                case '5':
                    n = 5;
                    break;
                case '6':
                    n = 6;
                    break;
                case '7':
                    n = 7;
                    break;
                case '8':
                    n = 8;
                    break;
                case '9':
                    n = 9;
                    break;

                // space

                case ' ':
                    n = 10;
                    break;

                // letters

                case 'a':
                    n = 11;
                    break;
                case 'b':
                    n = 12;
                    break;
                case 'c':
                    n = 13;
                    break;
                case 'd':
                    n = 14;
                    break;
                case 'e':
                    n = 15;
                    break;
                case 'f':
                    n = 16;
                    break;
                case 'g':
                    n = 17;
                    break;
                case 'h':
                    n = 18;
                    break;
                case 'i':
                    n = 19;
                    break;
                case 'j':
                    n = 20;
                    break;
                case 'k':
                    n = 21;
                    break;
                case 'l':
                    n = 22;
                    break;
                case 'm':
                    n = 23;
                    break;
                case 'n':
                    n = 24;
                    break;
                case 'o':
                    n = 25;
                    break;
                case 'p':
                    n = 26;
                    break;
                case 'q':
                    n = 27;
                    break;
                case 'r':
                    n = 28;
                    break;
                case 's':
                    n = 29;
                    break;
                case 't':
                    n = 30;
                    break;
                case 'u':
                    n = 31;
                    break;
                case 'v':
                    n = 32;
                    break;
                case 'w':
                    n = 33;
                    break;
                case 'x':
                    n = 34;
                    break;
                case 'y':
                    n = 35;
                    break;
                case 'z':
                    n = 36;
                    break;
            }
            byteList[i] = n;
        }
    }
}
