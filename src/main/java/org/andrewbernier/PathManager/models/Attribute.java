package org.andrewbernier.PathManager.models;

import lombok.Data;

@Data
public class Attribute {

  private String type;

  private String value;

  private boolean isRequired = false;

}
