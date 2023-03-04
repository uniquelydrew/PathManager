package org.andrewbernier.PathManager.generator;

import java.io.IOException;
import java.util.Objects;
import org.andrewbernier.PathManager.PathManager;
import org.andrewbernier.PathManager.models.Element;

public class PathGenerator extends PathManager {

  public PathGenerator() throws IOException {
  }

  public String lookupElement(String page, String elementName) {
    for(Element pageElement : pages){
      if(Objects.equals(pageElement.getName(), page)){
        return getXPathForMatchingElements(pageElement, elementName);
      }
    }
    return null;
  }

  public String getXPathForMatchingElements(Element element, String matchingName) {
    StringBuilder xPathBuilder = new StringBuilder();
    if (element.getName().equals(matchingName)) {
      xPathBuilder.append(element.getXPath());
    }
    for (Element child : element.getChildren()) {
      xPathBuilder.append(getXPathForMatchingElements(child, matchingName));
    }
    return xPathBuilder.toString();
  }

}
