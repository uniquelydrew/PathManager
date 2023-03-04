package org.andrewbernier.PathManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.andrewbernier.PathManager.models.Element;

public class PathManager {

  private String directoryPath = System.getProperty("json.directory.path");
  private File directory = new File(directoryPath);
  protected List<Element> pages = new ArrayList<>();

  protected ObjectMapper objectMapper = new ObjectMapper();

  public PathManager() throws IOException {
    loadFiles();
  }

  private void loadFiles() throws IOException {
    for (File file : directory.listFiles()) {
      if (file.isFile() && file.getName().endsWith(".json")) {
        Element element = objectMapper.readValue(file, Element.class);
        pages.add(element);
      }
    }

  }

}
