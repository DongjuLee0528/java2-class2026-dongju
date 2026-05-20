# java2-class2026-dongju
# 202430219 이동주

## 12주차 정리 (5월 20일)

---

## 1. 중간고사 풀이 — 배수 찾기 프로그램

시작 수와 끝 수를 입력받고, 그 사이에 있는 특정 수의 배수 개수와 목록을 출력하는 프로그램

### 핵심 흐름

1. `data[0]` — 시작 수 저장
2. `data[1]` — 끝 수 저장
3. `data[2]` — 찾을 배수 저장
4. 시작 수가 끝 수보다 크면 두 값 교환 (swap)
5. 범위 안에서 배수 개수(cnt) 계산
6. 배수 개수만큼 배열 생성
7. 배수들을 배열에 저장
8. 결과 출력

### 전체 코드

```java
public class Midterm {
    public static void main(String[] args) {
        java.util.Scanner input = new java.util.Scanner(System.in);
        int[] data = new int[3];

        System.out.print("시작 수를 입력하세요: ");
        data[0] = input.nextInt();

        System.out.print("끝 수를 입력하세요: ");
        data[1] = input.nextInt();

        System.out.print("찾고자 하는 배수를 입력하세요: ");
        data[2] = input.nextInt();

        if (data[0] > data[1]) {
            int swap = data[0];
            data[0] = data[1];
            data[1] = swap;
        }

        int from = data[0], to = data[1], num = data[2];
        int cnt = 0;

        for (int i = from; i <= to; i++) {
            if (i % num == 0) cnt++;
        }

        int[] arr = new int[cnt];
        int idx = 0;
        for (int i = from; i <= to; i++) {
            if (i % num == 0) arr[idx++] = i;
        }

        System.out.println(from + "에서 " + to + "까지 사이의 " + num + "의 배수는 " + cnt + "개입니다.");
        System.out.println("그 수는 다음과 같습니다.");
        System.out.print("[");
        for (int val : arr) System.out.print(val + " ");
        System.out.println("]");

        input.close();
    }
}
```

### 포인트 정리

| 코드 | 의미 |
|------|------|
| `if (data[0] > data[1])` | 입력 순서 무관하게 항상 작은 수부터 탐색 |
| `i % num == 0` | i를 num으로 나눈 나머지가 0 → num의 배수 |
| cnt 먼저 계산 후 배열 생성 | 배열 크기를 정확히 맞추기 위해 2번 순회 |

---

## 2. 이벤트 기반 프로그래밍 (Event-Driven Programming)

프로그램의 흐름이 **이벤트 발생 여부에 따라 결정되는 방식**

### 일반 프로그램 vs 이벤트 기반 프로그램

| 구분 | 일반 프로그램 (배치 처리) | 이벤트 기반 프로그램 |
|------|------------------------|-------------------|
| 실행 방식 | 개발자가 순서 결정, 순차 실행 | 이벤트 발생 시에만 코드 실행 |
| 흐름 결정 | 개발자 | 사용자 (일부) |
| 예시 | 1번→2번→3번 실행 | 버튼 클릭 → 코드 실행 |

### 이벤트 종류

| 분류 | 예시 |
|------|------|
| 사용자 입력 이벤트 | 마우스 클릭, 드래그, 키보드 입력 |
| 시스템/외부 이벤트 | 센서 입력, 네트워크 데이터 수신, 다른 프로그램 메시지 |

### GUI 프로그램과 이벤트 기반

GUI 프로그램은 거의 전부 이벤트 기반 — Java Swing / AWT, Android, Visual Basic, C# GUI, MFC

### 자바 GUI 라이브러리

| 라이브러리 | 설명 |
|-----------|------|
| AWT | 자바 초기 GUI 라이브러리 |
| Swing | AWT를 확장한 GUI 라이브러리. 더 발전된 형태 |

---

## 3. 이벤트 객체 (Event Object)

- 발생한 이벤트에 관한 정보를 가진 객체
- 이벤트 리스너에 전달되어, 리스너 코드가 이벤트 상황을 파악할 수 있게 함

### 이벤트 객체가 포함하는 정보

- 이벤트 종류와 이벤트 소스
- 이벤트가 발생한 화면 좌표 및 컴포넌트 내 좌표
- 이벤트가 발생한 버튼이나 메뉴 아이템의 문자열
- 클릭된 마우스 버튼 번호 및 마우스의 클릭 횟수
- 키의 코드 값과 문자 값
- 체크박스, 라디오버튼 등의 체크 상태

### 이벤트 소스 알아내는 메소드

```java
Object getSource()
// 발생한 이벤트의 소스 컴포넌트 리턴
// Object 타입으로 리턴 → 캐스팅하여 사용
// 모든 이벤트 객체에 적용 가능
```

### 이벤트 객체 계층 & 주요 메소드

| 클래스 | 주요 메소드 |
|--------|-----------|
| EventObject | `getSource()` |
| ActionEvent | `getActionCommand()` |
| InputEvent | `getModifiers()` |
| MouseEvent | `getButton()`, `getClickCount()`, `getPoint()`, `getX()`, `getY()` |
| KeyEvent | `getKeyChar()`, `getKeyCode()`, `getKeyText()` |
| ItemEvent | `getItem()`, `getStateChange()` |

---

## 4. 이벤트 객체 · 소스 · 발생 경우

| 이벤트 객체 | 이벤트 소스 | 발생하는 경우 |
|------------|-----------|-------------|
| ActionEvent | JButton | 마우스나 `<Enter>` 키로 버튼 선택 |
| ActionEvent | JMenuItem | 메뉴 아이템 선택 |
| ActionEvent | JTextField | 텍스트 입력 중 `<Enter>` 키 입력 |
| ItemEvent | JCheckBox | 체크박스의 선택 혹은 해제 |
| ItemEvent | JRadioButton | 라디오 버튼의 선택 상태가 변할 때 |
| ItemEvent | JCheckBoxMenuItem | 체크박스 메뉴 아이템의 선택 혹은 해제 |
| ListSelectionEvent | JList | 리스트에 선택된 아이템이 변경될 때 |
| KeyEvent | Component | 키가 눌러지거나 눌러진 키가 떼어질 때 |
| MouseEvent | Component | 마우스 버튼 누름/뗌/클릭/올라감/내려감/드래그/이동 |
| FocusEvent | Component | 컴포넌트가 포커스를 받거나 잃을 때 |
| WindowEvent | Window | 윈도우 활성화/비활성화/아이콘화/열기/닫기/종료 |
| ComponentEvent | Component | 컴포넌트가 사라지거나 나타나거나 이동/크기 변경 시 |
| ContainerEvent | Container | Container에 컴포넌트 추가 혹은 삭제 시 |

---

## 5. 리스너 인터페이스 (Listener Interface)

- **이벤트 리스너** : 이벤트를 처리하는 자바 프로그램 코드, 클래스로 작성
- 자바의 리스너 인터페이스(interface)를 상속받아 구현
- 리스너 인터페이스의 **모든 추상 메소드 구현** 필수

### 주요 리스너 인터페이스

| 리스너 인터페이스 | 설명 | 주요 메소드 |
|----------------|------|-----------|
| ActionListener | 버튼 클릭 이벤트 처리 | `actionPerformed(ActionEvent e)` |
| MouseListener | 마우스 조작 이벤트 처리 | `mousePressed`, `mouseReleased`, `mouseClicked`, `mouseEntered`, `mouseExited` |

### ActionListener 인터페이스

```java
interface ActionListener {
    public void actionPerformed(ActionEvent e);
    // Action 이벤트 발생시 호출됨
}
```

### MouseListener 인터페이스

```java
interface MouseListener {
    public void mousePressed(MouseEvent e);   // 버튼이 눌러지는 순간 호출
    public void mouseReleased(MouseEvent e);  // 눌러진 버튼이 떼어지는 순간 호출
    public void mouseClicked(MouseEvent e);   // 마우스가 클릭되는 순간 호출
    public void mouseEntered(MouseEvent e);   // 컴포넌트 위에 올라가는 순간 호출
    public void mouseExited(MouseEvent e);    // 컴포넌트 위에서 내려오는 순간 호출
}
```

---

## 6. 이벤트 리스너 작성 과정

### Step 1. 이벤트와 리스너 선택

| 이벤트 | 이벤트 리스너 |
|--------|-------------|
| ActionEvent | ActionListener |

### Step 2. 이벤트 리스너 클래스 작성

```java
class MyActionListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource(); // 클릭된 버튼 가져오기
        if (b.getText().equals("Action"))
            b.setText("액션");
        else
            b.setText("Action");
    }
}
```

### Step 3. 이벤트 리스너 등록

```java
MyActionListener listener = new MyActionListener(); // 리스너 인스턴스 생성
btn.addActionListener(listener);                    // 리스너 등록
```

> 형식: `component.addXXXListener(listener)` — xxx는 이벤트 명

---

## 7. 이벤트 리스너 작성 3가지 방법

| 작성 방법 | 설명 | 특징 |
|----------|------|------|
| 독립 클래스 | 이벤트 리스너를 완전한 별도 클래스로 작성 | 여러 곳에서 재사용할 때 적합 |
| 내부 클래스 (inner class) | 클래스 안에 멤버처럼 클래스 작성 | 특정 클래스에서만 사용할 때 적합 |
| 익명 클래스 (anonymous class) | 클래스 이름 없이 간단히 작성 | 리스너 코드가 간단한 경우 적합 |

---

## 8. 방법 1 — 독립 클래스로 작성

```java
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class IndepClassListener extends JFrame {
    public IndepClassListener() {
        setTitle("Action 이벤트 리스너 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        JButton btn = new JButton("Action");
        btn.addActionListener(new Ex91MyActionListener()); // 독립 리스너 등록
        c.add(btn);

        setSize(250, 120);
        setVisible(true);
    }
    public static void main(String[] args) {
        new IndepClassListener();
    }
}

// 독립 클래스로 이벤트 리스너 작성
class Ex91MyActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        if (b.getText().equals("Action"))
            b.setText("액션");
        else
            b.setText("Action");
    }
}
```

### 구성 요소 분석

| 구성 요소 | 설명 |
|----------|------|
| `JFrame` 상속 | 윈도우 창 생성 |
| `JButton` | 버튼 생성 |
| `ActionListener` | 버튼 클릭 이벤트 처리 인터페이스 |
| `actionPerformed()` | 버튼 클릭 시 자동 호출되는 메소드 |
| `getSource()` | 이벤트가 발생한 객체 반환 |
| `setText()` | 버튼 문자열 변경 |

### 독립 클래스 방식 특징

- 리스너 클래스를 별도로 작성
- 여러 컴포넌트에서 **재사용 가능**
- 클래스 수가 많아질 수 있음

---

## 9. 방법 2 — 익명 클래스(Anonymous Class)로 작성

- 이름 없는 클래스
- **클래스 선언 + 인스턴스 생성을 한 번에** 수행
- 메소드 개수가 1~2개인 리스너에 주로 사용 (ActionListener, ItemListener)

### 형식

```java
new 슈퍼클래스이름(생성자인자들) {
    // 익명 클래스의 멤버 구현
};
```

### 이름 있는 클래스 vs 익명 클래스 비교

| 이름 있는 클래스 | 익명 클래스 |
|----------------|-----------|
| `class MyActionListener implements ActionListener { public void actionPerformed(ActionEvent e) { ... } }` | 클래스 정의 없음 |
| `b.addActionListener(new MyActionListener());` | `b.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { ... } });` |

### 익명 클래스 특징

- 클래스 이름이 없음
- 클래스 작성과 객체 생성을 **동시에** 수행
- 코드가 짧고 간단함
- **재사용에는 부적합**

## 11주차 정리 (5월 13일)

---

## 1. GUI (Graphical User Interface)

GUI는 버튼, 창(Window), 메뉴, 텍스트박스 같은 그래픽 요소를 제공해서 사용자가 프로그램을 쉽게 사용할 수 있도록 하는 인터페이스다.

- 마우스 클릭, 키보드 입력, 화면 시각적 조작 가능
- 반대 개념: CLI(Command Line Interface) — 터미널에서 명령어 입력 방식
- GUI 프로그램 예시: 계산기 앱, 메모장, 크롬 브라우저, 카카오톡 창

자바에서는 대표적으로 **AWT**와 **Swing** 패키지를 사용한다.

---

## 2. AWT (Abstract Window Toolkit)

자바 초창기 GUI 패키지. `import java.awt.*;` 로 사용한다.

### 핵심 특징

**① Heavy Weight Component (중량 컴포넌트)**
운영체제(OS)의 실제 GUI 자원을 직접 사용한다. 자바가 직접 그리는 게 아니라 운영체제가 대신 그린다.

**② 운영체제 의존적**
OS마다 버튼 모양이 다르게 보일 수 있다. (Windows / Linux / Mac 전부 다름)

**③ 속도는 빠름, 하지만 단점 존재**
운영체제가 직접 그리므로 렌더링 속도는 빠르지만, 메모리 사용량이 크고 플랫폼 차이가 발생한다.

### 요즘 AWT를 거의 안 쓰는 이유

| 문제 | 설명 |
|------|------|
| OS 의존 | 화면 모양 달라짐 |
| 무거움 | 자원 많이 사용 |
| 유연성 부족 | 디자인 변경 어려움 |
| 기능 제한 | 현대 GUI 개발에 부족 |

현재는 대부분 **Swing** 또는 **JavaFX**를 사용한다.

---

## 3. Swing 패키지

AWT를 기반으로 만든 자바 GUI 라이브러리. AWT 기능을 포함하면서 더 풍부한 GUI 기능을 제공하고, 현재 자바 GUI 표준처럼 사용된다.

### 핵심 특징

**① AWT 기반 라이브러리** — 기존 AWT를 확장한 형태

**② 고급 컴포넌트 제공** — 메뉴, 테이블, 트리 구조, 탭 화면, 다중 텍스트 영역 등

**③ 컴포넌트 이름 앞에 J 사용** — AWT 컴포넌트를 Swing 방식으로 다시 만든 것

**④ Light Weight Component** — 운영체제 자원을 직접 사용하지 않고 자바 내부에서 GUI를 처리한다.
- OS 의존성 감소, 플랫폼 독립성 증가
- Windows / Mac / Linux에서 거의 동일한 화면 제공 가능

### AWT vs Swing 비교

| AWT | Swing |
|-----|-------|
| Heavy Weight | Light Weight |
| OS가 그림 | 자바가 그림 |
| 플랫폼 영향 큼 | 플랫폼 영향 적음 |
| 기능 적음 | 기능 많음 |
| 오래된 기술 | 현재 표준 수준 |

---

## 4. Swing 컴포넌트

### 기본 입력 컴포넌트

| 컴포넌트 | 역할 | 특징 |
|---------|------|------|
| JButton | 버튼 | 클릭 가능한 버튼 (로그인, 확인 등) |
| JCheckBox | 다중 선택 | 여러 개 동시에 선택 가능 (약관 동의, 취미 선택 등) |
| JRadioButton | 단일 선택 | 여러 개 중 하나만 선택 (성별, 결제 방식 등) |
| JSlider | 값 조절 | 막대를 움직여 값 조절 (음량, 밝기 등) |
| JTextField | 한 줄 입력 | 텍스트 한 줄 입력 (이름, 주소 등) |
| JPasswordField | 비밀번호 입력 | 입력값이 * 또는 점으로 표시됨 |
| JSpinner | 값 증가/감소 | 날짜, 수량 선택 등 |
| JTextArea | 여러 줄 입력 | 긴 문장 입력 (게시글, 메모 등) |
| JComboBox | 드롭다운 | 목록 중 하나 선택, 클릭 시 목록 펼쳐짐 |
| JList | 목록 출력 | 여러 데이터를 리스트 형태로 표시 |

### 고급 컴포넌트

| 컴포넌트 | 역할 | 특징 |
|---------|------|------|
| JProgressBar | 진행 상태 표시 | 다운로드 진행률, 로딩 상태 등 |
| JToolTip | 도움말 표시 | 마우스를 올렸을 때 설명 나타남 |
| JScrollPane | 스크롤 기능 | 내용이 많을 때 세로/가로 스크롤바 생성 |
| JMenu | 메뉴바 | 프로그램 상단 메뉴 (File, Edit, Save 등) |
| JDialog | 대화상자 | 메인 창 위에 뜨는 경고창, 알림창 등 |
| JApplet | 브라우저 실행 | 과거 웹에서 사용, 현재는 거의 사용 안 함 |
| JFrame | 메인 창 | GUI 프로그램의 기본 창, 대부분의 시작점 |
| JTable | 표 형태 데이터 | 행/열 구조, 데이터 수정 및 셀 단위 관리 가능 |
| JTree | 계층 구조 데이터 | 파일 탐색기, 폴더 구조 등 펼치기/접기 가능 |
| JEditorPane | HTML/문서 출력 | HTML 지원, URL 기반 문서 출력 가능 |
| JTextPane | 스타일 적용 텍스트 | 글자 색상, 폰트 변경, 이미지 삽입 가능 |
| JToolBar | 도구 모음 | 자주 쓰는 기능 버튼 모음 (IDE, 워드 등 상단 배치) |
| JTabbedPane | 탭 화면 전환 | 하나의 창에서 여러 화면 관리 (브라우저 탭 등) |
| JSplitPane | 화면 분할 | 창을 두 영역으로 나눔, 경계선 크기 조절 가능 |

---

## 5. GUI 패키지 계층 구조

GUI 클래스들은 상속 구조로 연결되어 있다. 최상위는 `Object`에서 시작한다.

### AWT 계층 흐름

```
Object
└ Component    ← 모든 GUI 요소의 기본 클래스
  └ Container  ← 다른 컴포넌트를 담을 수 있는 클래스
```

**AWT 주요 클래스**

| 클래스 | 역할 |
|--------|------|
| Button | 버튼 |
| Label | 글자 표시 |
| Checkbox | 체크박스 |
| Frame | 기본 창 |
| Dialog | 대화상자 |
| Panel | 컴포넌트 묶음 |

### Swing 계층 흐름

