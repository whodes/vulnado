package com.scalesec.vulnado;

import io.github.pixee.security.BoundedLineReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Cowsay {
  public static String run(String input) {
    ProcessBuilder processBuilder = new ProcessBuilder();
    String cmd = "/usr/games/cowsay '" + input + "'";
    System.out.println(cmd);
    processBuilder.command("bash", "-c", cmd);

    StringBuilder output = new StringBuilder();

    try {
      Process process = processBuilder.start();
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

      String line;
      while ((line = BoundedLineReader.readLine(reader, 5_000_000)) != null) {
        output.append(line + "\n");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return output.toString();
  }
}
