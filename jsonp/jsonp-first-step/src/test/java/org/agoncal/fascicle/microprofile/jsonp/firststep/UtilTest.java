package org.agoncal.fascicle.microprofile.jsonp.firststep;

import com.jayway.jsonpath.JsonPath;

import jakarta.json.JsonObject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// tag::adocBegin[]
public class UtilTest {

  public static BufferedWriter initBufferedWriter(String filePath) throws FileNotFoundException {
    File fout = new File(filePath);
    FileOutputStream fos = new FileOutputStream(fout);
    return new BufferedWriter(new OutputStreamWriter(fos));
  }

  // ======================================
  // =              Unit tests            =
  // ======================================

  public static void output(BufferedWriter bw, JsonObject json, String tag) throws IOException {
    JsonbConfig config = new JsonbConfig().withFormatting(true);
    Jsonb jsonb = JsonbBuilder.create(config);


    bw.write("// tag::" + tag + "[]\n");
    bw.write(jsonb.toJson(json));
    bw.write("\n");
    bw.write("// end::" + tag + "[]\n");
  }

  public static Object jsonPath(String json, String jsonPath) {
    return JsonPath.read(json, jsonPath);
  }
}