Swing 컴포넌트들은 대부분 `JComponent`를 상속받는다.

```
AWT → 기본 GUI 구조
Swing → AWT 확장 + 고급 기능 추가 (대부분 J로 시작)
```

---

## 6. 컨테이너와 컴포넌트

### 컨테이너 (Container)

다른 컴포넌트를 포함할 수 있는 GUI 객체. 버튼, 입력창, 체크박스 같은 것들을 담는 역할. 다른 컨테이너 안에 중첩 가능.

| AWT 컨테이너 | Swing 컨테이너 |
|-------------|--------------|
| Panel | JPanel |
| Frame | JFrame |
| Applet | JApplet |
| Dialog | JDialog |
| Window | JWindow |

### 컴포넌트 (Component)

실제로 화면에 출력되는 GUI 요소 (버튼, 텍스트 입력창, 체크박스 등). 반드시 컨테이너 안에 포함되어야 화면에 출력 가능하고, 일반 컴포넌트는 다른 컴포넌트를 포함하지 못한다.

- 모든 GUI 컴포넌트 기본 클래스: `java.awt.Component`
- Swing 컴포넌트 기본 클래스: `javax.swing.JComponent`

### 최상위 컨테이너

다른 컨테이너 안에 들어가지 않아도 독립적으로 화면에 출력 가능한 컨테이너.
- 대표: `JFrame`, `JDialog`, `JApplet`
- 스스로 창(Window)을 생성 가능하고, GUI 프로그램 시작점 역할

### 컨테이너/컴포넌트 포함 관계

GUI 프로그램은 **컨테이너 안에 컴포넌트를 넣는 구조**다.

```
JFrame                  ← 최상위 컨테이너 (메인 창)
└ JPanel                ← 중간 컨테이너 (컴포넌트 묶음)
  ├ JLabel
  ├ JTextField
  ├ JCheckBox
  └ JButton
```

---

## 7. Swing 프레임 (JFrame)

모든 Swing 컴포넌트를 담는 최상위 컨테이너. GUI 프로그램의 메인 창 역할.

- `JFrame`을 상속받아 GUI 프로그램 구현 가능
- 컴포넌트들은 JFrame 안에 있어야 화면 출력 가능
- 프레임을 닫으면 내부 컴포넌트도 함께 사라짐

### JFrame 기본 구성

```
JFrame
├ Menu Bar      ← 메뉴들이 배치되는 공간 (File, Edit, Window 등)
└ Content Pane  ← 실제 GUI 컴포넌트들이 올라가는 핵심 영역
  ├ JButton
  ├ JTextField
  └ JCheckBox
```

---

## 8. JFrame 클래스 상속으로 프레임 만들기

Swing 프레임은 보통 `extends JFrame` 방식으로 제작한다.

### 주요 메소드

| 메소드 | 설명 |
|--------|------|
| `setTitle("제목")` | 프레임 상단 제목 표시줄 설정 |
| `setSize(300, 300)` | 프레임 크기 설정 (가로, 세로 / 픽셀 단위) |
| `setVisible(true)` | 프레임을 실제 화면에 출력 (없으면 창이 안 보임) |
| `setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)` | X 버튼 클릭 시 프로그램 완전 종료 |

### 기본 예제 코드

```java
package week11;
import javax.swing.JFrame;

public class Java0513 extends JFrame {

    public Java0513() {
        setTitle("300x300 스윙 프레임 만들기");
        setSize(300, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Java0513();
    }
}
```

전체 실행 흐름: `main()` → 객체 생성 → 생성자 호출 → 제목 설정 → 크기 설정 → 화면 출력

---

## 9. Swing 응용프로그램에서 main()의 역할

`main()`은 프로그램 실행 시작 역할만 수행하고, 실제 GUI 작업은 프레임 클래스 내부에서 처리하는 것이 좋다.

- 버튼 생성, 이벤트 처리, 화면 배치 등을 `main()`에 전부 넣으면 코드 관리가 어려워짐
- 실무에서는 `new MyFrame();` 형태로 바로 생성 (변수 저장 불필요)
- `frame` 변수 선언 후 미사용 시 warning 발생 — 객체는 생성했지만 이후에 참조하지 않기 때문
- **SwingUtilities.invokeLater()** — GUI를 안전하게 실행하기 위한 코드. 이벤트 처리 스레드(EDT)에서 GUI를 실행해 Swing GUI 안정성을 높인다. 현재 단계에서는 "GUI 실행 전용 코드" 정도로 이해하면 충분

---

## 10. 컨텐트팬 (Content Pane)에 컴포넌트 붙이기

Swing에서는 컴포넌트를 **컨텐트팬(Content Pane)** 에 붙여서 화면에 출력한다.

### 타이틀 설정 방법

```java
super("타이틀문자열");    // 부모(JFrame) 생성자 호출하며 제목 설정
setTitle("타이틀문자열"); // 프레임 제목 설정
```

### 컨텐트팬 얻기 및 컴포넌트 추가

```java
Container contentPane = getContentPane(); // 현재 JFrame의 컨텐트팬 가져오기
contentPane.add(button);                  // 버튼을 컨텐트팬에 추가
```

### JDK 1.5 전후 방식 비교

| 구분 | 방식 | 코드 예시 |
|------|------|----------|
| JDK 1.5 이전 | ContentPane 객체 직접 사용 | `getContentPane().add(new JButton("OK"))` |
| JDK 1.5 이후 | JFrame에 바로 add() | `add(new JButton("OK"))` |

JDK 1.5 이후에는 `frame.add()`를 사용해도 내부적으로 ContentPane에 자동 추가된다. 구조 이해를 위해서는 ContentPane을 직접 다루는 방식을 익히는 것이 좋다.

전체 흐름: `JFrame 생성` → `ContentPane 얻기` → `컴포넌트 생성` → `add()로 부착` → `화면 출력`

### 예제 코드 비교

**Ex82ContentPaneEx.java** — JDK 1.5 이전 스타일 (ContentPane 직접 다루기)

```java
import javax.swing.*;
import java.awt.*;

public class Ex82ContentPaneEx extends JFrame {

    public Ex82ContentPaneEx() {
        setTitle("ContentPane과 JFrame 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setBackground(Color.ORANGE);
        contentPane.setLayout(new FlowLayout());
        contentPane.add(new JButton("OK"));
        contentPane.add(new JButton("Cancel"));
        contentPane.add(new JButton("Ignore"));

        setSize(300, 150);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Ex82ContentPaneEx();
    }
}
```

**Ex821ContentPaneEx.java** — JDK 1.5 이후 스타일 (JFrame에 직접 add)

```java
import javax.swing.*;
import java.awt.*;

public class Ex821ContentPaneEx extends JFrame {

    public Ex821ContentPaneEx() {
        setTitle("ContentPane과 JFrame 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setBackground(Color.ORANGE);
        getContentPane().setLayout(new FlowLayout());
        add(new JButton("OK"));
        add(new JButton("Cancel"));
        add(new JButton("Ignore"));

        setSize(300, 150);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Ex821ContentPaneEx();
    }
}
```

두 코드는 결과 화면이 거의 동일하고, 버튼이 붙는 실제 위치는 둘 다 ContentPane이다. 현재는 코드가 간결한 Ex821 방식(JDK 1.5 이후)을 더 많이 사용한다.

---

## 11. Swing 응용프로그램 종료

- **프로그램 내부에서 강제 종료:** `System.exit(0);`
- **X 버튼 클릭 시 프로그램까지 종료:** `setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);`

기본적으로는 프레임 창만 닫히고(invisible 상태), JVM 자체는 계속 실행된다. `setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)`를 설정해야 프레임 종료 시 JVM도 함께 종료된다.

`setVisible(true)`를 다시 호출하면 이전 상태 그대로 창을 다시 출력할 수 있다.

흐름: `X 버튼 클릭` → `프레임 종료` → `EXIT_ON_CLOSE 확인` → `JVM 종료` → `프로그램 종료`

---

## 12. 배치 관리자 (Layout Manager)

컴포넌트를 화면에 어떤 방식으로 배치할지 결정하는 시스템. 모두 `java.awt` 패키지에 구현되어 있다.

### 종류 비교

| 배치 관리자 | 방식 | 느낌 |
|------------|------|------|
| FlowLayout | 왼쪽→오른쪽 순서 배치, 공간 부족 시 자동 줄바꿈 | 문장 입력처럼 흐르듯 배치 |
| BorderLayout | 화면을 NORTH/SOUTH/EAST/WEST/CENTER 5개 영역 분할 | 동서남북 + 중앙 구조 |
| GridLayout | 격자(Grid) 형태, 모든 칸 크기 동일, 순서대로 배치 | 표(Table) 형태 |
| CardLayout | 카드를 겹쳐놓은 형태, 한 번에 하나의 화면만 보임 | 슬라이드 넘기는 구조 |

---

## 13. FlowLayout

```java
FlowLayout()                                   // 기본: 가운데 정렬, hGap=5, vGap=5
FlowLayout(int align, int hGap, int vGap)      // 정렬 방식, 가로/세로 간격 직접 지정
```

- `align`: `FlowLayout.LEFT` / `FlowLayout.RIGHT` / `FlowLayout.CENTER` (기본값)
- `hGap`: 좌우 컴포넌트 사이 간격 (픽셀, 기본값 5)
- `vGap`: 위아래 컴포넌트 사이 간격 (픽셀, 기본값 5)

```java
package week11;
import javax.swing.*;
import java.awt.*;

public class Java8_3 extends JFrame {

    public Java8_3() {
        setTitle("FlowLayout 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 40));
        contentPane.add(new JButton("add"));
        contentPane.add(new JButton("sub"));
        contentPane.add(new JButton("mul"));
        contentPane.add(new JButton("div"));
        contentPane.add(new JButton("Calculate"));

        setSize(300, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Java8_3();
    }
}
```

---

## 14. BorderLayout

```java
BorderLayout()                        // 기본: hGap=0, vGap=0
BorderLayout(int hGap, int vGap)      // 컴포넌트 간 간격 지정
```

- `add(Component comp, Object index)` 로 배치 위치 지정
- 위치 지정: `BorderLayout.EAST` / `WEST` / `SOUTH` / `NORTH` / `CENTER`
- 각 위치에 하나의 컴포넌트만 배치 가능 (같은 위치에 추가하면 기존 교체)
- CENTER 영역은 남은 공간을 자동으로 가장 크게 차지 → 메인 화면 영역으로 많이 사용

```java
package week11;
import javax.swing.*;
import java.awt.*;

public class Java8_4 extends JFrame {

    public Java8_4() {
        setTitle("BorderLayout 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout(30, 20));

        add(new JButton("Calculate"), BorderLayout.CENTER);
        add(new JButton("add"), BorderLayout.NORTH);
        add(new JButton("sub"), BorderLayout.SOUTH);
        add(new JButton("mul"), BorderLayout.EAST);
        add(new JButton("div"), BorderLayout.WEST);

        setSize(300, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Java8_4();
    }
}
```

---

## 15. GridLayout

```java
GridLayout(int rows, int cols)                     // 행/열 개수 지정
GridLayout(int rows, int cols, int hGap, int vGap) // 간격 포함
```

- 모든 칸 크기가 동일하고 컴포넌트 크기도 동일하게 맞춰짐
- 배치 순서: 왼쪽→오른쪽, 위→아래 (문서 읽는 순서)

```java
container.setLayout(new GridLayout(4, 3, 5, 5)); // 4행 3열, 간격 5픽셀
```

```java
package week11;
import java.awt.*;
import javax.swing.*;

public class Java8_5 extends JFrame {

    public Java8_5() {
        super("GridLayout 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(1, 10));

        for (int i = 0; i < 10; i++) {
            String text = Integer.toString(i);
            JButton button = new JButton(text);
            contentPane.add(button);
        }

        setSize(500, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Java8_5();
    }
}
```

---

## 16. 배치 관리자 없는 컨테이너

`container.setLayout(null);` 로 배치 관리자를 제거하면 개발자가 직접 컴포넌트의 위치와 크기를 지정해야 한다.

**필요한 경우:**
- 컴포넌트 위치/크기를 직접 정하고 싶을 때
- 게임처럼 키보드/마우스 입력이나 시간 변화에 따라 위치가 계속 변할 때
- 컴포넌트를 서로 겹쳐서 출력할 때

**주의사항:** 배치 관리자가 없으면 `setSize()`, `setLocation()` 또는 `setBounds()`로 직접 위치와 크기를 설정해야 한다. 설정하지 않으면 크기가 0이 되어 화면에 보이지 않을 수 있다.

흐름: `setLayout(null)` → 자동 배치 제거 → 개발자가 직접 좌표로 위치 지정 → 컴포넌트 출력

---

## 시험 포인트

**반드시 암기할 AWT 특징**
- Heavy Weight Component
- 운영체제가 직접 그림
- OS 의존적
- 속도 빠름, 자원 많이 사용

**자주 나오는 문제:**
> "AWT가 Heavy Weight인 이유를 설명하시오."

**핵심 답:** 운영체제의 GUI 자원을 직접 사용하기 때문이다.

**한 줄 요약**
```
AWT   = 운영체제 기반 GUI → 빠르지만 무겁고 OS 의존적
Swing = 자바 기반 GUI    → 더 유연하고 현재 주로 사용
```

---
## 10주차 정리 (5월 6일)

---

## 1. 자바 API의 모듈 파일 (Temurin OpenJDK 24)

- Temurin OpenJDK 24부터 **JEP 439 표준**을 따르게 되어 `jmods` 디렉토리가 포함되지 않음
- Temurin의 `jlink` tool을 활성화하면 JMOD 파일 없이도 사용자 지정 run-time 이미지 생성 가능 → JDK 크기 약 25% 감소
- Temurin OpenJDK 24 빌드 시 기본 자동 활성화
- jmods 파일 확인하려면 다른 OpenJDK 배포판 사용

---

## 2. 패키지 만들기

### 클래스 파일(.class) 저장 위치

- 클래스나 인터페이스가 컴파일되면 `.class` 파일 생성
- 클래스 파일은 패키지로 선언된 디렉터리에 저장

### 패키지 선언

소스 파일의 맨 앞에 컴파일 후 저장될 패키지 지정

```java
package 패키지명;
```

### 예시 1 - Tools 클래스 (경로명: UI.Tools)

```java
package UI; // Tools를 컴파일하여 UI 패키지(UI 디렉토리)에 저장할 것 지시

public class Tools {
    .........
}
```

### 예시 2 - Line 클래스

```java
package Graphic; // Line 클래스를 Graphic 패키지에 저장

import UI.Tools; // UI.Tools 클래스의 경로명 임포트

public class Line extends Shape {
    public void draw() {
        Tools t = new Tools();
    }
}
```

### 디폴트 패키지

- `package` 선언문이 없는 소스 파일 → 현재 디렉터리가 디폴트 패키지
- 같은 디폴트 패키지 안의 클래스끼리는 `import` 없이 사용 가능 (5장까지 했던 방식)
- 디폴트 패키지에 속해 있으면 다른 패키지를 사용할 수 없음
- 실제 프로젝트에서는 거의 모든 클래스가 명시적으로 `package` 선언

---

## 3. 패키지 운영 방법

- 패키지 이름은 도메인 기반으로 시작 (일반 관례)
    - 형식: `com.회사이름.프로젝트명.기능명` → 충돌 방지 / 모듈별 분리
- 기능/역할별로 하위 패키지를 구분: `utils`, `controller`, `service` 등
- 디렉토리 구조와 `package` 선언을 정확히 일치해야 함
- `import`는 필요한 만큼만, `*` 전체 import는 피하는 것이 좋음

### 디렉토리 구조 생성 예시

```bash
src/
└── com/
    └── foo/
        └── test/
```

### Bar.java

```java
package com.foo.test;

public class Bar {
    public static void main(String[] args) {
        System.out.println("Hello, My Package!");
    }
}
```

파일 구조 예시

```
src
├── com/foo/test/Bar.java
├── App.java
└── README.md
```

- Explorer 아래 "JAVA PROJECTS" 클릭 시 package 계층 구조 확인 가능
- 사용자 정의 package를 만든 후 사용하려면 `import` 키워드 사용 (`src/com/example/utils/HelloUtil.java`, `src/com/example/Main.java` 형식)

---

## 4. 자바의 모듈화

### 모듈화의 목적

- Java 9부터 자바 API를 여러 모듈(99개 → 현재 70개)로 분할 (Java 8까지는 `rt.jar` 한 파일에 모든 API 저장)
- 응용프로그램 실행 시 꼭 필요한 모듈들로만 실행 환경 구축
- 메모리 자원이 열악한 소형 기기(IoT 장치 등)에도 자바 응용프로그램이 실행되고 성능 유지

### 모듈의 개념 (Java 9 도입)

- 패키지와 이미지 등 리소스를 담은 컨테이너
- 모듈 파일(`.jmod`)로 저장

### Java 8 vs Java 9 이후 구조

| Java 8 | Java 9 이후 |
|--------|-------------|
| 패키지A, B, C (클래스 파일들) | 모듈N { 패키지A, B + 리소스 } / 모듈M { 패키지K, L + 리소스 } |

### 자바 플랫폼의 모듈화

- 자바 API의 모든 클래스가 여러 개의 모듈로 재구성
- 모듈 파일은 JDK의 `jmods` 디렉터리에 저장하여 배포 (현재는 OpenJDK에 따라 다름)

### 자바 모듈과 패키지 구조

**java.desktop 모듈**

- 하위 패키지: `applet`, `awt`, `beans`
    - `awt` 하위: `color`, `datatransfer`, `dnd`, `event`, `font`, `geom`, `im(spi)`, `image(renderable)`, `print`
    - `beans` 하위: `beancontext`

**java.base 모듈**

- 하위 패키지: `io`, `lang`, `math`, `time`, `text`, `nio`, `net`, `security`, `util`
    - `lang` 하위: `annotation`, `instrument`, `management`, `ref`, `reflect`
    - `nio` 하위: `channels(spi)`, `charset(spi)`
    - `security` 하위: `acl`, `cert`, `spec`, `interfaces`
    - `util` 하위: `concurrent(atomic, locks)`, `jar`, `logging`, `prefs`, `regex`, `spi`, `zip`

