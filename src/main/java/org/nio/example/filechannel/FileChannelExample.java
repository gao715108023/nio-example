package org.nio.example.filechannel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author gaochuanjun
 * @since 16/2/22
 */
public class FileChannelExample {

    public static void main(String[] args) {
        try {
            RandomAccessFile accessFile = new RandomAccessFile("data/nio-data.txt", "rw");
            FileChannel fileChannel = accessFile.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(48);
            int bytesRead = fileChannel.read(buffer); //读取数据到Buffer
            while (bytesRead != -1) {
                System.out.println("Read " + bytesRead);
                buffer.flip();//反转Buffer
                while (buffer.hasRemaining()) {
                    System.out.println((char) buffer.get()); //从Buffer中读取数据
                }
                buffer.clear();
                bytesRead = fileChannel.read(buffer);
            }
            fileChannel.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
