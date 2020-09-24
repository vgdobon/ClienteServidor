package com;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.HashMap;

public class DescargaFichero {

    HashMap<String,String> webs = new HashMap<>();

    public DescargaFichero(){
        webs.put("mirrors","http://mirrors.kernel.org/ubuntu/pool/main/m/mysql-5.7/mysql-server-5.7_5.7.31-0ubuntu0.16.04.1_amd64.deb");
        webs.put("ftp","http://ftp.osuosl.org/pub/ubuntu/pool/main/m/mysql-5.7/mysql-server-5.7_5.7.31-0ubuntu0.16.04.1_amd64.deb");
        webs.put("za","http://za.archive.ubuntu.com/ubuntu/pool/main/m/mysql-5.7/mysql-server-5.7_5.7.31-0ubuntu0.16.04.1_amd64.deb");
    }

    public void descargarFichero(String url, DataOutputStream dataOutputStream){
        try {
            ReadableByteChannel inputChannel = Channels.newChannel(new URL(webs.get(url)).openStream());
            WritableByteChannel outputChannel = Channels.newChannel(dataOutputStream);

            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

            while (inputChannel.read(buffer) != -1) {
                buffer.flip();
                outputChannel.write(buffer);
                buffer.clear();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