**java.rmi 모듈**

- 하위 패키지: `rmi` → `activation`, `dgc`, `registry`, `server`

**java.sql 모듈**

- 하위 패키지: `sql`

---

## 5. JDK의 주요 패키지

| 패키지 | 설명 |
|--------|------|
| `java.lang` | 스트링, 수학 함수, 입출력 등 기본 클래스와 인터페이스. 자동으로 import됨 |
| `java.util` | 날짜, 시간, 벡터, 해시맵 등 유틸리티 클래스와 인터페이스 제공 |
| `java.io` | 키보드, 모니터, 프린터, 디스크 등에 입출력할 수 있는 클래스와 인터페이스 제공 |
| `java.awt` | GUI 프로그램을 작성하기 위한 AWT 패키지 |
| `javax.swing` | GUI 프로그램을 작성하기 위한 스윙 패키지 |

---

## 6. Object 클래스

- 모든 자바 클래스는 반드시 `Object`를 상속받도록 자동 컴파일
- 모든 클래스의 슈퍼 클래스
- 모든 클래스가 상속받는 공통 메소드 포함

| 메소드 | 설명 |
|--------|------|
| `boolean equals(Object obj)` | obj가 가리키는 객체와 현재 객체를 비교하여 같으면 true 리턴 |
| `Class getClass()` | 현 객체의 런타임 클래스를 리턴 |
| `int hashCode()` | 현 객체에 대한 해시 코드 값 리턴 |
| `String toString()` | 현 객체에 대한 문자열 표현을 리턴 |
| `void notify()` | 현 객체에 대해 대기하고 있는 하나의 스레드를 깨운다 |
| `void notifyAll()` | 현 객체에 대해 대기하고 있는 모든 스레드를 깨운다 |
| `void wait()` | 다른 스레드가 깨울 때까지 현재 스레드를 대기하게 한다 |

---

## 7. Wrapper 클래스

- 자바의 기본 타입을 클래스화한 8개 클래스를 통칭
- 용도: 객체만 사용할 수 있는 컬렉션 등에 기본 타입의 값을 넣기 위해 Wrapper 객체로 변환하여 사용

| 기본 타입 | byte | short | int | long | char | float | double | boolean |
|-----------|------|-------|-----|------|------|-------|--------|---------|
| Wrapper 클래스 | Byte | Short | Integer | Long | Character | Float | Double | Boolean |

### Integer 클래스 주요 메소드 (다른 Wrapper 클래스도 유사)

| 메소드 | 설명 |
|--------|------|
| `static int bitCount(int i)` | 정수 i의 이진수 표현에서 1의 개수 리턴 |
| `float floatValue()` | float 타입으로 값 리턴 |
| `int intValue()` | int 타입으로 값 리턴 |
| `long longValue()` | long 타입으로 값 리턴 |
| `short shortValue()` | short 타입으로 값 리턴 |
| `static int parseInt(String s)` | 스트링 s를 10진 정수로 변환한 값 리턴 |
| `static int parseInt(String s, int radix)` | 스트링 s를 지정된 진법의 정수로 변환한 값 리턴 |
| `static String toBinaryString(int i)` | 정수 i를 이진수 표현으로 변환한 스트링 리턴 |
| `static String toHexString(int i)` | 정수 i를 16진수 표현으로 변환한 스트링 리턴 |
| `static String toOctalString(int i)` | 정수 i를 8진수 표현으로 변환한 스트링 리턴 |
| `static String toString(int i)` | 정수 i를 스트링으로 변환하여 리턴 |

---

## 8. 박싱과 언박싱

- 박싱(Boxing): 기본 타입의 값 → Wrapper 객체로 변환
- 언박싱(Unboxing): Wrapper 객체 → 기본 타입의 값으로 추출

```java
// 박싱
Integer ten = Integer.valueOf(10);   // int 10 → Integer 객체 ten

// 언박싱
int n = ten.intValue();              // Integer 객체 ten → int n
```

- 자동 박싱 / 자동 언박싱: JDK 1.5부터 박싱과 언박싱이 자동으로 이루어지도록 컴파일

```java
Integer ten = 10;   // 자동 박싱. Integer ten = Integer.valueOf(10);로 자동 처리
int n = ten;        // 자동 언박싱. int n = ten.intValue();로 자동 처리
```

---

## 9. String 클래스

### String의 생성과 특징

- 자바에는 기본 데이터 타입에 `string` 없음 → String 클래스 제공
- 스트링 리터럴(문자열 리터럴)은 String 객체로 처리됨

```java
String str1 = "abcd";

char data[] = {'a', 'b', 'c', 'd'};
String str2 = new String(data);
String str3 = new String("abcd"); // str2와 str3은 모두 "abcd" 스트링
```

### 스트링 리터럴 vs new String()

| 구분 | 스트링 리터럴 | new String() |
|------|--------------|--------------|
| 저장 위치 | JVM 내부 리터럴 테이블 | 힙(Heap) |
| 공유 여부 | 공유됨 (같은 리터럴이면 동일 객체 참조) | 공유 안됨 (각각 독립 객체) |

```java
String a = "Hello";
String b = "Java";
String c = "Hello";              // a와 c는 동일한 리터럴 "Hello" 공유
String d = new String("Hello");  // 힙에 독립 객체 생성
String e = new String("Java");   // 힙에 독립 객체 생성
String f = new String("Java");   // 힙에 독립 객체 생성 (e와 다른 객체)
```

### 스트링 비교 - equals()와 compareTo()

스트링 비교에 == 연산자 절대 사용 금지

```java
String java = "Java";
if(java.equals("Java")) // true
```

```java
String java = "Java";
String cpp = "C++";
int res = java.compareTo(cpp);
if(res == 0) System.out.println("the same");
else if(res < 0) System.out.println(java + " < " + cpp);
else System.out.println(java + " > " + cpp);
// 결과: Java > C++
```

- `compareTo()`: 같으면 0, 이 문자열이 먼저 나오면 음수, 나중에 나오면 양수 리턴

### 공백 제거 - trim()

```java
String a = " xyz\t";
String b = a.trim();    // b = "xyz". 빈칸과 '\t' 제거됨
```

### String 활용 종합 예제

```java
package week10;

public class Ex65StringEx {
    public static void main(String[] args) {
        String a = new String(" C#");
        String b = new String(",C++ ");
        System.out.println(a + "의 길이는 " + a.length());
        System.out.println(a.contains("#"));
        a = a.concat(b);
        System.out.println(a);
        a = a.trim();
        System.out.println(a);
        a = a.replace("C#", "Java");
        System.out.println(a);
        String[] s = a.split(",");

        for(int i = 0; i < s.length; ++i) {
            System.out.println("분리된 문자열" + i + ": " + s[i]);
        }

        a = a.substring(5);
        System.out.println(a);
        char c = a.charAt(2);
        System.out.println(c);
    }
}
```

---

## 10. StringBuffer 클래스

- 가변 스트링을 다루는 클래스 (String과 달리 문자열 변경 가능)
- 가변 크기의 버퍼를 가지고 있어 문자열 수정 가능
- 문자열의 수정이 많은 작업에 적합

```java
StringBuffer sb = new StringBuffer("This");
sb.append(" is pencil.");    // sb = "This is pencil."
sb.insert(7, " my");         // sb = "This is my pencil."
sb.replace(8, 10, "your");   // sb = "This is your pencil."
System.out.println(sb);      // "This is your pencil." 출력
```

---

## 11. StringTokenizer 클래스

- 구분 문자(delimiter)를 기준으로 문자열을 분리하는 클래스
- 토큰(token): 구분 문자로 분리된 문자열

```java
String query = "name=kitae&addr=seoul&age=21";
StringTokenizer st = new StringTokenizer(query, "&");
// 토큰1: "name=kitae"
// 토큰2: "addr=seoul"
// 토큰3: "age=21"

int count = st.countTokens();       // 토큰 개수 알아내기. count = 3
String token = st.nextToken();      // 다음 토큰 얻어내기. token = "name=kitae"
```

---

## 12. Math 클래스

- 기본 산술 연산 메소드를 제공하는 클래스
- 모든 메소드는 static으로 선언 → 클래스 이름으로 호출 가능

```java
// Math.random(): 0.0 이상 1.0 미만의 실수 난수 발생
// 1에서 100까지의 랜덤 정수 10개 발생
for(int x = 0; x < 10; x++) {
    int n = (int)(Math.random() * 100 + 1); // 1~100까지의 랜덤 정수 발생
    System.out.println(n);
}
```

```java
// java.util.Random 클래스를 이용한 난수 발생
Random r = new Random();
int n = r.nextInt();        // 음수, 양수, 0 포함, 자바의 정수 범위 난수 발생
int m = r.nextInt(100);     // 0에서 99 사이(0과 99 포함)의 정수 난수 발생
```

---

## 13. 컬렉션(Collection)

### 컬렉션의 개념

- 요소(element)라고 불리는 가변 개수의 객체들의 저장소 (객체들의 컨테이너)
- 요소의 개수에 따라 크기 자동 조절
- 요소의 삽입, 삭제에 따른 요소의 위치 자동 이동

| 구분 | 배열(array) | 컬렉션(collection) |
|------|------------|-------------------|
| 크기 | 고정 크기 이상의 객체 관리 불가 | 가변 크기, 객체 개수 염려 없음 |
| 삭제 | 중간 객체 삭제 시 응용프로그램에서 자리를 옮겨야 함 | 삭제 시 컬렉션이 자동으로 자리 이동 |

### 컬렉션 인터페이스와 클래스 구조

**인터페이스**

- `Collection` → `Set`, `List`, `Queue`
- `Map<K, V>`

**클래스**

- `HashSet` (Set 인터페이스 구현)
- `ArrayList` (List 인터페이스 구현)
- `Vector` (List 인터페이스 구현)
    - `Stack` (Vector 상속)
- `LinkedList` (List, Queue 인터페이스 구현)
- `HashMap<K, V>` (Map\<K, V\> 인터페이스 구현)

### 컬렉션의 특징

#### 1. 제네릭(Generics) 기법으로 구현

- 특정 타입만 다루지 않고, 여러 종류의 타입으로 변신할 수 있도록 클래스나 메소드를 일반화시키는 기법
- 클래스나 인터페이스 이름에 `<E>`, `<K>`, `<V>` 등 타입 매개변수 포함
- 제네릭은 형판과 같은 개념: 클래스나 메소드를 찍어내듯이 생산할 수 있도록 일반화된 형판을 만드는 기법

```java
Vector<Integer> v = new Vector<Integer>(); // 정수만 다루는 벡터
Vector<String> v2 = new Vector<String>();  // 문자열만 다루는 벡터
```

#### 2. 컬렉션의 요소는 객체만 가능

```java
Vector<int> v = new Vector<int>();         // 컴파일 오류. int는 사용 불가
Vector<Integer> v = new Vector<Integer>(); // 정상 코드
```

---

## 14. Vector\<E\>

### Vector 내부 구조

- `add()`를 이용하여 요소 삽입, `get()`을 이용하여 요소 검색

```
인덱스별 상태:
0: Integer(5)
1: Integer(4)
2: Integer(-1)
3: Integer(10)
4, 5, 6: (비어 있음)
```

### Vector 예제 코드

```java
import java.util.Vector;

public class Ex71VectorEx {
    public static void main(String[] args) {
        // 정수 값만 다루는 제네릭 벡터 생성
        Vector<Integer> v = new Vector<Integer>();
        v.add(5);   // 5 삽입
        v.add(4);   // 4 삽입
        v.add(-1);  // -1 삽입

        // 벡터 중간에 삽입하기
        v.add(2, 100); // 4와 -1 사이에 정수 100 삽입

        System.out.println("벡터 내의 요소 객체 수 : " + v.size());
        System.out.println("벡터의 현재 용량 : " + v.capacity());

        // 모든 요소 정수 출력하기
        for (int i = 0; i < v.size(); i++) {
            int n = v.get(i); // 벡터의 i번째 정수
            System.out.println(n);
        }

        // 벡터 속의 모든 정수 더하기
        int sum = 0;
        for (int i = 0; i < v.size(); i++) {
            int n = v.elementAt(i); // 벡터의 i번째 정수
            sum += n;
        }

        System.out.println("벡터에 있는 정수 합 : " + sum);
    }
}
```

### 코드 분석

**벡터 생성 및 데이터 삽입**

- `v.add(5)`: 순차적으로 데이터를 뒤에 추가
- `v.add(2, 100)`: 특정 인덱스(2번)에 값을 삽입 → 기존 요소들은 뒤로 밀림

**크기(Size)와 용량(Capacity)**

- `v.size()`: 현재 실제로 저장된 요소의 개수 반환
- `v.capacity()`: 현재 확보하고 있는 전체 메모리 공간의 크기 반환 (자동 증가)

**데이터 접근**

- `v.get(i)`: i번째 인덱스 요소 가져옴
- `v.elementAt(i)`: `get(i)`와 동일, 이전 버전과의 호환성을 위해 남겨진 메서드
- 오토박싱(Auto-boxing): 벡터에는 `Integer` 객체 저장, 꺼낼 때 자동으로 `int`로 변환

**예상 출력 결과**

```
벡터 내의 요소 객체 수 : 4
벡터의 현재 용량 : 10
5
4
100
-1
벡터에 있는 정수 합 : 108
```

---

## 15. ArrayList\<E\>

### 주요 특징

- 가변 크기 배열: 데이터 개수에 따라 크기 자동 조절
- 제네릭 사용: `<E>` 부분에 저장할 요소의 타입 명시
- 벡터와 기능은 거의 동일하나 스레드 동기화 없음

### ArrayList vs Vector 비교

| 항목 | ArrayList | Vector |
|------|-----------|--------|
| 동기화 여부 | 비동기화 (스레드 안전 X) | 동기화 (스레드 안전 O) |
| 성능 | 빠름 (싱글 스레드에 적합) | 느림 (동기화로 인한 오버헤드) |
| 기본 크기 증가 | 1.5배씩 증가 | 2배씩 증가 |
| 도입 시기 | Java 1.2 (Collection Framework) | Java 1.0 (초기부터 존재) |
| 사용 권장 여부 | 현대 개발에서 추천 | 특별한 이유 없다면 지양 |

### 멀티스레드 환경에서의 대안

- `synchronizedList`: 기존 ArrayList를 동기화된 리스트로 감싸서 사용
- `CopyOnWriteArrayList`: 읽기 작업이 많고 쓰기 작업이 적은 멀티스레드 환경에서 높은 성능

역사적 배경: Vector는 JDK 1.0의 Legacy 클래스. Java 1.2에서 컬렉션 프레임워크 표준화 후 ArrayList가 도입됨.

---

## 16. Iterator (이테레이터)

### Iterator 인터페이스란?

- 리스트 구조의 컬렉션에서 요소들을 순차적으로 검색하기 위해 사용하는 인터페이스
- `Vector<E>`, `ArrayList<E>`, `LinkedList<E>` 등이 공통적으로 구현

### Iterator 객체 얻어내기

```java
Vector<Integer> v = new Vector<Integer>();
Iterator<Integer> it = v.iterator(); // Iterator 객체 얻기
```

### 주요 메서드

| 메서드 | 설명 |
|--------|------|
| `hasNext()` | 다음에 방문할 요소가 남아있으면 true, 없으면 false 반환 |
| `next()` | 다음 요소 객체를 반환 (실제 데이터를 꺼내오는 역할) |
| `remove()` | next()로 읽어온 요소를 컬렉션에서 삭제 |

### 반복문과 함께 사용

```java
while(it.hasNext()) { // 다음에 올 요소가 있는지 확인
    int n = it.next(); // 있다면 요소 꺼내기
    // ... 처리 로직
}
```

### Iterator를 쓰는 이유

- 통일된 사용법: 컬렉션 종류(Vector, ArrayList 등)가 바뀌어도 데이터 꺼내는 방식 동일
- 안전성: 순회 도중 요소를 안전하게 삭제할 수 있는 `remove()` 제공. 일반 for문에서 인덱스로 접근하여 삭제 시 발생하는 오류 방지

---

## 17. HashMap\<K, V\>

### 핵심 개념 - 키(Key)와 값(Value)의 쌍

- K (Key): 데이터를 식별하기 위한 고유한 값 (예: "apple")
- V (Value): 키와 연결되어 실제로 저장되는 데이터 (예: "사과")
- 특정 값을 찾으려면 반드시 그에 연결된 키를 알아야 함
- 내부적으로 해싱(Hashing) 알고리즘 사용 → 삽입과 검색 속도 매우 빠름

### 기본 사용 예시

```java
// 1. String 타입의 키와 String 타입의 값을 가지는 해시맵 생성
HashMap<String, String> h = new HashMap<String, String>();

// 2. 데이터 삽입 (Key: "apple", Value: "사과")
h.put("apple", "사과");

// 3. 데이터 검색 (Key "apple"로 Value를 찾음)
String kor = h.get("apple"); // kor 변수에는 "사과"가 저장됨
```

### 주요 메서드 총정리

**데이터 관리 (추가, 삭제, 초기화)**

| 메서드 | 설명 |
|--------|------|
| `put(K key, V value)` | 지정된 키와 값을 연결하여 HashMap에 저장 |
| `remove(Object key)` | 지정된 키와 그에 매핑된 값을 삭제하고, 삭제된 값을 반환 |
| `clear()` | HashMap 안에 있는 모든 요소를 한꺼번에 삭제 |

**데이터 검색 및 확인**

| 메서드 | 설명 |
|--------|------|
| `get(Object key)` | 키를 입력하면 그에 맞는 값을 리턴. 찾는 키가 없으면 null 리턴 |
| `containsKey(Object key)` | 특정 키가 맵에 존재하는지 여부를 boolean으로 확인 |
| `containsValue(Object value)` | 특정 값이 하나 이상 존재하는지 확인 |

**상태 및 정보 확인**

