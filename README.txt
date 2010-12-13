dicebot -- a simple dice rolling irc bot

author: orion@perilouscodpiece.org
license: public domain, do whatever you want and have fun :)

to build:
    1) run "ant" in this directory (see "build.xml" for the gory details)

to run: 
    1) make dicebot.properties
        standard java properties file format
        e.g.:
        server: irc.example.com
        channel: somechannel
        nick: dicebot
        adminpassword: somepassword

        (note, '#' is a comment char, so omit it from 
         the channel name; dicebot will automatically
         prepend '#' to the given channel name)

        server, channel and adminpassword are required.  if nick is
        omitted, "dicebot" will be used.

    2) java -jar dicebot.jar

    3) commands:
        public commands (in a channel:)
            !roll XdY[+-Z]
            !vroll " " "
                will roll X dice of size Y, modifying the total by Z
                v(erbose) roll reports each die rolled

            !dicehelp 
                prints a short usage summary

        admin commands (in private message:)
            (for all, {password} is the admin password specified in
             the properties file as discussed above)
            
            !join channel password
                tells dicebot to join the given channel
            !leave channel password
                tells dicebot to leave the given channel
            !quit password
                tells dicebot to disconnect
            !op channel user password
                directs dicebot to op user {user} in channel {channel}

MISC NOTES:
    1) dicebot does support multi-channel usage (either by following invites
        or by admin commands), but by default the irc library uses one message
        queue per server, and one second per message, so if many users are
        sending many rolls (esp. verbose rolls) the single queue may take some
        time to clear through hence users might experience some lag before getting
        the results they had requested. Figuring out if there's some way to at least
        have per-channel queuing is a matter for later research.
