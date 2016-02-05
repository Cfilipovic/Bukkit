package com.cfil360.AdvancedBan.Util;

import com.cfil360.AdvancedBan.Objects.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.sql.*;
import java.util.UUID;

/**
 * Created by connor on 6/5/2014.
 */
public class MySQL {

    private Connection connection;

    public MySQL(String ip, String userName, String password, String db) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + db + "?user=" + userName + "&password=" + password);
        } catch (Exception e) {
            Bukkit.broadcastMessage(ChatColor.RED + "Unable to connect to MySQL database!");
        }
    }

//begin ban code
    public Ban getBan(UUID uuid) {
        try {
            PreparedStatement statement = connection.prepareStatement("select * from banLogger where uuid='" + uuid + "' order by id desc limit 1");
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                Ban ban = new Ban(uuid, result.getString("Moderator"), result.getTimestamp("Expiration"), result.getString("Reason"));
                result.close();
                statement.close();
                return ban;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public void ban(Ban ban) {
        //make sure there is only 1 ban at a time
        try {
            PreparedStatement statement = connection.prepareStatement("insert into banLogger (uuid, moderator, expiration, reason)\nvalues ('" + ban.getUUID() + "', '" + ban.getModerator() + "', '" + DateUtil.formatDate().format(ban.getExpiration()) + "', '" + ban.getReason() + "');");
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteBan(UUID uuid) {
        try {
            PreparedStatement statement = connection.prepareStatement("delete from banLogger where uuid='" + uuid + "'");
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getBanMessage(Ban ban) {
        return "§4>> §c§lBANNED §4<<\n" +
                            "§eYour access to " + "§dConnor's Server " + "§eis suspended.\n" +
                            "§eWhy? §f" + ban.getReason() + ".\n" +
                            "§eModerator? §f" + ban.getModerator() + " §eExpires? §f" + DateUtil.getBanLength(ban);
    }

    public int getBanCount(UUID uuid) {
        try {
            PreparedStatement statement = connection.prepareStatement("select * from banLogger where uuid='" + uuid + "'");
            ResultSet result = statement.executeQuery();

                int bans = 0;

                while(result.next()) bans++;

                return bans;
        } catch (Exception e) {
            return 0;
        }
    }
//end ban code

//begin kick code
    public Kick getKick(UUID uuid) {
        try {
            PreparedStatement statement = connection.prepareStatement("select * from kickLogger where uuid='" + uuid + "'");
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                Kick kick = new Kick(Bukkit.getPlayer(result.getString("UUID")).getUniqueId(), result.getString("Moderator"), result.getTimestamp("time"), result.getString("Reason"));
                result.close();
                statement.close();
                return kick;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public void kick(Kick kick) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into kickLogger (uuid, moderator, time, reason)\nvalues ('" + kick.getUUID() + "', '" + kick.getModerator() + "', '" + DateUtil.formatDate().format(kick.getTime()) + "', '" + kick.getReason() + "');");
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteKick(UUID uuid) {
        try {
            PreparedStatement statement = connection.prepareStatement("delete from kickLogger where uuid='" + uuid + "'");
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getKickMessage(Kick kick) {
        return "§4>> §c§lKICKED §4<<\n" +
                            "§eYour have been kicked from " + "§dConnor's Server.\n" +
                            "§eWhy? §f" + kick.getReason() + ".\n" +
                            "§eModerator? §f" + kick.getModerator() + " §eWarnings given? §f" + getWarningCount(kick.getUUID());
    }

    public int getKickCount(UUID uuid) {
        try {
            PreparedStatement statement = connection.prepareStatement("select * from kickLogger where uuid='" + uuid + "'");
            ResultSet result = statement.executeQuery();

            int kicks = 0;

            while(result.next()) kicks++;

            return kicks;
        } catch (Exception e) {
            return 0;
        }
    }
//end kick code

//begin mute code
    public Mute getMute(UUID uuid) {
        try {
            PreparedStatement statement = connection.prepareStatement("select * from muteLogger where uuid='" + uuid + "' order by id desc limit 1");
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                Mute mute = new Mute(uuid, result.getString("Moderator"), result.getTimestamp("Expiration"), result.getString("Reason"));
                result.close();
                statement.close();
                return mute;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public void mute(Mute mute) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into muteLogger (uuid, moderator, expiration, reason)\nvalues ('" + mute.getUUID() + "', '" + mute.getModerator() + "', '" + DateUtil.formatDate().format(mute.getExpiration()) + "', '" + mute.getReason() + "');");
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteMute(UUID uuid) {
        try {
            PreparedStatement statement = connection.prepareStatement("delete from muteLogger where uuid='" + uuid + "'");
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getMuteMessage(Mute mute) {
        return "§4>> §c§lMUTED §4<<\n" +
                "§eYou have been muted.\n" +
                "§eWhy? §f" + mute.getReason() + ".\n" +
                "§eModerator? §f" + mute.getModerator() + " §eExpires? §f" + DateUtil.getMuteLength(mute);
    }

    public int getMuteCount(UUID uuid) {
        try {
            PreparedStatement statement = connection.prepareStatement("select * from muteLogger where uuid='" + uuid + "'");
            ResultSet result = statement.executeQuery();

            int mutes = 0;

            while(result.next()) mutes++;

            return mutes;
        } catch (Exception e) {
            return 0;
        }
    }
//end mute code

//begin warn code
    public Warn getWarning(UUID uuid) {
        try {
            PreparedStatement statement = connection.prepareStatement("select * from warnLogger where uuid='" + uuid + "'");
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                Warn warn = new Warn(Bukkit.getPlayer(result.getString("UUID")).getUniqueId(), result.getString("Moderator"), result.getTimestamp("time"), result.getString("Reason"));
                result.close();
                statement.close();
                return warn;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
    public void warn(Warn warn) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into warnLogger (uuid, moderator, time, reason)\nvalues ('" + warn.getUUID() + "', '" + warn.getModerator() + "', '" + DateUtil.formatDate().format(warn.getTime()) + "', '" + warn.getReason() + "');");
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteWarn(UUID uuid) {
        try {
            PreparedStatement statement = connection.prepareStatement("delete from warnLogger where uuid='" + uuid + "'");
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getWarnMessage(Warn warn) {
        return "§4>> §c§lWARNED §4<<" + " §eYou have been warned by §f" + warn.getModerator() +" §efor §f" + warn.getReason() + ".";
    }

    public int getWarningCount(UUID uuid) {
        try {
            PreparedStatement statement = connection.prepareStatement("select * from warnLogger where uuid='" + uuid + "'");
            ResultSet result = statement.executeQuery();

            int warnings = 0;

            while(result.next()) warnings++;

            return warnings;
        } catch (Exception e) {
            return 0;
        }
    }
//end warn code

//begin report code
    public Report getReport(UUID uuid) {
        try {
            PreparedStatement statement = connection.prepareStatement("select * from reportLogger where uuid='" + uuid + "'");
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                Report report = new Report(result.getString("category"), Bukkit.getPlayer(result.getString("UUID")).getUniqueId(), result.getString("filedBy"), result.getTimestamp("happened"), result.getString("message"), result.getString("server"));
                result.close();
                statement.close();
                return report;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public void report(Report report) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into reportLogger (category, uuid, filedby, happened, message, server)\nvalues ('" + report.getCategory() + "', '" + report.getUUID() + "', '" + report.getFiledBy() + "', '" + DateUtil.formatDate().format(report.getHappened()) + "', '" + report.getMessage() + "', '" + report.getServer() + "');");
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteReport(UUID uuid) {
        try {
            PreparedStatement statement = connection.prepareStatement("delete from reportLogger where uuid='" + uuid + "'");
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getReportMessage(Report report) {
        return "§4>> §c§lREPORTED §4<<" + " §eYour have been reported by §f" + report.getFiledBy() +" §efor §f" + report.getMessage() + ".  " + report.getHappened();
    }

    public int getReportCount(UUID uuid) {
        try {
            PreparedStatement statement = connection.prepareStatement("select * from reportLogger where uuid='" + uuid + "'");
            ResultSet result = statement.executeQuery();

            int reports = 0;

            while(result.next()) reports++;

            return reports;
        } catch (Exception e) {
            return 0;
        }
    }
//end report code
}