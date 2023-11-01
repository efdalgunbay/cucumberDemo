package com.Demo.utilities;
import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.methods.MethodsClient;
import com.github.seratch.jslack.api.methods.SlackApiException;
import com.github.seratch.jslack.api.methods.request.files.FilesUploadRequest;
import com.github.seratch.jslack.api.methods.response.files.FilesUploadResponse;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class slacksend {
    public void uploadFile() throws IOException {
        String reportName = "Automation_Results.html";
        String reportPath = System.getProperty("user.dir") + "target/cucumber.html" + reportName;
        String reportFilePath = reportPath.replace("/", "\\");
        String filePath = reportFilePath;
        String fileName = "Automation_Results.html";
        String fileType = "text/html";

        Slack slack = Slack.getInstance();
        MethodsClient methods = slack.methods(ConfigReader.getProperty("slackToken"));

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File not found.");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sendTime = dateFormat.format(new Date());

        String fileNameWithTime = sendTime + "_" + fileName;

        List<String> channelids = new ArrayList<String>();
        channelids.add(ConfigReader.getProperty("slackChannelId"));
        FilesUploadRequest request = FilesUploadRequest.builder()
                .file(file)
                .filename(fileNameWithTime)
                .filetype(fileType)
                .channels(channelids)
                .build();

        try {
            FilesUploadResponse response = methods.filesUpload(request);
            if (response.isOk()) {
                System.out.println("File uploaded successfully!");
            } else {
                System.out.println("File upload failed. Error: " + response.getError());
            }
        } catch (IOException | SlackApiException e) {
            e.printStackTrace();
        }
    }
}




