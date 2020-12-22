package com.gc.common.entity;

/**
 * @author join wick
 * @version 1.0.0
 * @description node record
 * @createDate 2020/12/22 10:39
 * @since 1.0.0
 */
public class NodeRecord {
    // node ip address
    private String nodeIP;
    // node port
    private int nodePort;
    // node connection status
    private EnumEntity.ConnectionStatus connectionStatus;
    // node type
    private EnumEntity.NodeType nodeType;

    public String getNodeIP() {
        return nodeIP;
    }

    public void setNodeIP(String nodeIP) {
        this.nodeIP = nodeIP;
    }

    public int getNodePort() {
        return nodePort;
    }

    public void setNodePort(int nodePort) {
        this.nodePort = nodePort;
    }

    public EnumEntity.ConnectionStatus getConnectionStatus() {
        return connectionStatus;
    }

    public void setConnectionStatus(EnumEntity.ConnectionStatus connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    public EnumEntity.NodeType getNodeType() {
        return nodeType;
    }

    public void setNodeType(EnumEntity.NodeType nodeType) {
        this.nodeType = nodeType;
    }

    @Override
    public String toString() {
        return "NodeRecord{" +
                "nodeIP='" + nodeIP + '\'' +
                ", nodePort=" + nodePort +
                ", connectionStatus=" + connectionStatus +
                ", nodeType=" + nodeType +
                '}';
    }
}
