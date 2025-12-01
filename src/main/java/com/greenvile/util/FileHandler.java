package com.greenvile.util;

import com.greenvile.model.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private String dataFilePath = "data/greenvile_data.txt";
    private String websiteDataPath = "website/data/website_data.json";

    public void saveData(DataManager data) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(dataFilePath));
            
            writer.println("POINT_SETTINGS");
            writer.println(data.getPointSettings().getResetFrequency());
            writer.println(data.getPointSettings().getLastResetDate());
            writer.println(data.getCommunalPointsPool());
            writer.println("END_POINT_SETTINGS");
            
            writer.println("RESIDENTS");
            for (Resident r : data.getResidents()) {
                writer.println(r.getId() + "|" + r.getFullName() + "|" + r.getPhoneNumber() + "|" + 
                    r.getEmail() + "|" + r.getAddress() + "|" + r.getPicturePath() + "|" + r.getPersonalPoints());
            }
            writer.println("END_RESIDENTS");
            
            writer.println("GREEN_ACTIONS");
            for (GreenAction a : data.getGreenActions()) {
                String participants = "";
                for (int i = 0; i < a.getParticipantIds().size(); i++) {
                    participants += a.getParticipantIds().get(i);
                    if (i < a.getParticipantIds().size() - 1) {
                        participants += ",";
                    }
                }
                writer.println(a.getId() + "|" + a.getTitle() + "|" + a.getDescription() + "|" + 
                    a.getPicturePath() + "|" + a.getPointsAwarded() + "|" + a.isDisplayOnWebsite() + "|" + participants);
            }
            writer.println("END_GREEN_ACTIONS");
            
            writer.println("COMMUNAL_GOALS");
            for (CommunalGoal g : data.getCommunalGoals()) {
                writer.println("GOAL|" + g.getId() + "|" + g.getTitle() + "|" + g.getDescription() + "|" + 
                    g.getPointsNeeded() + "|" + g.getCurrentPoints() + "|" + g.isCompleted());
                for (CommunalTask t : g.getTasks()) {
                    writer.println("TASK|" + t.getId() + "|" + t.getTitle() + "|" + t.getDescription() + "|" + 
                        t.getPointsAwarded() + "|" + t.isCompleted() + "|" + t.isDisplayOnWebsite());
                }
            }
            writer.println("END_COMMUNAL_GOALS");
            
            writer.println("TRADES");
            for (Trade t : data.getTrades()) {
                writer.println(t.getId() + "|" + t.getTitle() + "|" + t.getDescription() + "|" + 
                    t.getPicturePath() + "|" + t.getPointsCost() + "|" + t.getResidentId() + "|" + 
                    t.isCompleted() + "|" + t.isDisplayOnWebsite());
            }
            writer.println("END_TRADES");
            
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public DataManager loadData() {
        DataManager data = new DataManager();
        File file = new File(dataFilePath);
        
        if (!file.exists()) {
            return data;
        }
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            String section = "";
            CommunalGoal currentGoal = null;
            
            while ((line = reader.readLine()) != null) {
                if (line.equals("POINT_SETTINGS")) {
                    section = "POINT_SETTINGS";
                    String frequency = reader.readLine();
                    String lastReset = reader.readLine();
                    int pool = Integer.parseInt(reader.readLine());
                    data.setPointSettings(new PointSettings(frequency, lastReset));
                    data.setCommunalPointsPool(pool);
                } else if (line.equals("RESIDENTS")) {
                    section = "RESIDENTS";
                } else if (line.equals("GREEN_ACTIONS")) {
                    section = "GREEN_ACTIONS";
                } else if (line.equals("COMMUNAL_GOALS")) {
                    section = "COMMUNAL_GOALS";
                } else if (line.equals("TRADES")) {
                    section = "TRADES";
                } else if (line.startsWith("END_")) {
                    section = "";
                    currentGoal = null;
                } else if (section.equals("RESIDENTS") && !line.isEmpty()) {
                    String[] parts = line.split("\\|", -1);
                    Resident r = new Resident(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        parts[2],
                        parts[3],
                        parts[4],
                        parts[5],
                        Integer.parseInt(parts[6])
                    );
                    data.getResidents().add(r);
                } else if (section.equals("GREEN_ACTIONS") && !line.isEmpty()) {
                    String[] parts = line.split("\\|", -1);
                    GreenAction a = new GreenAction(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        parts[2],
                        parts[3],
                        Integer.parseInt(parts[4])
                    );
                    a.setDisplayOnWebsite(Boolean.parseBoolean(parts[5]));
                    if (parts.length > 6 && !parts[6].isEmpty()) {
                        String[] pIds = parts[6].split(",");
                        for (String pid : pIds) {
                            a.addParticipant(Integer.parseInt(pid));
                        }
                    }
                    data.getGreenActions().add(a);
                } else if (section.equals("COMMUNAL_GOALS") && !line.isEmpty()) {
                    if (line.startsWith("GOAL|")) {
                        String[] parts = line.substring(5).split("\\|", -1);
                        currentGoal = new CommunalGoal(
                            Integer.parseInt(parts[0]),
                            parts[1],
                            parts[2],
                            Integer.parseInt(parts[3])
                        );
                        currentGoal.setCurrentPoints(Integer.parseInt(parts[4]));
                        currentGoal.setCompleted(Boolean.parseBoolean(parts[5]));
                        data.getCommunalGoals().add(currentGoal);
                    } else if (line.startsWith("TASK|") && currentGoal != null) {
                        String[] parts = line.substring(5).split("\\|", -1);
                        CommunalTask t = new CommunalTask(
                            Integer.parseInt(parts[0]),
                            parts[1],
                            parts[2],
                            Integer.parseInt(parts[3])
                        );
                        t.setCompleted(Boolean.parseBoolean(parts[4]));
                        t.setDisplayOnWebsite(Boolean.parseBoolean(parts[5]));
                        currentGoal.addTask(t);
                    }
                } else if (section.equals("TRADES") && !line.isEmpty()) {
                    String[] parts = line.split("\\|", -1);
                    Trade t = new Trade(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        parts[2],
                        parts[3],
                        Integer.parseInt(parts[4]),
                        Integer.parseInt(parts[5])
                    );
                    t.setCompleted(Boolean.parseBoolean(parts[6]));
                    t.setDisplayOnWebsite(Boolean.parseBoolean(parts[7]));
                    data.getTrades().add(t);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
        
        return data;
    }

    public void exportToWebsite(DataManager data) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(websiteDataPath));
            
            writer.println("{");
            
            writer.println("  \"greenActions\": [");
            List<GreenAction> displayActions = new ArrayList<>();
            for (GreenAction a : data.getGreenActions()) {
                if (a.isDisplayOnWebsite()) {
                    displayActions.add(a);
                }
            }
            for (int i = 0; i < displayActions.size(); i++) {
                GreenAction a = displayActions.get(i);
                writer.println("    {");
                writer.println("      \"title\": \"" + escapeJson(a.getTitle()) + "\",");
                writer.println("      \"description\": \"" + escapeJson(a.getDescription()) + "\",");
                writer.println("      \"picture\": \"" + escapeJson(a.getPicturePath()) + "\"");
                writer.print("    }");
                if (i < displayActions.size() - 1) {
                    writer.println(",");
                } else {
                    writer.println();
                }
            }
            writer.println("  ],");
            
            writer.println("  \"communalGoals\": [");
            for (int i = 0; i < data.getCommunalGoals().size(); i++) {
                CommunalGoal g = data.getCommunalGoals().get(i);
                writer.println("    {");
                writer.println("      \"title\": \"" + escapeJson(g.getTitle()) + "\",");
                writer.println("      \"description\": \"" + escapeJson(g.getDescription()) + "\",");
                writer.println("      \"pointsNeeded\": " + g.getPointsNeeded() + ",");
                writer.println("      \"currentPoints\": " + g.getCurrentPoints() + ",");
                writer.println("      \"completed\": " + g.isCompleted() + ",");
                writer.println("      \"tasks\": [");
                List<CommunalTask> displayTasks = new ArrayList<>();
                for (CommunalTask t : g.getTasks()) {
                    if (t.isDisplayOnWebsite()) {
                        displayTasks.add(t);
                    }
                }
                for (int j = 0; j < displayTasks.size(); j++) {
                    CommunalTask t = displayTasks.get(j);
                    writer.println("        {");
                    writer.println("          \"title\": \"" + escapeJson(t.getTitle()) + "\",");
                    writer.println("          \"description\": \"" + escapeJson(t.getDescription()) + "\",");
                    writer.println("          \"completed\": " + t.isCompleted());
                    writer.print("        }");
                    if (j < displayTasks.size() - 1) {
                        writer.println(",");
                    } else {
                        writer.println();
                    }
                }
                writer.println("      ]");
                writer.print("    }");
                if (i < data.getCommunalGoals().size() - 1) {
                    writer.println(",");
                } else {
                    writer.println();
                }
            }
            writer.println("  ],");
            
            writer.println("  \"trades\": [");
            List<Trade> displayTrades = new ArrayList<>();
            for (Trade t : data.getTrades()) {
                if (t.isDisplayOnWebsite() && !t.isCompleted()) {
                    displayTrades.add(t);
                }
            }
            for (int i = 0; i < displayTrades.size(); i++) {
                Trade t = displayTrades.get(i);
                writer.println("    {");
                writer.println("      \"title\": \"" + escapeJson(t.getTitle()) + "\",");
                writer.println("      \"description\": \"" + escapeJson(t.getDescription()) + "\",");
                writer.println("      \"picture\": \"" + escapeJson(t.getPicturePath()) + "\",");
                writer.println("      \"pointsCost\": " + t.getPointsCost());
                writer.print("    }");
                if (i < displayTrades.size() - 1) {
                    writer.println(",");
                } else {
                    writer.println();
                }
            }
            writer.println("  ],");
            
            writer.println("  \"communalPointsPool\": " + data.getCommunalPointsPool());
            
            writer.println("}");
            
            writer.close();
        } catch (IOException e) {
            System.out.println("Error exporting to website: " + e.getMessage());
        }
    }

    private String escapeJson(String text) {
        if (text == null) {
            return "";
        }
        return text.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n");
    }

    public boolean fileExists() {
        File file = new File(dataFilePath);
        return file.exists();
    }
}
