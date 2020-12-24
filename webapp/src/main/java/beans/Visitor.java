package beans;

import java.util.Date;

public class Visitor {
    private String IP;
    private String browser;
    private String browserVersion;
    private String OS;
    private String address;
    private Date time;

    @Override
    public String toString() {
        return "Visitor{" +
                "IP='" + IP + '\'' +
                ", browser='" + browser + '\'' +
                ", browserVersion='" + browserVersion + '\'' +
                ", OS='" + OS + '\'' +
                ", address='" + address + '\'' +
                ", time=" + time +
                '}';
    }

    public Visitor() {}

    public Visitor(String IP, String browser, String browserVersion, String OS, String address, Date time) {
        this.IP = IP;
        this.browser = browser;
        this.browserVersion = browserVersion;
        this.OS = OS;
        this.address = address;
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public void setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
