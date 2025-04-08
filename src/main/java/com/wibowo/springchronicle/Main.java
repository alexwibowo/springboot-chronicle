package com.wibowo.springchronicle;

import net.openhft.chronicle.queue.ExcerptAppender;
import net.openhft.chronicle.queue.impl.single.SingleChronicleQueue;
import net.openhft.chronicle.queue.impl.single.SingleChronicleQueueBuilder;
import net.openhft.chronicle.wire.WireType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootApplication
public class Main {
    public static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        final Path chronicleTest = Files.createTempDirectory("chronicleTest");
        LOGGER.info("Chronicle test path: {}", chronicleTest.toAbsolutePath());

        SingleChronicleQueue cq =  SingleChronicleQueueBuilder.builder()
                .path(chronicleTest)
                .wireType(WireType.BINARY_LIGHT)
                .readOnly(false)
                .build();
        ExcerptAppender appender = cq.createAppender();
        EntryListener build = appender.methodWriterBuilder(EntryListener.class).build();


    }
}
