package me.hsgamer.edublock.rs.samplegenerator;

import me.hsgamer.edublock.rs.samplegenerator.list.DataList;
import me.hsgamer.edublock.rs.samplegenerator.model.input.*;
import me.hsgamer.edublock.rs.samplegenerator.model.output.AccountWithProfileListResponse;
import me.hsgamer.edublock.rs.samplegenerator.model.output.ClassroomResponse;
import me.hsgamer.edublock.rs.samplegenerator.model.output.StringResponse;
import me.hsgamer.edublock.rs.samplegenerator.model.output.element.AccountWithProfileOutput;
import me.hsgamer.edublock.rs.samplegenerator.model.output.element.ClassroomOutput;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static UrlSupplier urlSupplier;
    private static HttpClient httpClient;

    public static void main(String[] args) throws Exception {
        String baseUrl = args.length > 0 ? args[0] : "http://localhost:7070";
        urlSupplier = path -> {
            String normalizedBaseUrl = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl;
            String normalizedPath = path.startsWith("/") ? path.substring(1) : path;
            return normalizedBaseUrl + "/" + normalizedPath;
        };
        httpClient = HttpClient.newHttpClient();

        // Create admin accounts
        List<Map.Entry<AccountWithProfileOutput, String>> adminTokenList = new ArrayList<>();
        for (var entry : DataList.adminList) {
            adminTokenList.add(createAccount(entry.profileUpdate(), "admin", null));
        }

        // Create staff accounts
        List<Map.Entry<AccountWithProfileOutput, String>> staffTokenList = new ArrayList<>();
        for (var entry : DataList.staffList) {
            staffTokenList.add(createAccount(entry.profileUpdate(), "staff", null));
        }
        String staffToken = staffTokenList.get(0).getValue();

        // Create student accounts
        List<Long> studentIdList = new ArrayList<>();
        for (var entry : DataList.studentList) {
            studentIdList.add(createAccount(entry.profileUpdate(), "student", staffToken).getKey().getAccount().getId());
        }

        // Create teacher accounts
        Map<Long, Long> teacherIdList = new HashMap<>();
        for (var entry : DataList.teacherList) {
            teacherIdList.put(createAccount(entry.profileUpdate(), "teacher", staffToken).getKey().getAccount().getId(), entry.subjectId());
        }
        long homeroomTeacherId = teacherIdList.keySet().stream().findFirst().orElseThrow();

        // Create classroom
        ClassroomOutput classroomOutput = createClassroom(staffToken, homeroomTeacherId, studentIdList, teacherIdList);
        System.out.println("Classroom: " + classroomOutput);
    }

    private static void assertResponse(HttpResponse<?> response) {
        if (response.statusCode() != 200) {
            throw new RuntimeException("Response code is not 200: " + response.statusCode());
        }
    }

    private static Map.Entry<AccountWithProfileOutput, String> createAccount(ProfileUpdate profileUpdate, String role, String updateProfileToken) throws IOException, InterruptedException {
        System.out.println("Creating " + role + " account for " + profileUpdate.getFirstName() + " " + profileUpdate.getLastName());

        AccountCreate accountCreate = new AccountCreate(profileUpdate.getFirstName(), profileUpdate.getLastName(), role);
        AccountCreateListInput accountCreateListInput = new AccountCreateListInput(List.of(accountCreate));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(urlSupplier.getUri("/account/list"))
                .headers("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(JsonUtil.toJson(accountCreateListInput)))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        assertResponse(response);
        AccountWithProfileListResponse accountWithProfileListResponse = JsonUtil.fromJson(response.body(), AccountWithProfileListResponse.class);
        AccountWithProfileOutput rawAccountWithProfileOutput = accountWithProfileListResponse.getData().get(0);

        String username = rawAccountWithProfileOutput.getAccount().getUsername();
        String password = "password";

        AccountLogin accountLogin = new AccountLogin(username, password);
        HttpRequest loginRequest = HttpRequest.newBuilder()
                .uri(urlSupplier.getUri("/login"))
                .headers("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(JsonUtil.toJson(accountLogin)))
                .build();
        HttpResponse<String> loginResponse = httpClient.send(loginRequest, HttpResponse.BodyHandlers.ofString());
        assertResponse(loginResponse);
        StringResponse stringResponse = JsonUtil.fromJson(loginResponse.body(), StringResponse.class);
        String token = stringResponse.getData();

        HttpRequest updateRequest;
        if (updateProfileToken == null) {
            updateRequest = HttpRequest.newBuilder()
                    .uri(urlSupplier.getUri("/account/self/profile"))
                    .headers("Content-Type", "application/json")
                    .headers("Authorization", "Bearer " + token)
                    .PUT(HttpRequest.BodyPublishers.ofString(JsonUtil.toJson(profileUpdate)))
                    .build();
        } else {
            updateRequest = HttpRequest.newBuilder()
                    .uri(urlSupplier.getUri("/account/" + rawAccountWithProfileOutput.getAccount().getId() + "/profile"))
                    .headers("Content-Type", "application/json")
                    .headers("Authorization", "Bearer " + updateProfileToken)
                    .PUT(HttpRequest.BodyPublishers.ofString(JsonUtil.toJson(profileUpdate)))
                    .build();
        }
        HttpResponse<String> updateResponse = httpClient.send(updateRequest, HttpResponse.BodyHandlers.ofString());
        assertResponse(updateResponse);

        return Map.entry(rawAccountWithProfileOutput, token);
    }

    private static ClassroomOutput createClassroom(String staffToken, long homeroomTeacherId, List<Long> studentIdList, Map<Long, Long> teacherIdList) throws IOException, InterruptedException {
        ClassCreate classCreate = new ClassCreate(
                "10A",
                10,
                2018,
                homeroomTeacherId
        );
        HttpRequest request = HttpRequest.newBuilder()
                .uri(urlSupplier.getUri("/classroom"))
                .headers("Content-Type", "application/json")
                .headers("Authorization", "Bearer " + staffToken)
                .POST(HttpRequest.BodyPublishers.ofString(JsonUtil.toJson(classCreate)))
                .build();

        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        assertResponse(response);
        ClassroomResponse classroomResponse = JsonUtil.fromJson(response.body(), ClassroomResponse.class);

        AccountListInput accountListInput = new AccountListInput(studentIdList);
        HttpRequest addStudentRequest = HttpRequest.newBuilder()
                .uri(urlSupplier.getUri("/classroom/" + classroomResponse.getData().getId() + "/student"))
                .headers("Content-Type", "application/json")
                .headers("Authorization", "Bearer " + staffToken)
                .POST(HttpRequest.BodyPublishers.ofString(JsonUtil.toJson(accountListInput)))
                .build();

        var addStudentResponse = httpClient.send(addStudentRequest, HttpResponse.BodyHandlers.ofString());
        assertResponse(addStudentResponse);

        List<TeacherWithSubjectInput> teacherWithSubjectInputList = new ArrayList<>();
        for (var entry : teacherIdList.entrySet()) {
            teacherWithSubjectInputList.add(new TeacherWithSubjectInput(entry.getKey(), entry.getValue()));
        }
        TeacherWithSubjectListInput teacherWithSubjectListInput = new TeacherWithSubjectListInput(teacherWithSubjectInputList);
        HttpRequest addTeacherRequest = HttpRequest.newBuilder()
                .uri(urlSupplier.getUri("/classroom/" + classroomResponse.getData().getId() + "/teacher"))
                .headers("Content-Type", "application/json")
                .headers("Authorization", "Bearer " + staffToken)
                .POST(HttpRequest.BodyPublishers.ofString(JsonUtil.toJson(teacherWithSubjectListInput)))
                .build();

        var addTeacherResponse = httpClient.send(addTeacherRequest, HttpResponse.BodyHandlers.ofString());
        assertResponse(addTeacherResponse);

        return classroomResponse.getData();
    }
}