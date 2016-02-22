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
            ////create buffer with capacity of 48 bytes
            ByteBuffer buffer = ByteBuffer.allocate(48);
            int bytesRead = fileChannel.read(buffer); //读取数据到Buffer
            while (bytesRead != -1) {
                System.out.println("Read " + bytesRead);
                buffer.flip();//将Buffer从写模式切换到读模式
                while (buffer.hasRemaining()) {
                    System.out.println((char) buffer.get()); // read 1 byte at a time
                }
                buffer.clear();//make buffer ready for writing
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
