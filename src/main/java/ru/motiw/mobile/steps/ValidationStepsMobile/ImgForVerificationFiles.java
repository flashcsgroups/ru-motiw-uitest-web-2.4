package ru.motiw.mobile.steps.ValidationStepsMobile;


import java.awt.image.BufferedImage;

import static ru.motiw.utils.LoadImage.loadImage;

/**
 * Изображения для проверки файлов
 */
public class ImgForVerificationFiles {
    private static final String MAIN_FILES_PATH = "img.files/";

//    public static final BufferedImage IMAGE_PEN = loadImage(MAIN_FILES_PATH + "pen.png");
//    public static final BufferedImage IMAGE_MARKER = loadImage(MAIN_FILES_PATH + "marker.png");
     public static final BufferedImage IMAGE_PEN_AND_MARKER_ON_IMG= loadImage(MAIN_FILES_PATH + "pen_and_marker_on_img.png");
     public static final BufferedImage IMAGE_ERASER_ON_IMG = loadImage(MAIN_FILES_PATH + "eraser_on_img.png");
     public static final BufferedImage IMAGE_FIRST_AND_SECOND_ANNOTATION_ON_IMG = loadImage(MAIN_FILES_PATH + "first_and_second_annotation_on_img.png");

    public static final BufferedImage IMAGE_PEN_ON_PDF = loadImage(MAIN_FILES_PATH + "pen_on_pdf.png");
    public static final BufferedImage IMAGE_MARKER_ON_PDF = loadImage(MAIN_FILES_PATH + "marker_on_pdf.png");
    public static final BufferedImage IMAGE_PEN_AND_MARKER_ON_PDF = loadImage(MAIN_FILES_PATH + "pen_and_marker_on_pdf.png");
    public static final BufferedImage IMAGE_ERASER_ON_PDF = loadImage(MAIN_FILES_PATH + "eraser_on_pdf.png");
    public static final BufferedImage IMAGE_FIRST_AND_SECOND_ANNOTATION_ON_PDF = loadImage(MAIN_FILES_PATH + "first_and_second_annotation_on_pdf.png");
}
