package ru.motiw.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class LoadImage {

    /**
     * Загрузка изображения для последующего сравнения
     *
     * @param path путь к файлу
     */
    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(ClassLoader.getSystemResourceAsStream(path));
        } catch (Throwable e) {
            System.err.println("Не найден файл в папке img для проверки!");
            throw new RuntimeException(e);
        }
    }

}
