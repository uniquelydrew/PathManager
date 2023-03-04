package org.andrewbernier.PathManager.pageify;

import java.io.File;
import java.io.IOException;
import org.andrewbernier.PathManager.PathManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.andrewbernier.PathManager.models.Attribute;

public class Pageify extends PathManager {


  public Pageify() throws IOException {
  }

  public static org.andrewbernier.PathManager.models.Element getPageElements(String htmlFilePath) throws Exception {
    Document doc = Jsoup.parse(new File(htmlFilePath), "UTF-8");
    org.andrewbernier.PathManager.models.Element windowElement = new org.andrewbernier.PathManager.models.Element();
    windowElement.setName("window");
    windowElement.setType("window");
    Elements interactableElements = doc.select("input, textarea, select, button");
    for (Element interactableElement : interactableElements) {
      org.andrewbernier.PathManager.models.Element element = new org.andrewbernier.PathManager.models.Element();
      element.setName(interactableElement.tagName());
      element.setType(interactableElement.tagName());
      // Add required attributes to element
      Attributes htmlAttributes = interactableElement.attributes();
      for (org.jsoup.nodes.Attribute htmlAttribute : htmlAttributes) {
        if (isAttributeRequired(htmlAttribute.getKey())) {
          Attribute attribute = new Attribute();
          attribute.setType(htmlAttribute.getKey());
          attribute.setValue(htmlAttribute.getValue());
          attribute.setRequired(true);
          element.getAttributes().add(attribute);
        }
      }
      windowElement.getChildren().add(element);
    }
    return windowElement;
  }

  private static boolean isAttributeRequired(String attributeName) {
    // Define which attributes are required for interactable elements
    switch (attributeName) {
      case "name":
      case "id":
      case "class":
      case "type":
        return true;
      default:
        return false;
    }
  }
}
