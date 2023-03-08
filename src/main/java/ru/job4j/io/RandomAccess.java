package ru.job4j.io;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccess {

    public static void main(String[] args)  {
        try {
            RandomAccessFile randomAccess = new RandomAccessFile("data/random.txt", "rw");
            randomAccess.writeInt(100);
            randomAccess.writeChar('A');
            randomAccess.writeBoolean(true);
            randomAccess.seek(0);
            System.out.println(randomAccess.readInt());
            System.out.println(randomAccess.readChar());
            System.out.println(randomAccess.readBoolean());
            System.out.println("-------------------------------------");
            /*
            *Также читать мы можем из любого места файла. Для этого переставим указатель на позицию 4,
            * чтобы считать записанный ранее char. На 4, так как записанный перед char тип int хранится в первых 4
            *  байтах (0, 1, 2, 3), соответственно, наш char записан в ячейках 4-5.
             */
            randomAccess.seek(4);
            System.out.println(randomAccess.readChar());
            /*
            *Например, мы хотим перезаписать символ A на символ B. После считывания char указатель у нас сейчас
            * находится на позиции 6 (4 байта int + 2 байта char, то есть позади указателя ячейки 0-5.
            *  Проверить положение указателя можно с помощью метода getFilePointer():
            */
            System.out.println(randomAccess.getFilePointer());
            /*
            *Чтобы переписать наш char, нужно установить позицию указателя прямо перед char, а далее записать char еще раз:
             */
            randomAccess.seek(4);
            randomAccess.writeChar('B');
            randomAccess.seek(4);
            System.out.println(randomAccess.readChar());
            /*
            *Важно! Запись данных в произвольном месте будет затирать существующие данные, поэтому запись в произвольном
            *  месте можно использовать только для замены данных в пределах того же типа (в том же диапазоне памяти).
            *  Также можно установить указатель в конец файла с помощью метода length() и дописать в файл новые данные:
             */
            randomAccess.seek(randomAccess.length());
            System.out.println("Положение указателя после boolean: " + randomAccess.getFilePointer());
            randomAccess.writeDouble(3.01);
            randomAccess.seek(7);
            System.out.println(randomAccess.readDouble());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}