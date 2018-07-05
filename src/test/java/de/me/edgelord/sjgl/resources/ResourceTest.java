package de.me.edgelord.sjgl.resources;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Test;

public class ResourceTest {

    @Test
    public void readResources() throws URISyntaxException, IOException {
        final ClassLoader classLoader = getClass().getClassLoader();
        final URL url = classLoader.getResource("test/notes.txt");
        final Path path = Paths.get(url.toURI());
        Files.lines(path, StandardCharsets.UTF_8).forEach(System.out::println);
    }
}
