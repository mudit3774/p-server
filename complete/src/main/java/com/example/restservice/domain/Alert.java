package com.example.restservice.domain;

import java.util.Objects;

public class Alert {
    private String host;
    private String app;
    private String maskedLine;
    private Long timestamp;
    private String filename;


    public Alert(String host, String app, String maskedLine, Long timestamp, String filename) {
        this.host = host;
        this.app = app;
        this.maskedLine = maskedLine;
        this.timestamp = timestamp;
        this.filename = filename;
    }

    public Alert() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alert alert = (Alert) o;
        return Objects.equals(host, alert.host) && Objects.equals(app, alert.app) && Objects.equals(maskedLine, alert.maskedLine) && Objects.equals(timestamp, alert.timestamp) && Objects.equals(filename, alert.filename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, app, maskedLine, timestamp, filename);
    }

    public String getHost() {
        return host;
    }

    public String getApp() {
        return app;
    }

    public String getMaskedLine() {
        return maskedLine;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getFilename() {
        return filename;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "host='" + host + '\'' +
                ", app='" + app + '\'' +
                ", maskedLine='" + maskedLine + '\'' +
                ", timestamp=" + timestamp +
                ", filename='" + filename + '\'' +
                '}';
    }
}