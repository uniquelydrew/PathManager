package org.andrewbernier.PathManager.models;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Element {

  private String name;

  private String type;

  private List<Attribute> attributes = new ArrayList<>();

  private List<Element> children = new ArrayList<>();

  public String getXPath(){
    StringBuilder builder = new StringBuilder();
    boolean isFirst = true;
    builder.append("/web:");
    builder.append(type);
    builder.append("[");
    for(Attribute attribute : attributes){
      if(!isFirst){
        builder.append(attribute.isRequired() ? " and " : " or ");
      }
      builder.append(String.format("@%s='%s", attribute.getType(), attribute.getValue()));
      isFirst = false;
    }
    builder.append("]");

    return builder.toString();
  }
}