| 메서드 | 설명 |
|--------|------|
| `size()` | 현재 HashMap에 저장된 키-값 쌍의 총 개수 반환 |
| `isEmpty()` | 맵이 비어 있는지 확인하여 비어 있으면 true 리턴 |

**전체 데이터 탐색**

| 메서드 | 설명 |
|--------|------|
| `keySet()` | 모든 키(Key)들만 따로 뽑아서 Set 컬렉션 형태로 리턴. for문이나 Iterator로 맵 전체 순회 시 유용 |

### HashMap 주의사항

- 키의 중복 불가: 같은 키로 `put` 수행 시 기존 값이 새로운 값으로 덮어쓰기 (값은 중복 가능)
- 순서 보장 없음: 데이터를 넣은 순서대로 저장되지 않음. 순서가 중요하면 `LinkedHashMap` 사용
## 9주차 정리 (4월 29일)

---

## 1. 상속 (Inheritance)

공통 기능을 슈퍼 클래스에 작성하고, 서브 클래스는 필요한 기능만 추가해 중복을 제거하는 구조다. `extends` 키워드를 사용하며 "부모 클래스를 물려받아 자식 클래스를 확장한다"는 의미다.

- 슈퍼 클래스 객체와 서브 클래스 객체는 별개
- 서브 클래스 객체는 슈퍼 클래스 멤버 + 자신의 멤버를 함께 가짐
- 상속은 부모 객체를 따로 만드는 게 아니라 한 객체 안에 부모 부분 + 자식 부분이 같이 존재 (많이 오해하는 부분)

**예제 5-1: Point & ColorPoint**

```java
class Point {
    private int x, y;

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void showPoint() {
        System.out.println("(" + x + "," + y + ")");
    }
}

class ColorPoint extends Point {
    private String color;

    public void setColor(String color) {
        this.color = color;
    }

    public void showColorPoint() {
        System.out.print(color);
        showPoint();
    }
}

public class Ex51ColorPointEx {
    public static void main(String[] args) {
        Point p = new Point();
        p.set(1, 2);
        p.showPoint();

        ColorPoint cp = new ColorPoint();
        cp.set(3, 4);
        cp.setColor("red");
        cp.showColorPoint();
    }
}
```

실행 결과:
```
(1,2)
red(3,4)
```

- `cp.set()`은 `ColorPoint` 객체를 통해 `Point`의 `set()` 호출
- `showColorPoint()`에서 `showPoint()` 호출 가능 (상속받았으므로)

**서브 클래스 객체의 메모리 구조**

```
ColorPoint 객체 (cp)
 ├── int x = 3        (Point 클래스 멤버)
 ├── int y = 4        (Point 클래스 멤버)
 ├── set(int x, int y)
 ├── showPoint()
 ├── color = "red"    (ColorPoint 클래스 멤버)
 ├── setColor(String color)
 └── showColorPoint()
```

**자바 상속의 특징**

- 다중 상속 불허 — C++와 달리 모호성(Ambiguity) 문제 방지 목적
- 두 부모 클래스에 동일한 이름의 멤버가 있으면 어느 부모 멤버를 호출할지 모호해짐
- 인터페이스의 다중 상속은 허용
- 모든 자바 클래스는 묵시적으로 `Object` 클래스를 상속 (`java.lang.Object`가 모든 클래스의 슈퍼 클래스)

**슈퍼 클래스 멤버 접근 규칙**

| 접근 제어자 | 서브 클래스 접근 |
|---|---|
| private | 불가 |
| default | 같은 패키지일 때만 가능 |
| protected | 패키지 무관하게 서브 클래스 항상 가능 |
| public | 항상 가능 |

---

## 2. 생성자 선택과 super()

서브 클래스 객체 생성 시 슈퍼 클래스 생성자 1개 + 서브 클래스 생성자 1개가 실행된다.

- `super()`로 슈퍼 클래스 생성자를 명시적으로 선택 가능
- `super()`를 작성하지 않으면 컴파일러가 자동으로 슈퍼 클래스 기본 생성자를 호출 → 좌표가 `(0,0)`으로 초기화될 수 있으므로 원하는 생성자는 명시적으로 호출하는 것이 중요

**예제 5-2: super() 사용**

```java
class Point1 {
    private int x, y;

    public Point1() {
        this.x = this.y = 0;
    }

    public Point1(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void showPoint() {
        System.out.println("(" + x + "," + y + ")");
    }
}

class ColorPoint1 extends Point1 {
    private String color;

    public ColorPoint1(int x, int y, String color) {
        super(x, y); // Point1(int x, int y) 호출
        this.color = color;
    }

    public void showColorPoint() {
        System.out.print(color);
        showPoint();
    }
}

public class Ex52SuperEx {
    public static void main(String[] args) {
        ColorPoint1 cp = new ColorPoint1(5, 6, "blue");
        cp.showColorPoint();
    }
}
```

실행 결과:
```
blue(5,6)
```

실행 순서:
```
new ColorPoint1(5, 6, "blue")
  → super(5, 6) 호출
    → Point1(int x, int y) 실행 → x=5, y=6 저장
  → this.color = "blue"
  → 객체 생성 완료
  → showColorPoint() 호출 → blue(5,6)
```

---

## 3. 업캐스팅 / 다운캐스팅

**업캐스팅**: 서브 클래스 레퍼런스를 슈퍼 클래스 레퍼런스에 대입. 자동 변환.

```java
class Person {
    String name;
    String id;

    public Person(String name) {
        this.name = name;
    }
}

class Student extends Person {
    String grade;
    String department;

    public Student(String name) {
        super(name);
    }
}

public class UpcastingEx {
    public static void main(String[] args) {
        Person p;
        Student s = new Student("이재문");

        p = s; // 업캐스팅 발생

        System.out.println(p.name); // 오류 없음
        p.grade = "A";              // 컴파일 오류
        p.department = "Com";       // 컴파일 오류
    }
}
```

- `p` 레퍼런스는 `Person` 타입이므로 `Person` 멤버만 접근 가능
- 모든 멤버 접근하려면 `s` 레퍼런스 사용
- 실제 활용 목적은 여러 자식 클래스를 하나의 부모 타입으로 다루기 위해서

```java
Person[] people = new Person[3];
people[0] = new Student("홍길동");
people[1] = new Student("김영희");
people[2] = new Person("이순신");
```

**다운캐스팅**: 업캐스팅된 레퍼런스를 다시 서브 클래스 타입으로 복원. 반드시 명시적 형변환 필요.

```java
public class DowncastingEx {
    public static void main(String[] args) {
        Person p = new Student("이재문"); // 업캐스팅

        Student s;
        s = (Student)p; // 다운캐스팅

        System.out.println(s.name); // 오류 없음
        s.grade = "A";              // 오류 없음
    }
}
```

**instanceof**: 레퍼런스가 가리키는 객체 타입 식별. `true` / `false` 반환.

```java
Person p = new Professor();

if (p instanceof Person)      // true
if (p instanceof Student)     // false
if (p instanceof Researcher)  // true
if (p instanceof Professor)   // true

if ("java" instanceof String) // true
if (3 instanceof int)         // 문법 오류 - 기본형에는 사용 불가
```

`new Professor()` 객체는 `Professor` 타입이면서 `Researcher` 타입이고 `Person` 타입이기도 하다.

---

## 4. 오버라이딩 & 동적 바인딩

서브 클래스에서 슈퍼 클래스 메소드를 재정의하는 것. 업캐스팅 후 호출해도 항상 오버라이딩된 메소드가 실행된다.

**(a) 직접 호출**

```java
class A {
    void f() { System.out.println("A의 f() 호출"); }
}

class B extends A {
    void f() { System.out.println("B의 f() 호출"); }
}

B b = new B();
b.f(); // "B의 f() 호출"
```

**(b) 업캐스팅 후 호출**

```java
A a = new B();
a.f(); // 오버라이딩된 B의 f() 실행
```

**동적 바인딩 심화 — SuperObject 예제**

슈퍼 클래스 내부에서 호출할 때도 동적 바인딩이 적용된다.

```java
class SuperObject {
    protected String name;

    public void paint() {
        draw(); // 여기서도 동적 바인딩 적용
    }

    public void draw() {
        System.out.println("Super Object");
    }
}

public class SubObject extends SuperObject {
    public void draw() {
        System.out.println("Sub Object");
    }

    public static void main(String[] args) {
        SuperObject b = new SubObject();
        b.paint(); // "Sub Object" 출력
    }
}
```

`paint()`는 `SuperObject`에 있지만, `draw()`는 실제 객체인 `SubObject`의 것이 호출된다. `SuperObject`는 키워드가 아님에 주의.

**예제 5-4: 다형성 실현**

```java
class Shape {
    public void draw() { System.out.println("Shape"); }
}

class Line extends Shape {
    public void draw() { System.out.println("Line"); }
}

class Rect extends Shape {
    public void draw() { System.out.println("Rect"); }
}

class Circle extends Shape {
    public void draw() { System.out.println("Circle"); }
}

public class Ex54MethodOverridingEx {
    static void paint(Shape p) {
        p.draw(); // 동적 바인딩
    }

    public static void main(String[] args) {
        Line line = new Line();
        paint(line);

        paint(new Shape());
        paint(new Line());
        paint(new Rect());
        paint(new Circle());
    }
}
```

실행 결과:
```
Line
Shape
Line
Rect
Circle
```

`paint()` 하나로 모든 도형 처리 가능. 새로운 도형 클래스를 추가해도 기존 코드 수정 불필요.

**오버로딩 vs 오버라이딩 비교**

| 구분 | 오버로딩 | 오버라이딩 |
|---|---|---|
| 관계 | 동일 클래스 내 또는 상속 | 상속 관계 |
| 조건 | 이름 동일, 인자 개수/타입 달라야 함 | 이름, 인자 타입/개수, 리턴 타입 모두 동일 |
| 목적 | 사용 편리성 향상 | 새로운 기능으로 재정의 |
| 바인딩 | 정적 바인딩 | 동적 바인딩 |

---

## 5. 추상 클래스 (Abstract Class)

`abstract`로 선언된 메소드를 추상 메소드라 하며, 메소드 코드 없이 원형만 선언한다.

```java
abstract public void draw();                         // 추상 메소드 O
abstract public String fail() { return "Good Bye"; } // 추상 메소드 X - 컴파일 오류
```

추상 클래스는 두 가지 형태가 있다.

```java
// 추상 메소드를 가진 추상 클래스
abstract class Shape {
    public Shape() { }
    public void edit() { }
    abstract public void draw();
}

// 추상 메소드 없는 추상 클래스 (abstract만 선언해도 가능)
abstract class JComponent {
    String name;
    public void load(String name) { this.name = name; }
}
```

추상 메소드를 가진 클래스를 `abstract`로 선언하지 않으면 컴파일 오류.

```java
class fault {
    abstract public void f(); // 오류 - abstract 클래스로 선언되어야 함
}
```

**인스턴스 생성 불가**

```java
JComponent p;             // 레퍼런스 선언은 가능
p = new JComponent();     // 컴파일 오류
Shape obj = new Shape();  // 컴파일 오류
```

**추상 클래스 상속**

추상 클래스를 상속받고 추상 메소드를 구현하지 않으면 서브 클래스도 `abstract`로 선언해야 한다.

```java
abstract class A {
    abstract public int add(int x, int y);
}

abstract class B extends A { // 구현 안 했으므로 abstract 필수
    public void show() { System.out.println("B"); }
}

class C extends A {
    public int add(int x, int y) { return x + y; } // 구현 완료 → abstract 불필요
    public void show() { System.out.println("C"); }
}

A a = new A(); // 컴파일 오류
B b = new B(); // 컴파일 오류
C c = new C(); // 정상
```

목적: 상속을 위한 슈퍼 클래스로 활용, 서브 클래스에서 추상 메소드 구현 강제, 다형성 실현.

```java
abstract class Shape {
    public abstract void draw();
}

class Line extends Shape {
    @Override
    public void draw() { System.out.println("Line"); }
}

class Rect extends Shape {
    @Override
    public void draw() { System.out.println("Rect"); }
}

class Circle extends Shape {
    @Override
    public void draw() { System.out.println("Circle"); }
}
```

---

## 6. 인터페이스 (Interface)

정해진 규격에 맞기만 하면 연결 가능하고 각 구현 방법은 달라도 된다는 개념. 클래스가 구현해야 할 메소드들이 선언되는 추상형. `interface` 키워드 사용.

```java
interface PhoneInterface {
    public static final int TIMEOUT = 10000; // 상수 (public static final 생략 가능)
    public abstract void sendCall();          // 추상 메소드 (public abstract 생략 가능)
    public abstract void receiveCall();
    public default void printLogo() {         // 디폴트 메소드 (public 생략 가능)
        System.out.println("** Phone **");
    }
}
```

**인터페이스 구성 요소별 규칙**

| 구성 요소 | 규칙 |
|---|---|
| 상수 | `public`만 허용. `public static final` 생략 가능 |
| 추상 메소드 | `public abstract` 생략 가능 |
| default 메소드 | 코드 작성 가능. 구현 클래스에 자동 상속. `public`만 허용, 생략 가능 |
| private 메소드 | 코드 작성 필수. 인터페이스 내 다른 메소드에 의해서만 호출 가능 |
| static 메소드 | `public`, `private` 모두 지정 가능. 생략 시 `public` |

**추상 클래스 vs 인터페이스 비교**

| 구분 | 추상 클래스 | 인터페이스 |
|---|---|---|
| 키워드 | abstract | interface |
| 변수 | 제한 없음 | static final (상수만) |
| 접근 제어자 | 제한 없음 | public |
| 메소드 | 제한 없음 | abstract, default, static, private |
| 상속 키워드 | extends | implements |
| 다중 상속 | 불가 | 가능 |

**공통점**

- 추상 메소드를 가지고 있어야 함
- 인스턴스 생성 불가 (`new` 사용 불가)
- 상속받은 구현체 인스턴스를 사용해야 함
- 상속한 클래스는 추상 메소드를 반드시 구현해야 함

---

## 7. 패키지 & import

클래스 이름 충돌 방지 및 관련 클래스 관리를 위한 디렉터리 구조. 이름이 같아도 경로가 다르면 다른 파일로 취급한다.

```
Project/FileIO/Tools.class   // 서로 다른 파일로 인식
Project/UI/Tools.class
```

**패키지 vs 모듈 비교**

| 구분 | 패키지 | 모듈 |
|---|---|---|
| 개념 | 관련 클래스/인터페이스 묶음 | 여러 패키지와 자원 묶음 |
| 단위 | 작은 단위 | 더 큰 단위 |
| 저장 | 디렉터리, jar | .jmod 파일 |
| 관계 | 패키지가 모여 모듈 구성 | 모듈이 패키지 포함 |

클래스 → 패키지 → 모듈

**import 방법**

| 방법 | 예시 |
|---|---|
| import 없이 완전 경로명 사용 | `java.util.Scanner sc = new java.util.Scanner(System.in)` |
| 특정 클래스만 import | `import java.util.Scanner` |
| 패키지 전체 import | `import java.util.*` |

`import java.util.*`은 `java.util` 패키지 내 클래스만 포함하며, 하위 패키지는 포함하지 않는다.
---
## 7주차 정리 (4월 15일)

---

## 1. 클래스 (Class)

클래스는 객체를 만들기 위한 설계도이며, `class` 키워드로 선언한다. 필드(데이터 저장)와 메소드(기능 수행)로 구성된다.

`public`을 클래스에 붙이면 다른 모든 클래스에서 해당 클래스를 사용할 수 있고, 멤버에 붙이면 다른 모든 클래스에서 해당 멤버에 접근할 수 있다.

```java
public class Circle {
    int radius;
    String name;

    public double getArea() {
        return 3.14 * radius * radius;
    }
}
```

핵심 개념:
- 클래스로 객체를 생성 (`new`)
- 객체는 필드 값 저장 + 메소드 호출
- 같은 클래스로 여러 객체 생성 가능하며 서로 독립적

---

## 2. 객체 생성과 활용

객체는 선언 → 생성 → 사용 순서로 다룬다. `new` 키워드로 메모리를 할당하고, 점 연산자(`.`)로 필드와 메소드에 접근한다.

```java
Circle pizza;                  // 1. 선언
pizza = new Circle();          // 2. 생성
pizza.radius = 10;             // 3. 필드 값 설정
pizza.name = "자바피자";
double area = pizza.getArea(); // 4. 메소드 호출
```

객체를 생성하지 않고 접근하면 `NullPointerException`이 발생한다.

```java
// 잘못된 코드
Circle pizza;
pizza.radius = 10; // NullPointerException

// 올바른 코드
pizza = new Circle();
pizza.radius = 10;
```

---

## 3. 생성자 (Constructor)

객체 생성 시 자동으로 호출되어 필드를 초기화하는 메소드다.

특징:
- 클래스 이름과 동일해야 한다
- 리턴 타입이 없다 (`void`도 불가)
- `new` 연산자와 함께 딱 한 번 실행된다
- 객체를 바로 사용할 수 있는 상태로 만든다

```java
class Circle {
    int radius;
    String name;

    Circle(int r, String n) {
        radius = r;
        name = n;
    }
}

Circle pizza = new Circle(10, "자바피자");
```

실행 흐름:
1. `new Circle(10, "자바피자")` 실행
2. 생성자 호출
3. `radius = 10`, `name = "자바피자"` 초기화
```java
public void Circle() { } // 컴파일 오류4. 객체 생성 완료

잘못된 코드 (리턴 타입 사용 시 오류):

```

---

## 4. 생성자 오버로딩

매개변수가 다른 생성자를 여러 개 정의할 수 있다. 생성자가 하나라도 선언되면 기본 생성자는 자동 생성되지 않으므로 필요하면 직접 만들어야 한다.

