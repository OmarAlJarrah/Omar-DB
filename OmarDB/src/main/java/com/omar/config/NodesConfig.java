package com.omar.config;

import com.omar.model.db.impl.Master;
import com.omar.model.db.impl.NodesWrapper;
import com.omar.model.db.impl.Slave;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

@Configuration
public class NodesConfig {

  // Note: I know the following code is shit as fuck,
  // it needs to be rewritten from scratch, without
  // the static path, and with a cleaner code.
  // Though for the time being, and due to
  // lack of time, I am keeping it like this
  // temporarily. At least it works.
  // I hate my life
  // @OmarAlJarrah
  @Bean
  public NodesWrapper initNodes() throws FileNotFoundException {
    NodesWrapper nodesWrapper = new NodesWrapper();
    Yaml yaml = new Yaml();
    InputStream stream = new FileInputStream("/Users/oaljarrah/IdeaProjects/Omar-DB/docker.yaml");
    Map map = (Map) yaml.load(stream);

    Map<String, Map> nodes = (Map<String, Map>) map.get("services");

    for (var node : nodes.keySet()) {
      boolean isMaster = node.contains("m") && !node.contains("s");

      String port = (String) ((ArrayList) (nodes.get(node).get("ports"))).get(0);
      port = port.split(":")[0];

      if (isMaster) {
        Master master = new Master(port, new ArrayList<>());
        nodesWrapper.masters.putIfAbsent(node, master);
      } else {
        String parent = node.split("s")[0];
        Slave slave = new Slave(port);
        nodesWrapper.masters.get(parent).slaves().add(slave);
      }
    }
    return nodesWrapper;
  }
}
