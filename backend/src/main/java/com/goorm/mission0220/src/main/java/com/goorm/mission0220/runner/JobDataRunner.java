package com.goorm.mission0220.runner;


import com.goorm.mission0220.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component // 스프링 빈으로 등록되어 자동으로 실행됨
@RequiredArgsConstructor
public class JobDataRunner implements CommandLineRunner {
    private final EmployeeService employeeService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>> [Runner] 데이터베이스 초기화 및 미션 명령 실행");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            boolean keepGoing = true;
            boolean anyInserted = false;
            String lastName = null;
            if (askYes(reader, "사원 정보를 입력하시겠습니까? (y/N): ")) {
                while (keepGoing) {
                    String name = readRequired(reader, "사원 이름을 입력하세요: ");
                    Long salary = Long.parseLong(readRequired(reader, "급여를 입력하세요(숫자): "));
                    String deptName = readRequired(reader, "부서명을 입력하세요: ");
                    String deptLoc = readRequired(reader, "부서 위치를 입력하세요: ");
                    String memo = readRequired(reader, "메모를 입력하세요 (선택): ", true);

                    employeeService.addEmployee(name, salary, employeeService.getOrCreateDepartment(deptName, deptLoc).getDptId(), memo);
                    anyInserted = true;
                    lastName = name;
                    keepGoing = askContinue(reader);
                    System.out.println();
                }
            }

            if (askYes(reader, "모든 사원 정보를 출력할까요? (y/N): ")) {
                employeeService.printAllEmployees();
            } else {
                String targetName = readRequired(reader, "검색할 사원 이름을 입력하세요 (빈 입력 시 검색 안 함): ", true);
                if (targetName != null && !targetName.isEmpty()) {
                    employeeService.printEmployeesByName(targetName);
                } else if (anyInserted && lastName != null) {
                    employeeService.printEmployeesByName(lastName);
                } else {
                    System.out.println("검색을 건너뜁니다.");
                }
            }

            if (!anyInserted) {
                System.out.println(">>> [Runner] 입력된 사원이 없습니다.");
            }
            System.out.println(">>> [Runner] 모든 명령이 성공적으로 완료됨");
        } catch (IllegalStateException e) {
            System.err.println(">>> [Runner] 콘솔 입력을 읽지 못했습니다. 터미널에서 직접 실행하거나 IDE의 run/debug 콘솔에서 입력을 허용해 주세요.");
            return;
        } catch (NumberFormatException e) {
            System.err.println(">>> [Runner] 급여는 숫자로 입력해야 합니다.");
            return;
        } catch (IllegalArgumentException e) {
            System.err.println(">>> [Runner] 입력 오류: " + e.getMessage());
            return;
        } catch (Exception e) {
            System.err.println(">>> [Runner] 미션 실행 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
            return;
        }
    }

    private String readRequired(BufferedReader reader, String prompt) throws IOException {
        System.out.print(prompt);
        String line = reader.readLine();
        if (line == null) {
            throw new IllegalStateException("콘솔 입력을 받을 수 없습니다.");
        }
        line = line.trim();
        if (line.isEmpty()) {
            throw new IllegalArgumentException("값을 입력해 주세요.");
        }
        return line;
    }

    private String readRequired(BufferedReader reader, String prompt, boolean allowEmpty) throws IOException {
        System.out.print(prompt);
        String line = reader.readLine();
        if (line == null) {
            throw new IllegalStateException("콘솔 입력을 받을 수 없습니다.");
        }
        line = line.trim();
        if (!allowEmpty && line.isEmpty()) {
            throw new IllegalArgumentException("값을 입력해 주세요.");
        }
        return line;
    }

    private boolean askContinue(BufferedReader reader) throws IOException {
        System.out.print("다른 사원을 추가하시겠습니까? (y/N): ");
        String line = reader.readLine();
        if (line == null) {
            throw new IllegalStateException("콘솔 입력을 받을 수 없습니다.");
        }
        line = line.trim().toLowerCase();
        return line.equals("y") || line.equals("yes");
    }

    private boolean askYes(BufferedReader reader, String prompt) throws IOException {
        System.out.print(prompt);
        String line = reader.readLine();
        if (line == null) {
            throw new IllegalStateException("콘솔 입력을 받을 수 없습니다.");
        }
        line = line.trim().toLowerCase();
        return line.equals("y") || line.equals("yes");
    }
}