```java
public class Ex44Book {
    String title;
    String author;

    public Ex44Book(String t) {
        title = t;
        author = "작자미상";
    }

    public Ex44Book(String t, String a) {
        title = t;
        author = a;
    }

    public static void main(String[] args) {
        Ex44Book littlePrince = new Ex44Book("어린왕자", "생텍쥐페리");
        Ex44Book loveStory = new Ex44Book("춘향전");

        System.out.println(littlePrince.title + " " + littlePrince.author);
        System.out.println(loveStory.title + " " + loveStory.author);
    }
}
```

실행 결과:
```
어린왕자 생텍쥐페리
춘향전 작자미상
```

기본 생성자가 자동 생성되지 않는 경우:
```java
class Circle {
    public Circle(int r) { } // 생성자 존재
}

Circle c = new Circle(); // 오류 발생 - 기본 생성자 없음

// 해결: 직접 추가
class Circle {
    public Circle() { }       // 직접 추가
    public Circle(int r) { }
}
```

---

## 5. this 레퍼런스

현재 객체 자신을 가리키는 참조 변수다. 매개변수 이름과 필드 이름이 같을 때 구분하기 위해 사용한다.

```java
class Circle {
    int radius;

    public Circle(int radius) {
        this.radius = radius; // this.radius = 필드, radius = 매개변수
    }

    double getArea() {
        return 3.14 * this.radius * this.radius;
    }
}
```

`this` 없이 작성하면 둘 다 지역변수로 처리되어 의미 없는 코드가 된다:
```java
public Circle(int radius) {
    radius = radius; // 의미 없음 (둘 다 지역변수)
}
```

---

## 6. this()로 다른 생성자 호출

같은 클래스의 다른 생성자를 호출할 때 사용한다. 반드시 생성자의 첫 줄에 위치해야 한다.

```java
class Book {
    String title;
    String author;

    public Book() {
        this("제목없음", "작자미상"); // 반드시 첫 줄
    }

    public Book(String t, String a) {
        title = t;
        author = a;
    }
}
```

첫 줄이 아닌 경우 컴파일 오류:
```java
public Book() {
    System.out.println("생성자 호출");
    this("제목", "저자"); // 오류 - 첫 줄 아님
}
```

---

## 7. 객체 배열

객체 배열은 참조(주소)를 저장하는 배열이다. `new Circle[5]`는 배열(참조 공간)만 만들 뿐 실제 객체는 생성되지 않으므로, 각 요소에 별도로 `new`를 해야 한다.

```java
public class Ex46CircleArray {
    public static void main(String[] args) {

        Circle[] c;
        c = new Circle[5];

        for (int i = 0; i < c.length; i++) {
            c[i] = new Circle(i); // 객체 생성 필수
        }

        for (int i = 0; i < c.length; i++) {
            System.out.print((int)(c[i].getArea()) + " ");
        }
    }
}
```

실행 결과:
```
0 3 12 28 50
```

잘못된 코드:
```java
Circle[] c = new Circle[5];
c[0].getArea(); // 오류 - 객체 없음

// 올바른 코드
c[0] = new Circle(0);
c[0].getArea();
```

---

## 8. 메소드 (Method)

모든 메소드는 반드시 클래스 내부에 존재해야 한다.

```java
public int getSum(int i, int j) {
    int sum = i + j;
    return sum;
}
```

인자 전달 방식:

기본 타입 (값 전달 - Call by Value):
```java
static void increase(int m) {
    m = m + 1;
}

public static void main(String[] args) {
    int n = 10;
    increase(n);
    System.out.println(n); // 10 - 원본 변경 없음
}
```

배열 (참조 전달):
```java
static void increase(int[] array) {
    for (int i = 0; i < array.length; i++) {
        array[i]++;
    }
}

public static void main(String[] args) {
    int[] a = {1, 2, 3, 4, 5};
    increase(a);
    // 출력: 2 3 4 5 6 - 원본 변경됨
}
```

---

## 9. 메소드 오버로딩 vs 오버라이딩

| 구분 | 오버로딩 (Overloading) | 오버라이딩 (Overriding) |
|---|---|---|
| 위치 | 같은 클래스 내 | 부모 - 자식 클래스 간 |
| 메소드 이름 | 동일 | 동일 |
| 매개변수 | 달라야 함 | 동일해야 함 |
| 리턴 타입 | 무관 | 동일해야 함 |
| 결정 시점 | 컴파일 시 | 실행 시 (객체 기준) |
| 목적 | 같은 이름으로 다양한 기능 제공 | 부모 메소드를 자식이 재정의 |

오버로딩은 매개변수가 달라야 성립한다. 리턴 타입만 다른 경우는 오버로딩이 아니라 컴파일 오류다.

```java
// 오버로딩 성공
public int getSum(int i, int j) { return i + j; }
public int getSum(int i, int j, int k) { return i + j + k; }
public double getSum(double i, double j) { return i + j; }

// 오버로딩 실패 - 컴파일 오류
public int getSum(int i, int j) { return i + j; }
public double getSum(int i, int j) { return (double)(i + j); } // 매개변수 동일 → 오류
```

오버라이딩 예시:
```java
class Dog extends Animal {
    @Override
    void speak() {
        System.out.println("멍멍");
    }
}
```

`@Override` 어노테이션은 필수는 아니지만 부모 메소드 이름을 잘못 입력했을 때 컴파일러가 오류를 잡아주므로 붙이는 것이 권장된다.

---

## 10. 객체 치환과 가비지 컬렉션

객체 대입은 값 복사가 아니라 참조(주소) 복사다.

```java
Samp ob1 = new Samp(3);
Samp ob2 = new Samp(4);

ob1 = ob2; // ob1도 id=4 객체를 가리킴
           // 기존 id=3 객체는 참조 끊김 → 가비지

System.out.println("ob1.id=" + ob1.get()); // 4
System.out.println("ob2.id=" + ob2.get()); // 4
```

가비지 컬렉션:
- 참조가 끊긴 객체는 가비지가 되며 JVM이 자동으로 메모리를 회수한다
- C/C++과 달리 개발자가 직접 삭제할 수 없다
- `System.gc()`로 요청은 가능하지만 실행 시점은 JVM이 결정한다

```java
Person a = new Person("이몽룡");
Person b = new Person("성춘향");

b = a; // "성춘향" 객체는 참조 끊김 → 가비지
```

---

## 11. 접근 지정자

| 지정자 | 접근 범위 |
|---|---|
| private | 같은 클래스 내부만 |
| default (생략) | 같은 패키지 내 |
| protected | 같은 패키지 + 상속 관계 |
| public | 모든 클래스 |

범위 크기: `private` < `default` < `protected` < `public`

`default`는 키워드 없이 아무것도 쓰지 않는 것이며, `protected`는 상속 관계 포함임을 반드시 기억해야 한다.

---

## 12. static 멤버

클래스에 속하며 모든 객체가 공유하는 멤버다. 클래스 로딩 시 1번만 생성되며, 객체 없이 클래스 이름으로 직접 접근한다.

```java
class StaticSample {
    int n;              // non-static 필드
    void g() { }        // non-static 메소드

    static int m;       // static 필드
    static void f() { } // static 메소드
}

StaticSample.m = 10; // 클래스명으로 접근 (권장)
StaticSample.f();
```

| 구분 | static | non-static |
|---|---|---|
| 생성 시점 | 클래스 로딩 시 | 객체 생성 시 |
| 공유 여부 | 모든 객체가 공유 | 객체마다 별도 존재 |
| 접근 방법 | 클래스명으로 접근 | 객체로 접근 |

static 메소드의 제약:
- non-static 멤버에 접근 불가 (객체가 없기 때문)
- `this` 사용 불가 (객체가 없기 때문)

```java
static void s1(int x) {
    n = x;      // 오류 - non-static 필드 접근 불가
    f1(3);      // 오류 - non-static 메소드 호출 불가
    this.m = x; // 오류 - this 사용 불가
}
```

전역 변수와 전역 함수가 필요할 때, 또는 모든 객체가 공유해야 하는 값이 있을 때 `static`을 활용한다.

---

## 13. final 키워드

| 대상 | 의미 |
|---|---|
| final 클래스 | 상속 불가 (`extends` 불가) |
| final 메소드 | 오버라이딩 불가 |
| final 필드 | 값 변경 불가 (상수), 선언 시 초기화 필수 |

```java
// final 클래스
final class FinalClass { }
class DerivedClass extends FinalClass { } // 오류

// final 메소드
class SuperClass {
    protected final int finalMethod() { return 0; }
}
class SubClass extends SuperClass {
    protected int finalMethod() { return 1; } // 오류
}

// final 필드
public static final double PI = 3.14; // 선언과 동시에 초기화 필수
ROWS = 30; // 오류 - 값 변경 불가
```

---
## 6주차 정리 (4월 8일)

---

## 1. 배열의 크기 - length 필드

자바의 배열은 객체로 처리되며, 배열의 크기는 `length` 필드에 저장된다.

```java
int intArray[] = new int[5];
int size = intArray.length; // 5
```

`length`를 활용한 순회:
```java
for(int i=0; i<intArray.length; i++)
    System.out.println(intArray[i]);
```

> **추가 설명**
> `length`는 메소드가 아니라 필드(변수)다. `String`의 `length()`와 혼동하기 쉬운데, 배열은 괄호 없이 `length`만 쓴다.
> ```java
> int[] arr = new int[5];
> arr.length        // 배열 - 괄호 없음
> "hello".length()  // String - 괄호 있음
> ```

> **실수하기 쉬운 포인트**
> `length`는 배열에 실제로 값이 들어있는 개수가 아니라 선언된 크기를 반환한다. `new int[5]`로 만들면 아무것도 안 넣어도 `length`는 5다.

---

## 2. 함수 호출 시 배열 전달 - C/C++ vs Java

C/C++는 배열과 크기를 별도로 전달해야 하지만, 자바는 배열만 전달해도 `length`로 크기를 알 수 있다.

```java
// Java
int sum(int x[]) {
    int s = 0;
    for(int n=0; n < x.length; n++)
        s += x[n];
    return s;
}
```

> **추가 설명**
> 자바에서 배열을 메소드에 전달하면 배열 전체가 복사되는 게 아니라 참조(주소)가 전달된다. 따라서 메소드 내에서 배열 원소를 수정하면 원본 배열도 바뀐다.
> ```java
> void addOne(int[] arr) {
>     arr[0] = 999; // 원본도 바뀜
> }
> ```

> **실수하기 쉬운 포인트**
> C처럼 "배열을 넘기면 복사본이 생긴다"고 생각하면 안 된다. 같은 배열을 가리키고 있어서 의도치 않게 원본을 수정할 수 있다.

---

## 3. for-each 문

배열이나 열거형의 원소를 순차적으로 접근하는 간결한 for문이다.

```java
int[] n = {1,2,3,4,5};
int sum = 0;

for(int k : n) {
    sum += k;
}
```

반복마다 `k`는 `n[0]`, `n[1]`, ..., `n[4]` 순서로 할당된다.

**예제 - 정수 배열 합산 및 문자열 배열 출력:**

```java
public class Ex39ForeachEx {
    public static void main(String[] args) {
        int[] n = { 1, 2, 3, 4, 5 };
        int sum = 0;

        for (int k : n) {
            System.out.print(k + " ");
            sum += k;
        }
        System.out.println("합은 " + sum);

        String f[] = { "사과", "배", "바나나", "체리", "딸기", "포도" };
        for (String s : f) {
            System.out.print(s + " ");
        }
    }
}
```

| 항목 | 내용 |
|---|---|
| 예상 출력 | `1 2 3 4 5 합은 15` / `사과 배 바나나 체리 딸기 포도` |

> **추가 설명**
> for-each는 읽기 전용 순회에 적합하다. 내부적으로 인덱스를 따로 관리하지 않기 때문에 현재 몇 번째 원소인지 알 수 없다. 인덱스가 필요한 경우에는 일반 for문을 써야 한다.

> **실수하기 쉬운 포인트**
> for-each 내부에서 변수에 값을 대입해도 원본 배열이 바뀌지 않는다.
> ```java
> for (int k : n) {
>     k = 0; // 원본 배열 n은 그대로
> }
> ```
> `k`는 배열 원소의 복사본이기 때문에 원본을 수정하려면 일반 for문으로 인덱스를 사용해야 한다.

---

## 4. 2차원 배열

**선언 및 생성:**

```java
int intArray[][] = new int[2][5];
```

**length 필드:**

```java
int i[][] = new int[2][5];
i.length       // 2 (행의 수)
i[0].length    // 5 (0번째 행의 열 수)
```

**Ragged Array (비정형 배열):**

자바의 2차원 배열은 실제로 배열 안에 배열이므로 행마다 열 개수가 달라도 된다.

```java
int[][] arr = new int[2][];
arr[0] = new int[3];
arr[1] = new int[5];
```

**선언과 동시에 초기화:**

```java
int intArray[][] = {
    { 0, 1, 2 },
    { 3, 4, 5 },
    { 6, 7, 8 }
};
```

> **추가 설명**
> 자바의 2차원 배열은 C처럼 메모리가 연속적으로 배치된 진짜 2D 구조가 아니다. 각 행이 독립적인 1차원 배열 객체이기 때문에 행마다 크기가 달라도 된다. 이것이 Ragged Array가 가능한 이유다.
>
> 2차원 배열 전체를 순회할 때는 이중 for문을 사용한다.
> ```java
> for(int r=0; r<intArray.length; r++) {
>     for(int c=0; c<intArray[r].length; c++) {
>         System.out.print(intArray[r][c] + " ");
>     }
> }
> ```
> `intArray[r].length`를 쓰면 Ragged Array에서도 안전하게 순회할 수 있다.

> **실수하기 쉬운 포인트**
> `intArray.length`는 행의 수고, 열의 수는 `intArray[0].length`다. 헷갈려서 행과 열을 반대로 쓰면 `ArrayIndexOutOfBoundsException`이 발생한다.

---

## 5. 메소드의 배열 리턴

배열 전체가 복사되는 것이 아니라 참조(주소)만 반환된다. 메소드 내 지역변수가 사라져도 Heap에 생성된 배열은 유지된다.

```java
int[] makeArray() {
    int temp[] = new int[4];
    return temp; // 참조 반환
}

int[] intArray = makeArray(); // 같은 배열을 가리킴
```

실제 배열이 사라지는 경우는 어떤 변수도 해당 배열을 참조하지 않을 때이며, 그 시점에 GC(Garbage Collector) 대상이 된다.

> **추가 설명**
> 지역변수 `temp`는 Stack에 저장되고, `new int[4]`로 생성된 실제 배열은 Heap에 저장된다. 메소드가 종료되면 Stack의 `temp`는 사라지지만 Heap의 배열은 살아있고, `intArray`가 그 주소를 이어받아 참조하게 된다.

> **실수하기 쉬운 포인트**
> 리턴 타입 선언 시 배열 크기를 지정하지 않는다.
> ```java
> int[4] makeArray() { ... } // 컴파일 에러
> int[]  makeArray() { ... } // 올바른 선언
> ```

---

## 6. 예외 처리 (Exception Handling)

**예외란:** 실행 중 발생하는 예상치 못한 오류 상황이다. 처리하지 않으면 프로그램이 강제 종료된다.

**주요 예외 클래스:**

| 예외 클래스 | 발생 상황 | 패키지 |
|---|---|---|
| ArithmeticException | 정수를 0으로 나눌 때 | java.lang |
| NullPointerException | null 참조 접근 시 | java.lang |
| ArrayIndexOutOfBoundsException | 배열 범위 초과 접근 시 | java.lang |
| ClassCastException | 잘못된 타입 변환 시 | java.lang |
| OutOfMemoryError | 메모리 부족 시 | java.lang |
| IllegalArgumentException | 잘못된 인자 전달 시 | java.lang |
| IOException | 입출력 실패 시 | java.io |
| NumberFormatException | 숫자 형식 불일치 변환 시 | java.lang |
| InputMismatchException | Scanner 입력 타입 불일치 시 | java.util |

**try-catch-finally 구조:**

```java
try {
    // 예외 발생 가능 코드
}
catch (처리할예외타입 e) {
    // 예외 처리 코드
}
finally {
    // 예외 발생 여부와 무관하게 항상 실행
}
```

**예제 - 0으로 나누기 예외 처리:**

```java
public class Ex13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dividend, divisor;

        System.out.print("나눔수를 입력하시오:");
        dividend = scanner.nextInt();

        System.out.print("나눗수를 입력하시오:");
        divisor = scanner.nextInt();

        try {
            System.out.println(dividend + "를 " + divisor + "로 나누면 몫은 "
                    + dividend / divisor + "입니다.");
        }
        catch (ArithmeticException e) {
            System.out.println("0으로 나눌 수 없습니다!");
        }
        finally {
            scanner.close();
        }
    }
}
```

| 항목 | 내용 |
|---|---|
| 정상 입력 | 몫 출력 후 scanner 닫힘 |
| 0 입력 시 | catch 블록 실행 → "0으로 나눌 수 없습니다!" 출력 |

> **추가 설명**
> `finally` 블록은 try에서 예외가 발생하든 안 하든, 심지어 catch에서 또 다른 예외가 발생해도 반드시 실행된다. `scanner.close()`처럼 반드시 해제해야 하는 자원 정리 코드를 넣기에 적합하다.
>
> `catch` 블록의 `e`를 통해 예외 정보를 출력할 수 있다.
> ```java
> catch (ArithmeticException e) {
>     System.out.println(e.getMessage());
> }
> ```

> **실수하기 쉬운 포인트**
> catch에 선언한 예외 타입과 실제 발생한 예외가 일치하거나 부모-자식 관계여야 처리된다. 타입이 다르면 catch를 통과해서 프로그램이 종료된다.
>
> `finally`는 `return`문 이후에도 실행된다는 점에 주의해야 한다.

---

## 7. 객체 지향 프로그래밍 (OOP)

### 캡슐화 (Encapsulation)

객체를 캡슐로 감싸 내부를 외부로부터 보호하는 것. `class`가 그 틀이 된다.

```java
class Animal {
    String name;
    int age;
    void eat() { ... }
    void speak() { ... }
}
```

