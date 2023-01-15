package com.javamsdt.filestore;

import java.util.Arrays;

public class Runner {
  public static void main(String[] args) {
    String imageName = "Egyptian.in Poland Notes.png";
    int lastDot =  imageName.lastIndexOf(".");
    System.out.println(imageName.substring(0, lastDot));
    System.out.println(imageName.substring(lastDot));
  }
}
