package com.martinmimiGames.util.graphics.opengl2.v3.images.shapes;

import com.martinmimiGames.util.graphics.opengl2.v3.Images;

public class Rectangle{

    public static void getDefaultVertexData(Images shape) {
        shape.points = 4;
        shape.updateStride();
        shape.vertex_data = new float[shape.stride];
        setWidth(shape, 100f);
        setHeight(shape, 100f);
        setImageCrop(shape, 0f, 1f, 0f, 1f);
    }

    public static final class POINTS{

        static final int NULL = 0;
        static final int MINUS_HALF_WIDTH = 1;
        static final int MINUS_HALF_HEIGHT = 2;
        static final int PLUS_HALF_WIDTH = 3;
        static final int PLUS_HALF_HEIGHT = 4;

        public static final int[] VALUE =
                {
                        MINUS_HALF_WIDTH,
                        MINUS_HALF_HEIGHT,
                        NULL,
                        NULL,

                        MINUS_HALF_WIDTH,
                        PLUS_HALF_HEIGHT,
                        NULL,
                        NULL,

                        PLUS_HALF_WIDTH,
                        MINUS_HALF_HEIGHT,
                        NULL,
                        NULL,

                        PLUS_HALF_WIDTH,
                        PLUS_HALF_HEIGHT,
                        NULL,
                        NULL
                };

    }

    public static final class Crop{

        static final int NULL = 0;
        static final int LEFT = 1;
        static final int RIGHT = 2;
        static final int TOP = 3;
        static final int BOTTOM = 4;

        public static final int[] VALUE =
                {
                        NULL,
                        NULL,
                        LEFT,
                        BOTTOM,

                        NULL,
                        NULL,
                        LEFT,
                        TOP,

                        NULL,
                        NULL,
                        RIGHT,
                        BOTTOM,

                        NULL,
                        NULL,
                        RIGHT,
                        TOP
                };

    }

    public static Images setImageCrop(final Images shape,
                                     final float left,
                                     final float right,
                                     final float top,
                                     final float bottom
    ){
        for (int i = 0; i < shape.vertex_data.length; i++){
            switch (Crop.VALUE[i]){
                case Crop.LEFT:
                    shape.vertex_data[i] = left;
                    break;
                case Crop.RIGHT:
                    shape.vertex_data[i] = right;
                    break;
                case Crop.TOP:
                    shape.vertex_data[i] = top;
                    break;
                case Crop.BOTTOM:
                    shape.vertex_data[i] = bottom;
                    break;
                case Crop.NULL:
                default:
                    break;

            }
        }
        return shape;
    }

    public static Images setWidth(final Images shape, final float width){
        final float half_width = width / 2f;
        for (int i = 0; i < shape.vertex_data.length; i++) {
            switch (POINTS.VALUE[i]) {
                case POINTS.MINUS_HALF_WIDTH:
                    shape.vertex_data[i] = -half_width;
                    break;
                case POINTS.PLUS_HALF_WIDTH:
                    shape.vertex_data[i] = half_width;
                    break;
                default:
                    break;
            }
        }
        return shape;
    }

    public static Images setHeight(final Images shape, final float height){
        final float half_height = height / 2f;
        for (int i = 0; i < shape.vertex_data.length; i++) {
            switch (POINTS.VALUE[i]) {
                case POINTS.MINUS_HALF_HEIGHT:
                    shape.vertex_data[i] = -half_height;
                    break;
                case POINTS.PLUS_HALF_HEIGHT:
                    shape.vertex_data[i] = half_height;
                    break;
                default:
                    break;
            }
        }
        return shape;
    }

}