> **추가 설명**
> 캡슐화의 핵심은 접근 제한자(`private`, `public`, `protected`)를 활용해 외부에서 함부로 내부 데이터를 건드리지 못하게 하는 것이다. 필드는 `private`으로 막고, 필요한 경우에만 `getter/setter` 메소드를 통해 접근하게 하는 패턴이 일반적이다.
> ```java
> class Animal {
>     private String name;
>
>     public String getName() { return name; }
>     public void setName(String n) { name = n; }
> }
> ```

### 상속 (Inheritance)

상위 클래스(슈퍼 클래스)의 멤버를 하위 클래스(서브 클래스)가 물려받는 것. `extends` 키워드 사용. is-a 관계일 때만 사용해야 한다.

```java
class Human extends Animal {
    String hobby;
    void work() { ... }
}
```

> **추가 설명**
> 자바는 단일 상속만 지원한다. 하나의 클래스는 하나의 부모 클래스만 가질 수 있으며, 다중 상속이 필요한 경우에는 인터페이스(`interface`)를 사용한다.

> **실수하기 쉬운 포인트**
> is-a 관계가 아닌데 코드 재사용만을 목적으로 상속을 쓰면 설계가 엉켜버린다. 단순 재사용이 목적이라면 상속보다 포함(has-a) 관계를 쓰는 게 낫다.

### 다형성 (Polymorphism)

같은 이름의 메소드가 클래스나 객체에 따라 다르게 동작하는 것.

**오버로딩 vs 오버라이딩:**

| 구분 | 오버로딩 (Overloading) | 오버라이딩 (Overriding) |
|---|---|---|
| 발생 위치 | 같은 클래스 내 | 상속 관계 |
| 조건 | 메소드 이름 같고, 매개변수 다름 | 부모 메소드를 자식이 재정의 |
| 결정 시점 | 컴파일 시 | 실행 시 (객체 기준) |

```java
// 오버로딩
class Test {
    void print(int a) {}
    void print(String a) {}
}

// 오버라이딩
class Dog extends Animal {
    @Override
    void speak() {
        System.out.println("멍멍");
    }
}
```

> **추가 설명**
> 오버라이딩 시 `@Override` 어노테이션은 필수는 아니지만 붙이는 것이 강력히 권장된다. 부모 메소드 이름을 잘못 입력했을 때 컴파일러가 오류를 잡아주기 때문이다.

> **실수하기 쉬운 포인트**
> 오버로딩은 반환 타입만 다르고 매개변수가 같으면 성립하지 않는다. 반드시 매개변수의 타입이나 개수가 달라야 한다.
> ```java
> int print(int a) {}
> void print(int a) {} // 컴파일 에러 - 오버로딩 아님
> ```

---

## 8. 클래스와 객체

| 구분 | 설명 |
|---|---|
| 클래스 | 객체의 속성과 행위를 정의한 설계도 |
| 객체 | 클래스로 생성된 실체, 메모리 공간을 가짐. 인스턴스(instance)라고도 함 |

클래스는 필드(멤버 변수)와 메소드(멤버 함수)로 구성되며, `public` 접근 지정자를 통해 외부 클래스의 접근을 허용할 수 있다.

> **추가 설명**
> 객체는 `new` 키워드로 생성하며, 생성할 때마다 독립적인 메모리 공간(Heap)이 할당된다. 같은 클래스로 만든 객체라도 각자 독립적인 필드 값을 가진다.
> ```java
> Animal a1 = new Animal();
> Animal a2 = new Animal();
> a1.name = "강아지";
> a2.name = "고양이";
> // a1과 a2는 서로 영향을 주지 않음
> ```

> **실수하기 쉬운 포인트**
> 객체를 선언만 하고 생성하지 않으면 `null` 상태가 된다. 이 상태에서 필드나 메소드에 접근하면 `NullPointerException`이 발생한다.
> ```java
> Animal a;
> a.name = "강아지"; // NullPointerException 발생
>
> Animal a = new Animal(); // 이렇게 생성해야 함
> ```

---
## 5주차 수업 (4월 1일)

## 1. 비교 연산자

```java
a < b      // a가 b보다 작으면 true
a > b      // a가 b보다 크면 true
a <= b     // a가 b보다 작거나 같으면 true
a >= b     // a가 b보다 크거나 같으면 true
a == b     // a가 b와 같으면 true
a != b     // a가 b와 같지 않으면 true
```

---

## 2. 논리 연산자

```java
!a         // a가 true면 false, false면 true
a ^ b      // XOR (서로 다르면 true, 같으면 false)
a || b     // OR (둘 중 하나라도 true면 true)
a && b     // AND (둘 다 true일 때만 true)
```

---

## 3. 조건 연산자 (삼항 연산자)

- 3개의 피연산자로 구성된 연산자 (ternary)
- `if-else`를 간결하게 표현 가능

**기본 구조**
```java
opr1 ? opr2 : opr3
// opr1이 true → opr2
// opr1이 false → opr3
```

**if-else vs 삼항 연산자 비교**
```java
// if-else 방식
int x = 5;
int y = 3;
int big;

if (x > y)
    big = x;
else
    big = y;

// 삼항 연산자 방식
int big = (x > y) ? x : y;
```

**코드 예제**
```java
public class TernaryOperator29 {

    public static void main(String[] args) {
        int a = 3, b = 5;

        System.out.println("두 수의 차는 " + ((a > b) ? (a - b) : (b - a)));
    }
}
```

**분석**

| 단계 | 내용 |
|------|------|
| `a = 3, b = 5` | a가 b보다 작으므로 `a > b`는 false |
| `(a > b) ? (a - b) : (b - a)` | false이므로 `b - a = 5 - 3 = 2` 반환 |

**실행 결과**
```
두 수의 차는 2
```

---

## 4. 비트 연산

**비트 기본 개념**
```java
byte x = 10;
// x → 00001010 (1바이트 = 8비트)
```

**비트 연산 활용 예시**

```java
// 1. 성능 최적화 - 곱셈/나눗셈 대체
int x = 5;
int result = x << 1;  // 5 * 2 = 10
System.out.println(result);
```
```
10
```

```java
// 2. 비트 마스크 - 권한 플래그 설정
class Permissions {
    static final int READ = 1;   // 0001
    static final int WRITE = 2;  // 0010
    static final int EXEC = 4;   // 0100
}

int userPermissions = Permissions.READ | Permissions.WRITE; // 0011 (3)
boolean canWrite = (userPermissions & Permissions.WRITE) != 0;
System.out.println("Can Write: " + canWrite);
```
```
Can Write: true
```

```java
// 3. RGB 색상 데이터 압축
int red = 255;
int green = 128;
int blue = 64;
int packedData = (red << 16) | (green << 8) | blue;
```

```java
// 4. 짝수/홀수 판별
int num = 10;
boolean isEven = (num & 1) == 0;

// 5. 부호 바꾸기 (2의 보수)
int x = 5;
int negativeX = ~x + 1;
```

---

## 5. 조건문

### 5-1. 단순 if문

```java
if (조건식) {
    // 실행 문장 (조건식이 참인 경우)
}

// 예제 1
int n = 10;
if (n % 2 == 0) {
    System.out.print(n);
    System.out.println("은 짝수입니다.");
}

// 예제 2
int score = 85;
if (score >= 80 && score <= 89)
    System.out.println("학점은 B입니다.");
```

### 5-2. if-else 문

```java
if (조건식) {
    // 실행 문장1 (참인 경우)
} else {
    // 실행 문장2 (거짓인 경우)
}

// 예제
int score2 = 90;
if (score2 >= 90) {
    System.out.println("합격입니다.");
} else {
    System.out.println("불합격입니다.");
}
```

**코드 예제**
```java
package week05;

import java.util.Scanner;

public class Ex10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("나이를 입력하시오:");
        int age = scanner.nextInt();
        if ((age >= 20) && (age < 30)) {
            System.out.println("20대입니다.");
            System.out.println("20대라서 행복합니다!.");
        } else {
            System.out.println("20대가 아닙니다.");
        }
        scanner.close();
    }
}
```

**분석**

| 조건 | 내용 |
|------|------|
| `age >= 20 && age < 30` | 입력값이 20 이상 30 미만이면 true |
| true일 때 | "20대입니다." + "20대라서 행복합니다!." 출력 |
| false일 때 | "20대가 아닙니다." 출력 |

**실행 결과** (입력: `25`)
```
나이를 입력하시오:25
20대입니다.
20대라서 행복합니다!.
```

### 5-3. 다중 if-else 문

```java
if (조건식1) {
    // 실행 문장1
} else if (조건식2) {
    // 실행 문장2
} else if (조건식3) {
    // 실행 문장3
} else {
    // 앞의 모든 조건이 거짓인 경우
}
```

> 조건문이 너무 많은 경우 switch 문 사용 권장. 실행 문장이 실행되면 이후 else 블록은 건너뜀.

---

## 6. switch 문

```java
switch (식) {
    case 값1:
        // 실행 문장1
        break;
    case 값2:
        // 실행 문장2
        break;
    default:
        // 어떤 case와도 같지 않을 때 실행
}
```

- `break`를 만나면 switch 문 탈출
- 일치하는 case가 없으면 `default` 실행
- `default`는 생략 가능

**예제**
```java
char grade = 'B';

switch (grade) {
    case 'A':
        System.out.println("축하합니다.");
        System.out.println("잘했습니다.");
        break;
    case 'B':
        System.out.println("좋아요.");
        break;
    case 'C':
        System.out.println("노력하세요.");
        break;
    default:
        System.out.println("탈락입니다!");
}
```

**실행 결과**
```
좋아요.
```

**case 값 허용 범위**

| 허용 | 불허 |
|------|------|
| 정수 리터럴 | 실수 리터럴 |
| 문자 리터럴 | 변수 |
| 문자열 리터럴 (JDK 1.7+) | 수식 (예: `a > 3`) |

**코드 예제 - 계절 판별**
```java
package week05;

import java.util.Scanner;

public class Ex13 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("월(1~12)을 입력하시오:");
        int month = scanner.nextInt();

        switch (month) {
            case 3:
            case 4:
            case 5:
                System.out.println("봄입니다.");
                break;
            case 6:
            case 7:
            case 8:
                System.out.println("여름입니다.");
                break;
            case 9:
            case 10:
            case 11:
                System.out.println("가을입니다.");
                break;
            case 12:
            case 1:
            case 2:
                System.out.println("겨울입니다.");
                break;
            default:
                System.out.println("잘못된 입력입니다.");
        }

        scanner.close();
    }
}
```

**분석**

| 단계 | 내용 |
|------|------|
| `month = 7` (예시) | case 6, 7, 8 중 7에 해당 |
| fall-through | case 6, 7은 break 없이 case 8로 이어지다가 "여름입니다." 출력 후 break |
| 1~12 외 값 | default 실행 |

**실행 결과** (입력: `7`)
```
월(1~12)을 입력하시오:7
여름입니다.
```

---

## 7. 반복문

### 7-1. while 문

```java
// 기본 구조
while (조건식) {
    // 조건식이 참인 동안 반복 실행
}

// 예시
int i = 0;
while (i < 10) {
    System.out.print(i);
    i++;
}
```
```
0123456789
```

**코드 예제 - 평균 구하기**
```java
import java.util.Scanner;

public class Ex32WhileSample {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int count = 0, n = 0;
        double sum = 0;

        System.out.println("정수를 입력하고 마지막에 0을 입력하세요.");

        while ((n = scanner.nextInt()) != 0) {
            sum = sum + n;
            count++;
        }

        System.out.print("수의 개수는 " + count + "개이며 ");
        System.out.println("평균은 " + (sum / count) + "입니다.");

        scanner.close();
    }
}
```

**분석**

| 단계 | 내용 |
|------|------|
| `n = scanner.nextInt()` | 입력값을 n에 저장하면서 동시에 0인지 비교 |
| `n != 0` | 0이 아니면 반복 계속, 0이면 탈출 |
| `sum += n`, `count++` | 합계와 개수 누적 |
| `sum / count` | 반복 종료 후 평균 출력 |

**실행 결과** (입력: `10 20 30 0`)
```
정수를 입력하고 마지막에 0을 입력하세요.
수의 개수는 3개이며 평균은 20.0입니다.
```

### 7-2. do-while 문

```java
// 기본 구조 - 작업문이 최소 한 번 반드시 실행됨
do {
    // 작업문
} while (조건식);

// 예시
int i = 0;
do {
    System.out.print(i);
    i++;
} while (i < 10);
```
```
0123456789
```

### 7-3. 중첩 반복

```java
for (int i = 0; i < 100; i++) {
    for (int j = 0; j < 10000; j++) {
        // 작업 수행
    }
}
```

**코드 예제 - 구구단 (가로 출력)**
```java
package week05;

public class Ex04 {
    public static void main(String[] args) {

        for (int i = 2; i <= 9; i++) {
            System.out.print("[" + i + "단]\t");
        }
        System.out.println();

        for (int j = 1; j <= 9; j++) {
            for (int i = 2; i <= 9; i++) {
                System.out.print(i + "X" + j + "=" + (i * j) + "\t");
            }
            System.out.println();
        }
    }
}
```

**분석**

| 단계 | 내용 |
|------|------|
| 첫 번째 for | 단 제목(2단~9단) 가로로 출력 |
| 바깥 for (`j`) | 행(1~9) 기준, 세로 반복 |
| 안쪽 for (`i`) | 열(2단~9단) 기준, 가로 반복 |
| `\t` | 탭으로 정렬 |

**실행 결과**
```
[2단]   [3단]   [4단]   [5단]   [6단]   [7단]   [8단]   [9단]
2X1=2   3X1=3   4X1=4   5X1=5   6X1=6   7X1=7   8X1=8   9X1=9
2X2=4   3X2=6   4X2=8   5X2=10  6X2=12  7X2=14  8X2=16  9X2=18
...
2X9=18  3X9=27  4X9=36  5X9=45  6X9=54  7X9=63  8X9=72  9X9=81
```

**코드 예제 - 구구단 (단 별 출력)**
```java
package week05;

public class Ex04r {
    public static void main(String[] args) {

        for (int i = 2; i <= 9; i++) {
            System.out.println("[" + i + "단]");

            for (int j = 1; j <= 9; j++) {
                System.out.println(i + " * " + j + " = " + (i * j));
            }

            System.out.println();
        }
    }
}
```

**실행 결과**
```
[2단]
2 * 1 = 2
2 * 2 = 4
...
2 * 9 = 18

[3단]
3 * 1 = 3
...
```

---

## 8. continue 문

```java
// 다음 반복으로 넘어감 (아래 코드 건너뜀)

// 예제: 양수 합 구하기
import java.util.Scanner;

public class Ex35ContinueExample {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("정수를 5개 입력하세요.");
        int sum = 0;

        for (int i = 0; i < 5; i++) {
            int n = scanner.nextInt();

            if (n <= 0) continue;  // 0 또는 음수는 건너뜀
            else sum += n;         // 양수만 더함
        }

        System.out.println("양수의 합은 " + sum);

        scanner.close();
    }
}
```

**분석**

| 단계 | 내용 |
|------|------|
| `n <= 0` | 0 이하인 경우 continue로 이번 반복 건너뜀 |
| `sum += n` | 양수인 경우에만 합산 |

**실행 결과** (입력: `3 -1 0 5 2`)
```
정수를 5개 입력하세요.
양수의 합은 10
```

---

## 9. break 문

```java
// 가장 가까운 반복문 하나를 즉시 종료

// 예제 1: 단일 반복문
for (int i = 1; i <= 10; i++) {
    if (i == 5) break;
    System.out.println(i);
}
```
```
1
2
3
4
```

```java
// 예제 2: 중첩 반복 - 안쪽 반복문만 종료
for (int i = 1; i <= 3; i++) {
    for (int j = 1; j <= 5; j++) {
        if (j == 3) break;
        System.out.println("i=" + i + ", j=" + j);
    }
}
```
```
i=1, j=1
i=1, j=2
i=2, j=1
i=2, j=2
i=3, j=1
i=3, j=2
```

**코드 예제 - 입력 종료**
```java
import java.util.Scanner;

public class Ex36BreakExample {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("exit를 입력하면 종료합니다.");

        while (true) {
            System.out.print(">>");
            String text = scanner.nextLine();

            if (text.equals("exit"))
                break;
        }

        System.out.println("종료합니다...");
        scanner.close();
    }
}
```

**분석**

| 단계 | 내용 |
|------|------|
| `while (true)` | 무한 루프 |
| `text.equals("exit")` | 입력값이 "exit"이면 break로 탈출 |
| `scanner.nextLine()` | 공백 포함 한 줄 전체 읽음 |

**실행 결과** (입력: `hello`, `exit`)
```
exit를 입력하면 종료합니다.
>>hello
>>exit
종료합니다...
```

---

## 10. 배열 (Array)

- 인덱스와 인덱스에 대응하는 데이터들로 이루어진 자료 구조
- 같은 타입의 데이터들이 순차적으로 저장됨
- 인덱스는 0부터 시작
- 반복문을 이용하여 처리하기에 적합한 자료 구조

### 10-1. 배열 선언과 생성

```java
// (1) 선언과 생성 분리
int intArray[];          // 선언
intArray = new int[5];   // 생성

// (2) 선언과 생성 동시에
int[] intArray = new int[5];

// (3) 선언과 동시에 초기화
int[] intArray = {4, 3, 2, 1, 0};
double[] doubleArray = {0.01, 0.02, 0.03, 0.04};

// 잘못된 선언 (오류)
int intArray[5];  // 선언 시 크기 지정 불가
```

### 10-2. 배열 인덱스와 원소 접근

```java
int[] intArray = new int[5];

intArray[0] = 5;  // 인덱스 0에 5 저장
intArray[3] = 6;  // 인덱스 3에 6 저장

int n = intArray[3];  // 인덱스 3의 값 읽기 → n = 6

// 잘못된 접근 (오류)
int x = intArray[-2];  // 음수 인덱스 불가
int y = intArray[5];   // 범위 초과 (0~4까지)

// 생성 전 접근 (오류)
int[] arr;
arr[1] = 8;  // NullPointer 오류

// 올바른 방식
arr = new int[5];
arr[1] = 8;
```

