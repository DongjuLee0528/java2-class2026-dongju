# java2-class2026-dongju
# 202430219 이동주
# 2주차 수업
# Git 주요 명령어 정리

## 초기 설정

### git init
- 현재 폴더를 Git 저장소로 초기화함

### git clone <주소>
- 원격 저장소를 복제하여 로컬에 다운로드함


---

## 상태 확인

### git status
- 현재 파일 상태 확인 (추적 여부, 변경 여부 등)

### git log
- 커밋 히스토리 확인


---

## 파일 추가 및 커밋

### git add <파일명>
- 특정 파일을 스테이징 영역에 추가

### git add .
- 현재 폴더의 모든 변경 파일을 추가

### git commit -m "메시지"
- 스테이징된 파일들을 하나의 버전으로 저장


---

## 원격 저장소 작업

### git remote add origin <주소>
- 원격 저장소 연결

### git push origin <브랜치명>
- 로컬 커밋을 원격 저장소에 업로드

### git pull origin <브랜치명>
- 원격 저장소 변경사항을 내려받고 병합


---

## 브랜치 관리

### git branch
- 브랜치 목록 확인

### git branch <브랜치명>
- 새로운 브랜치 생성

### git checkout <브랜치명>
- 해당 브랜치로 이동

### git checkout -b <브랜치명>
- 브랜치 생성 + 이동 동시에 수행


---

## 병합

### git merge <브랜치명>
- 현재 브랜치에 다른 브랜치 내용을 합침


---

## 기타 유용한 명령어

### git diff
- 변경된 내용 상세 비교

### git reset --hard HEAD
- 마지막 커밋 상태로 완전히 되돌림 (주의)

### git stash
- 현재 작업 임시 저장

### git stash pop
- 임시 저장한 작업 다시 가져오기


#  마크다운
### 잘 몰라
...java
public class Main {
public static void main(String[] args) {
//TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
// to see how IntelliJ IDEA suggests fixing it.
System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
    }
}

...