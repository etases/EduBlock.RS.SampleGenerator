package me.hsgamer.edublock.rs.samplegenerator;

import me.hsgamer.edublock.rs.samplegenerator.data.AdminAccountCreateAndProfile;
import me.hsgamer.edublock.rs.samplegenerator.data.StaffAccountCreateAndProfile;
import me.hsgamer.edublock.rs.samplegenerator.data.StudentAccountCreateAndProfile;
import me.hsgamer.edublock.rs.samplegenerator.data.TeacherAccountCreateAndProfile;
import me.hsgamer.edublock.rs.samplegenerator.list.FakerUtil;
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
import java.util.concurrent.ThreadLocalRandom;

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
        List<AdminAccountCreateAndProfile> adminList = FakerUtil.createDataList(2, faker -> new AdminAccountCreateAndProfile(
                new ProfileUpdate(
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.demographic().sex().equalsIgnoreCase("male"),
                        faker.internet().avatar(),
                        faker.date().birthday(20, 21),
                        faker.address().fullAddress(),
                        faker.phoneNumber().subscriberNumber(10),
                        faker.internet().emailAddress()
                )
        ));
        List<Map.Entry<AccountWithProfileOutput, String>> adminTokenList = new ArrayList<>();
        for (var entry : adminList) {
            adminTokenList.add(createAccount(entry.profileUpdate(), "admin", null));
        }

        // Create staff accounts
        List<StaffAccountCreateAndProfile> staffList = FakerUtil.createDataList(10, faker -> new StaffAccountCreateAndProfile(
                new ProfileUpdate(
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.demographic().sex().equalsIgnoreCase("male"),
                        faker.internet().avatar(),
                        faker.date().birthday(20, 21),
                        faker.address().fullAddress(),
                        faker.phoneNumber().subscriberNumber(10),
                        faker.internet().emailAddress()
                )
        ));
        List<Map.Entry<AccountWithProfileOutput, String>> staffTokenList = new ArrayList<>();
        for (var entry : staffList) {
            staffTokenList.add(createAccount(entry.profileUpdate(), "staff", null));
        }
        String staffToken = staffTokenList.get(0).getValue();

        // Create teacher accounts
        List<ProfileUpdate> teacherProfileList = FakerUtil.createDataList(15, faker ->
                new ProfileUpdate(
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.demographic().sex().equalsIgnoreCase("male"),
                        faker.internet().avatar(),
                        faker.date().birthday(20, 21),
                        faker.address().fullAddress(),
                        faker.phoneNumber().subscriberNumber(10),
                        faker.internet().emailAddress()
                )
        );
        List<TeacherAccountCreateAndProfile> teacherList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            teacherList.add(new TeacherAccountCreateAndProfile(
                    teacherProfileList.get(i),
                    i + 1
            ));
        }
        Map<Long, Long> teacherIdList = new HashMap<>();
        Map<String, Long> teacherTokenList = new HashMap<>();
        for (var entry : teacherList) {
            var accountWithProfileOutputEntry = createAccount(entry.profileUpdate(), "teacher", staffToken);
            teacherIdList.put(accountWithProfileOutputEntry.getKey().getAccount().getId(), entry.subjectId());
            teacherTokenList.put(accountWithProfileOutputEntry.getValue(), entry.subjectId());
        }
        long homeroomTeacherId = teacherIdList.keySet().stream().findFirst().orElseThrow();

        // Create student accounts
        List<StudentAccountCreateAndProfile> studentList = FakerUtil.createDataList(30, faker -> StudentAccountCreateAndProfile.of(
                new ProfileUpdate(
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.demographic().sex().equalsIgnoreCase("male"),
                        faker.internet().avatar(),
                        faker.date().birthday(17, 18),
                        faker.address().fullAddress(),
                        faker.phoneNumber().subscriberNumber(10),
                        faker.internet().emailAddress()
                )
        ));
        List<Long> studentIdList = new ArrayList<>();
        for (var entry : studentList) {
            studentIdList.add(createAccount(entry.profileUpdate(), "student", staffToken).getKey().getAccount().getId());
        }
        // Create classroom
        ClassCreate classCreate = new ClassCreate(
                "10A",
                10,
                2018,
                homeroomTeacherId
        );
        ClassroomOutput classroomOutput = createClassroom(classCreate, staffToken, studentIdList, teacherIdList);
        teacherTokenList.forEach((teacherToken, subjectId) -> {
            try {
                updateRecordEntry(classroomOutput, teacherToken, subjectId, studentIdList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println("Classroom: " + classroomOutput);
        ClassCreate classCreate1 = new ClassCreate(
                "11A",
                11,
                2019,
                homeroomTeacherId
        );
        ClassroomOutput classroomOutput1 = createClassroom(classCreate1, staffToken, studentIdList, teacherIdList);
        teacherTokenList.forEach((teacherToken, subjectId) -> {
            try {
                updateRecordEntry(classroomOutput1, teacherToken, subjectId, studentIdList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println("Classroom: " + classroomOutput1);
        ClassCreate classCreate2 = new ClassCreate(
                "12A",
                12,
                2020,
                homeroomTeacherId
        );
        ClassroomOutput classroomOutput2 = createClassroom(classCreate2, staffToken, studentIdList, teacherIdList);
        teacherTokenList.forEach((teacherToken, subjectId) -> {
            try {
                updateRecordEntry(classroomOutput2, teacherToken, subjectId, studentIdList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println("Classroom: " + classroomOutput2);

        // Create another student accounts
        List<StudentAccountCreateAndProfile> studentList1 = FakerUtil.createDataList(30, faker -> StudentAccountCreateAndProfile.of(
                new ProfileUpdate(
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.demographic().sex().equalsIgnoreCase("male"),
                        faker.internet().avatar(),
                        faker.date().birthday(17, 18),
                        faker.address().fullAddress(),
                        faker.phoneNumber().subscriberNumber(10),
                        faker.internet().emailAddress()
                )
        ));
        List<Long> studentIdList1 = new ArrayList<>();
        for (var entry : studentList1) {
            studentIdList1.add(createAccount(entry.profileUpdate(), "student", staffToken).getKey().getAccount().getId());
        }
        // Create another classroom
        ClassCreate classCreate3 = new ClassCreate(
                "10B",
                10,
                2018,
                homeroomTeacherId
        );
        ClassroomOutput classroomOutput3 = createClassroom(classCreate3, staffToken, studentIdList1, teacherIdList);
        teacherTokenList.forEach((teacherToken, subjectId) -> {
            try {
                updateRecordEntry(classroomOutput3, teacherToken, subjectId, studentIdList1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println("Classroom: " + classroomOutput3);
        ClassCreate classCreate4 = new ClassCreate(
                "11B",
                11,
                2019,
                homeroomTeacherId
        );
        ClassroomOutput classroomOutput4 = createClassroom(classCreate4, staffToken, studentIdList1, teacherIdList);
        teacherTokenList.forEach((teacherToken, subjectId) -> {
            try {
                updateRecordEntry(classroomOutput4, teacherToken, subjectId, studentIdList1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println("Classroom: " + classroomOutput4);
        ClassCreate classCreate5 = new ClassCreate(
                "12B",
                12,
                2020,
                homeroomTeacherId
        );
        ClassroomOutput classroomOutput5 = createClassroom(classCreate5, staffToken, studentIdList1, teacherIdList);
        teacherTokenList.forEach((teacherToken, subjectId) -> {
            try {
                updateRecordEntry(classroomOutput5, teacherToken, subjectId, studentIdList1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println("Classroom: " + classroomOutput5);
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

    private static ClassroomOutput createClassroom(ClassCreate classCreate, String staffToken, List<Long> studentIdList, Map<Long, Long> teacherIdList) throws IOException, InterruptedException {
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

    private static void updateRecordEntry(ClassroomOutput classroom, String teacherToken, long subjectId, List<Long> studentIds) throws Exception {
        for (var studentId : studentIds) {
            PendingRecordEntryInput input = new PendingRecordEntryInput(
                    studentId,
                    classroom.getId(),
                    ThreadLocalRandom.current().nextFloat() * 10,
                    ThreadLocalRandom.current().nextFloat() * 10,
                    ThreadLocalRandom.current().nextFloat() * 10,
                    subjectId
            );
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(urlSupplier.getUri("/record/entry"))
                    .headers("Content-Type", "application/json")
                    .headers("Authorization", "Bearer " + teacherToken)
                    .POST(HttpRequest.BodyPublishers.ofString(JsonUtil.toJson(input)))
                    .build();

            var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            assertResponse(response);
        }
    }
}