**코드 예제 - 최대값 찾기**
```java
package week05;

import java.util.Scanner;

public class Ex07 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int intArray[];
        intArray = new int[5];

        int max = 0;

        System.out.println("양수 5개를 입력하세요.");

        for (int i = 0; i < 5; i++) {
            intArray[i] = scanner.nextInt();

            if (intArray[i] > max) {
                max = intArray[i];
            }
        }

        System.out.println("가장 큰 수는 " + max + "입니다.");

        scanner.close();
    }
}
```

**분석**

| 단계 | 내용 |
|------|------|
| `max = 0` | 최대값 초기화 (입력이 모두 양수라는 전제) |
| `intArray[i] = scanner.nextInt()` | 입력값을 배열에 순서대로 저장 |
| `if (intArray[i] > max)` | 현재 입력값이 기존 최대값보다 크면 갱신 |

**실행 결과** (입력: `3 7 2 9 5`)
```
양수 5개를 입력하세요.
가장 큰 수는 9입니다.
```

**코드 예제 - for문 합산**
```java
package week05;

public class Ex01 {
    public static void main(String[] args) {

        int i, sum = 0;
        for (i = 1; i <= 10; i++) {
            sum += i;
            System.out.println(i);
            if (i < 9)
                System.out.println("+");
            else {
                System.out.println("=");
                System.out.println(sum);
            }
        }
    }
}
```

**분석**

| 단계 | 내용 |
|------|------|
| `sum += i` | 1부터 i까지 누적 합산 |
| `i < 9` | 9 미만이면 "+" 출력 |
| `i >= 9` (else) | 9, 10일 때 "=" 출력 후 누적합 출력 |

**실행 결과**
```
1
+
2
+
3
+
4
+
5
+
6
+
7
+
8
+
9
=
45
10
=
55
```

---

## 4주차 수업 (3월 25일)

## 1. 자바의 특징 (계속)

### 가비지 컬렉션 (Garbage Collection)

- 자바 언어는 메모리 할당 기능은 있어도 **메모리 반환 기능 없음**
- 사용하지 않는 메모리는 **JVM에 의해 자동 반환 — 가비지 컬렉션(GC)**
- 개발자가 직접 메모리를 해제하지 않아도 됨 (C/C++과의 차이점)

> **실시간 응용프로그램에 부적합한 이유**: 실행 도중 **예측할 수 없는 시점**에 GC가 실행되어 응용프로그램이 일시적으로 중단될 수 있음

---

### 안전한 프로그램

- **타입 체크가 엄격**하여 잘못된 타입 사용 시 컴파일 단계에서 오류 발생
- **포인터(Pointer) 개념 없음** → 물리적 메모리 주소 직접 접근 불가 → 메모리 오염 방지

---

### 프로그램 작성 용이

- 포인터 개념 없음
- 동적 메모리 직접 반환 불필요 (GC가 처리)
- 다양한 라이브러리(Java API) 지원

---

### JIT 컴파일러 (Just-In-Time Compiler)

- 자바는 기본적으로 바이트코드를 **인터프리터 방식**으로 실행 → 기계어 직접 실행보다 느림
- **JIT 컴파일 기법**으로 실행 속도 개선
  - 실행 중에 바이트코드를 **기계어 코드로 컴파일**하여 실행
  - 자주 실행되는 코드는 컴파일된 기계어로 캐싱되어 반복 실행 시 빠름

---

## 2. 소스코드 · 바이트코드 · 기계어 정리

| 구분 | 파일 | 실행 주체 | 플랫폼 종속성 |
|------|------|-----------|---------------|
| 소스코드 | `.java` | 사람이 읽음 | 없음 |
| 바이트코드 | `.class` | JVM | 없음 (독립적) |
| 기계어 | 없음 (메모리) | CPU 직접 실행 | 있음 (CPU/OS 종속) |

- **소스코드**: 우리가 작성하는 Java 코드 (`.java` 파일), 사람이 읽을 수 있는 고수준 언어
- **바이트코드**: `javac`가 소스코드를 변환한 중간 코드 (`.class` 파일)
  - CPU가 직접 실행 불가 → **JVM이 인터프리터**로 해석하거나 **JIT 컴파일러**가 기계어로 변환하여 실행
  - 플랫폼 독립적 (Windows, Mac, Linux 동일하게 실행 가능)
- **기계어**: CPU가 직접 실행하는 0과 1의 이진 코드, OS와 CPU 아키텍처(Intel, ARM 등)에 따라 다름

---

## 3. Temurin OpenJDK 24에서 jmods 디렉토리가 사라진 이유

- **JEP 493** 표준을 따르게 되어 `jmods` 디렉토리가 포함되지 않음
- JEP 493 요약:
  - `jlink` 툴이 **JMOD 파일 없이도** 사용자 지정 런타임 이미지(Custom run-time image) 생성 가능
  - JDK 크기 약 **25% 절감** 효과
  - 기본적으로 비활성화이나, **Temurin OpenJDK 24는 빌드 시 자동 활성화**

---

## 4. 식별자 (Identifier) — 명명 규칙

> 클래스, 변수, 상수, 메소드 등에 붙이는 이름

| 규칙 | 설명 |
|------|------|
| 사용 가능 특수문자 | `_`, `$` 만 허용 (`@`, `#`, `!` 등 불가) |
| 공백·탭 | 사용 불가 |
| 유니코드 | 한글 포함 가능 (실무에서는 사용 금지) |
| 첫 문자 | 숫자 사용 불가, `_`·`$`는 가능하나 지양 |
| 키워드 사용 | `int`, `class`, `for` 등 예약어 사용 불가 |
| 리터럴 사용 | `true`, `false`, `null` 사용 불가 |
| 길이 | 제한 없음 |
| 대소문자 | 구별함 (`barChart` ≠ `barchart`) |

---

## 5. Java 기본 자료형 (Primitive Type) 8개

| 타입 | 크기 | 범위 / 특징 |
|------|------|-------------|
| `boolean` | 1비트 | `true` 또는 `false` |
| `char` | 2바이트 | Unicode 문자 (0 ~ 65535) |
| `byte` | 1바이트 | -128 ~ 127 |
| `short` | 2바이트 | -32,768 ~ 32,767 |
| `int` | 4바이트 | -2³¹ ~ 2³¹-1 |
| `long` | 8바이트 | -2⁶³ ~ 2⁶³-1 |
| `float` | 4바이트 | 약 ±3.4E38 |
| `double` | 8바이트 | 약 ±1.7E308 |

> Java 기본 타입 크기는 **CPU나 운영체제와 무관하게 고정**됨 (C/C++과의 차이)

---

## 6. 참조 자료형 (Reference Type)

- **레퍼런스형 1개**, 용도는 클래스 / 인터페이스 / 배열에 대한 참조
- 포인터와 유사하나, **임의의 메모리 주소를 직접 저장할 수 없음** → JVM이 주소를 관리
- 객체는 **힙(Heap) 영역**에 저장, 참조 변수는 해당 주소를 가리킴
- `new` 키워드로 객체(인스턴스) 생성
- 객체를 참조하지 않을 때 `null` 값 가질 수 있음
- 같은 객체를 여러 변수가 참조 가능, `==` 연산자로 주소 비교 가능
- 문자열은 기본 타입이 아님 → **`String` 클래스**로 표현

> **배열(Array), 인터페이스(Interface), 열거형(Enum)** 도 객체이므로 참조 자료형

---

## 7. 메모리 구조
```
[ 스택 (Stack) ]          [ 힙 (Heap) ]
- LIFO 구조               - FIFO 구조
- 기본 자료형 저장         - 참조 자료형(객체) 저장
- 메소드 호출 시 자동 생성  - new 키워드로 생성
- 메소드 종료 시 자동 소멸  - GC가 자동 반환
```

- **힙 오버플로우**: 힙이 스택 영역을 침범하는 경우
- **스택 오버플로우**: 스택이 힙 영역을 침범하는 경우 (재귀 무한 호출 등)

---

## 8. 변수 (Variable) vs 상수 (Constant)

| 구분 | 선언 | 값 변경 |
|------|------|---------|
| 변수 | `int a = 10;` | 가능 |
| 상수 | `final int A = 10;` | 불가 (컴파일 에러) |

- 상수는 `final` 키워드 사용
- 상수명은 관례상 **대문자 + 언더스코어** 사용 (예: `MAX_SIZE`)

---

## 9. var 키워드 (Java 10+)

- **타입 추론**: 컴파일러가 초기값을 보고 타입 자동 결정
- **지역 변수 전용** (클래스 필드에서는 사용 불가)
- 초기값 없이 선언하면 **컴파일 오류** 발생
```java
var list = new ArrayList<String>(); // ArrayList<String>으로 추론
var num = 10;                        // int로 추론
```

> **권장 사용 기준**: 가독성이 유지되는 경우에만 사용. 기본적으로는 명시적 자료형(`int`, `String` 등) 사용 권장

---

## 10. System.out.print 종류

| 메소드 | 줄 바꿈 | 특징 |
|--------|---------|------|
| `System.out.print()` | 없음 | 줄 바꿈 없음. `\n` 직접 삽입 필요 |
| `System.out.println()` | 있음 | 자동 줄 바꿈 (가장 많이 사용) |
| `System.out.printf()` | 없음 | 형식 지정(formatting) 출력 |

### printf 형식 지정자

| 지정자 | 타입 | 예시 |
|--------|------|------|
| `%d` | 정수 | `printf("%d", 10)` → `10` |
| `%f` | 실수 | `printf("%.2f", 3.1415)` → `3.14` |
| `%s` | 문자열 | `printf("%s", "Hello")` → `Hello` |
| `%c` | 문자 | `printf("%c", 'A')` → `A` |
| `%b` | 불리언 | `printf("%b", true)` → `true` |

---

## 11. 타입 변환

### 자동 타입 변환 (묵시적)

- 컴파일러가 **작은 타입 → 큰 타입**으로 자동 변환
- 데이터 손실 없음
```java
long m = 25;           // int → long 자동 변환
double d = 3.14 * 10;  // int 10 → double 10.0 자동 변환
```

### 강제 타입 변환 (명시적, 캐스팅)

- 개발자가 `(타입)` 형태로 직접 지정
- **큰 타입 → 작은 타입** 변환 시 **값 손실** 가능
```java
int i = (int) 2.9;   // 2 (소수점 버림)
byte b = (byte) 200; // 값 손실 발생
```

> `int → byte` 등 **큰 타입에서 작은 타입으로는 자동 변환되지 않음** → 컴파일 오류

---

## 12. System.in vs Scanner

### System.in

- 키보드와 연결된 **표준 입력 스트림**
- 입력된 키를 **바이트(byte) 단위**로 반환 (저수준 스트림)
- 바이트를 직접 문자/숫자로 변환하기 어려워 **직접 사용하기 불편**

### Scanner 클래스

- `System.in`을 감싸는 **고수준 입력 처리 클래스**
- 읽은 바이트를 문자, 정수, 실수, 불린, 문자열 등 **다양한 타입으로 변환하여 반환**
- 입력을 **공백 문자(`\t`, `\r`, `\n`, ` `, `\f`)** 기준으로 토큰 단위로 분리

| 구분 | System.in | Scanner |
|------|-----------|---------|
| 반환 타입 | byte | int, String, double 등 다양 |
| 사용 난이도 | 어려움 | 쉬움 |
| 실무 사용 빈도 | 낮음 | 높음 |
| 임포트 필요 | 없음 | `import java.util.Scanner` 필요 |

> **실무 및 학습에서는 Scanner를 훨씬 많이 사용**. `System.in`은 성능이 중요한 저수준 I/O나 커스텀 파싱이 필요한 경우에 사용

---

## 13. 자바 연산자 전체 정리

### 13-1. 산술 연산자

| 연산자 | 의미 | 예시 | 결과 |
|--------|------|------|------|
| `+` | 덧셈 (문자열 연결) | `3 + 2` / `"Hi" + 1` | `5` / `"Hi1"` |
| `-` | 뺄셈 | `3 - 2` | `1` |
| `*` | 곱셈 | `3 * 2` | `6` |
| `/` | 나눗셈 (정수끼리면 몫만) | `10 / 4` / `10.0 / 4` | `2` / `2.5` |
| `%` | 나머지 | `10 % 3` | `1` |

---

### 13-2. 증감 연산자

| 연산자 | 의미 | 예시 |
|--------|------|------|
| `++a` | 전위 증가: 먼저 +1, 그 다음 사용 | `int a=5; int b=++a;` → `a=6, b=6` |
| `a++` | 후위 증가: 먼저 사용, 그 다음 +1 | `int a=5; int b=a++;` → `a=6, b=5` |
| `--a` | 전위 감소 | `int a=5; int b=--a;` → `a=4, b=4` |
| `a--` | 후위 감소 | `int a=5; int b=a--;` → `a=4, b=5` |

---

### 13-3. 비교(관계) 연산자 — 결과: `boolean`

| 연산자 | 의미 | 예시 | 결과 |
|--------|------|------|------|
| `==` | 같음 | `5 == 5` | `true` |
| `!=` | 다름 | `5 != 3` | `true` |
| `>` | 크다 | `5 > 3` | `true` |
| `<` | 작다 | `5 < 3` | `false` |
| `>=` | 크거나 같다 | `5 >= 5` | `true` |
| `<=` | 작거나 같다 | `3 <= 5` | `true` |

---

### 13-4. 논리 연산자 — 결과: `boolean`

| 연산자 | 의미 | 예시 | 설명 |
|--------|------|------|------|
| `&&` | AND (논리곱) | `true && false` → `false` | 둘 다 `true`여야 `true` |
| `\|\|` | OR (논리합) | `true \|\| false` → `true` | 하나라도 `true`면 `true` |
| `!` | NOT (부정) | `!true` → `false` | 반전 |

> **단락 평가(Short-circuit evaluation)**: `&&`는 앞이 `false`면 뒤를 평가하지 않음, `||`는 앞이 `true`면 뒤를 평가하지 않음

---

### 13-5. 대입(치환) 연산자

| 연산자 | 의미 | 예시 | 동일 표현 |
|--------|------|------|-----------|
| `=` | 대입 | `a = 5` | — |
| `+=` | 더한 후 대입 | `a += 3` | `a = a + 3` |
| `-=` | 뺀 후 대입 | `a -= 3` | `a = a - 3` |
| `*=` | 곱한 후 대입 | `a *= 3` | `a = a * 3` |
| `/=` | 나눈 후 대입 | `a /= 3` | `a = a / 3` |
| `%=` | 나머지 후 대입 | `a %= 3` | `a = a % 3` |

---

### 13-6. 비트 연산자

| 연산자 | 의미 | 예시 (`a=5(0101)`, `b=3(0011)`) | 결과 |
|--------|------|---------------------------------|------|
| `&` | 비트 AND | `5 & 3` | `1` (0001) |
| `\|` | 비트 OR | `5 \| 3` | `7` (0111) |
| `^` | 비트 XOR | `5 ^ 3` | `6` (0110) |
| `~` | 비트 NOT (반전) | `~5` | `-6` |
| `<<` | 왼쪽 시프트 | `5 << 1` | `10` (×2 효과) |
| `>>` | 오른쪽 시프트 (부호 유지) | `5 >> 1` | `2` (÷2 효과) |
| `>>>` | 오른쪽 시프트 (부호 무시, 0 채움) | `-1 >>> 1` | `2147483647` |

---

### 13-7. 삼항 연산자 (조건 연산자)
```java
조건식 ? 참일 때 값 : 거짓일 때 값
```
```java
int a = 10, b = 20;
int max = (a > b) ? a : b;  // max = 20
```

---

### 13-8. instanceof 연산자
```java
객체 instanceof 클래스명
```

- 객체가 해당 클래스의 인스턴스인지 확인 → `boolean` 반환
```java
String s = "Hello";
System.out.println(s instanceof String);  // true
```

---

### 13-9. 연산자 우선순위

| 우선순위 (높음 → 낮음) | 연산자 |
|------------------------|--------|
| 1 | `()`, `[]`, `.` |
| 2 | `++`, `--`, `!`, `~`, `+`, `-` (단항) |
| 3 | `*`, `/`, `%` |
| 4 | `+`, `-` |
| 5 | `<<`, `>>`, `>>>` |
| 6 | `<`, `<=`, `>`, `>=`, `instanceof` |
| 7 | `==`, `!=` |
| 8 | `&` |
| 9 | `^` |
| 10 | `\|` |
| 11 | `&&` |
| 12 | `\|\|` |
| 13 | `? :` (삼항) |
| 14 | `=`, `+=`, `-=` 등 대입 |

---

## 14. 코드 분석

### 14-1. Foo.java

```java
package week04;

public class Foo {
  public static int sum(int n, int m) {
    return n + m;
  }

  public static void main(String[] args) {
    int i = 20;
    int s;
    char a;

    s = sum(i, 10);
    a = '?';
    System.out.println(a);
    System.out.println("Hello");
    System.out.println(s);
  }
}
```

**분석**

| 단계 | 내용 |
|------|------|
| `int i = 20` | 정수 변수 `i`에 20 저장 |
| `s = sum(i, 10)` | `sum(20, 10)` 호출 → `20 + 10 = 30` → `s = 30` |
| `a = '?'` | 문자 변수 `a`에 `'?'` 저장 |
| `println(a)` | 문자 `'?'` 출력 |
| `println("Hello")` | 문자열 출력 |
| `println(s)` | 정수 30 출력 |

**실행 결과**
```
?
Hello
30
```

---

### 14-2. bar.java

```java
package week04;

public class bar {
  public static void main(String[] args) {
    final double PI = 3.14;
    double radius = 10.2;
    double circleArea = radius * radius * PI;

    System.out.print("반지름 " + radius + ", ");
    System.out.println("원의 면적");
    System.out.println("원의 면적 = " + circleArea);
  }
}
```

**분석**

