package org.perilouscodpiece.dicebot;

import java.io.*;
import java.util.*;
import org.jibble.pircbot.*;

public class Main {
    public static void main(String[] args) {
        // read configs
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("dicebot.properties"));
        } catch (IOException ioe) {
            System.err.println("Could not load dicebot.properties: " +
                               ioe.getMessage());
            System.exit(1);
        }
        
        String server = props.getProperty("server");
        if (server == null) {
            System.err.println("Please set the 'server' property.");
            System.exit(1);
        }

        String channel = props.getProperty("channel");
        if (channel == null) {
            System.err.println("Please set the 'channel' property.");
            System.exit(1);
        }

        String adminPassword = props.getProperty("adminpassword");
        if (adminPassword == null) {
            System.err.println("Please set the 'adminpassword' property.");
            System.exit(1);
        }

        String nick = props.getProperty("nick", "dicebot");
        String antifloodthreshold = props.getProperty("antifloodthreshold", "30");

        // instantiate dicebot
        DiceBot bot = new DiceBot(nick, adminPassword);
        bot.setVerbose(false);
        bot.setAntiFloodThreshold(antifloodthreshold);

        // connect
        try {
            bot.connect(server);
            bot.joinChannel("#" + channel);
        } catch (IOException ioe) {
            System.err.println("Woah! Something exploded trying to connect to " + server + " or joining channel #" + channel + ":" + ioe.getMessage());
            ioe.printStackTrace();
            System.exit(1);
        } catch (IrcException irce) {
            System.err.println("IRC exception encountered: " + irce.getMessage());
            irce.printStackTrace();
            System.exit(1);
        }
    }
}
