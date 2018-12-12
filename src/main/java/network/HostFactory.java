package network;

public class HostFactory {

    public static Host newHostForConfiguration(String hostName, String portNumber) {
        return new Host(hostName, Integer.parseInt(portNumber));
    }

}
