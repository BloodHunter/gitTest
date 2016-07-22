package com.wbl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created with Simple_love
 * Date: 2016/4/27.
 * Time: 20:28
 */
public class Main {

        public static void main(String[] args) throws IOException {
                FileChannel channel = FileChannel.open(Paths.get("absolute.txt"), StandardOpenOption.READ,
                        StandardOpenOption.CREATE,StandardOpenOption.WRITE);
                ByteBuffer writeBuffer = ByteBuffer.allocate(4).putChar('A').putChar('B');
                writeBuffer.flip();
                channel.write(writeBuffer);
                ByteBuffer readBuffer = ByteBuffer.allocate(2);
                channel.read(readBuffer,1026);
                readBuffer.flip();
                System.out.println(readBuffer.getChar());
        }
}
