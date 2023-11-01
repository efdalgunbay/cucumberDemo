package com.Demo.utilities;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.model.Attachment;
import com.github.seratch.jslack.api.model.block.SectionBlock;
import com.github.seratch.jslack.api.model.block.composition.MarkdownTextObject;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class slackMassage {





    public List<String> getCucumberReportStatuses() throws FileNotFoundException {
        JsonParser parser = new JsonParser();
        JsonArray originalJSONArray = parser.parse(new FileReader("target/cucumber.json")).getAsJsonArray();
        JsonArray jsonArray = removeDuplicateScenarios(originalJSONArray);
         int totalFailed = 0;
          int scenarioCount = 0;
         int passedScenarioCount = 0;
         int totalPassed = 0;
         int totalSkipped = 0;

         int totalSteps = 0;

        try {
            for (JsonElement element : jsonArray) {
                JsonObject lines = element.getAsJsonObject();
                JsonArray elements = lines.getAsJsonArray("elements");

                for (JsonElement el : elements) {
                    JsonObject scenarios = el.getAsJsonObject();
                    String elementType = scenarios.get("type").getAsString();

                    if ("scenario".equals(elementType)) {
                        scenarioCount++;

                        JsonArray stepResults = scenarios.getAsJsonArray("steps");
                        boolean isScenarioPassed = true;

                        for (JsonElement step : stepResults) {
                            JsonObject result = step.getAsJsonObject().getAsJsonObject("result");
                            String status = result.get("status").getAsString();
                            totalSteps += 1;
                            if ("passed".equals(status)) {
                                totalPassed++;
                            } else if ("failed".equals(status)) {
                                totalFailed++;
                                isScenarioPassed = false;
                            } else if ("skipped".equals(status)) {
                                totalSkipped++;
                                isScenarioPassed = false;
                            }
                        }

                        if (isScenarioPassed) {
                            passedScenarioCount++;
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> reportElements = new ArrayList<>();
        reportElements.add("Automation Test Report: ");
        reportElements.add("Total Scenarios: " + scenarioCount);
        reportElements.add("Passed Scenarios: " + passedScenarioCount);
        reportElements.add("Total steps: " + totalSteps);
        reportElements.add("Total Passed steps: " + totalPassed);
        reportElements.add("Total Failed: " + totalFailed);
        reportElements.add("Total Skipped: " + totalSkipped);
        return reportElements;
    }

    public JsonArray removeDuplicateScenarios(JsonArray jsonArray) {
        JsonArray cleanedArray = new JsonArray();
        Set<String> processedScenarios = new HashSet<>();

        for (JsonElement element : jsonArray) {
            JsonObject feature = element.getAsJsonObject();
            JsonArray elements = feature.getAsJsonArray("elements");
            JsonArray cleanedElements = new JsonArray();

            for (JsonElement el : elements) {
                JsonObject scenario = el.getAsJsonObject();
                String scenarioName = scenario.get("name").getAsString();

                if (!processedScenarios.contains(scenarioName)) {
                    cleanedElements.add(el);
                    processedScenarios.add(scenarioName);
                }
            }

            feature.add("elements", cleanedElements);
            cleanedArray.add(feature);
        }

        return cleanedArray;
    }
    public static String slackWrbHookUrl="https://hooks.slack.com/services/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";
    public String slackChannelId="C05JTFQ0Y7M";
    public void sendStatus() throws Exception
    {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
        String sendTime = currentDate.format(formatter);
        ConfigReader properties = new ConfigReader();
        InputStream input = null;
        try {
            String propName = "cucumber.json";
            String propPath = System.getProperty("user.dir") + propName;
            String propFilePath = propPath.replace("/", "\\");

            input = new FileInputStream(propFilePath);


        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //Slack Message Body View
        Payload payload = Payload.builder()
                .text("[Topic] PROD" + "\n\n\n")
                .attachments(Collections.singletonList(
                        Attachment.builder()
                                .color("#00B050")
                                .blocks(Collections.singletonList(
                                        SectionBlock.builder()
                                                .text(MarkdownTextObject.builder()
                                                        .text("*Regression test results on * " + sendTime + "\n\n" +
                                                                "Total test cases executed: " + getCucumberReportStatuses().get(1) + "\n\n\n" +
                                                                "\u2705 * Pass Count:* " + getCucumberReportStatuses().get(2) + "\n\n" +
                                                                "\u274C * Failed Count:* " + getCucumberReportStatuses().get(4) + "\n\n")
                                                        .build())
                                                .build()
                                ))
                                .build()
                ))
                .build();

        try {
            WebhookResponse webhookResponse = Slack.getInstance().send(slackWrbHookUrl, payload);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}