| 단계 | 내용 |
|------|------|
| `final double PI = 3.14` | 원주율을 **상수**로 선언. `final`이므로 이후 변경 불가 |
| `double radius = 10.2` | 반지름 변수 선언 |
| `circleArea = 10.2 * 10.2 * 3.14` | 원의 넓이 = 반지름² × π = `104.04 * 3.14 = 326.6856` |
| `print(...)` | 줄 바꿈 없이 출력 |
| `println(...)` | 줄 바꿈 포함 출력 |

**실행 결과**
```
반지름 10.2, 원의 면적
원의 면적 = 326.6856
```

---

### 14-3. Woo.java (타입 변환)

```java
package week04;

public class Woo {
  public static void main(String[] args) {
    byte b = 127;
    int i = 100;

    System.out.println(b + i);
    System.out.println(10 / 4);
    System.out.println(10.0 / 4);
    System.out.println((char) 0x12340041);
    System.out.println((byte) (b + i));
    System.out.println((int) 2.9 + 1.8);
    System.out.println((int) (2.9 + 1.8));
    System.out.println((int) 2.9 + (int) 1.8);
  }
}
```

**분석 및 실행 결과**

| 코드 | 분석 | 결과 |
|------|------|------|
| `b + i` | `byte(127) + int(100)` → `b`가 `int`로 자동 변환 → `227` | `227` |
| `10 / 4` | 정수 나눗셈 → 몫만 반환 | `2` |
| `10.0 / 4` | `4`가 `4.0`으로 자동 변환 → 실수 나눗셈 | `2.5` |
| `(char) 0x12340041` | 하위 2바이트 `0x0041` = 65 = `'A'`로 강제 변환 | `A` |
| `(byte)(b + i)` | `227`을 `byte`로 강제 변환 → `227 - 256 = -29` (오버플로우) | `-29` |
| `(int) 2.9 + 1.8` | `(int)2.9 = 2` → `2 + 1.8` → `int + double` → `3.8` | `3.8` |
| `(int)(2.9 + 1.8)` | `2.9 + 1.8 = 4.7` → `(int)4.7 = 4` | `4` |
| `(int)2.9 + (int)1.8` | `2 + 1 = 3` | `3` |

**실행 결과**
```
227
2
2.5
A
-29
3.8
4
3
```

---

### 14-4. Boo.java (Scanner)

```java
package week04;

import java.util.Scanner;

public class Boo {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    String name = scanner.next();
    System.out.println("당신의 이름은 " + name + "입니다.");

    String city = scanner.next();
    System.out.println("당신이 사는 도시는 " + city + "입니다.");

    int age = scanner.nextInt();
    System.out.println("당신의 나이는 " + age + "살입니다.");

    double weight = scanner.nextDouble();
    System.out.println("당신의 체중은 " + weight + "kg입니다.");

    boolean single = scanner.nextBoolean();
    System.out.println("당신은 독신 여부는 " + single + "입니다.");

    scanner.close();
  }
}
```

**분석**

| 메소드 | 읽는 타입 | 설명 |
|--------|-----------|------|
| `scanner.next()` | `String` | 공백 기준 다음 토큰 하나 읽음 |
| `scanner.nextInt()` | `int` | 정수 읽음 |
| `scanner.nextDouble()` | `double` | 실수 읽음 |
| `scanner.nextBoolean()` | `boolean` | `true`/`false` 읽음 |
| `scanner.close()` | — | 스트림 자원 해제 (사용 후 반드시 닫기) |

> `next()`는 **공백 포함 문자열을 읽지 못함**. 공백 포함 한 줄 전체를 읽으려면 `nextLine()` 사용

**실행 결과** (입력: `이동주 서울 22 70.5 false`)
```
당신의 이름은 이동주입니다.
당신이 사는 도시는 서울입니다.
당신의 나이는 22살입니다.
당신의 체중은 70.5kg입니다.
당신은 독신 여부는 false입니다.
```

---

## 3주차 수업 (3월 18일)

## 1. 프로그래밍 언어의 종류

### 1-1. 기계어 (Machine Language)

- `0`과 `1`의 이진수로 구성된 언어
- **CPU는 기계어만 이해하고 처리할 수 있음**

---

### 1-2. 어셈블리어 (Assembly Language)

- 기계어 명령을 `ADD`, `SUB`, `MOV` 등의 **니모닉 기호(Mnemonic Symbol)** 로 일대일 대응시킨 언어
- 기계어보다 읽기 쉽지만, 여전히 하드웨어에 종속적

> 니모닉(Mnemonic): 기억하기 쉽도록 만든 상징적 약어

---

### 1-3. 고급 언어 (High-Level Language)

- 사람이 이해하기 쉽도록 설계된 언어
- 예: Pascal, BASIC, C/C++, Java, C#
- **절차 지향 언어**와 **객체 지향 언어**, **함수형 언어**로 분류됨

---

### 1-4. 언어 패러다임 비교

| 구분 | 절차 지향 | 객체 지향 | 함수형 |
|------|-----------|-----------|--------|
| 핵심 개념 | 순서·절차 | 객체·클래스 | 함수·불변성 |
| 데이터 처리 | 데이터와 함수 분리 | 데이터와 함수 캡슐화 | 데이터 변환 |
| 재사용성 | 낮음 | 높음 | 높음 |
| 대표 언어 | C, Pascal, Fortran | Java, C++, Python | Haskell, Lisp, Scala |
| 특징 | 전역 변수 다수 사용 | 상속·캡슐화·다형성 | 순수 함수·고차 함수·재귀 |

#### 절차 지향 언어 (Procedural Language)

- 프로그램을 절차·순서에 따라 실행하는 방식
- 데이터(입력)와 함수를 **분리**하여 작성
- 전역 변수 사용이 많아 코드 가독성·유지보수가 어려움
- 코드 유연성 부족, 재사용 어려움
- 예: `C`, `Pascal`, `Fortran`

#### 객체 지향 언어 (Object-Oriented Language)

- 현실의 객체를 모델링하여 프로그램을 작성하는 방식
- **상속(Inheritance), 캡슐화(Encapsulation), 다형성(Polymorphism)** 을 활용
- 유연하고 재사용 가능한 코드 작성 가능
- 예: `Java`, `C++`, `Python`

#### 함수형 언어 (Functional Language)

- **함수를 일급 객체(First-Class Object)로 취급**
- 상태 변경을 피하고 **불변성(Immutability)** 을 지향
- 함수의 조합으로 복잡한 작업 수행
- **재귀**, **고차 함수(Higher-Order Function)**, **순수 함수(Pure Function)** 개념 중시
- 병렬 처리와 높은 수준의 추상화 지원
- 예: `Python`, `Kotlin`, `Haskell`, `Lisp`, `Scala`

> 함수형 언어는 객체 지향의 특성을 그대로 가져가되 함수형 기능을 추가한 형태 (Python, Kotlin 등은 멀티 패러다임 언어)

---

## 2. 컴파일 (Compile)

| 용어 | 설명 |
|------|------|
| 소스(Source) | 프로그래밍 언어로 작성된 텍스트 파일 |
| 컴파일(Compile) | 소스 파일을 컴퓨터가 이해할 수 있는 기계어로 변환하는 과정 |

### 언어별 컴파일 과정
```
Java  : .java  →  .class (바이트코드)  →  JVM 실행
C     : .c     →  .obj   →  .exe
C++   : .cpp   →  .obj   →  .exe
```

> Java는 `.exe`가 아닌 `.class`(바이트코드)로 컴파일되며, JVM 위에서 실행됩니다.

---

## 3. Java의 탄생과 목적

### 3-1. 역사

- **1991년** 썬 마이크로시스템즈(Sun Microsystems)의 **제임스 고슬링(James Gosling)** 이 주도한 **그린 프로젝트(Green Project)** 에서 시작
- 가전 제품용 소프트웨어를 위해 개발 (초기 이름: **오크(Oak)**)
- **1995년** Java 공식 발표
- 인터넷과 웹의 발전과 함께 급격히 성장

### 3-2. 개발 목적: 플랫폼 독립성

**문제점 (기존 언어의 플랫폼 종속성)**

플랫폼 = 하드웨어 + 운영체제

플랫폼 호환성이 없었던 이유:
- CPU마다 기계어가 다름
- 운영체제마다 API가 다름
- 운영체제마다 실행 파일 형식이 다름

**해결 방향**
- 모든 플랫폼에서 호환되는 프로그래밍 언어 필요
- 메모리 사용량이 적고 다양한 플랫폼을 가지는 가전 제품에도 적용 가능하도록 설계

---

## 4. WORA & 바이트코드

### 4-1. WORA (Write Once, Run Anywhere)

> **"한번 작성된 코드는 OS·H/W에 상관없이 모든 플랫폼에서 실행된다"**

- C/C++ 등 기존 언어가 가진 **플랫폼 종속성 극복**
- 네트워크에 연결된 어느 클라이언트에서도 실행 가능
- 웹 브라우저, 분산 환경 지원

### 4-2. 바이트코드 (Bytecode)

- 자바 소스를 컴파일한 **목적 코드** (`.class` 파일)
- **CPU에 종속적이지 않은 중립적인 코드**
- JVM에 의해 해석되고 실행됨
- CPU가 직접 실행하지 않음 → JVM이 인터프리터 방식으로 해석

### 4-3. JVM (Java Virtual Machine)
```
[ Java Source (.java) ]
        ↓ javac (컴파일러)
[ Byte Code (.class) ]
        ↓ JVM (각 플랫폼별)
[ 실행 결과 ]
```

- **JVM 자체는 플랫폼에 종속적** (각 OS에 맞는 JVM이 따로 존재)
- 그러나 JVM이 설치된 곳이라면 **동일한 바이트코드(.class)가 그대로 실행**됨
- 오라클(Oracle) 외 IBM, MS 등 다양한 회사에서 제작·공급
- **실행 환경 = JVM + Java API (클래스 라이브러리)**

---

## 5. JDK 주요 개발 도구

| 도구 | 설명 |
|------|------|
| `javac` | 자바 소스(.java)를 바이트코드(.class)로 변환하는 **컴파일러** |
| `java` | 자바 응용프로그램 실행기 (JVM을 작동시켜 프로그램 실행) |
| `javadoc` | 자바 소스로부터 HTML 형식의 API 도큐먼트 생성 |
| `jar` | 자바 클래스들(패키지 포함)을 압축한 자바 아카이브 파일(.jar) 생성·관리 |
| `jmod` | 자바 모듈 파일(.jmod)을 만들거나 내용 출력 |
| `jlink` | 응용프로그램에 맞춘 맞춤형(Custom) JRE 제공 |
| `jdb` | 자바 응용프로그램 실행 중 오류를 찾는 **디버거** |
| `javap` | 클래스 파일의 바이트코드를 소스와 함께 보여주는 **디어셈블러** |

### javac 기본 사용법
```bash
# 기본
javac 파일명.java

# 다른 폴더에 있는 경우
javac ./src/파일명.java
```

---

## 6. Java 플랫폼 에디션

| 에디션 | 대상 | 주요 내용 |
|--------|------|-----------|
| **Java SE** (Standard Edition) | 데스크톱·서버·임베디드 | `java.lang`, `java.util`, `java.awt` 등 표준 패키지 포함 |
| **Java EE** (Enterprise Edition) | 서버 사이드 개발 | JSP, Servlet, JDBC, EJB 등 웹 프로그래밍 기능 포함 (SE 기반) |
| **Java ME** (Micro Edition) | 임베디드 (휴대폰, PDA, 셋톱박스) | SE 기반으로 소형 기기에 최적화 |

---

## 7. Java 버전 역사

### 버전 표기 변천

- 초기: `1.x` 방식 (예: Java 1.2)
- Java 1.2 이후: `J2SE` (Java 2 Standard Edition)
- Java 1.6 이후: `Java SE 6` 형태로 변경
- 내부 버전 표기는 `1.x.x` 형태로 유지

### LTS (Long-Term Support) 버전

| 버전 | 출시 연도 |
|------|-----------|
| Java 8 (LTS) | 2014 |
| Java 11 (LTS) | 2018 |
| Java 17 (LTS) | 2021 |
| Java 21 (LTS) | 2023 |

> LTS 버전은 장기간 지원이 보장되어 업계에서 안정적으로 앱을 개발하도록 하는 버전. Java 11부터는 3년 간격으로 출시.

---

## 8. Java 9 모듈화 (Modularity)

- **2017년 9월 21일** Java 9에서 도입된 새로운 기능
- **모듈(Module)**: 자바 패키지들과 이미지, XML 파일 등의 자원들을 묶은 단위
- **모듈 프로그래밍**: 레고처럼 필요한 모듈을 연결하는 방식으로 프로그램 작성
- Java 9부터 SE의 모든 클래스들을 모듈로 재구성
- JDK 설치 디렉터리 밑의 `jmods` 디렉터리에서 확인 가능

**모듈화의 장점**
- 실행 시 사용되는 Java API 클래스들을 모듈로 분할
- 필요 없는 모듈 배제 → 작은 크기의 실행 환경 구성
- **하드웨어가 열악한 소형 IoT 장치 지원 가능**

---

## 9. Java API와 패키지

### Java API

- JDK에 포함된 **클래스 라이브러리**
- 개발자는 API를 이용하여 쉽고 빠르게 자바 프로그램 개발 가능

### 패키지 (Package)

- 서로 관련된 클래스들을 계층 구조로 묶어 놓은 것 (폴더 개념)
- 클래스 이름에 패키지 이름도 포함
  - 예) `java.lang.System` → `java\lang` 디렉터리의 `System.class`
- 다른 패키지에 동일한 이름의 클래스 존재 가능
- 필요한 클래스가 속한 패키지만 `import`하여 사용
- 사용자 정의 패키지 생성 가능

---

## 10. Java의 주요 특징

### 플랫폼 독립성

- 하드웨어·운영체제에 종속되지 않는 바이트코드로 컴파일
- WORA 실현

### 객체지향 (OOP)

- **캡슐화(Encapsulation), 상속(Inheritance), 다형성(Polymorphism)** 지원
- 자바의 모든 변수와 함수는 **클래스 내에 선언**
- 내부 클래스(Inner Class) 작성 가능

### 소스와 클래스 파일

- 하나의 소스 파일에 여러 클래스 작성 가능 (단, `public` 클래스는 하나만)
- 소스 파일 이름 = `public`으로 선언된 클래스 이름 (반드시 동일해야 함)
- 클래스 파일 하나에는 클래스 하나만 존재
  - 여러 클래스를 가진 소스를 컴파일하면 클래스마다 별도의 `.class` 파일 생성

### 실행 코드 배포

- 한 개 또는 다수의 `.class` 파일로 구성
- 여러 폴더에 걸친 경우 → `jar` 압축 파일로 배포
- 응용프로그램 실행은 **`main()` 메소드에서 시작**
- 하나의 클래스 파일에 두 개 이상의 `main()` 메소드 불가

### 멀티스레드 (Multi-Thread)

- 여러 스레드의 동시 수행 환경 지원
- **운영체제 도움 없이 자체적으로 멀티스레드 지원**
- C/C++은 멀티스레드를 위해 운영체제 API를 호출해야 함

### 가비지 컬렉션 (Garbage Collection)

- 자바 언어에는 메모리 할당 기능은 있으나 메모리 반환 기능은 없음
- **사용하지 않는 메모리는 JVM이 자동으로 반환 (GC)**
- 개발자가 직접 메모리를 해제하지 않아도 됨 (C/C++과의 차이점)

---

## 11. 프로그래밍 언어 계보

| 언어 | 등장 연도 | 영향 받은 언어 |
|------|-----------|----------------|
| Assembly | - | - |
| Fortran | 1954 | Assembly |
| Algol | 1958 | Fortran |
| C | 1972 | Assembly |
| C++ | 1983 | C |
| **Java** | **1995** | **C++** |
| JavaScript | 1995 | C, Java |
| C# | 2000 | Java, C++ |
| Java 8 (LTS) | 2014 | - |
| Java 11 (LTS) | 2018 | - |
| Java 17 (LTS) | 2021 | - |
| Java 21 (LTS) | 2023 | - |

---

## 2주차 수업 (3월 11일)

## Git 주요 명령어 정리

### 초기 설정

#### git init
- 현재 폴더를 Git 저장소로 초기화함

#### git clone `<주소>`
- 원격 저장소를 복제하여 로컬에 다운로드함

---

### 상태 확인

#### git status
- 현재 파일 상태 확인 (추적 여부, 변경 여부 등)

#### git log
- 커밋 히스토리 확인

---

### 파일 추가 및 커밋

#### git add `<파일명>`
- 특정 파일을 스테이징 영역에 추가

#### git add .
- 현재 폴더의 모든 변경 파일을 추가

#### git commit -m "메시지"
- 스테이징된 파일들을 하나의 버전으로 저장

---

### 원격 저장소 작업

#### git remote add origin `<주소>`
- 원격 저장소 연결

#### git push origin `<브랜치명>`
- 로컬 커밋을 원격 저장소에 업로드

#### git pull origin `<브랜치명>`
- 원격 저장소 변경사항을 내려받고 병합

---

### 브랜치 관리

#### git branch
- 브랜치 목록 확인

#### git branch `<브랜치명>`
- 새로운 브랜치 생성

#### git checkout `<브랜치명>`
- 해당 브랜치로 이동

#### git checkout -b `<브랜치명>`
- 브랜치 생성 + 이동 동시에 수행

---

### 병합

#### git merge `<브랜치명>`
- 현재 브랜치에 다른 브랜치 내용을 합침

---

### 기타 유용한 명령어

#### git diff
- 변경된 내용 상세 비교

#### git reset --hard HEAD
- 마지막 커밋 상태로 완전히 되돌림 (주의)

#### git stash
- 현재 작업 임시 저장

#### git stash pop
- 임시 저장한 작업 다시 가져오기

---

## 마크다운 (Markdown)

> 추후 정리 예정

---

## Java 기본 코드 예제
```java
public class Main {
  public static void main(String[] args) {
    System.out.printf("Hello and welcome!");

    for (int i = 1; i <= 5; i++) {
      System.out.println("i = " + i);
    }
  }
}
```

---

## 1주차 수업

> 내용 없음